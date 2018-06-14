package com.phuongkhanh.youmetrips.services.api.exceptions;

public class CouldNotConnectApiServerException extends ApiException {
    @Override
    public String getMessage(final String language) {
        // TODO: handle language
        return "Could not connect to server, please check your network connection!";
    }
}
