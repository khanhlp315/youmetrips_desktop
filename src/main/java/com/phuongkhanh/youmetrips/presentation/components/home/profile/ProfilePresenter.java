package com.phuongkhanh.youmetrips.presentation.components.home.profile;

import com.phuongkhanh.youmetrips.presentation.framework.PresenterBase;
import com.phuongkhanh.youmetrips.services.api.models.Profile;
import com.phuongkhanh.youmetrips.services.stores.AuthenticationStore;
import com.phuongkhanh.youmetrips.services.stores.HomeStore;
import javafx.concurrent.Task;

import javax.inject.Inject;

public class ProfilePresenter extends PresenterBase<ProfileScreen> {
    private final ProfileService _service;

    @Inject
    public ProfilePresenter(ProfileService service)
    {
        _service = service;
    }

    void fetchProfile()
    {
        AuthenticationStore authenticationStore = _service.getAuthenticationStore();
        Profile profile = authenticationStore.getProfile();
        if(profile != null)
        {
            getView().updateProfile(profile);
        }

        new Thread(
                new Task<Object>() {
                    @Override
                    protected Object call() throws Exception {
                        _doFetchProfile();
                        return null;
                    }

                    @Override
                    protected void succeeded() {
                        _onFetchProfileSucceeded();
                        //getView().setLoading(false);
                    }

                    @Override
                    protected void failed() {
                        //_onFetchUserFailed(getException());
                        //getView().setLoading(false);
                    }
                }
        ).start();
    }

    void fetchFriends()
    {
        HomeStore homeStore = _service.getHomeStore();

    }

    private void _doFetchProfile()
    {

    }

    private void _onFetchProfileSucceeded()
    {

    }

    private void _onFetchProfileFailed()
    {

    }
}
