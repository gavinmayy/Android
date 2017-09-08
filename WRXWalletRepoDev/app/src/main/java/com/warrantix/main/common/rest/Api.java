package com.warrantix.main.common.rest;

import com.warrantix.main.BuildConfig;

public class Api {
    public static final String SERVER;
    static {
        SERVER = BuildConfig.ENDPOINT;
    }
}
