package com.phuongkhanh.youmetrips.services.components;

import com.phuongkhanh.youmetrips.presentation.components.editprofile.EditProfileService;
import com.phuongkhanh.youmetrips.presentation.components.friendlist.FriendListService;
import com.phuongkhanh.youmetrips.presentation.components.home.HomeService;
import com.phuongkhanh.youmetrips.presentation.components.home.friend_requests.FriendRequestsService;
import com.phuongkhanh.youmetrips.presentation.components.home.place_details.PlaceDetailsService;
import com.phuongkhanh.youmetrips.presentation.components.home.places.PlaceService;
import com.phuongkhanh.youmetrips.presentation.components.home.plans.PlanService;
import com.phuongkhanh.youmetrips.presentation.components.home.profile.ProfileService;
import com.phuongkhanh.youmetrips.presentation.components.plandetails.PlanDetailsService;
import com.phuongkhanh.youmetrips.presentation.components.planlist.PlanListService;
import com.phuongkhanh.youmetrips.presentation.components.trekingplan.TrekkingPlanService;
import com.phuongkhanh.youmetrips.presentation.components.trekingplan.trekking_plan_place.TrekkingPlanPlaceService;
import com.phuongkhanh.youmetrips.presentation.components.trekingplan.trekking_plan_preview.TrekkingPlanPreviewService;
import com.phuongkhanh.youmetrips.presentation.components.trekkingplace.TrekkingPlaceService;
import com.phuongkhanh.youmetrips.presentation.exceptions.ExpiredJwtException;
import com.phuongkhanh.youmetrips.presentation.exceptions.InvalidJwtException;
import com.phuongkhanh.youmetrips.services.api.RestApi;
import com.phuongkhanh.youmetrips.services.api.exceptions.ApiCodedException;
import com.phuongkhanh.youmetrips.services.api.exceptions.ExistedUserTrekkingPlanToPlaceException;
import com.phuongkhanh.youmetrips.services.api.models.*;
import com.phuongkhanh.youmetrips.services.stores.AuthenticationStore;
import com.phuongkhanh.youmetrips.services.stores.HomeStore;

import java.io.File;
import java.util.List;

public class HomeServiceImpl implements
        HomeService,
        PlanService,
        PlaceService,
        FriendRequestsService,
        TrekkingPlanService,
        TrekkingPlaceService,
        TrekkingPlanPlaceService,
        TrekkingPlanPreviewService,
        ProfileService,
        PlanListService,
        EditProfileService,
        PlaceDetailsService,
        PlanDetailsService,
        FriendListService
{
    private final RestApi _api;
    private final HomeStore _homeStore;
    private final AuthenticationStore _authenticationStore;

    public HomeServiceImpl(final RestApi api, final AuthenticationStore authenticationStore, final  HomeStore homeStore) {
        _api = api;
        _authenticationStore = authenticationStore;
        _homeStore = homeStore;
    }

    @Override
    public void updateUserProfile(EditedUserProfile profile, int userId, String jwt) {
        try {
            _api.updateProfile(profile, userId, jwt);
        }
        catch(ApiCodedException e){
            if(e.getError().getErrorCode().equals("com.youmetrips.server.core.exceptions.ExpiredJwtException")){
                throw new ExpiredJwtException();
            }
            if(e.getError().getErrorCode().equals("com.youmetrips.server.core.exceptions.InvalidJwtException")){
                throw new InvalidJwtException();
            }
            else{
                throw e;
            }
        }
    }

    @Override
    public List<Country> getAllCountries() {
        try {
            return _api.getAllCountries();
        }
        catch(ApiCodedException e){
            throw e;
        }
    }

    @Override
    public void acceptRequest(int fromUserId, int userId, String jwt) {
        try {
            _api.acceptFriendRequest(fromUserId, userId, jwt);
        }
        catch(ApiCodedException e){
            if(e.getError().getErrorCode().equals("com.youmetrips.server.core.exceptions.ExpiredJwtException")){
                throw new ExpiredJwtException();
            }
            if(e.getError().getErrorCode().equals("com.youmetrips.server.core.exceptions.InvalidJwtException")){
                throw new InvalidJwtException();
            }
            else{
                throw e;
            }
        }
    }

    @Override
    public void declineRequest(int fromUserId, int userId, String jwt) {
        try {
            _api.declineFriendRequest(fromUserId, userId, jwt);
        }
        catch(ApiCodedException e){
            if(e.getError().getErrorCode().equals("com.youmetrips.server.core.exceptions.ExpiredJwtException")){
                throw new ExpiredJwtException();
            }
            if(e.getError().getErrorCode().equals("com.youmetrips.server.core.exceptions.InvalidJwtException")){
                throw new InvalidJwtException();
            }
            else{
                throw e;
            }
        }
    }

    @Override
    public List<FriendRequest> fetchAllFriendRequests(int userId, String jwt) {
        return null;
    }

    @Override
    public void review(int rate, String message, int userId, int placeId, String jwt) {
        try {
            _api.review(placeId, rate, message, userId, jwt);
        }
        catch(ApiCodedException e){
            switch (e.getError().getErrorCode()) {
                case "com.youmetrips.server.core.exceptions.ExpiredJwtException":
                    throw new ExpiredJwtException();
                case "com.youmetrips.server.core.exceptions.InvalidJwtException":
                    throw new InvalidJwtException();
                default:
                    throw e;
            }
        }
    }

    @Override
    public void addTag(CreateTag tag, int userId, int placeId, String jwt) {
        try {
            _api.addTag(placeId, tag.getTag(), tag.getType(),  userId, jwt);
        }
        catch(ApiCodedException e){
            switch (e.getError().getErrorCode()) {
                case "com.youmetrips.server.core.exceptions.ExpiredJwtException":
                    throw new ExpiredJwtException();
                case "com.youmetrips.server.core.exceptions.InvalidJwtException":
                    throw new InvalidJwtException();
                default:
                    throw e;
            }
        }
    }

    @Override
    public List<Place> fetchPlaces(int userId, String jwt) {
        try{
            return _api.getAllPlaces(userId, jwt);
        }
        catch(ApiCodedException e){
            if(e.getError().getErrorCode().equals("com.youmetrips.server.core.exceptions.ExpiredJwtException")){
                throw new ExpiredJwtException();
            }
            if(e.getError().getErrorCode().equals("com.youmetrips.server.core.exceptions.InvalidJwtException")){
                throw new InvalidJwtException();
            }
            else{
                throw e;
            }
        }
    }

    @Override
    public void like(int userId, int placeId, String jwt) {
        try {
            _api.like(placeId, userId, jwt);
        }
        catch(ApiCodedException e){
            if(e.getError().getErrorCode().equals("com.youmetrips.server.core.exceptions.ExpiredJwtException")){
                throw new ExpiredJwtException();
            }
            if(e.getError().getErrorCode().equals("com.youmetrips.server.core.exceptions.InvalidJwtException")){
                throw new InvalidJwtException();
            }
            else{
                throw e;
            }
        }
    }

    @Override
    public void unlike(int userId, int placeId, String jwt) {
        try {
            _api.unlikePlace(placeId, userId, jwt);
        }
        catch(ApiCodedException e){
            if(e.getError().getErrorCode().equals("com.youmetrips.server.core.exceptions.ExpiredJwtException")){
                throw new ExpiredJwtException();
            }
            if(e.getError().getErrorCode().equals("com.youmetrips.server.core.exceptions.InvalidJwtException")){
                throw new InvalidJwtException();
            }
            else{
                throw e;
            }
        }
    }

    @Override
    public List<RelevantPlan> fetchPlans(int userId, String jwt) {
        try{
            return _api.getRelevantTrekkingPlans(userId, jwt);
        }
        catch(ApiCodedException e){
            if(e.getError().getErrorCode().equals("com.youmetrips.server.core.exceptions.ExpiredJwtException")){
                throw new ExpiredJwtException();
            }
            if(e.getError().getErrorCode().equals("com.youmetrips.server.core.exceptions.InvalidJwtException")){
                throw new InvalidJwtException();
            }
            else{
                throw e;
            }
        }
    }

    @Override
    public void sendFriendRequest(int toUserId, int userId, String jwt) {
        try{
            _api.sendFriendRequests(toUserId, userId, jwt);
        }
        catch(ApiCodedException e){
            if(e.getError().getErrorCode().equals("com.youmetrips.server.core.exceptions.ExpiredJwtException")){
                throw new ExpiredJwtException();
            }
            if(e.getError().getErrorCode().equals("com.youmetrips.server.core.exceptions.InvalidJwtException")){
                throw new InvalidJwtException();
            }
            else{
                throw e;
            }
        }
    }

    @Override
    public Profile getUserProfile(int userId, String jwt) {
        try{
            return _api.getProfile(userId, jwt);
        }
        catch(ApiCodedException e){
            if(e.getError().getErrorCode().equals("com.youmetrips.server.core.exceptions.ExpiredJwtException")){
                throw new ExpiredJwtException();
            }
            if(e.getError().getErrorCode().equals("com.youmetrips.server.core.exceptions.InvalidJwtException")){
                throw new InvalidJwtException();
            }
            else{
                throw e;
            }
        }
    }

    @Override
    public List<Friend> fetchAllFriends(int userId, String jwt) {
        try{
            return _api.getFriends(userId, jwt);
        }
        catch(ApiCodedException e){
            switch (e.getError().getErrorCode()) {
                case "com.youmetrips.server.core.exceptions.ExpiredJwtException":
                    throw new ExpiredJwtException();
                case "com.youmetrips.server.core.exceptions.InvalidJwtException":
                    throw new InvalidJwtException();
                default:
                    throw e;
            }
        }
    }

    @Override
    public int createTrekkingPlan(int userId, String jwt, CreatePlan trekkingPlan) {
        try {
            return _api.createTrekkingPlan(trekkingPlan, userId, jwt);
        }
        catch(ApiCodedException e){
            if(e.getError().getErrorCode().equals("com.youmetrips.server.core.exceptions.ExpiredJwtException")){
                throw new ExpiredJwtException();
            }
            else if(e.getError().getErrorCode().equals("com.youmetrips.server.core.exceptions.InvalidJwtException")){
                throw new InvalidJwtException();
            }
            else if(e.getError().getErrorCode().equals("com.youmetrips.server.core.exceptions.ExistedUserTrekkingPlanToPlaceException")){
                throw new ExistedUserTrekkingPlanToPlaceException();
            }
            else{
                throw e;
            }
        }
    }

    @Override
    public int createPlace(int userId, String jwt, CreatePlace place) {
        try {
            return _api.createPlace(place, userId, jwt);
        }
        catch(ApiCodedException e){
            if(e.getError().getErrorCode().equals("com.youmetrips.server.core.exceptions.ExpiredJwtException")){
                throw new ExpiredJwtException();
            }
            if(e.getError().getErrorCode().equals("com.youmetrips.server.core.exceptions.InvalidJwtException")){
                throw new InvalidJwtException();
            }
            else{
                throw e;
            }
        }
    }

    @Override
    public String uploadFile(int userId, String jwt, File file) {
        try {
            return _api.uploadFile(file, userId, jwt);
        }
        catch(ApiCodedException e){

            if(e.getError().getErrorCode().equals("com.youmetrips.server.core.exceptions.ExpiredJwtException")){
                throw new ExpiredJwtException();
            }
            if(e.getError().getErrorCode().equals("com.youmetrips.server.core.exceptions.InvalidJwtException")){
                throw new InvalidJwtException();
            }
            else{
                throw e;
            }
        }
    }

    @Override
    public AuthenticationStore getAuthenticationStore() {
        return null;
    }

    @Override
    public PlaceDetails getPlaceDetails(int userId, int placeId, String jwt) {
        try{
            return _api.getPlaceDetails(placeId, userId, jwt);
        }
        catch(ApiCodedException e){
            if(e.getError().getErrorCode().equals("com.youmetrips.server.core.exceptions.ExpiredJwtException")){
                throw new ExpiredJwtException();
            }
            if(e.getError().getErrorCode().equals("com.youmetrips.server.core.exceptions.InvalidJwtException")){
                throw new InvalidJwtException();
            }
            else{
                throw e;
            }
        }
    }

    @Override
    public HomeStore getHomeStore() {
        return null;
    }

    @Override
    public String getMapUrl(String location) {
        return null;
    }

    @Override
    public List<Comment> fetchComments(int userId, int trekkingPlanId, String jwt) {
        try {
            return _api.getComments(trekkingPlanId, userId, jwt);
        }
        catch(ApiCodedException e){
            switch (e.getError().getErrorCode()) {
                case "com.youmetrips.server.core.exceptions.ExpiredJwtException":
                    throw new ExpiredJwtException();
                case "com.youmetrips.server.core.exceptions.InvalidJwtException":
                    throw new InvalidJwtException();
                default:
                    throw e;
            }
        }
    }

    @Override
    public void postComment(String comment, int userId, int trekkingPlanId, String jwt) {
        try {
            _api.sendComment(comment, trekkingPlanId, userId, jwt);
        }
        catch(ApiCodedException e){
            if(e.getError().getErrorCode().equals("com.youmetrips.server.core.exceptions.ExpiredJwtException")){
                throw new ExpiredJwtException();
            }
            else if(e.getError().getErrorCode().equals("com.youmetrips.server.core.exceptions.InvalidJwtException")){
                throw new InvalidJwtException();
            }
            else{
                throw e;
            }
        }
    }
}
