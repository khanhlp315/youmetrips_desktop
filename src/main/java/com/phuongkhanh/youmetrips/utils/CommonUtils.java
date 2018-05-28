package com.phuongkhanh.youmetrips.utils;

import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.RegexValidator;

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
}
