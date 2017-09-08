package com.warrantix.main.activities.brandlist;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.warrantix.main.R;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.activities.MainActivity;
import com.warrantix.main.customview.NonSwipeableViewPager;
import com.warrantix.main.fragments.about.WarrantixAboutFragment1;
import com.warrantix.main.fragments.about.WarrantixAboutFragment2;
import com.warrantix.main.fragments.about.WarrantixAboutFragment3;
import com.warrantix.main.fragments.about.WarrantixAboutFragment4;
import com.warrantix.main.fragments.about.WarrantixAboutFragment5;

import me.relex.circleindicator.CircleIndicator;


/**
 * Created by MyUserName on 2/26/2016.
 */
public class WalletBrandListSettingsAbout extends BaseActivity {

    private String toWhere = "";
    private ViewPager contentPager = null;

    private WarrantixAboutFragment1 fragment1 = new WarrantixAboutFragment1();
    private WarrantixAboutFragment2 fragment2 = new WarrantixAboutFragment2();
    private WarrantixAboutFragment3 fragment3 = new WarrantixAboutFragment3();
    private WarrantixAboutFragment4 fragment4 = new WarrantixAboutFragment4();
    private WarrantixAboutFragment5 fragment5 = new WarrantixAboutFragment5();
    private CircleIndicator indicator;
    private TextView txtNext;

    private final FragmentStatePagerAdapter contentPageAdapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {
        @Override
        public Fragment getItem(int position) {
            if (position == 0)
                return fragment1;
            else if (position == 1)
                return fragment2;
            else if (position == 2)
                return fragment3;
            else if (position == 3)
                return fragment4;
            else if (position == 4)
                return fragment5;

            return new Fragment();
        }

        @Override
        public int getCount() {
            return 5;
        }
    };

    int pageStep = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_list_settings_about);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (isInitialized == false) {
            Initialize();
            isInitialized = true;
        }
    }

    public void Initialize(){

        toWhere = getIntent().getStringExtra("Main");

        final ImageView btnBack = (ImageView) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toWhere.equalsIgnoreCase(""))
                    finish(true);
                else {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent, true, true);
                    finish();
                }
            }
        });

        contentPager = (ViewPager) findViewById(R.id.viewPager);
        contentPager.setAdapter(contentPageAdapter);
        contentPager.setOffscreenPageLimit(0);
        contentPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                pageStep = position;
                if (pageStep >= 4)
                    txtNext.setText("FINISH");
                else
                    txtNext.setText("NEXT");
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        contentPager.setCurrentItem(0);

        indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(contentPager);

        txtNext = (TextView) findViewById(R.id.txtNext);
        txtNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (txtNext.getText().toString().equalsIgnoreCase("FINISH"))
                    btnBack.callOnClick();

                pageStep++;
                if (pageStep > 4)
                    pageStep = 4;

                contentPager.setCurrentItem(pageStep);
                if (pageStep >= 4)
                    txtNext.setText("FINISH");
                else
                    txtNext.setText("NEXT");
            }
        });
    }
}
