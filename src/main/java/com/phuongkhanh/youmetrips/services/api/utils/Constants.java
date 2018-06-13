package com.phuongkhanh.youmetrips.services.api.utils;

import okhttp3.MediaType;

public final class Constants {
    public static final String    CONTENT_TYPE                = "Content-Type";
    public static final String    APPLICATION_JSON            = "application/json";
    public static final String    IMAGE                       = "image/*";
    public static final String    CHARSET_UTF8                = "charset=utf-8";
    public static final MediaType MEDIA_TYPE_APPLICATION_JSON = MediaType.parse( String.format( "%s; %s",
            APPLICATION_JSON,
            CHARSET_UTF8 ) );
    public static final MediaType MEDIA_TYPE_IMAGE            = MediaType.parse( String.format("%s", IMAGE));

    public static final String    APP_DATA                    = "APPDATA";
}

