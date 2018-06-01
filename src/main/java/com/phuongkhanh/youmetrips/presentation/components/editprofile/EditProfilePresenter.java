package com.phuongkhanh.youmetrips.presentation.components.editprofile;

import com.phuongkhanh.youmetrips.presentation.framework.PresenterBase;
import com.phuongkhanh.youmetrips.services.api.models.Country;
import com.phuongkhanh.youmetrips.services.api.models.EditedUserProfile;
import com.phuongkhanh.youmetrips.services.api.models.Profile;
import com.phuongkhanh.youmetrips.services.stores.AuthenticationStore;
import com.phuongkhanh.youmetrips.services.stores.HomeStore;
import com.phuongkhanh.youmetrips.utils.CommonUtils;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;

import javax.inject.Inject;
import java.io.File;
import java.util.List;

public class EditProfilePresenter extends PresenterBase<EditProfileScreen> {
    private final EditProfileService _service;

    @Inject
    public EditProfilePresenter(EditProfileService service) {
        _service = service;
    }

    //region fetch countries
    public void fetchCountries(){
        assert (getView() != null);

        HomeStore homeStore = _service.getHomeStore();
        List<Country> countries = homeStore.getAllCountries();

        if(countries != null){
            getView().updateCountries(countries);
            return;
        }

        Task<List<Country>> task = new Task<List<Country>>() {
            @Override
            protected List<Country> call() throws Exception {
                return doFetchCountries();
            }

            @Override
            protected void failed() {
                super.failed();
                onFetchCountriesFailed(getException());
            }
        };
        task.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, event ->{
            onFetchCountriesSucceeded(task.getValue());
        });

        new Thread(task).start();
    }

    private List<Country> doFetchCountries(){
        return _service.getAllCountries();
    }

    private void onFetchCountriesSucceeded(List<Country> countries){
        _service.getHomeStore().storeCountries(countries);
        getView().updateCountries(countries);
    }

    private void onFetchCountriesFailed(final Throwable ex){

    }
    //endregion

    //region fetch profile
    public void fetchProfile(){
        assert (getView() != null);

        AuthenticationStore authenticationStore = _service.getAuthenticationStore();
        Profile profile = authenticationStore.getProfile();
        if(profile != null){
            getView().updateProfile(profile);
            return;
        }

        Task<Profile> task = new Task<Profile>() {
            @Override
            protected Profile call() throws Exception {
                return doFetchProfile();
            }

            @Override
            protected void failed() {
                onFetchProfileFailed(getException());
            }
        };

        task.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED,event->{
            onFetchProfileSucceeded(task.getValue());
        } );

        new Thread(task).start();
    }

    private Profile doFetchProfile(){
        AuthenticationStore authenticationStore = _service.getAuthenticationStore();
        return _service.getUserProfile(authenticationStore.getUserId(), authenticationStore.getJwt());
    }

    private void onFetchProfileSucceeded(Profile profile){
        AuthenticationStore authenticationStore = _service.getAuthenticationStore();
        authenticationStore.storeProfile(profile);
        getView().updateProfile(profile);
    }

    private void onFetchProfileFailed(Throwable ex){
    }

    //endregion

    //region change avatar
    public void uploadAvatar (File image){
        assert (getView() != null);

        Task<String> task = new Task<String>() {
            @Override
            protected String call() throws Exception {
                return doUploadAvatar(image);
            }

            @Override
            protected void failed() {
                super.failed();
                onUploadAvatarFailed(getException());
            }
        };

        task.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, event ->{
            onUploadAvatarSucceeded(task.getValue());
        });

        new Thread(task).start();
    }

    private String doUploadAvatar(File image) {
        if(image == null){
            //TODO: throw exception
        }
        AuthenticationStore authenticationStore = _service.getAuthenticationStore();

        return _service.uploadFile(authenticationStore.getUserId(), authenticationStore.getJwt(), image);
    }

    private void onUploadAvatarSucceeded(String url){
        getView().updateAvatarUrl(url);
    }

    private void onUploadAvatarFailed(Throwable ex){

    }
    //endregion

    //region update profile
    public void updateProfile (EditedUserProfile profile){
        assert (getView() != null);

        getView().setLoading(true);

        Task<Object> task = new Task<Object>() {
            @Override
            protected Object call() throws Exception {
                doUpdateProfile(profile);
                return null;
            }

            @Override
            protected void succeeded() {
                onUpdateProfileSucceeded();
            }

            @Override
            protected void failed() {
                super.failed();
                onUpdateProfileFailed(getException());
            }
        };

        new Thread(task).start();
    }

    private void doUpdateProfile(EditedUserProfile profile){
        AuthenticationStore authenticationStore = _service.getAuthenticationStore();
        _service.updateUserProfile(profile, authenticationStore.getUserId(), authenticationStore.getJwt());
    }

    private void onUpdateProfileSucceeded(){
        getView().setLoading(false);
        getView().navigateBack();
    }

    private void onUpdateProfileFailed(Throwable ex){
        getView().setLoading(false);
    }

    //endregion

    public void onEditedProfileUpdated(EditedUserProfile profile){
        if(isProfileValid(profile)){
            getView().showNext();
        }
        else {
            getView().hideNext();
        }
    }

    private boolean isProfileValid(EditedUserProfile profile){
        if(profile.getFirstName().equals("") || profile.getLastName().equals("")){
            return false;
        }

        if(profile.getPhoneNumber() != null && !CommonUtils.validatePhoneNumber(profile.getPhoneNumber())){
            return false;
        }

        if(profile.getEmail() != null && !CommonUtils.validateEmail(profile.getEmail())){
            return false;
        }

        return true;

    }
}
