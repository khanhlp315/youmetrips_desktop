package com.phuongkhanh.youmetrips.services.api.exceptions;

public class UnknownApiResponseContentTypeException extends ApiException {
    @Override
    public String getMessage( final String language ) {
        // TODO: handle language
        return "Unknown Content-Type in API response!";
    }
}
