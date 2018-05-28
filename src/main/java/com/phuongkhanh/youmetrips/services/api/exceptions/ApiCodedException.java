package com.phuongkhanh.youmetrips.services.api.exceptions;

import com.phuongkhanh.youmetrips.services.api.models.ApiError;

public abstract class ApiCodedException extends ApiException {
    private final ApiError _error;

    protected ApiCodedException( final ApiError error ) {
        _error = error;
    }

    @Override
    public String getMessage( final String language ) {
        if ( _error.getUserMessageDict().isEmpty() ) return null;
        if ( _error.getUserMessageDict().containsKey( language ) ) return _error.getUserMessageDict().get( language );
        return _error.getUserMessageDict().values().iterator().next();
    }

    public ApiError getError() {
        return _error;
    }
}
