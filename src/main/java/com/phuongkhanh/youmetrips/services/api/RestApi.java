package com.phuongkhanh.youmetrips.services.api;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.phuongkhanh.youmetrips.services.api.exceptions.*;
import com.phuongkhanh.youmetrips.services.api.models.*;
import com.phuongkhanh.youmetrips.services.stores.AuthenticationStore;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import javax.inject.Inject;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.phuongkhanh.youmetrips.services.api.utils.Constants.APPLICATION_JSON;
import static com.phuongkhanh.youmetrips.services.api.utils.Constants.CONTENT_TYPE;
import static com.phuongkhanh.youmetrips.services.api.utils.Constants.MEDIA_TYPE_APPLICATION_JSON;
import static java.util.Objects.requireNonNull;

public class RestApi {
    private final ThreadLocal<OkHttpClient> _client;
    private final ThreadLocal<Gson> _gson;
    private String _baseUrl = "http://docker.youthdev.net:23010";
    private NewUser _currentUser;
    private String _userIdToResetPassword;
    private String _userTokenToResetPassword;
    private AuthenticationStore _store;

    public RestApi() {
        _client = new ThreadLocal<OkHttpClient>() {
            @Override
            protected OkHttpClient initialValue() {
                return new OkHttpClient.Builder()
                        .connectTimeout(10, TimeUnit.SECONDS)
                        .readTimeout(10, TimeUnit.SECONDS)
                        .writeTimeout(10, TimeUnit.SECONDS)
                        .build();
            }
        };

        _gson = new ThreadLocal<Gson>() {
            @Override
            protected Gson initialValue() {
                return new Gson();
            }
        };
        _store = new AuthenticationStore();
    }

    public String getBaseUrl() {
        return _baseUrl;
    }

    private String getUrl(final String path) {
        return String.format("%s/%s", _baseUrl, path);
    }

    private Response executePost(final String path, final Map<String, String> jsonMap) {
        Request request = new Request.Builder()
                .url(getUrl(path))
                .post(RequestBody.create(MEDIA_TYPE_APPLICATION_JSON,
                        jsonMap != null ? _gson.get().toJson(jsonMap) : "{}"))
                .build();

        try {
            return _client.get().newCall(request).execute();
        } catch (IOException e) {
            throw new CouldNotConnectApiServerException();
        }
    }

    private Response executePost(final String path, final Map<String, String> jsonMap, String authorization) {
        Request request = new Request.Builder()
                .url(getUrl(path))
                .addHeader("Authorization", "Bearer " + authorization)
                .post(RequestBody.create(MEDIA_TYPE_APPLICATION_JSON,
                        jsonMap != null ? _gson.get().toJson(jsonMap) : "{}"))
                .build();

        try {
            return _client.get().newCall(request).execute();
        } catch (IOException e) {
            throw new CouldNotConnectApiServerException();
        }
    }

    private Response executePut(final String path, final Map<String, String> jsonMap) {
        Request request = new Request.Builder()
                .url(getUrl(path))
                .put(RequestBody.create(MEDIA_TYPE_APPLICATION_JSON,
                        jsonMap != null ? _gson.get().toJson(jsonMap) : "{}"))
                .build();

        try {
            return _client.get().newCall(request).execute();
        } catch (IOException e) {
            throw new CouldNotConnectApiServerException();
        }
    }

    private Response executePut(final String path, final Map<String, String> jsonMap, String authorization) {
        Request request = new Request.Builder()
                .url(getUrl(path))
                .addHeader("Authorization", "Bearer " + authorization)
                .put(RequestBody.create(MEDIA_TYPE_APPLICATION_JSON,
                        jsonMap != null ? _gson.get().toJson(jsonMap) : "{}"))
                .build();

        try {
            return _client.get().newCall(request).execute();
        } catch (IOException e) {
            throw new CouldNotConnectApiServerException();
        }
    }

    private Response executeGet(final String path) {
        Request request = new Request.Builder()
                .url(getUrl(path))
                .build();

        try {
            return _client.get().newCall(request).execute();
        } catch (IOException e) {
            throw new CouldNotConnectApiServerException();
        }
    }

    private Response executeGet(final String path, final String authorization) {
        Request request = new Request.Builder()
                .url(getUrl(path))
                .addHeader("Authorization", "Bearer " + authorization)
                .build();

        try {
            return _client.get().newCall(request).execute();
        } catch (IOException e) {
            throw new CouldNotConnectApiServerException();
        }
    }

    private Response executeDelete(final String path, String authorization) {
        Request request = new Request.Builder()
                .url(getUrl(path))
                .addHeader("Authorization", "Bearer " + authorization)
                .delete()
                .build();

        try {
            return _client.get().newCall(request).execute();
        } catch (IOException e) {
            throw new CouldNotConnectApiServerException();
        }
    }

    private <T> T parseResponseJsonBody(final Response response, final Class<T> clazz) {
        if (!APPLICATION_JSON.equals(response.header(CONTENT_TYPE))) {
            throw new UnknownApiResponseContentTypeException();
        }

        try {
            String json = requireNonNull(response.body()).string();
            return requireNonNull(_gson.get().fromJson(json, clazz));
        } catch (Throwable e) {
            throw new CouldNotParseApiResponseBodyException();
        }
    }

    public Login login(String email, String password) {
        Response response = executePost("login", ImmutableMap.of(
                "emailOrPhoneNumber", email,
                "password", password));
        validateResponse(response);
        return parseResponseJsonBody(response, Login.class);
    }

    public void signUp(String emailOrPhone,
                       String password,
                       String firstName,
                       String lastName) {
        Response response = executePost("signup", ImmutableMap.of(
                "emailOrPhoneNumber", emailOrPhone,
                "password", password,
                "firstName", firstName,
                "lastName", lastName));
        validateResponse(response);

        _currentUser = parseResponseJsonBody(response, NewUser.class);

        _store.storeSignupData(_currentUser.getUserId(), _currentUser.getConfirmToken(), _currentUser.getResendConfirmationCodeToken());
    }


    public Login loginWithFB(String accessToken) {
        Response response = executePost("loginwithfacebook", ImmutableMap.of(
                "facebookAccessToken", accessToken));
        validateResponse(response);
        return parseResponseJsonBody(response, Login.class);
    }

    private void validateResponse(final Response response) {
        if (response.isSuccessful()) {
            return;
        }

        if (!APPLICATION_JSON.equals(response.header(CONTENT_TYPE))) {
            throw new UnknownApiResponseContentTypeException();
        }

        ApiError error = new ApiError();

        try {
            String json = requireNonNull(response.body().string());
            Map<String, Object> jsonMap = _gson.get().fromJson(json, new TypeToken<Map<String, Object>>() {
            }.getType());

            error.setErrorCode((String) jsonMap.get("errorCode"));
            error.setUserMessageDict((Map<String, String>) jsonMap.get("userMessageDict"));
            error.setMoreInformationDict((Map<String, String>) jsonMap.get("moreInformationDict"));
        } catch (Throwable e) {
            throw new CouldNotParseApiResponseBodyException();
        }

        if (response.code() >= 400 && response.code() < 500)
            throw new ApiClientException(error);

        throw new ApiServerException(error);
    }

    public void sendConfirmationCode(String confirmationCode) {
        Response response = executePost("users/" + _store.getUserId() + "/confirmnewuser", ImmutableMap.of(
                "confirmationCode", confirmationCode), _store.getConfirmToken());
        validateResponse(response);
        parseResponseJsonBody(response, Login.class);
    }

    public void resendConfirmationCOde() {
        Response response = executePost("users/" + _store.getUserId() + "/resendnewuserconfirmationcode", null, _store.getResendConfirmationCodeToken());
        validateResponse(response);
        parseResponseJsonBody(response, Login.class);
    }

    public void sendEmailToResetPassword(String email) {
        Response response = executePost("requestrecoverycode", ImmutableMap.of(
                "emailOrPhoneNumber", email));
        validateResponse(response);
        _userIdToResetPassword = String.valueOf(parseResponseJsonBody(response, UserResetPassword.class).getUserId());
        _store.storeUserId(Integer.valueOf(_userIdToResetPassword));
    }

    public void sendCodeToResetPassword(String recoveryCode) {
        Response response = executePost("users/" + _store.getUserId() + "/requestresetpasswordtoken", ImmutableMap.of(
                "recoveryCode", recoveryCode));
        validateResponse(response);
        _userTokenToResetPassword = parseResponseJsonBody(response, UserResetPassword.class).getUserToken();
        _store.storeResetPasswordToken(_userTokenToResetPassword);
    }

    public void sendPasswordToResetPassword(String newPassword) {
        Response response = executePost("users/" + _store.getUserId() + "/resetpassword", ImmutableMap.of(
                "newPassword", newPassword), _store.getResetPasswordToken());
        validateResponse(response);
    }

    public void resendCodeToResetPassword() {

    }

    /**
     * get friends of current user
     * returns:     an array of users
     * {
     *     "userId": 0,
     *     "userFirstName": "string",
     *     "userLastName": "string",
     *     "userAvatarUrl": "string"
     * }
     */
    public void getFriends() {
        Response response = executeGet("users/" + _store.getUserId() + "/friends");
        validateResponse(response);

        return; // return an array of users
    }

    /**
     * get friend requests of current user
     */
    public void sendFriendRequests(int toUserId) {
        Response response = executePost(
                "users/" + _store.getUserId() + "/friendrequests",
                ImmutableMap.of(
                        "toUserId", String.valueOf(_store.getUserId())),
                "Unknown Authorization"
        );
        validateResponse(response);

        return; // an array of users
    }

    /**
     * get friend requests of current user
     * returns:     an array of users
     * {
     *     "userId": 0,
     *     "userFirstName": "string",
     *     "userLastName": "string",
     *     "userAvatarUrl": "string"
     * }
     */
    public void getFriendRequest() {
        Response response = executeGet(
                "user/" + _store.getUserId() + "/friendrequests",
                "Unknown Authorization"
        );
        validateResponse(response);

        return;
    }

    /**
     * post a photo
     * @param photoUrl: Url of photo
     * @param caption:  Caption for this photo
     */
    public void postPhotos(String photoUrl, String caption)
    {
        Response response = executePost(
                "users/" + _store.getUserId() + "/places/" + "placeID" + "/photos",
                ImmutableMap.of(
                        "photoUrl", photoUrl,
                        "caption", caption
                ),
                "Unknown Authorization"
        );
        validateResponse(response);
    }

    /**
     * get a review
     * returns:
     * {
     *   "reviewId": 0,
     *   "reviewRate": 0,
     *   "reviewMessage": "string",
     *   "reviewTime": "2018-05-28T23:09:25.112Z"
     * }
     */
    public void getReview()
    {
        Response response = executeGet(
                "users/" + _store.getUserId() + "/places/" + "placeID" + "/review",
                "Unknown Authorization"
        );
        validateResponse(response);

        return;
    }

    /**
     * post a place
     * @param nameofPlace:  a String represents name of place to post
     * @param location      a String represents the place's address
     * @param coverImageUrl a String represents cover image url
     * @param photoUrls     a List of String represents photo's urls
     * @param tags          a List of String represents people who be tagged
     * @return              a Integer is id of place in server
     */
    public int postPlace(String nameofPlace,
                         String location,
                         String coverImageUrl,
                         List<String> photoUrls,
                         List<String> tags)
    {
        HashMap map = new HashMap<>();
        map.put("nameofPlace" , nameofPlace);
        map.put("location" , location);
        map.put("coverImageUrl" , coverImageUrl);
        map.put("photoUrls" , photoUrls);
        map.put("tags" , tags);
        Response response = executePost(
                "users/" + _store.getUserId() + "/places",
                map,
                "Unknown Authorization"
        );
        validateResponse(response);
        return parseResponseJsonBody(response, Place.class).getId();
    }

    public void sendPlaceId(String name, String coverImageUrl)
    {

    }

    /**
     * get a place
     * @param placeId
     */
    public void getPlaceId(int placeId)
    {
        Response response = executeGet(
                "users/" + _store.getUserId() + "/places/" + placeId,
                "authorization"
        );
        validateResponse(response);
        parseResponseJsonBody(response, Place.class);
    }

    /**
     * get all places
     * @return      a List
     */
    public List getAllPlaces()
    {
        Response response = executeGet(
                "users/" + _store.getUserId() + "/places/all",
                "authorization"
        );
        validateResponse(response);
        class Places
        {
            private List places = null;
        }
        return parseResponseJsonBody(response, Places.class).places;
    }

    /**
     * send a comment to server
     * @param comment           a user's comment
     * @param trekkingPlanId    id of the plan's trek
     */
    public void sendComment(String comment, int trekkingPlanId)
    {
        Response response = executePost(
                "users/" + _store.getUserId() + "/trekkingplans/" + trekkingPlanId + "/comments",
                ImmutableMap.of("comment", comment),
                "authorization"
        );
        validateResponse(response);
    }

    /**
     * get all comments about something
     * @param trekkingPlanId    id of the plan's trek
     */
    public void getComments(int trekkingPlanId)
    {
        Response response = executeGet(
                "users/" + _store.getUserId() + "/trekkingplans/" + trekkingPlanId + "/comments",
                "authorization"
        );
        validateResponse(response);
        // dm thang cho Phuc, how to parse :"))
    }

    /**
     * get relevant trekking plan
     */
    public void getRelevantTrekkingPlans()
    {
        Response response = executeGet(
                "users/" + _store.getUserId() + "/relevanttrekkingplans",
                "authorization"
        );
        validateResponse(response);
        class relevantPlans
        {
            public List relevantPlans;
        }
        parseResponseJsonBody(response, relevantPlans.class);
    }

    /**
     * get id of plan's trek
     * @param placeId       id of place
     * @param whenToGoMin   min time to go
     * @param whenToGoMax   max time to go
     * @param howLongMin    min duration of plan
     * @param howLongMax    max duration of plan
     * @param hotelLevel    stars of hotel
     * @param description   descriptions about plan of other users
     * @return              id of plan
     */
    public int getTrekkingPlans(
            int placeId,
            String whenToGoMin,
            String whenToGoMax,
            int howLongMin,
            int howLongMax,
            int hotelLevel,
            String description)
    {
        HashMap<String,String> map = new HashMap<>();
        map.put("placeId", String.valueOf(placeId));
        map.put("whenToGoMin", whenToGoMin);
        map.put("whenToGoMax", whenToGoMax);
        map.put("howLongMin", String.valueOf(howLongMin));
        map.put("howLongMax", String.valueOf(howLongMax));
        map.put("hotelLevel", String.valueOf(hotelLevel));
        map.put("description", description);

        Response response = executePost(
                "users/" + _store.getUserId() + "trekkingplans",
                map,
                "authorization"
        );
        class trekkingplans
        {
            public int id;
        }
        return parseResponseJsonBody(response, trekkingplans.class).id;
    }

    /**
     * get user profile
     * @return a Profile class
     */
    public Profile getProfile()
    {
        Response response = executeGet(
                "users/" + _store.getUserId() + "/profile",
                "authorization"
        );
        validateResponse(response);
        return parseResponseJsonBody(response, Profile.class);
    }

    /**
     * put a profile
     * @param firstName     user's first name
     * @param lastName      user's last name
     * @param avatar        user's avatar
     * @param bio           user's bio
     * @param nationality   user's nationality
     * @param birthday      user's birthday
     * @param gender        user's gender
     * @param occupation    user's occupation
     * @param email         user's email
     * @param phoneNumber   user's phoneNumber
     * @param address       user's address
     */
    public void putProfile(
            String firstName,
            String lastName,
            String avatar,
            String bio,
            eNationality nationality,
            String birthday,
            eGender gender,
            String occupation,
            String email,
            String phoneNumber,
            String address)
    {
        HashMap<String,String> map = new HashMap<>();
        map.put("firstName", firstName);
        map.put("lastName", lastName);
        map.put("avatar", avatar);
        map.put("bio", bio);
        map.put("nationality", String.valueOf(nationality));
        map.put("birthday", birthday);
        map.put("gender", String.valueOf(gender));
        map.put("occupation", occupation);
        map.put("email", email);
        map.put("phoneNumber", phoneNumber);
        map.put("address", address);

        Response response = executePut(
                "users/" + _store.getUserId() + "/profile",
                map,
                "authorization"
        );
        validateResponse(response);
    }

    public void uploadFile()
    {
        //TODO: uploadFile - multipart
    }

    /**
     * get user's country
     * @return a Country class
     */
    public Country getCountry()
    {
        Response response = executeGet("countries");
        validateResponse(response);
        return parseResponseJsonBody(response, Country.class);
    }

    /**
     * put trekking plan
     * @param trekkingplanid id of plan
     * @param placeId        id of place
     * @param whenToGoMin    min time to go
     * @param whenToGoMax    max time to go
     * @param howLongMin     min duration of plan
     * @param howLongMax     max duration of plan
     * @param hotelLevel     stars of hotel
     * @param description    descriptions about plan of other users
     */
    public void putTrekkingPlansId(
            int trekkingplanid,
            int placeId,
            String whenToGoMin,
            String whenToGoMax,
            int howLongMin,
            int howLongMax,
            int hotelLevel,
            String description
    )
    {
        HashMap<String,String> map = new HashMap<>();
        map.put("placeId", String.valueOf(placeId));
        map.put("whenToGoMin", whenToGoMin);
        map.put("whenToGoMax", whenToGoMax);
        map.put("howLongMin", String.valueOf(howLongMin));
        map.put("howLongMax", String.valueOf(howLongMax));
        map.put("hotelLevel", String.valueOf(hotelLevel));
        map.put("description", description);

        Response response = executePut(
                "users/" + _store.getUserId() + "/trekkingplans/" + trekkingplanid,
                map,
                "authorization"
        );
        validateResponse(response);
    }

    /**
     * put place id
     * @param placeId       place id
     * @param name          name of place
     * @param coverImageUrl image of place
     */
    public void putPlaceId(int placeId, String name, String coverImageUrl)
    {
        Response response = executePut(
                "users/" + _store.getUserId() + "/places/" + placeId,
                ImmutableMap.of(
                        "name", name,
                        "coverImageUrl", coverImageUrl
                ),
                "authorization"
        );
        validateResponse(response);
    }

    /**
     * put a like
     * @param placeId   id of place
     */
    public void putLike(int placeId)
    {
        Response response = executePut(
                "users/" + _store.getUserId() + "/places/" + placeId + "/like",
                ImmutableMap.of(),
                "authorization"
        );
        validateResponse(response);
    }

    /**
     * put a dislike
     * @param placeId   id of place
     */
    public void putDislike(int placeId)
    {
        Response response = executePut(
                "users/" + _store.getUserId() + "/places/" + placeId + "/dislike",
                ImmutableMap.of(),
                "authorization"
        );
        validateResponse(response);
    }

    /**
     * put a review
     * @param placeId   id of place
     * @param rate      user's rate
     * @param message   user's message
     */
    public void putReview(int placeId, int rate, String message)
    {
        Response response = executePut(
                "users/" + _store.getUserId() + "/places/" + placeId + "/review",
                ImmutableMap.of(
                        "rate", String.valueOf(rate),
                        "message", message
                ),
                "authorization"
        );
        validateResponse(response);
    }

    /**
     * put photo id
     * @param placeId   id of place
     * @param photoId   id of photo
     * @param caption   user's caption
     */
    public void putPhotoId(int placeId, int photoId, String caption)
    {
        Response response = executePut(
                "users/" + _store.getUserId() + "/places/" + placeId + "/photos/" + photoId,
                ImmutableMap.of(
                        "caption", caption
                ),
                "authorization"
        );
        validateResponse(response);
    }

    /**
     * put a tag
     * @param placeId   id of place
     * @param tag       a tag
     * @param type      type
     */
    public void putTags(int placeId, String tag, String type)
    {
        Response response = executePut(
                "users/" + _store.getUserId() + "/places/" + placeId + "/tags",
                ImmutableMap.of(
                        "tag", tag,
                        "type", type
                ),
                "authorization"
        );
        validateResponse(response);
    }

    /**
     *
     * @param fromUserId    from which user
     */
    public void putFriendRequest(int fromUserId)
    {
        Response response = executePut(
                "users/" + _store.getUserId() + "/friendrequests/" + fromUserId + "/accept",
                ImmutableMap.of(),
                "authorization"
        );
        validateResponse(response);
    }

    /**
     *
     * @param fromUserId from which user
     */
    public void putDeclineFriendRequest(int fromUserId)
    {
        Response response = executePut(
                "users/" + _store.getUserId() + "/friendrequests/" + fromUserId + "/decline",
                ImmutableMap.of(),
                "authorization"
        );
        validateResponse(response);
    }

    /**
     * delete trekking plan
     * @param trekkingPlanId    id of plan
     */
    public void deleteTrekkingPlan(int trekkingPlanId)
    {
        Response response = executeDelete(
                "users/" + _store.getUserId() + "/trekkingplans/" + trekkingPlanId,
                "authorization"
        );
        validateResponse(response);
    }

    /**
     * delete photo
     * @param photoId   id of photo
     * @param placeId   id of place
     */
    public void deletePhoto(int photoId, int placeId)
    {
        Response response = executeDelete(
                "users/" + _store.getUserId() + "/places/" + placeId + "/photos/" + photoId,
                "authorization"
        );
        validateResponse(response);
    }

    /**
     * delete friend request
     * @param toUserId  to which user
     */
    public void deleteFriendRequest(int toUserId)
    {
        Response response = executeDelete(
                "users/" + _store.getUserId() + "/friendrequests/" + toUserId,
                "authorization"
        );
        validateResponse(response);
    }

    /**
     * put decline a friend request
     * @param fromUserId    from which user
     */
    public void putDecline(int fromUserId)
    {
        Response response = executePut(
                "users/" + _store.getUserId() + "/friendrequests/" + fromUserId + "/decline",
                ImmutableMap.of(),
                "authorization"
        );
        validateResponse(response);
    }
}

