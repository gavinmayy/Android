package com.warrantix.main.manager;


import android.util.Log;

import com.citrus.sdk.CitrusClient;
import com.citrus.sdk.Environment;
import com.citrus.sdk.response.CitrusError;
import com.warrantix.main.WarrantixApplication;

public class PaymentManager {
    private static final String TAG = PaymentManager.class.getSimpleName();

    private static final String CITRUS_ACCESS_KEY = "T6UTG8KTVTY6TCCZNACX";
    private static final String CITRUS_SIGNUP_ID = "2y0voyc9j0-signup";
    private static final String CITRUS_SIGNUP_SECRET = "34fb067e7a2fcd36ce589a5f11c6fd2c";
    private static final String CITRUS_SIGNIN_ID = "2y0voyc9j0-signin";
    private static final String CITRUS_SIGNIN_SECRET = "ae7a693a99a50061453152ea339af8e8";
    private static final String CITRUS_VANITY = "2y0voyc9j0";

    private static PaymentManager instance = null;

    private CitrusClient citrusClient;

    public static PaymentManager getInstance() {
        if (instance == null)
            instance = new PaymentManager();

        return instance;
    }

    public PaymentManager() {
        citrusClient = CitrusClient.getInstance(WarrantixApplication.getInstance().getApplicationContext());
        citrusClient.init(
                CITRUS_SIGNUP_ID,
                CITRUS_SIGNUP_SECRET,
                CITRUS_SIGNIN_ID,
                CITRUS_SIGNIN_SECRET,
                CITRUS_VANITY,
                Environment.SANDBOX );
        citrusClient.enableLog(true);
//        citrusClient.isUserSignedIn(new com.citrus.sdk.Callback<Boolean>() {
//            @Override
//            public void success(Boolean loggedIn) {
//                Log.v(TAG, "Citrus User Login Succeeded");
//            }
//
//            @Override
//            public void error(CitrusError error) {
//                Log.v(TAG, "Citrus User Login Failed");
//            }
//        });

    }
}