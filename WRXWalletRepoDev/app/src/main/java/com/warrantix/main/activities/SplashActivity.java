package com.warrantix.main.activities;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NumberRule;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.mobsandgeeks.saripaar.annotation.Required;
import com.mobsandgeeks.saripaar.annotation.TextRule;
import com.nineoldandroids.animation.Animator;
import com.warrantix.main.R;
import com.warrantix.main.WarrantixApplication;
import com.warrantix.main.activities.brandlist.WalletBrandListSettingsAbout;
import com.warrantix.main.common.pref.WarrantixPreference;
import com.warrantix.main.customview.MoveAnimation;
import com.warrantix.main.customview.ResizeAnimation;
import com.warrantix.main.customview.framevideoview.FrameVideoView;
import com.warrantix.main.customview.framevideoview.FrameVideoViewListener;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Splash Activity is the start-up activity that appears until a delay is expired
 * or the user taps the screen.  When the splash activity starts, various app
 * initialization operations are performed.
 */
public class SplashActivity extends BaseActivity implements Validator.ValidationListener {

    private final static String LOG_TAG = SplashActivity.class.getSimpleName();

    private FrameVideoView vidHolder;
    private ImageView splashBG;
    private ImageView logoImageView;
    private ImageView backToMain;
    private TextView txtTitle;

    private LinearLayout mainLayout;
    private LinearLayout signLayout;
    private RelativeLayout titleLayout;

    private TextView txtCaption1;
    private TextView txtCaption2;
    private Button btnSignup;
    private Button btnSignIn;

    private ImageView customerImageView;
    private TextView customerTextView;

    @Required(order = 1, message = "Please input the customer name")
    private EditText customerEditText;

    private ImageView passwordImageView;
    private TextView passwordTextView;

    @Required(order = 6, message = "Please input the customer password")
    @Password(order = 7)
    @TextRule(order = 8, minLength = 6, maxLength = 15, message = "Enter at least 6 characters in password.")
    private EditText passwordEditText;

    private ImageView emailImageView;
    private TextView emailTextView;

    @Required(order = 4, message = "Please input the customer email address")
    @Email(order = 5, message = "Please Check and Enter a valid Email Address")
    private EditText emailEditText;

    private ImageView phoneImageView;
    private TextView phoneTextView;

    @Required(order = 2, message = "Please input the customer mobile number")
    @NumberRule(order = 3, type = NumberRule.NumberType.LONG, message = "Enter Phone Number in Numeric")
    private EditText phoneEditText;

    private View dividerLine1;
    private View dividerLine2;
    private View dividerLine3;
    private View dividerLine4;

    private TextView txtErrorMessage;
    private Validator validator;

    Timer timer;
    TimerTask myTimerTask;

    private final int CAPTIONS_SIZE = 6;
    private final String captions[] = {
            "Organize & Upkeep products",
            "Organize & Manage Brand Apps",
            "Manage Post-Purchase tasks",
            "Buy Products, Accessories, AMC, Insurance",
            "Consult close friends before any purchase",
            "To Organize, Upkeep, Manage & Buy Propducts"
    };

    static int mCaptionIndex = 0;

    static final int STATUS_MAIN_SCREEN = 0;
    static final int STATUS_SIGNUP_SCREEN = 1;
    static final int STATUS_SIGNIN_SCREEN = 2;

    private int mCurrentStatus = STATUS_MAIN_SCREEN;

    private Animation slide_down =
            AnimationUtils.loadAnimation(WarrantixApplication.getInstance().getApplicationContext(), R.anim.animation_splash_fade_out);
    private Animation slide_up =
            AnimationUtils.loadAnimation(WarrantixApplication.getInstance().getApplicationContext(), R.anim.animation_splash_slide_in);

    private MediaPlayer player;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (isInitialized == false) {
            initialize();
//            showMainScreen(true);
            showLogoAnimation();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    private void initialize() {

        setContentView(R.layout.activity_splash);

        logoImageView = (ImageView) findViewById(R.id.logoImageView);
        txtCaption1 = (TextView) findViewById(R.id.txtCaption1);
        txtCaption2 = (TextView) findViewById(R.id.txtCaption2);
        btnSignup = (Button) findViewById(R.id.btnSignUp);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        splashBG = (ImageView) findViewById(R.id.splashBGView);
        backToMain = (ImageView) findViewById(R.id.backToMain);
        txtTitle = (TextView) findViewById(R.id.txtTitle);

        mainLayout = (LinearLayout) findViewById(R.id.mainLayout);
        signLayout = (LinearLayout) findViewById(R.id.signLayout);
        titleLayout = (RelativeLayout) findViewById(R.id.titleLayout);

        logoImageView.setVisibility(View.GONE);
        txtCaption1.setVisibility(View.GONE);
        txtCaption2.setVisibility(View.GONE);
        btnSignIn.setVisibility(View.GONE);
        btnSignup.setVisibility(View.GONE);
        splashBG.setVisibility(View.GONE);

        mainLayout.setVisibility(View.GONE);
        signLayout.setVisibility(View.GONE);
        titleLayout.setVisibility(View.GONE);

        passwordImageView = (ImageView) findViewById(R.id.passwordImageView);
        passwordTextView = (TextView) findViewById(R.id.passwordTextView);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        emailImageView = (ImageView) findViewById(R.id.emailImageView);
        emailTextView = (TextView) findViewById(R.id.emailTextView);
        emailEditText = (EditText) findViewById(R.id.emailEditText);
        customerImageView = (ImageView) findViewById(R.id.customerImageView);
        customerTextView = (TextView) findViewById(R.id.customerTextView);
        customerEditText = (EditText) findViewById(R.id.customerEditText);
        phoneImageView = (ImageView) findViewById(R.id.phoneImageview);
        phoneTextView = (TextView) findViewById(R.id.phoneTextView);
        phoneEditText = (EditText) findViewById(R.id.phoneEditText);

        dividerLine1 = (View) findViewById(R.id.dividerLine1);
        dividerLine2 = (View) findViewById(R.id.dividerLine2);
        dividerLine3 = (View) findViewById(R.id.dividerLine3);
        dividerLine4 = (View) findViewById(R.id.dividerLine4);

        passwordImageView.setVisibility(View.GONE);
        passwordTextView.setVisibility(View.GONE);
        passwordEditText.setVisibility(View.GONE);
        emailImageView.setVisibility(View.GONE);
        emailTextView.setVisibility(View.GONE);
        emailEditText.setVisibility(View.GONE);
        customerImageView.setVisibility(View.GONE);
        customerTextView.setVisibility(View.GONE);
        customerEditText.setVisibility(View.GONE);
        phoneImageView.setVisibility(View.GONE);
        phoneTextView.setVisibility(View.GONE);
        phoneEditText.setVisibility(View.GONE);

        txtErrorMessage = (TextView) findViewById(R.id.txtErrorMessage);

        dividerLine1.setVisibility(View.GONE);
        dividerLine2.setVisibility(View.GONE);
        dividerLine3.setVisibility(View.GONE);
        dividerLine4.setVisibility(View.GONE);

        validator = new Validator(this);
        validator.setValidationListener(this);

        btnSignup.setOnClickListener(btnSignUpClickListener);
        btnSignIn.setOnClickListener(btnSignInClickListener);

        InputFilter filter = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    if (!Character.isLetterOrDigit(source.charAt(i))) {
                        return "";
                    }
                }
                return null;
            }
        };
        passwordEditText.setFilters(new InputFilter[] { filter });
    }

    private void showLogoAnimation() {
        try
        {
            vidHolder = (FrameVideoView) findViewById(R.id.myvideo);

            Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.splashvideo);
            vidHolder.setVisibility(View.VISIBLE);
            vidHolder.setup(video, getResources().getColor(R.color.wx_primary_color));
            vidHolder.setFrameVideoViewListener(new FrameVideoViewListener() {
                @Override
                public void mediaPlayerPrepared(final MediaPlayer mediaPlayer) {
                    SplashActivity.this.player = mediaPlayer;

                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            SplashActivity.this.player.stop();
                            showMainScreen(true);
                        }
                    });
                }

                @Override
                public void mediaPlayerPrepareFailed(MediaPlayer mediaPlayer, String error) {
                    showMainScreen(true);
                }
            });
        }
        catch(Exception ex) {
            showMainScreen(true);
        }
    }

    private void showMainScreen(final boolean bInit) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                if ((isInitialized == true) && (bInit == true))
                    return;

                if (bInit == true)
                    isInitialized = true;

                mCurrentStatus = STATUS_MAIN_SCREEN;

                btnSignup.setEnabled(true);
                btnSignIn.setEnabled(true);

                mainLayout.setVisibility(View.VISIBLE);
                logoImageView.setVisibility(View.VISIBLE);
                if (bInit == true)
                    logoImageView.setBackgroundResource(R.drawable.animation_logo1_animatein);
                else
                    logoImageView.setBackgroundResource(R.drawable.animation_logo3_downscale);

                ((AnimationDrawable) logoImageView.getBackground()).start();

                if (bInit == true) {
                    Animation animSplashBG = createShowAnimation(splashBG, 0, R.anim.animation_splash_background_show);
                    splashBG.clearAnimation();
                    splashBG.startAnimation(animSplashBG);
                }

                Animation animCaption1 = createShowAnimation(txtCaption1, 100, R.anim.animation_splash_slide_in);
                txtCaption1.clearAnimation();
                txtCaption1.startAnimation(animCaption1);

                Animation animCaption2 = createShowAnimation(txtCaption2, 200, R.anim.animation_splash_slide_in);
                txtCaption2.clearAnimation();
                txtCaption2.startAnimation(animCaption2);

                if (bInit == true) {
                    Animation animSignUp = createShowAnimation(btnSignup, 350, R.anim.animation_splash_slide_in);
                    btnSignup.clearAnimation();
                    btnSignup.startAnimation(animSignUp);

                    Animation animSignIn = createShowAnimation(btnSignIn, 450, R.anim.animation_splash_slide_in);
                    btnSignIn.clearAnimation();
                    btnSignIn.startAnimation(animSignIn);
                }

                timer = new Timer();
                myTimerTask = new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mCaptionIndex ++;
                                if (mCaptionIndex >= CAPTIONS_SIZE)
                                    mCaptionIndex = 0;

                                if (mCurrentStatus == STATUS_MAIN_SCREEN) {
                                    Animation animCaption1 = createShowAnimation(txtCaption1, 0, R.anim.animation_splash_bounce);
                                    txtCaption1.clearAnimation();
                                    txtCaption1.startAnimation(animCaption1);

                                    txtCaption2.setText(captions[mCaptionIndex]);
                                    Animation animCaption2 = createShowAnimation(txtCaption2, 100, R.anim.animation_splash_slide_miniin);
                                    animCaption2.setDuration(200);
                                    txtCaption2.clearAnimation();
                                    txtCaption2.startAnimation(animCaption2);
                                }
                                else {
                                    if (txtCaption1.getVisibility() == View.VISIBLE) {
                                        Animation animCaption1 = createHideAnimation(txtCaption1, 0, R.anim.animation_splash_fade_out);
                                        txtCaption1.clearAnimation();
                                        txtCaption1.startAnimation(animCaption1);
                                    }

                                    if (txtCaption2.getVisibility() == View.VISIBLE) {
                                        Animation animCaption2 = createHideAnimation(txtCaption2, 0, R.anim.animation_splash_fade_out);
                                        txtCaption2.clearAnimation();
                                        txtCaption2.startAnimation(animCaption2);
                                    }
                                }
                            }
                        });
                    }
                };
                timer.schedule(myTimerTask, 3000, 3000);
            }
        });
    }

    private void showSignupScreen() {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                mCurrentStatus = STATUS_SIGNUP_SCREEN;

                myTimerTask.cancel();

                Animation animCaption1 = createHideAnimation(txtCaption1, 0, R.anim.animation_splash_fade_out);
                txtCaption1.clearAnimation();
                txtCaption1.startAnimation(animCaption1);

                Animation animCaption2 = createHideAnimation(txtCaption2, 0, R.anim.animation_splash_fade_out);
                txtCaption2.clearAnimation();
                txtCaption2.startAnimation(animCaption2);

                Animation animSignIn = createSigninDownAnimation(btnSignIn, 0);
                btnSignIn.clearAnimation();
                btnSignIn.startAnimation(animSignIn);

                AnimationSet downAnimSet = createSignUpDownAnimation(btnSignup, 0);
                btnSignup.startAnimation(downAnimSet);

                logoImageView.setBackgroundResource(R.drawable.animation_logo2_downscale);
                ((AnimationDrawable) logoImageView.getBackground()).start();

                txtTitle.setText("SIGN UP");
                Animation animTitle = createShowAnimation(txtTitle, 600, R.anim.animation_splash_fade_in);
                txtTitle.startAnimation(animTitle);

                Animation animBackToMain = createShowAnimation(backToMain, 600, R.anim.animation_splash_slide_minifromright);
                backToMain.startAnimation(animBackToMain);
                backToMain.setEnabled(true);
                backToMain.setOnClickListener(btnBackToMainClickListener);

                customerImageView.setVisibility(View.VISIBLE);
                customerTextView.setVisibility(View.VISIBLE);
                customerEditText.setVisibility(View.VISIBLE);
                dividerLine1.setVisibility(View.VISIBLE);

                emailImageView.setVisibility(View.VISIBLE);
                emailTextView.setVisibility(View.VISIBLE);
                emailEditText.setVisibility(View.VISIBLE);
                dividerLine2.setVisibility(View.VISIBLE);

                passwordImageView.setVisibility(View.VISIBLE);
                passwordTextView.setVisibility(View.VISIBLE);
                passwordEditText.setVisibility(View.VISIBLE);
                dividerLine3.setVisibility(View.VISIBLE);

                phoneImageView.setVisibility(View.VISIBLE);
                phoneTextView.setVisibility(View.VISIBLE);
                phoneEditText.setVisibility(View.VISIBLE);
                dividerLine4.setVisibility(View.VISIBLE);

                titleLayout.setVisibility(View.VISIBLE);
                signLayout.setVisibility(View.VISIBLE);

                Animation animCustomerImageView = createShowAnimation(customerImageView, 200 + 700, R.anim.animation_splash_slide_minifromright);
                customerImageView.clearAnimation();
                customerImageView.startAnimation(animCustomerImageView);

                Animation animCustomerTextView = createShowAnimation(customerTextView, 300 + 700, R.anim.animation_splash_slide_fromright);
                customerTextView.clearAnimation();
                customerTextView.startAnimation(animCustomerTextView);

                Animation animCustomEditText = createShowAnimation(customerEditText, 400 + 700, R.anim.animation_splash_slide_fromright);
                customerEditText.clearAnimation();
                customerEditText.startAnimation(animCustomEditText);

                Animation animDivider1 = createShowAnimation(dividerLine1, 500 + 700, R.anim.animation_splash_slide_fromright);
                dividerLine1.clearAnimation();
                dividerLine1.startAnimation(animDivider1);

                Animation animEmailImageView = createShowAnimation(emailImageView, 600 + 700, R.anim.animation_splash_show);
                emailImageView.clearAnimation();
                emailImageView.startAnimation(animEmailImageView);

                Animation animEmailTextView = createShowAnimation(emailTextView, 700 + 700, R.anim.animation_splash_show);
                emailTextView.clearAnimation();
                emailTextView.startAnimation(animEmailTextView);

                Animation animEmailEditText = createShowAnimation(emailEditText, 800 + 700, R.anim.animation_splash_slide_fromright);
                emailEditText.clearAnimation();
                emailEditText.startAnimation(animEmailEditText);

                Animation animDivider2 = createShowAnimation(dividerLine2, 900 + 700, R.anim.animation_splash_slide_fromright);
                dividerLine2.clearAnimation();
                dividerLine2.startAnimation(animDivider2);

                Animation animPasswordImageView = createShowAnimation(passwordImageView, 1000 + 700, R.anim.animation_splash_show);
                passwordImageView.clearAnimation();
                passwordImageView.startAnimation(animPasswordImageView);

                Animation animPasswordTextView = createShowAnimation(passwordTextView, 1100 + 700, R.anim.animation_splash_slide_fromright);
                passwordTextView.clearAnimation();
                passwordTextView.startAnimation(animPasswordTextView);

                Animation animPasswordEditText = createShowAnimation(passwordEditText, 1200 + 700, R.anim.animation_splash_slide_fromright);
                passwordEditText.clearAnimation();
                passwordEditText.startAnimation(animPasswordEditText);

                Animation animDivider3 = createShowAnimation(dividerLine3, 1300 + 700, R.anim.animation_splash_slide_fromright);
                dividerLine3.clearAnimation();
                dividerLine3.startAnimation(animDivider3);

                Animation animPhoneImageView = createShowAnimation(phoneImageView, 1400 + 700, R.anim.animation_splash_show);
                phoneImageView.clearAnimation();
                phoneImageView.startAnimation(animPhoneImageView);

                Animation animPhoneTextView = createShowAnimation(phoneTextView, 1500 + 700, R.anim.animation_splash_slide_fromright);
                phoneTextView.clearAnimation();
                phoneTextView.startAnimation(animPhoneTextView);

                Animation animPhoneEditText = createShowAnimation(phoneEditText, 1600 + 700, R.anim.animation_splash_slide_fromright);
                phoneEditText.clearAnimation();
                phoneEditText.startAnimation(animPhoneEditText);

                Animation animDivider4 = createShowAnimation(dividerLine4, 1700 + 700, R.anim.animation_splash_slide_fromright);
                dividerLine4.clearAnimation();
                dividerLine4.startAnimation(animDivider4);

                btnSignup.setEnabled(true);
            }
        });
    }

    private void showSigninScreen() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mCurrentStatus = STATUS_SIGNIN_SCREEN;

                myTimerTask.cancel();

                Animation animCaption1 = createHideAnimation(txtCaption1, 0, R.anim.animation_splash_fade_out);
                txtCaption1.clearAnimation();
                txtCaption1.startAnimation(animCaption1);

                Animation animCaption2 = createHideAnimation(txtCaption2, 0, R.anim.animation_splash_fade_out);
                txtCaption2.clearAnimation();
                txtCaption2.startAnimation(animCaption2);

                AnimationSet animSignin = createSigninScaleDownAnimation(btnSignIn, 0);
                btnSignIn.clearAnimation();
                btnSignIn.startAnimation(animSignin);
                btnSignIn.setText("Log In");

                Animation animSignup = createHideAnimation(btnSignup, 0, R.anim.animation_splash_fade_out);
                btnSignup.clearAnimation();
                btnSignup.startAnimation(animSignup);

                logoImageView.setBackgroundResource(R.drawable.animation_logo2_downscale);
                ((AnimationDrawable) logoImageView.getBackground()).start();

                txtTitle.setText("SIGN IN");
                Animation animTitle = createShowAnimation(txtTitle, 100, R.anim.animation_splash_fade_in);
                txtTitle.startAnimation(animTitle);

                Animation animBackToMain = createShowAnimation(backToMain, 100, R.anim.animation_splash_slide_minifromright);
                backToMain.clearAnimation();
                backToMain.startAnimation(animBackToMain);
                backToMain.setEnabled(true);
                backToMain.setOnClickListener(btnBackToMainClickListener);

                emailImageView.setVisibility(View.VISIBLE);
                emailTextView.setVisibility(View.VISIBLE);
                emailEditText.setVisibility(View.VISIBLE);
                passwordImageView.setVisibility(View.VISIBLE);
                passwordTextView.setVisibility(View.VISIBLE);
                passwordEditText.setVisibility(View.VISIBLE);
                customerImageView.setVisibility(View.INVISIBLE);
                customerTextView.setVisibility(View.INVISIBLE);
                customerEditText.setVisibility(View.INVISIBLE);
                phoneImageView.setVisibility(View.GONE);
                phoneTextView.setVisibility(View.GONE);
                phoneEditText.setVisibility(View.GONE);

                dividerLine1.setVisibility(View.INVISIBLE);
                dividerLine2.setVisibility(View.VISIBLE);
                dividerLine3.setVisibility(View.VISIBLE);
                dividerLine4.setVisibility(View.INVISIBLE);

                titleLayout.setVisibility(View.VISIBLE);
                signLayout.setVisibility(View.VISIBLE);

                Animation animEmailImageView = createShowAnimation(emailImageView, 200 + 500, R.anim.animation_splash_show);
                emailImageView.clearAnimation();
                emailImageView.startAnimation(animEmailImageView);

                Animation animEmailTextView = createShowAnimation(emailTextView, 300 + 500, R.anim.animation_splash_slide_fromright);
                emailTextView.clearAnimation();
                emailTextView.startAnimation(animEmailTextView);

                Animation animEmailEditText = createShowAnimation(emailEditText, 400 + 500, R.anim.animation_splash_slide_fromright);
                emailEditText.clearAnimation();
                emailEditText.startAnimation(animEmailEditText);

                Animation animDivider2 = createShowAnimation(dividerLine2, 500 + 500, R.anim.animation_splash_slide_fromright);
                dividerLine2.clearAnimation();
                dividerLine2.startAnimation(animDivider2);

                Animation animPasswordImageView = createShowAnimation(passwordImageView, 600 + 500, R.anim.animation_splash_show);
                passwordImageView.clearAnimation();
                passwordImageView.startAnimation(animPasswordImageView);

                Animation animPasswordTextView = createShowAnimation(passwordTextView, 700 + 500, R.anim.animation_splash_slide_fromright);
                passwordTextView.clearAnimation();
                passwordTextView.startAnimation(animPasswordTextView);

                Animation animPasswordEditText = createShowAnimation(passwordEditText, 800 + 500, R.anim.animation_splash_slide_fromright);
                passwordEditText.clearAnimation();
                passwordEditText.startAnimation(animPasswordEditText);

                Animation animDivider3 = createShowAnimation(dividerLine3, 900 + 500, R.anim.animation_splash_slide_fromright);
                dividerLine3.clearAnimation();
                dividerLine3.startAnimation(animDivider3);

                btnSignIn.setEnabled(true);
            }
        });
    }

    private void hideSignScreen() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                hideKeyboard(SplashActivity.this);

                Animation animBackToMain = createHideAnimation(backToMain, 0, R.anim.animation_splash_fade_out);
                backToMain.clearAnimation();
                backToMain.startAnimation(animBackToMain);

                Animation animTitle = createHideAnimation(txtTitle, 0, R.anim.animation_splash_fade_out);
                txtTitle.clearAnimation();
                txtTitle.startAnimation(animTitle);

                if (mCurrentStatus == STATUS_SIGNUP_SCREEN) {
                    AnimationSet upAnimSet = createSignUpUpAnimation(btnSignup, 900);
                    btnSignup.startAnimation(upAnimSet);

                    Animation animSignIn = createSigninUpAnimation(btnSignIn, 900);
                    btnSignIn.clearAnimation();
                    btnSignIn.startAnimation(animSignIn);
                }
                else {
                    Animation animSignUp = createShowAnimation(btnSignup, 700, R.anim.animation_splash_show);
                    btnSignup.startAnimation(animSignUp);

                    Animation animSignIn = createSigninScaleUpAnimation(btnSignIn, 700);
                    btnSignIn.clearAnimation();
                    btnSignIn.startAnimation(animSignIn);
                    btnSignIn.setText("Existing Customer? Log In");
                }

                if (mCurrentStatus == STATUS_SIGNUP_SCREEN) {
                    Animation animCustomerImageView = createHideAnimation(customerImageView, 100, R.anim.animation_splash_slide_toright);
                    customerImageView.clearAnimation();
                    customerImageView.startAnimation(animCustomerImageView);

                    Animation animCustomerTextView = createHideAnimation(customerTextView, 100, R.anim.animation_splash_slide_toright);
                    customerTextView.clearAnimation();
                    customerTextView.startAnimation(animCustomerTextView);

                    Animation animCustomerEditText = createHideAnimation(customerEditText, 100, R.anim.animation_splash_slide_toright);
                    customerEditText.clearAnimation();
                    customerEditText.startAnimation(animCustomerEditText);

                    Animation animDivider1 = createHideAnimation(dividerLine1, 100, R.anim.animation_splash_slide_toright);
                    dividerLine1.clearAnimation();
                    dividerLine1.startAnimation(animDivider1);
                }

                Animation animEmailImageView = createHideAnimation(emailImageView, 200, R.anim.animation_splash_slide_toright);
                emailImageView.clearAnimation();
                emailImageView.startAnimation(animEmailImageView);

                Animation animEmailTextView = createHideAnimation(emailTextView, 200, R.anim.animation_splash_slide_toright);
                emailTextView.clearAnimation();
                emailTextView.startAnimation(animEmailTextView);

                Animation animEditText = createHideAnimation(emailEditText, 200, R.anim.animation_splash_slide_toright);
                emailEditText.clearAnimation();
                emailEditText.startAnimation(animEditText);

                Animation animDivider2 = createHideAnimation(dividerLine2, 200, R.anim.animation_splash_slide_toright);
                dividerLine2.clearAnimation();
                dividerLine2.startAnimation(animDivider2);

                Animation animPasswordImageView = createHideAnimation(passwordImageView, 300, R.anim.animation_splash_slide_toright);
                passwordImageView.clearAnimation();
                passwordImageView.startAnimation(animPasswordImageView);

                Animation animPasswordTextView = createHideAnimation(passwordTextView, 300, R.anim.animation_splash_slide_toright);
                passwordTextView.clearAnimation();
                passwordTextView.startAnimation(animPasswordTextView);

                Animation animPasswordEditText = createHideAnimation(passwordEditText, 300, R.anim.animation_splash_slide_toright);
                passwordEditText.clearAnimation();
                passwordEditText.startAnimation(animPasswordEditText);

                Animation animDivider3 = createHideAnimation(dividerLine3, 300, R.anim.animation_splash_slide_toright);
                dividerLine3.clearAnimation();
                dividerLine3.startAnimation(animDivider3);

                if (mCurrentStatus == STATUS_SIGNUP_SCREEN) {
                    Animation animPhoneImageView = createHideAnimation(phoneImageView, 400, R.anim.animation_splash_slide_toright);
                    phoneImageView.clearAnimation();
                    phoneImageView.startAnimation(animPhoneImageView);

                    Animation animPhoneTextView = createHideAnimation(phoneTextView, 400, R.anim.animation_splash_slide_toright);
                    phoneTextView.clearAnimation();
                    phoneTextView.startAnimation(animPhoneTextView);

                    Animation animPhoneEditText = createHideAnimation(phoneEditText, 400, R.anim.animation_splash_slide_toright);
                    phoneEditText.clearAnimation();
                    phoneEditText.startAnimation(animPhoneEditText);

                    Animation animDivider4 = createHideAnimation(dividerLine4, 400, R.anim.animation_splash_slide_toright);
                    dividerLine4.clearAnimation();
                    dividerLine4.startAnimation(animDivider4);
                }

                txtErrorMessage.setVisibility(View.GONE);
                txtErrorMessage.setText("");

                // showMainScreen(false);
            }
        });
    }

    private void goMainScreen() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent, true);
        finish();
    }

    private void goAboutScreen() {
        Intent intent = new Intent(SplashActivity.this, WalletBrandListSettingsAbout.class);
        intent.putExtra("Main", "Main");
        startActivity(intent, true);
        finish();
    }

    private void signup() {
        goAboutScreen();
    }

    private void signin() {
        goMainScreen();
    }

    private final View.OnClickListener btnSignUpClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            btnSignup.setEnabled(false);

            if (mCurrentStatus == STATUS_MAIN_SCREEN)
                showSignupScreen();
            else if (mCurrentStatus == STATUS_SIGNUP_SCREEN)
                // validator.validate();
                goAboutScreen();
        }
    };

    private final View.OnClickListener btnSignInClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            btnSignIn.setEnabled(false);

            if (mCurrentStatus == STATUS_MAIN_SCREEN)
                showSigninScreen();
            else if (mCurrentStatus == STATUS_SIGNIN_SCREEN)
                validator.validate();
        }
    };

    private final View.OnClickListener btnBackToMainClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            backToMain.setEnabled(false);

            hideSignScreen();
        }
    };

    @Override
    public void onValidationSucceeded() {
        txtErrorMessage.setText("");
        txtErrorMessage.setVisibility(View.INVISIBLE);
        if (mCurrentStatus == STATUS_SIGNUP_SCREEN)
            signup();
        else if (mCurrentStatus == STATUS_SIGNIN_SCREEN)
            signin();
    }

    private void showErrorMessage(String errorMessage) {
        txtErrorMessage.setText(errorMessage);
        txtErrorMessage.setVisibility(View.VISIBLE);
    }

    @Override
    public void onValidationFailed(View failedView, Rule<?> failedRule) {
        final String failureMessage = failedRule.getFailureMessage();
        showErrorMessage(failureMessage);

        btnSignup.setEnabled(true);
        btnSignIn.setEnabled(true);
    }

    public static void hideKeyboard(Context ctx) {
        InputMethodManager inputManager = (InputMethodManager) ctx.getSystemService(Context.INPUT_METHOD_SERVICE);

        // check if no view has focus:
        View v = ((Activity) ctx).getCurrentFocus();
        if (v == null)
            return;

        inputManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    //
    // General View show animation
    //
    private Animation createShowAnimation(final View targetView, final int delay, int animID) {
        Animation anim = AnimationUtils.loadAnimation(WarrantixApplication.getInstance().getApplicationContext(), animID);
        Animation.AnimationListener listener = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                targetView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                targetView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };
        anim.setStartOffset(delay);
        anim.setAnimationListener(listener);
        return anim;
    }

    //
    // General View hide animation
    //
    private Animation createHideAnimation(final View targetView, final int delay, int animID) {
        Animation anim = AnimationUtils.loadAnimation(WarrantixApplication.getInstance().getApplicationContext(), animID);
        Animation.AnimationListener listener = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                targetView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                 targetView.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };
        anim.setStartOffset(delay);
        anim.setAnimationListener(listener);
        return anim;
    }

    //
    // Signup Button up / down animation
    //
    private AnimationSet createSignUpDownAnimation(final View targetView, final int delay) {
        Animation animBounce = createShowAnimation(targetView, 0, R.anim.animation_splash_bounce);
        animBounce.setStartOffset(0);
        Animation animResize = new ResizeAnimation(targetView, 80, 200);
        animResize.setStartOffset(100);

        float moveY = 50 * getResources().getDisplayMetrics().density;
        Animation animDown = new MoveAnimation(targetView, (int)moveY, 300);
        animDown.setStartOffset(300);

        AnimationSet downAnimSet = new AnimationSet(false);
        downAnimSet.addAnimation(animBounce);
        downAnimSet.addAnimation(animResize);
        downAnimSet.addAnimation(animDown);
        downAnimSet.setStartOffset(delay);
        downAnimSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                targetView.setEnabled(false);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                targetView.setEnabled(true);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        return downAnimSet;
    }

    private AnimationSet createSignUpUpAnimation(final View targetView, final int delay) {

        float moveY = -50 * getResources().getDisplayMetrics().density;
        Animation animDown = new MoveAnimation(targetView, (int)moveY, 300);
        animDown.setStartOffset(0);

        Animation animResize = new ResizeAnimation(targetView, 125, 200);
        animResize.setStartOffset(300);

        Animation animEnd = new ScaleAnimation(1.0f, 1.0f, 1.0f, 1.0f);
        animEnd.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                showMainScreen(false);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        animEnd.setStartOffset(500);

        AnimationSet downAnimSet = new AnimationSet(false);
        downAnimSet.addAnimation(animDown);
        downAnimSet.addAnimation(animResize);
        downAnimSet.addAnimation(animEnd);
        downAnimSet.setStartOffset(delay);
        downAnimSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                targetView.setEnabled(false);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                targetView.setEnabled(true);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        return downAnimSet;
    }

    //
    // Sign in button up animation
    //
    private AnimationSet createSigninUpAnimation(final View targetView, final int delay) {
        Animation animFadeIn = createShowAnimation(targetView, 0, R.anim.animation_splash_fade_in);
        animFadeIn.setStartOffset(0);

        float moveY = -50 * getResources().getDisplayMetrics().density;
        Animation animDown = new MoveAnimation(targetView, (int)moveY, 500);
        animDown.setStartOffset(0);

        AnimationSet downAnimSet = new AnimationSet(false);
        downAnimSet.addAnimation(animDown);
        downAnimSet.addAnimation(animFadeIn);
        downAnimSet.setStartOffset(delay);
        downAnimSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                targetView.setEnabled(false);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                targetView.setEnabled(true);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        return downAnimSet;
    }

    private AnimationSet createSigninDownAnimation(final View targetView, final int delay) {
        Animation animFadeout = createHideAnimation(targetView, 0, R.anim.animation_splash_fade_out);
        animFadeout.setStartOffset(0);

        float moveY = 50 * getResources().getDisplayMetrics().density;
        Animation animDown = new MoveAnimation(targetView, (int)moveY, 500);
        animDown.setStartOffset(0);

        AnimationSet downAnimSet = new AnimationSet(false);
        downAnimSet.addAnimation(animFadeout);
        downAnimSet.addAnimation(animDown);
        downAnimSet.setStartOffset(delay);
        downAnimSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                targetView.setEnabled(false);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                targetView.setEnabled(true);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        return downAnimSet;
    }

    private AnimationSet createSigninScaleDownAnimation(final View targetView, final int delay) {
        Animation animBounce = createShowAnimation(targetView, 0, R.anim.animation_splash_bounce);
        animBounce.setStartOffset(0);
        Animation animResize = new ResizeAnimation(targetView, 80, 200);
        animResize.setStartOffset(100);

        AnimationSet downAnimSet = new AnimationSet(false);
        downAnimSet.addAnimation(animBounce);
        downAnimSet.addAnimation(animResize);
        downAnimSet.setStartOffset(delay);
        downAnimSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                targetView.setEnabled(false);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                targetView.setEnabled(true);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        return downAnimSet;
    }

    private AnimationSet createSigninScaleUpAnimation(final View targetView, final int delay) {
        Animation animResize = new ResizeAnimation(targetView, 125, 200);
        animResize.setStartOffset(0);
        animResize.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                showMainScreen(false);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        AnimationSet downAnimSet = new AnimationSet(false);
        downAnimSet.addAnimation(animResize);
        downAnimSet.setStartOffset(delay);
        downAnimSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                targetView.setEnabled(false);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                targetView.setEnabled(true);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        return downAnimSet;
    }

}
