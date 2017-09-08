package com.warrantix.main.fragments.about;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.warrantix.main.R;
import com.warrantix.main.WarrantixApplication;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.activities.MainActivity;
import com.warrantix.main.activities.WalletBrandAMC0;
import com.warrantix.main.activities.WalletBrandFinance0;
import com.warrantix.main.activities.WalletBrandInsurance0;
import com.warrantix.main.fragments.BaseFragment;

import java.util.Random;

public class WarrantixAboutFragment4 extends BaseFragment {

    private ImageView tutorial4;

    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;
    private ImageView imageView4;
    private ImageView imageView5;
    private ImageView tutorialm1;
    private ImageView tutorialm2;
    private ImageView tutorialm3;
    private ImageView tutorialm4;
    private ImageView tutorialm5;

    private TextView txtView1;
    private TextView txtView2;
    private TextView txtView3;
    private TextView txtView4;
    private TextView txtView5;
    private TextView txtView6;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_about4, container, false);

        tutorial4 = (ImageView) v.findViewById(R.id.tutorial4);

        imageView1 = (ImageView) v.findViewById(R.id.imageView1);
        imageView2 = (ImageView) v.findViewById(R.id.imageView2);
        imageView3 = (ImageView) v.findViewById(R.id.imageView3);
        imageView4 = (ImageView) v.findViewById(R.id.imageView4);
        imageView5 = (ImageView) v.findViewById(R.id.imageView5);

        tutorialm1 = (ImageView) v.findViewById(R.id.tutorialm1);
        tutorialm2 = (ImageView) v.findViewById(R.id.tutorialm2);
        tutorialm3 = (ImageView) v.findViewById(R.id.tutorialm3);
        tutorialm4 = (ImageView) v.findViewById(R.id.tutorialm4);
        tutorialm5 = (ImageView) v.findViewById(R.id.tutorialm5);

        txtView1 = (TextView) v.findViewById(R.id.txtView1);
        txtView2 = (TextView) v.findViewById(R.id.txtView2);
        txtView3 = (TextView) v.findViewById(R.id.txtView3);
        txtView4 = (TextView) v.findViewById(R.id.txtView4);
        txtView5 = (TextView) v.findViewById(R.id.txtView5);
        txtView6 = (TextView) v.findViewById(R.id.txtView6);

        return v;
    }

    @Override
    public void setMenuVisibility(final boolean visible) {
        super.setMenuVisibility(visible);

        if (visible == true){
            if (tutorial4 != null) {
                tutorial4.setVisibility(View.INVISIBLE);
                imageView1.setVisibility(View.INVISIBLE);
                imageView2.setVisibility(View.INVISIBLE);
                imageView3.setVisibility(View.INVISIBLE);
                imageView4.setVisibility(View.INVISIBLE);
                imageView5.setVisibility(View.INVISIBLE);

                tutorialm1.setVisibility(View.INVISIBLE);
                tutorialm2.setVisibility(View.INVISIBLE);
                tutorialm3.setVisibility(View.INVISIBLE);
                tutorialm4.setVisibility(View.INVISIBLE);
                tutorialm5.setVisibility(View.INVISIBLE);

                txtView1.setVisibility(View.INVISIBLE);
                txtView2.setVisibility(View.INVISIBLE);
                txtView3.setVisibility(View.INVISIBLE);
                txtView4.setVisibility(View.INVISIBLE);
                txtView5.setVisibility(View.INVISIBLE);
                txtView6.setVisibility(View.INVISIBLE);

                animStart();
            }
        }
        else {
            if (tutorial4 != null) {
                tutorial4.clearAnimation();
                imageView1.clearAnimation();
                imageView2.clearAnimation();
                imageView3.clearAnimation();
                imageView4.clearAnimation();
                imageView5.clearAnimation();

                tutorialm1.clearAnimation();
                tutorialm2.clearAnimation();
                tutorialm3.clearAnimation();
                tutorialm4.clearAnimation();
                tutorialm5.clearAnimation();

                tutorial4.setVisibility(View.INVISIBLE);
                imageView1.setVisibility(View.INVISIBLE);
                imageView2.setVisibility(View.INVISIBLE);
                imageView3.setVisibility(View.INVISIBLE);
                imageView4.setVisibility(View.INVISIBLE);
                imageView5.setVisibility(View.INVISIBLE);

                tutorialm1.setVisibility(View.INVISIBLE);
                tutorialm2.setVisibility(View.INVISIBLE);
                tutorialm3.setVisibility(View.INVISIBLE);
                tutorialm4.setVisibility(View.INVISIBLE);
                tutorialm5.setVisibility(View.INVISIBLE);

                txtView1.setVisibility(View.INVISIBLE);
                txtView2.setVisibility(View.INVISIBLE);
                txtView3.setVisibility(View.INVISIBLE);
                txtView4.setVisibility(View.INVISIBLE);
                txtView5.setVisibility(View.INVISIBLE);
                txtView6.setVisibility(View.INVISIBLE);
            }
        }
    }

    public void animStart() {
        if (tutorial4 != null) {
            AnimationDrawable drawable = (AnimationDrawable) tutorial4.getBackground();
            tutorial4.setVisibility(View.VISIBLE);
            drawable.stop();
            drawable.start();

            // imageview 1
            AnimationSet animSet1 = createMoveAnimation(imageView1, 900);
            imageView1.startAnimation(animSet1);

            AnimationSet animSet2 = createMoveAnimation(imageView2, 900);
            imageView2.startAnimation(animSet2);

            AnimationSet animSet3 = createMoveAnimation(imageView3, 900);
            imageView3.startAnimation(animSet3);

            AnimationSet animSet4 = createMoveAnimation(imageView4, 900);
            imageView4.startAnimation(animSet4);

            AnimationSet animSet5 = createMoveAnimation(imageView5, 900);
            imageView5.startAnimation(animSet5);

            AnimationSet animTut1 = createMoveAnimation(tutorialm1, 800);
            tutorialm1.startAnimation(animTut1);

            AnimationSet animTut2 = createMoveAnimation(tutorialm2, 825);
            tutorialm2.startAnimation(animTut2);

            AnimationSet animTut3 = createMoveAnimation(tutorialm3, 850);
            tutorialm3.startAnimation(animTut3);

            AnimationSet animTut4 = createMoveAnimation(tutorialm4, 875);
            tutorialm4.startAnimation(animTut4);

            AnimationSet animTut5 = createMoveAnimation(tutorialm5, 900);
            tutorialm5.startAnimation(animTut5);

            Animation animTxt1 = createShowAnimation(txtView1, 700 - 200, R.anim.animation_splash_slide_in);
            animTxt1.setDuration(200);
            txtView1.startAnimation(animTxt1);

            Animation animTxt2 = createShowAnimation(txtView2, 750 - 200, R.anim.animation_splash_slide_in);
            animTxt2.setDuration(200);
            txtView2.startAnimation(animTxt2);

            Animation animTxt3 = createShowAnimation(txtView3, 800 - 200, R.anim.animation_splash_slide_in);
            animTxt3.setDuration(200);
            txtView3.startAnimation(animTxt3);

            Animation animTxt4 = createShowAnimation(txtView4, 850 - 200, R.anim.animation_splash_slide_in);
            animTxt4.setDuration(200);
            txtView4.startAnimation(animTxt4);

            Animation animTxt5 = createShowAnimation(txtView5, 900 - 200, R.anim.animation_splash_slide_in);
            animTxt5.setDuration(200);
            txtView5.startAnimation(animTxt5);

            Animation animTxt6 = createShowAnimation(txtView6, 950 - 200, R.anim.animation_splash_slide_in);
            animTxt6.setDuration(200);
            txtView6.startAnimation(animTxt6);
        }
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
        anim.setInterpolator(new AccelerateInterpolator());
        return anim;
    }

    private AnimatorSet createRandomXY(View targetView, Random random) {
        ObjectAnimator animX = ObjectAnimator.ofFloat(targetView, "translationX", (random.nextFloat() - 0.5f) * 30 + targetView.getTranslationX());
        ObjectAnimator animY = ObjectAnimator.ofFloat(targetView, "translationY", (random.nextFloat() - 0.5f) * 30 + targetView.getTranslationY());
        AnimatorSet animSetXY = new AnimatorSet();
        animSetXY.setDuration(random.nextInt(100)+1000);
        animSetXY.setInterpolator(new LinearInterpolator());
        animSetXY.playTogether(animX, animY);
        return animSetXY;
    }

    public Animation createFloatingRandomAnimations(View targetView) {
        Random random = new Random();

        int originalPos[] = new int[2];
        targetView.getLocationOnScreen(originalPos);
        originalPos[0] += targetView.getWidth()/2;
        originalPos[1] += targetView.getHeight()/2;

        float deltaX = (random.nextFloat() - 0.5f) * 30;
        float deltaY = (random.nextFloat() - 0.5f) * 30;
        TranslateAnimation anim1 = new TranslateAnimation(0, deltaX, 0, deltaY);
        anim1.setDuration(1000);
        anim1.setRepeatMode(Animation.REVERSE);
        anim1.setRepeatCount(Animation.INFINITE);
        return anim1;
    }


    public AnimatorSet createRandomFloatingAnimation(View targetView) {
        AnimatorSet set = new AnimatorSet();

        Random random = new Random();

        AnimatorSet anim1 = createRandomXY(targetView, random);
        AnimatorSet anim2 = createRandomXY(targetView, random);
        AnimatorSet anim3 = createRandomXY(targetView, random);
        AnimatorSet anim4 = createRandomXY(targetView, random);

        set.playSequentially(anim1, anim2, anim3, anim4);
        return set;
    }

    private AnimationSet createMoveAnimation(final View targetView, int delay) {
        int centerPos[] = new int[2];
        tutorial4.getLocationOnScreen(centerPos);
        centerPos[0] += tutorial4.getWidth()/2;
        centerPos[1] += tutorial4.getHeight()/2;

        int originalPos[] = new int[2];
        targetView.getLocationOnScreen(originalPos);
        originalPos[0] += targetView.getWidth()/2;
        originalPos[1] += targetView.getHeight()/2;

        AnimationSet set = new AnimationSet(true);
        float density = getResources().getDisplayMetrics().density;
        TranslateAnimation anim = new TranslateAnimation((centerPos[0] - originalPos[0])/density, 0,
                (centerPos[1] - originalPos[1])/density, 0);
        anim.setDuration(300);
        set.addAnimation(anim);

        Animation fadeIn = FadeIn(100);
        set.addAnimation(fadeIn);
        set.setStartOffset(delay);
        set.setFillAfter(true);
        set.setFillEnabled(true);
        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                targetView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                targetView.setVisibility(View.VISIBLE);
                Animation animation1 = createFloatingRandomAnimations(targetView);
                targetView.startAnimation(animation1);

//                final AnimatorSet animatorSet = createRandomFloatingAnimation(targetView);
//                animatorSet.addListener(new AnimatorListenerAdapter() {
//                    @Override
//                    public void onAnimationEnd(Animator animation) {
//                        super.onAnimationEnd(animation);
//                        animatorSet.start();
//                    }
//                });
//                animatorSet.start();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        set.setInterpolator(new AccelerateInterpolator());
        return set;
    }

    private Animation FadeIn(int t) {
        Animation fade;
        fade = new AlphaAnimation(0.0f, 1.0f);
        fade.setDuration(t);
        fade.setInterpolator(new AccelerateInterpolator());
        return fade;
    }

    private Animation FadeOut(int t) {
        Animation fade;
        fade = new AlphaAnimation(1.0f, 0.1f);
        fade.setDuration(t);
        fade.setInterpolator(new AccelerateInterpolator());
        return fade;
    }
}