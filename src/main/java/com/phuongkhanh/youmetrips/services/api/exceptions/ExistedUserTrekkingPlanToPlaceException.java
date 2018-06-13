package com.phuongkhanh.youmetrips.services.api.exceptions;

import com.phuongkhanh.youmetrips.presentation.exceptions.PresentationException;

public class ExistedUserTrekkingPlanToPlaceException extends PresentationException {
    public ExistedUserTrekkingPlanToPlaceException()
    {
        message("Exited User Trekking Plan Exception");
    }
}
