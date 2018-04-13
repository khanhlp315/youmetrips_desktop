package com.phuongkhanh.youmetrips.services.api.exceptions;

public class CouldNotParseApiResponseBodyException extends ApiException {
    @Override
    public String getMessage( final String language ) {
        return "Error while parsing API response body!";
    }
}
