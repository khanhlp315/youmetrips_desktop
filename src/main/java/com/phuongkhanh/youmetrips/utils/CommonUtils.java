package com.phuongkhanh.youmetrips.utils;

import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.RegexValidator;

import java.io.File;

import static com.phuongkhanh.youmetrips.services.api.utils.Constants.APP_DATA;

public class CommonUtils {
    public static boolean validateEmail( final String email ) {
        return EmailValidator.getInstance().isValid( email );
    }

    public static boolean validateUrl( final String url ) {
        return new RegexValidator( "(https?://.*)(:(\\d+))?\\/?(.*)" ).isValid( url );
    }

    public static boolean validatePhoneNumber( final String number ) {
        return new RegexValidator( "^\\+(?:[0-9] ?){6,14}[0-9]$" ).isValid( number );
    }

    public static String getLocalFilePath(String fileName){
        String localPath = System.getenv(APP_DATA) + "/youmetrips";
        File localDirectory = new File(localPath);

        if(!localDirectory.exists())
        {
            localDirectory.mkdir();
        }

        return System.getenv(APP_DATA) + "/youmetrips/" + fileName;
    }
}
