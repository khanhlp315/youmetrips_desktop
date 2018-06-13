package com.phuongkhanh.youmetrips.services.api.exceptions;

import com.phuongkhanh.youmetrips.services.api.models.ApiError;

public class ApiClientException extends ApiCodedException {
    public ApiClientException( final ApiError error ) {
        super( error );
    }
}
