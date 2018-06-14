package com.phuongkhanh.youmetrips.configuration;

import com.phuongkhanh.youmetrips.presentation.components.editprofile.EditProfilePresenter;
import com.phuongkhanh.youmetrips.presentation.components.editprofile.EditProfileScreen;
import com.phuongkhanh.youmetrips.presentation.components.editprofile.EditProfileScreenImpl;
import com.phuongkhanh.youmetrips.presentation.components.editprofile.EditProfileService;
import com.phuongkhanh.youmetrips.presentation.components.friendlist.FriendListPresenter;
import com.phuongkhanh.youmetrips.presentation.components.friendlist.FriendListScreen;
import com.phuongkhanh.youmetrips.presentation.components.friendlist.FriendListScreenImpl;
import com.phuongkhanh.youmetrips.presentation.components.friendlist.FriendListService;
import com.phuongkhanh.youmetrips.presentation.components.home.friend_requests.FriendRequestsPresenter;
import com.phuongkhanh.youmetrips.presentation.components.home.friend_requests.FriendRequestsScreen;
import com.phuongkhanh.youmetrips.presentation.components.home.friend_requests.FriendRequestsScreenImpl;
import com.phuongkhanh.youmetrips.presentation.components.home.friend_requests.FriendRequestsService;
import com.phuongkhanh.youmetrips.presentation.components.place_details.PlaceDetailsPresenter;
import com.phuongkhanh.youmetrips.presentation.components.place_details.PlaceDetailsScreen;
import com.phuongkhanh.youmetrips.presentation.components.place_details.PlaceDetailsScreenImpl;
import com.phuongkhanh.youmetrips.presentation.components.place_details.PlaceDetailsService;
import com.phuongkhanh.youmetrips.presentation.components.home.places.PlacePresenter;
import com.phuongkhanh.youmetrips.presentation.components.home.places.PlaceScreen;
import com.phuongkhanh.youmetrips.presentation.components.home.places.PlaceScreenImpl;
import com.phuongkhanh.youmetrips.presentation.components.home.places.PlaceService;
import com.phuongkhanh.youmetrips.presentation.components.home.plans.PlanPresenter;
import com.phuongkhanh.youmetrips.presentation.components.home.plans.PlanScreen;
import com.phuongkhanh.youmetrips.presentation.components.home.plans.PlanScreenImpl;
import com.phuongkhanh.youmetrips.presentation.components.home.plans.PlanService;
import com.phuongkhanh.youmetrips.presentation.components.home.profile.ProfilePresenter;
import com.phuongkhanh.youmetrips.presentation.components.home.profile.ProfileScreen;
import com.phuongkhanh.youmetrips.presentation.components.home.profile.ProfileScreenImpl;
import com.phuongkhanh.youmetrips.presentation.components.home.profile.ProfileService;
import com.phuongkhanh.youmetrips.presentation.components.login.LoginPresenter;
import com.phuongkhanh.youmetrips.presentation.components.login.LoginScreen;
import com.phuongkhanh.youmetrips.presentation.components.login.LoginScreenImpl;
import com.phuongkhanh.youmetrips.presentation.components.login.LoginService;
import com.phuongkhanh.youmetrips.presentation.components.needhelp.init_code.NewPasswordInitCodePresenter;
import com.phuongkhanh.youmetrips.presentation.components.needhelp.init_code.NewPasswordInitCodeScreen;
import com.phuongkhanh.youmetrips.presentation.components.needhelp.init_code.NewPasswordInitCodeScreenImpl;
import com.phuongkhanh.youmetrips.presentation.components.needhelp.init_code.NewPasswordInitCodeService;
import com.phuongkhanh.youmetrips.presentation.components.needhelp.init_email.NewPasswordInitEmailPresenter;
import com.phuongkhanh.youmetrips.presentation.components.needhelp.init_email.NewPasswordInitEmailScreen;
import com.phuongkhanh.youmetrips.presentation.components.needhelp.init_email.NewPasswordInitEmailScreenImpl;
import com.phuongkhanh.youmetrips.presentation.components.needhelp.init_email.NewPasswordInitEmailService;
import com.phuongkhanh.youmetrips.presentation.components.needhelp.init_new_password.NewPasswordPresenter;
import com.phuongkhanh.youmetrips.presentation.components.needhelp.init_new_password.NewPasswordScreen;
import com.phuongkhanh.youmetrips.presentation.components.needhelp.init_new_password.NewPasswordScreenImpl;
import com.phuongkhanh.youmetrips.presentation.components.needhelp.init_new_password.NewPasswordService;
import com.phuongkhanh.youmetrips.presentation.components.plandetails.PlanDetailsPresenter;
import com.phuongkhanh.youmetrips.presentation.components.plandetails.PlanDetailsScreen;
import com.phuongkhanh.youmetrips.presentation.components.plandetails.PlanDetailsScreenImpl;
import com.phuongkhanh.youmetrips.presentation.components.plandetails.PlanDetailsService;
import com.phuongkhanh.youmetrips.presentation.components.planlist.PlanListPresenter;
import com.phuongkhanh.youmetrips.presentation.components.planlist.PlanListScreen;
import com.phuongkhanh.youmetrips.presentation.components.planlist.PlanListScreenImpl;
import com.phuongkhanh.youmetrips.presentation.components.planlist.PlanListService;
import com.phuongkhanh.youmetrips.presentation.components.signup.confirmation_code.SignUpConfirmationCodePresenter;
import com.phuongkhanh.youmetrips.presentation.components.signup.confirmation_code.SignUpConfirmationCodeScreen;
import com.phuongkhanh.youmetrips.presentation.components.signup.confirmation_code.SignUpConfirmationCodeScreenImpl;
import com.phuongkhanh.youmetrips.presentation.components.signup.confirmation_code.SignUpConfirmationCodeService;
import com.phuongkhanh.youmetrips.presentation.components.signup.signup.SignUpPresenter;
import com.phuongkhanh.youmetrips.presentation.components.signup.signup.SignUpScreen;
import com.phuongkhanh.youmetrips.presentation.components.signup.signup.SignUpScreenImpl;
import com.phuongkhanh.youmetrips.presentation.components.signup.signup.SignUpService;
import com.phuongkhanh.youmetrips.presentation.components.trekingplan.trekking_plan_hotel.TrekkingPlanHotelService;
import com.phuongkhanh.youmetrips.presentation.components.trekingplan.trekking_plan_stay.TrekkingPlanStayService;
import com.phuongkhanh.youmetrips.presentation.components.trekingplan.trekking_plan_time.TrekkingPlanTimeService;
import com.phuongkhanh.youmetrips.presentation.components.trekkingplace.trekking_place_hashtags.TrekkingPlaceHashtagsService;
import com.phuongkhanh.youmetrips.presentation.components.trekkingplace.trekking_place_location.TrekkingPlaceLocationService;
import com.phuongkhanh.youmetrips.presentation.components.trekkingplace.trekking_place_name.TrekkingPlaceNameService;
import com.phuongkhanh.youmetrips.presentation.components.trekkingplace.trekking_place_photos.TrekkingPlacePhotosService;
import com.phuongkhanh.youmetrips.presentation.windows.CreatePlaceWindow;
import com.phuongkhanh.youmetrips.presentation.windows.CreatePlanWindow;
import com.phuongkhanh.youmetrips.presentation.windows.HomeWindow;
import com.phuongkhanh.youmetrips.presentation.components.trekingplan.trekking_plan_hotel.TrekkingPlanHotelPresenter;
import com.phuongkhanh.youmetrips.presentation.components.trekingplan.trekking_plan_hotel.TrekkingPlanHotelScreen;
import com.phuongkhanh.youmetrips.presentation.components.trekingplan.trekking_plan_hotel.TrekkingPlanHotelScreenImpl;
import com.phuongkhanh.youmetrips.presentation.components.trekingplan.trekking_plan_place.TrekkingPlanPlacePresenter;
import com.phuongkhanh.youmetrips.presentation.components.trekingplan.trekking_plan_place.TrekkingPlanPlaceScreen;
import com.phuongkhanh.youmetrips.presentation.components.trekingplan.trekking_plan_place.TrekkingPlanPlaceScreenImpl;
import com.phuongkhanh.youmetrips.presentation.components.trekingplan.trekking_plan_place.TrekkingPlanPlaceService;
import com.phuongkhanh.youmetrips.presentation.components.trekingplan.trekking_plan_preview.TrekkingPlanPreviewPresenter;
import com.phuongkhanh.youmetrips.presentation.components.trekingplan.trekking_plan_preview.TrekkingPlanPreviewScreen;
import com.phuongkhanh.youmetrips.presentation.components.trekingplan.trekking_plan_preview.TrekkingPlanPreviewScreenImpl;
import com.phuongkhanh.youmetrips.presentation.components.trekingplan.trekking_plan_preview.TrekkingPlanPreviewService;
import com.phuongkhanh.youmetrips.presentation.components.trekingplan.trekking_plan_stay.TrekkingPlanStayPresenter;
import com.phuongkhanh.youmetrips.presentation.components.trekingplan.trekking_plan_stay.TrekkingPlanStayScreen;
import com.phuongkhanh.youmetrips.presentation.components.trekingplan.trekking_plan_stay.TrekkingPlanStayScreenImpl;
import com.phuongkhanh.youmetrips.presentation.components.trekingplan.trekking_plan_time.TrekkingPlanTimePresenter;
import com.phuongkhanh.youmetrips.presentation.components.trekingplan.trekking_plan_time.TrekkingPlanTimeScreen;
import com.phuongkhanh.youmetrips.presentation.components.trekingplan.trekking_plan_time.TrekkingPlanTimeScreenImpl;
import com.phuongkhanh.youmetrips.presentation.components.trekkingplace.trekking_place_hashtags.TrekkingPlaceHashtagsPresenter;
import com.phuongkhanh.youmetrips.presentation.components.trekkingplace.trekking_place_hashtags.TrekkingPlaceHashtagsScreen;
import com.phuongkhanh.youmetrips.presentation.components.trekkingplace.trekking_place_hashtags.TrekkingPlaceHashtagsScreenImpl;
import com.phuongkhanh.youmetrips.presentation.components.trekkingplace.trekking_place_location.TrekkingPlaceLocationPresenter;
import com.phuongkhanh.youmetrips.presentation.components.trekkingplace.trekking_place_location.TrekkingPlaceLocationScreen;
import com.phuongkhanh.youmetrips.presentation.components.trekkingplace.trekking_place_location.TrekkingPlaceLocationScreenImpl;
import com.phuongkhanh.youmetrips.presentation.components.trekkingplace.trekking_place_name.TrekkingPlaceNamePresenter;
import com.phuongkhanh.youmetrips.presentation.components.trekkingplace.trekking_place_name.TrekkingPlaceNameScreen;
import com.phuongkhanh.youmetrips.presentation.components.trekkingplace.trekking_place_name.TrekkingPlaceNameScreenImpl;
import com.phuongkhanh.youmetrips.presentation.components.trekkingplace.trekking_place_photos.TrekkingPlacePhotosPresenter;
import com.phuongkhanh.youmetrips.presentation.components.trekkingplace.trekking_place_photos.TrekkingPlacePhotosScreen;
import com.phuongkhanh.youmetrips.presentation.components.trekkingplace.trekking_place_photos.TrekkingPlacePhotosScreenImpl;
import com.phuongkhanh.youmetrips.presentation.windows.LoginWindow;
import com.phuongkhanh.youmetrips.services.api.RestApi;
import com.phuongkhanh.youmetrips.services.components.AuthServiceImpl;
import com.phuongkhanh.youmetrips.services.components.HomeServiceImpl;
import com.phuongkhanh.youmetrips.services.stores.AuthenticationStore;
import com.phuongkhanh.youmetrips.services.stores.HomeStore;
import dagger.Component;
import dagger.Module;
import dagger.Provides;

import javax.inject.Provider;
import javax.inject.Singleton;

@Singleton
@Component(modules = {
        ApplicationContext.PresentationModule.class,
        ApplicationContext.ServiceModule.class
})

public interface ApplicationContext {
    LoginWindow loginWindow();
    HomeWindow homeWindow();
    CreatePlanWindow createPlanWindow();
    CreatePlaceWindow createPlaceWindow();

    @Module
    static class PresentationModule {
        @Provides
        static LoginScreen loginScreen(LoginPresenter presenter, Provider<HomeWindow> homeWindowProvider ) {
            return new LoginScreenImpl( presenter, homeWindowProvider  );
        }

        @Provides
        static LoginPresenter loginPresenter(LoginService loginService) {
            return new LoginPresenter(loginService);
        }

        @Provides
        static SignUpScreen signUpScreen(SignUpPresenter presenter) {
            return new SignUpScreenImpl(presenter);
        }

        @Provides
        static SignUpPresenter signUpPresenter(SignUpService signUpService) {
            return new SignUpPresenter(signUpService);
        }

        @Provides
        static SignUpConfirmationCodeScreen signUpReceiveCodeScreen(SignUpConfirmationCodePresenter presenter) {
            return new SignUpConfirmationCodeScreenImpl(presenter);
        }

        @Provides
        static SignUpConfirmationCodePresenter signUpReceiveCodePresenter(SignUpConfirmationCodeService service) {
            return new SignUpConfirmationCodePresenter(service);
        }

        @Provides
        static NewPasswordInitEmailScreen newPasswordInitEmailScreen(NewPasswordInitEmailPresenter presenter) {
            return new NewPasswordInitEmailScreenImpl(presenter);
        }

        @Provides
        static NewPasswordInitEmailPresenter newPasswordInitEmailPresenter(NewPasswordInitEmailService service) {
            return new NewPasswordInitEmailPresenter(service);
        }

        @Provides
        static NewPasswordInitCodeScreen newPasswordInitCodeScreen(NewPasswordInitCodePresenter presenter) {
            return new NewPasswordInitCodeScreenImpl(presenter);
        }

        @Provides
        static NewPasswordInitCodePresenter newPasswordInitCodePresenter(NewPasswordInitCodeService service) {
            return new NewPasswordInitCodePresenter(service);
        }

        @Provides
        static NewPasswordScreen newPasswordScreen(NewPasswordPresenter presenter) {
            return new NewPasswordScreenImpl(presenter);
        }

        @Provides
        static NewPasswordPresenter newPasswordPresenter(NewPasswordService service) {
            return new NewPasswordPresenter(service);
        }

        @Provides
        static EditProfileScreen editProfileScreen(EditProfilePresenter presenter) {
            return new EditProfileScreenImpl(presenter);
        }

        @Provides
        static EditProfilePresenter editProfilePresenter(EditProfileService service) {
            return new EditProfilePresenter(service);
        }

        @Provides
        static FriendListScreen friendListScreen(FriendListPresenter presenter) {
            return new FriendListScreenImpl(presenter);
        }

        @Provides
        static FriendListPresenter friendListPresenter(FriendListService service) {
            return new FriendListPresenter(service);
        }

        @Provides
        static FriendRequestsScreen friendRequestsScreen(FriendRequestsPresenter presenter) {
            return new FriendRequestsScreenImpl(presenter);
        }

        @Provides
        static FriendRequestsPresenter friendRequestsPresenter(FriendRequestsService service) {
            return new FriendRequestsPresenter(service);
        }

        @Provides
        static PlaceDetailsScreen placeDetailsScreen(PlaceDetailsPresenter presenter) {
            return new PlaceDetailsScreenImpl(presenter);
        }

        @Provides
        static PlaceDetailsPresenter placeDetailsPresenter(PlaceDetailsService service) {
            return new PlaceDetailsPresenter(service);
        }

        @Provides
        static PlaceScreen placeScreen(PlacePresenter presenter, Provider<CreatePlanWindow> planWindowProvider, Provider<CreatePlaceWindow> placeWindowProvider) {
            return new PlaceScreenImpl(presenter, planWindowProvider, placeWindowProvider);
        }

        @Provides
        static PlacePresenter placePresenter(PlaceService service) {
            return new PlacePresenter(service);
        }

        @Provides
        static PlanScreen planScreen(PlanPresenter presenter, Provider<CreatePlanWindow> planWindowProvider, Provider<CreatePlaceWindow> placeWindowProvider) {
            return new PlanScreenImpl(presenter, planWindowProvider, placeWindowProvider);
        }

        @Provides
        static PlanPresenter planPresenter(PlanService service) {
            return new PlanPresenter(service);
        }

        @Provides
        static ProfileScreen profileScreen(ProfilePresenter presenter, Provider<CreatePlanWindow> planWindowProvider, Provider<CreatePlaceWindow> placeWindowProvider) {
            return new ProfileScreenImpl(presenter, planWindowProvider, placeWindowProvider);
        }

        @Provides
        static ProfilePresenter profilePresenter(ProfileService service) {
            return new ProfilePresenter(service);
        }

        @Provides
        static PlanDetailsScreen planDetailsScreen(PlanDetailsPresenter presenter) {
            return new PlanDetailsScreenImpl(presenter);
        }

        @Provides
        static PlanDetailsPresenter planDetailsPresenter(PlanDetailsService service) {
            return new PlanDetailsPresenter(service);
        }

        @Provides
        static PlanListScreen planListScreen(PlanListPresenter presenter) {
            return new PlanListScreenImpl(presenter);
        }

        @Provides
        static PlanListPresenter planListPresenter(PlanListService service) {
            return new PlanListPresenter(service);
        }

        @Provides
        static TrekkingPlanHotelScreen trekkingPlanHotelScreen(TrekkingPlanHotelPresenter presenter) {
            return new TrekkingPlanHotelScreenImpl(presenter);
        }

        @Provides
        static TrekkingPlanHotelPresenter trekkingPlanHotelPresenter(TrekkingPlanHotelService service)
        {
            return new TrekkingPlanHotelPresenter(service);
        }

        @Provides
        static TrekkingPlanPlaceScreen trekkingPlanPlaceScreen(TrekkingPlanPlacePresenter presenter) {
            return new TrekkingPlanPlaceScreenImpl(presenter);
        }

        @Provides
        static TrekkingPlanPlacePresenter trekkingPlanPlacePresenter(TrekkingPlanPlaceService service) {
            return new TrekkingPlanPlacePresenter(service);
        }

        @Provides
        static TrekkingPlanPreviewScreen trekkingPlanPreviewScreen(TrekkingPlanPreviewPresenter presenter) {
            return new TrekkingPlanPreviewScreenImpl(presenter);
        }

        @Provides
        static TrekkingPlanPreviewPresenter trekkingPlanPreviewPresenter(TrekkingPlanPreviewService service) {
            return new TrekkingPlanPreviewPresenter(service);
        }

        @Provides
        static TrekkingPlanStayScreen trekkingPlanStayScreen(TrekkingPlanStayPresenter presenter) {
            return new TrekkingPlanStayScreenImpl(presenter);
        }

        @Provides
        static TrekkingPlanStayPresenter trekkingPlanStayPresenter(TrekkingPlanStayService service)
        {
            return new TrekkingPlanStayPresenter(service);
        }

        @Provides
        static TrekkingPlanTimeScreen trekkingPlanTimeScreen(TrekkingPlanTimePresenter presenter) {
            return new TrekkingPlanTimeScreenImpl(presenter);
        }

        @Provides
        static TrekkingPlanTimePresenter trekkingPlanTimePresenter(TrekkingPlanTimeService service)
        {
            return new TrekkingPlanTimePresenter(service);
        }

        @Provides
        static TrekkingPlaceHashtagsScreen trekkingPlaceHashtagsScreen(TrekkingPlaceHashtagsPresenter presenter) {
            return new TrekkingPlaceHashtagsScreenImpl(presenter);
        }

        @Provides
        static TrekkingPlaceHashtagsPresenter trekkingPlaceHashtagsPresenter(TrekkingPlaceHashtagsService service)
        {
            return new TrekkingPlaceHashtagsPresenter(service);
        }

        @Provides
        static TrekkingPlaceLocationScreen trekkingPlaceLocationScreen(TrekkingPlaceLocationPresenter presenter) {
            return new TrekkingPlaceLocationScreenImpl(presenter);
        }

        @Provides
        static TrekkingPlaceLocationPresenter trekkingPlaceLocationPresenter(TrekkingPlaceLocationService service)
        {
            return new TrekkingPlaceLocationPresenter(service);
        }

        @Provides
        static TrekkingPlaceNameScreen trekkingPlaceNameScreen(TrekkingPlaceNamePresenter presenter) {
            return new TrekkingPlaceNameScreenImpl(presenter);
        }

        @Provides
        static TrekkingPlaceNamePresenter trekkingPlaceNamePresenter(TrekkingPlaceNameService service)
        {
            return new TrekkingPlaceNamePresenter(service);
        }

        @Provides
        static TrekkingPlacePhotosScreen trekkingPlacePhotosScreen(TrekkingPlacePhotosPresenter presenter) {
            return new TrekkingPlacePhotosScreenImpl(presenter);
        }

        @Provides
        static TrekkingPlacePhotosPresenter trekkingPlacePhotosPresenter(TrekkingPlacePhotosService service)
        {
            return new TrekkingPlacePhotosPresenter(service);
        }
    }

    @Module
    static class ServiceModule {
        static RestApi _restApi = new RestApi();
        static AuthenticationStore _authenticationStore = new AuthenticationStore();
        static HomeStore _homeStore = new HomeStore();

        @Provides
        static LoginService loginService() {
            return new AuthServiceImpl(restApi(), authenticationStore());
        }

        @Provides
        static SignUpService signUpService() {
            return new AuthServiceImpl(restApi(), authenticationStore());
        }

        @Provides
        static SignUpConfirmationCodeService signUpReceiveCodeService() {
            return new AuthServiceImpl(restApi(), authenticationStore());
        }

        @Provides
        static NewPasswordInitEmailService newPasswordInitEmailService() {
            return new AuthServiceImpl(restApi(), authenticationStore());
        }

        @Provides
        static NewPasswordInitCodeService newPasswordInitCodeService() {
            return new AuthServiceImpl(restApi(), authenticationStore());
        }

        @Provides
        static NewPasswordService newPasswordService() {
            return new AuthServiceImpl(restApi(), authenticationStore());
        }

        @Provides
        static RestApi restApi() {
            return _restApi;
        }

        @Provides
        static AuthenticationStore authenticationStore() {
            return _authenticationStore;
        }

        @Provides
        static HomeStore homeStore() {
            return _homeStore;
        }

        @Provides
        static EditProfileService editProfileService() {
            return new HomeServiceImpl(restApi(), authenticationStore(), homeStore());
        }

        @Provides
        static FriendListService friendListService() {
            return new HomeServiceImpl(restApi(), authenticationStore(), homeStore());
        }

        @Provides
        static FriendRequestsService friendRequestsService() {
            return new HomeServiceImpl(restApi(), authenticationStore(), homeStore());
        }

        @Provides
        static PlaceDetailsService placeDetailsService() {
            return new HomeServiceImpl(restApi(), authenticationStore(), homeStore());
        }

        @Provides
        static PlaceService placeService() {
            return new HomeServiceImpl(restApi(), authenticationStore(), homeStore());
        }

        @Provides
        static PlanService planService() {
            return new HomeServiceImpl(restApi(), authenticationStore(), homeStore());
        }

        @Provides
        static ProfileService profileService() {
            return new HomeServiceImpl(restApi(), authenticationStore(), homeStore());
        }

        @Provides
        static PlanDetailsService planDetailsService() {
            return new HomeServiceImpl(restApi(), authenticationStore(), homeStore());
        }

        @Provides
        static PlanListService planListService() {
            return new HomeServiceImpl(restApi(), authenticationStore(), homeStore());
        }

        @Provides
        static TrekkingPlanPlaceService trekkingPlanPlaceService() {
            return new HomeServiceImpl(restApi(), authenticationStore(), homeStore());
        }

        @Provides
        static TrekkingPlanPreviewService trekkingPlanPreviewService() {
            return new HomeServiceImpl(restApi(), authenticationStore(), homeStore());
        }

        @Provides
        static TrekkingPlaceNameService trekkingPlaceNameService()
        {
            return new HomeServiceImpl(restApi(), authenticationStore(), homeStore());
        }

        @Provides
        static TrekkingPlaceLocationService trekkingPlaceLocationService()
        {
            return new HomeServiceImpl(restApi(), authenticationStore(), homeStore());
        }

        @Provides
        static TrekkingPlacePhotosService trekkingPlacePhotosService()
        {
            return new HomeServiceImpl(restApi(), authenticationStore(), homeStore());
        }

        @Provides
        static TrekkingPlaceHashtagsService trekkingPlaceHashtagsService()
        {
            return new HomeServiceImpl(restApi(), authenticationStore(), homeStore());
        }

        @Provides
        static TrekkingPlanTimeService trekkingPlanTimeService()
        {
            return new HomeServiceImpl(restApi(), authenticationStore(), homeStore());
        }

        @Provides
        static TrekkingPlanStayService trekkingPlanStayService()
        {
            return new HomeServiceImpl(restApi(), authenticationStore(), homeStore());
        }

        @Provides
        static TrekkingPlanHotelService trekkingPlanHotelService()
        {
            return new HomeServiceImpl(restApi(), authenticationStore(), homeStore());
        }
    }
}
