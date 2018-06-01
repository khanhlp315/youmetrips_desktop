package com.phuongkhanh.youmetrips.presentation.components.home;

import com.phuongkhanh.youmetrips.presentation.framework.PresenterBase;

import javax.inject.Inject;

public class HomePresenter extends PresenterBase<HomeScreen> {
    private final HomeService _service;
    
    @Inject
    public HomePresenter(HomeService service)
    {
        _service = service;
    }

    void requestNavigateToPlans(){
        assert(getView() != null);

        getView().navigateToPlans();
    }

    void requestNavigateToPlaces(){
        assert(getView() != null);

        getView().navigateToPlaces();
    }

    void requestNavigateToProfile(){
        assert(getView() != null);

        getView().navigateToProfile();
    }

    void requestNavigateToFriendRequests() {
        assert(getView() != null);

        getView().navigateToFriendRequests();
    }

}
