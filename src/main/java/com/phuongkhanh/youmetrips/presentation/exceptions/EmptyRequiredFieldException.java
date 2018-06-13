package com.phuongkhanh.youmetrips.presentation.exceptions;

import com.phuongkhanh.youmetrips.services.api.exceptions.ApiException;

public class EmptyRequiredFieldException extends ApiException {
    @Override
    public String getMessage( final String language ) {
        return "Please fill all the fields!";
    }
}
