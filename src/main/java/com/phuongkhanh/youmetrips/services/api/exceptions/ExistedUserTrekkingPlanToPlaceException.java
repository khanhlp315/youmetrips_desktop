package com.phuongkhanh.youmetrips.services.api.exceptions;

public class ExistedUserTrekkingPlanToPlaceException extends ApiException {
    @Override
    public String getMessage( final String language ) {
        return "You have already had a plan to go there, please update that plan instead of create new one! ";
    }
}
