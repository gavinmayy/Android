package com.warrantix.main;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.location.Location;
import android.net.Uri;
import android.support.v7.app.AlertDialog;

import com.crashlytics.android.Crashlytics;
import com.noveogroup.android.log.Log;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.activities.PDFViewActivity;
import com.warrantix.main.common.pref.WarrantixPreference;
import com.warrantix.main.dialog.MessageDialog;
import com.warrantix.main.loader.plugin.PluginManager;
import com.warrantix.main.loader.plugin.RepositoryManager;
import com.warrantix.main.modules.b2c.AlarmReceiver;
import com.warrantix.main.modules.b2c.b2cUtil;
import com.warrantix.main.modules.gcm.WarrantixRegIntentService;

import io.fabric.sdk.android.Fabric;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Locale;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class WarrantixApplication extends Application {
	public static final String PRIMARY_SCHEME = "app";

	public static final boolean DEBUG = true;
	public static final String LOG_APP_NAME = "WX:";

	private static WarrantixApplication instance = null;

	private PluginManager pluginManager = null;
	private RepositoryManager repositoryManager = null;
	private Context appContext = null;
	private Activity currentActivity = null;

	private AlarmManager manager;
	private PendingIntent pendingIntent;

	public static WarrantixApplication getInstance() {
		if (instance == null)
			instance = new WarrantixApplication();

		return instance;
	}

	public static Context getAppContext() {
		return instance.appContext;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		Fabric.with(this, new Crashlytics());

		initManagers();
		initFontLibrary();

		instance = this;
		appContext = getApplicationContext();

		// Retrieve a PendingIntent that will perform a broadcast
		Intent alarmIntent = new Intent(this, AlarmReceiver.class);
		pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, 0);
		startAlarm(2*3600* b2cUtil.second);

//		// start the regIntentService to get the RegID(GCM Token of My Device).
//		Intent regIntent = new Intent(this, WarrantixRegIntentService.class);
//		startService(regIntent);
	}


	private void initManagers() {
		pluginManager = new PluginManager();
		repositoryManager = new RepositoryManager(this);
	}

	public PluginManager pluginManager() {
		if (pluginManager == null) {
			pluginManager = new PluginManager();
		}
		return pluginManager;
	}

	public RepositoryManager repositoryManager() {
		if (repositoryManager == null) {
			repositoryManager = new RepositoryManager(this);
		}
		return repositoryManager;
	}

	private static void initFontLibrary() {
		CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
				.setDefaultFontPath("fonts/Montserrat-Regular.ttf")
				.setFontAttrId(R.attr.fontPath)
				.build());
	}

	public void openPDFFiles(Context context) {
		AssetManager assetManager = getAssets();
		InputStream in = null;
		OutputStream out = null;
		File file = new File(getFilesDir(), "temp.pdf");
		try {
			in = assetManager.open("temp.pdf");
			out = openFileOutput(file.getName(), Context.MODE_WORLD_READABLE);

			copyFile(in, out);
			in.close();
			in = null;
			out.flush();
			out.close();
			out = null;
		} catch (Exception e) {
			Log.e(e.getMessage());
		}
//
//		Intent intent = new Intent(Intent.ACTION_VIEW);
//		intent.setDataAndType(Uri.parse("file://" + getFilesDir() + "/temp.pdf"), "application/pdf");
//		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//		startActivity(intent);

		Intent intent = new Intent(context, PDFViewActivity.class);
		intent.putExtra("path", "file://" + getFilesDir() + "/temp.pdf");

		if (context instanceof BaseActivity)
			((BaseActivity)context).startActivity(intent, true);
		else
			context.startActivity(intent);
	}

	private void copyFile(InputStream in, OutputStream out) throws IOException {
		byte[] buffer = new byte[1024];
		int read;
		while ((read = in.read(buffer)) != -1) {
			out.write(buffer, 0, read);
		}
	}

	public void showDial(final Activity activity) {

		final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		builder.setMessage("Dial Now?")
				.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						Intent intent = new Intent(Intent.ACTION_DIAL);
						if (activity instanceof BaseActivity)
							((BaseActivity) activity).startActivity(intent, true);
						else
							activity.startActivity(intent);
					}
				})
				.setNegativeButton("No", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
		builder.show();
	}

	public void setCurrentActivity(Activity activity) {
		currentActivity = activity;
	}

    public void showMessage(final String title, final String message) {
		if (currentActivity == null)
			return;

		currentActivity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				MessageDialog dialog = new MessageDialog(currentActivity);
				dialog.setTitle(title);
				dialog.setMessage(message);
				dialog.show();
			}
		});
    }

//	public void showMyLocation(Activity context, Location location) {
//		String uri = "";
//		if (location != null)
//			uri = String.format(Locale.ENGLISH, "geo:%f,%f", location.getLatitude(), location.getLongitude());
//
//		Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
//		mapIntent.setPackage("com.google.android.apps.maps");
//		if (mapIntent.resolveActivity(getPackageManager()) != null) {
//			context.startActivity(mapIntent);
//		}
//	}

	public void startAlarm(int interval) {

		manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
		manager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
//		Toast.makeText(this, "Alarm Set", Toast.LENGTH_SHORT).show();
	}

	public void endAlarm(){

		manager.cancel(pendingIntent);
	}


}
