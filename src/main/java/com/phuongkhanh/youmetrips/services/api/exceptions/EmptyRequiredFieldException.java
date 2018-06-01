package com.phuongkhanh.youmetrips.services.api.exceptions;

public class EmptyRequiredFieldException extends ApiException {
    @Override
    public String getMessage( final String language ) {
        return "Please fill all the fields!";
    }
}
