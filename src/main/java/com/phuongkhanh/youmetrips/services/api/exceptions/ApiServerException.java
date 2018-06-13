package com.phuongkhanh.youmetrips.services.api.exceptions;

import com.phuongkhanh.youmetrips.services.api.models.ApiError;

public class ApiServerException extends ApiCodedException {
    public ApiServerException(final ApiError error) {
        super(error);
    }
}