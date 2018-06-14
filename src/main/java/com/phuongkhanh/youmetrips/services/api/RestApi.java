package com.phuongkhanh.youmetrips.services.api;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.phuongkhanh.youmetrips.services.api.exceptions.*;
import com.phuongkhanh.youmetrips.services.api.models.*;
import okhttp3.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.phuongkhanh.youmetrips.services.api.utils.Constants.*;
import static java.util.Objects.requireNonNull;

public class RestApi {
    private final ThreadLocal<OkHttpClient> _client;
    private final ThreadLocal<Gson> _gson;
    private String _baseUrl = "http://docker.youthdev.net:23010";

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

    private Response executePostFile(final String path, final File file, final String authorization) {
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", file.getName(), RequestBody.create(MEDIA_TYPE_IMAGE, file))
                .build();

        Request request = new Request.Builder()
                .url(getUrl(path))
                .addHeader("Authorization", "Bearer " + authorization)
                .post(requestBody)
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

    private int parseResponseJsonBodyAsInt(final Response response, String field) {
        if (!APPLICATION_JSON.equals(response.header(CONTENT_TYPE))) {
            throw new UnknownApiResponseContentTypeException();
        }

        try {
            String json = requireNonNull(response.body()).string();
            JsonObject jsonObject = requireNonNull(_gson.get().fromJson(json, JsonObject.class));
            return jsonObject.get(field).getAsInt();
        } catch (Throwable e) {
            throw new CouldNotParseApiResponseBodyException();
        }
    }

    private String parseResponseJsonBodyAsString(final Response response, String field) {
        if (!APPLICATION_JSON.equals(response.header(CONTENT_TYPE))) {
            throw new UnknownApiResponseContentTypeException();
        }

        try {
            String json = requireNonNull(response.body()).string();
            JsonObject jsonObject = requireNonNull(_gson.get().fromJson(json, JsonObject.class));
            return jsonObject.get(field).getAsString();
        } catch (Throwable e) {
            throw new CouldNotParseApiResponseBodyException();
        }
    }

    private <T> List<T> parseResponseJsonBodyAsList(final Response response, String field, Class<T> clazz) {
        if (!APPLICATION_JSON.equals(response.header(CONTENT_TYPE))) {
            throw new UnknownApiResponseContentTypeException();
        }

        try {
            String json = requireNonNull(response.body()).string();
            JsonObject jsonObject = requireNonNull(_gson.get().fromJson(json, JsonObject.class));
            JsonArray jsonArray = jsonObject.get(field).getAsJsonArray();
            List<T> ret = new ArrayList<>();
            for (JsonElement element : jsonArray) {
                ret.add(_gson.get().fromJson(element, clazz));
            }
            return ret;
        } catch (Throwable e) {
            throw new CouldNotParseApiResponseBodyException();
        }
    }

    private <T> List<T> parseResponseJsonBodyAsList(final Response response, Class<T> clazz) {
        if (!APPLICATION_JSON.equals(response.header(CONTENT_TYPE))) {
            throw new UnknownApiResponseContentTypeException();
        }

        try {
            String json = requireNonNull(response.body()).string();
            JsonArray jsonArray = requireNonNull(_gson.get().fromJson(json, JsonArray.class));
            List<T> ret = new ArrayList<>();
            for (JsonElement element : jsonArray) {
                ret.add(_gson.get().fromJson(element, clazz));
            }
            return ret;
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

    public SignUp signUp(String emailOrPhone,
                         String password,
                         String firstName,
                         String lastName) {
        Response response = executePost("signup", ImmutableMap.of(
                "emailOrPhoneNumber", emailOrPhone,
                "password", password,
                "firstName", firstName,
                "lastName", lastName));
        validateResponse(response);
        return parseResponseJsonBody(response, SignUp.class);
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
            error.setData((Map<String, Object>) jsonMap.get("data"));
        } catch (Throwable e) {
            throw new CouldNotParseApiResponseBodyException();
        }

        if (response.code() >= 400 && response.code() < 500)
            throw new ApiClientException(error);

        throw new ApiServerException(error);
    }

    public Login sendConfirmationCode(String confirmationCode, int userId, String token) {
        Response response = executePost("users/" + userId + "/confirmnewuser", ImmutableMap.of(
                "confirmationCode", confirmationCode), token);
        validateResponse(response);
        return parseResponseJsonBody(response, Login.class);
    }

    public void resendConfirmationCode(int userId, String token) {
        Response response = executePost("users/" + userId + "/resendnewuserconfirmationcode", null, token);
        validateResponse(response);
        parseResponseJsonBody(response, Login.class);
    }

    public int sendEmailToResetPassword(String email) {
        Response response = executePost("requestrecoverycode", ImmutableMap.of(
                "emailOrPhoneNumber", email));
        validateResponse(response);
        return parseResponseJsonBodyAsInt(response, "userId");
    }

    public String sendCodeToResetPassword(String recoveryCode, int userId) {
        Response response = executePost("users/" + userId + "/requestresetpasswordtoken", ImmutableMap.of(
                "recoveryCode", recoveryCode));
        validateResponse(response);
        return parseResponseJsonBodyAsString(response, "resetPasswordToken");
    }

    public Login resetPassword(String newPassword, int userId, String token) {
        Response response = executePost("users/" + userId + "/resetpassword", ImmutableMap.of(
                "newPassword", newPassword), token);
        validateResponse(response);
        return parseResponseJsonBody(response, Login.class);

    }

    public void resendCodeToResetPassword() {

    }

    /**
     * get friends of current user
     * returns:     an array of users
     * {
     * "userId": 0,
     * "userFirstName": "string",
     * "userLastName": "string",
     * "userAvatarUrl": "string"
     * }
     */
    public List<Friend> getFriends(int userId, String jwt) {
        Response response = executeGet("users/" + userId + "/friends", jwt);
        validateResponse(response);
        return parseResponseJsonBodyAsList(response, Friend.class);
    }

    /**
     * get friend requests of current user
     */
    public void sendFriendRequests(int toUserId, int userId, String jwt) {
        Response response = executePost(
                "users/" + userId + "/friendrequests",
                ImmutableMap.of(
                        "toUserId", String.valueOf(toUserId)),
                jwt
        );
        validateResponse(response);
    }

    /**
     * get friend requests of current user
     * returns:     an array of users
     * {
     * "userId": 0,
     * "userFirstName": "string",
     * "userLastName": "string",
     * "userAvatarUrl": "string"
     * }
     */
    public void getAllFriendRequests(int userId, String jwt) {
        Response response = executeGet(
                "user/" + userId + "/friendrequests",
                jwt
        );
        validateResponse(response);

        return;
    }

    /**
     * post a photo
     *
     * @param photoUrl: Url of photo
     * @param caption:  Caption for this photo
     */
    public void addPhoto(String photoUrl, String caption, int userId, int placeId, String jwt) {
        Response response = executePost(
                "users/" + userId + "/places/" + placeId + "/photos",
                ImmutableMap.of(
                        "photoUrl", photoUrl,
                        "caption", caption
                ),
                jwt
        );
        validateResponse(response);
    }

    /**
     * get a review
     * returns:
     * {
     * "reviewId": 0,
     * "reviewRate": 0,
     * "reviewMessage": "string",
     * "reviewTime": "2018-05-28T23:09:25.112Z"
     * }
     */
    public UserReview getUserReview(int placeId, int userId, String jwt) {
        Response response = executeGet(
                "users/" + userId + "/places/" + placeId + "/review",
                jwt
        );
        validateResponse(response);

        return parseResponseJsonBody(response, UserReview.class);
    }


    public int createPlace(CreatePlace place, int userId, String jwt) {
        HashMap map = new HashMap<>();
        map.put("name", place.getName());
        map.put("location", place.getLocation());
        map.put("coverImageUrl", place.getCoverImageUrl());
        map.put("photoUrls", place.getPhotoUrls());
        map.put("tags", place.getTags());
        Response response = executePost(
                "users/" + userId + "/places",
                map,
                jwt
        );
        validateResponse(response);
        return parseResponseJsonBodyAsInt(response, "id");
    }

    /**
     * get a place
     *
     * @param placeId
     */
    public PlaceDetails getPlaceDetails(int placeId, int userId, String jwt) {
        Response response = executeGet(
                "users/" + userId + "/places/" + placeId,
                jwt
        );
        validateResponse(response);
        return parseResponseJsonBody(response, PlaceDetails.class);
    }

    /**
     * get all places
     *
     * @return a List
     */
    public List<Place> getAllPlaces(int userId, String jwt) {
        Response response = executeGet(
                "users/" + userId + "/places/all",
                jwt
        );
        validateResponse(response);
        return parseResponseJsonBodyAsList(response, "places", Place.class);
    }

    /**
     * send a comment to server
     *
     * @param comment        a user's comment
     * @param trekkingPlanId id of the plan's trek
     */
    public void sendComment(String comment, int trekkingPlanId, int userId, String jwt) {
        Response response = executePost(
                "users/" + userId + "/trekkingplans/" + trekkingPlanId + "/comments",
                ImmutableMap.of("comment", comment),
                jwt
        );
        validateResponse(response);
    }

    /**
     * get all comments about something
     *
     * @param trekkingPlanId id of the plan's trek
     */
    public List<Comment> getComments(int trekkingPlanId, int userId, String jwt) {
        Response response = executeGet(
                "users/" + userId + "/trekkingplans/" + trekkingPlanId + "/comments",
                jwt
        );
        validateResponse(response);
        return parseResponseJsonBodyAsList(response, Comment.class);
    }

    /**
     * get relevant trekking plan
     */
    public List<RelevantPlan> getRelevantTrekkingPlans(int userId, String jwt) {
        Response response = executeGet(
                "users/" + userId + "/relevanttrekkingplans",
                jwt
        );
        validateResponse(response);
        return parseResponseJsonBodyAsList(response, "relevantPlans", RelevantPlan.class);
    }

    public int createTrekkingPlan(CreatePlan plan, int userId, String jwt) {
        HashMap<String, String> map = new HashMap<>();
        map.put("placeId", String.valueOf(plan.getPlaceId()));
        map.put("whenToGoMin", plan.getWhenToGoMin());
        map.put("whenToGoMax", plan.getWhenToGoMax());
        map.put("howLongMin", String.valueOf(plan.getHowLongMin()));
        map.put("howLongMax", String.valueOf(plan.getHowLongMax()));
        map.put("hotelLevel", String.valueOf(plan.getHotelLevel()));
        map.put("description", plan.getDescription());

        Response response = executePost(
                "users/" + userId + "/trekkingplans",
                map,
                jwt
        );

        validateResponse(response);
        return parseResponseJsonBodyAsInt(response, "id");
    }

    /**
     * get user profile
     *
     * @return a Profile class
     */
    public Profile getProfile(int userId, String jwt) {
        Response response = executeGet(
                "users/" + userId + "/profile",
                jwt
        );
        validateResponse(response);
        return parseResponseJsonBody(response, Profile.class);
    }

    public void updateProfile(EditedUserProfile profile, int userId, String jwt) {
        HashMap<String, String> map = new HashMap<>();
        map.put("firstName", profile.getFirstName());
        map.put("lastName", profile.getLastName());
        map.put("avatar", profile.getAvatar());
        map.put("bio", profile.getBio());
        map.put("nationality", profile.getNationality());
        map.put("birthday", profile.getBirthday());
        map.put("gender", profile.getGender());
        map.put("occupation", profile.getOccupation());
        map.put("email", profile.getEmail());
        map.put("phoneNumber", profile.getPhoneNumber());
        map.put("address", profile.getAddress());

        Response response = executePut(
                "users/" + userId + "/profile",
                map,
                jwt
        );
        validateResponse(response);
    }

    /**
     * get user's country
     *
     * @return a Country class
     */
    public List<Country> getAllCountries() {
        Response response = executeGet("countries");
        validateResponse(response);
        Type listCountryType = new TypeToken<List<Country>>() {
        }.getType();
        return parseResponseJsonBodyAsList(response, Country.class);
    }

    public void updatePlan(int trekkingPlanId, CreatePlan plan, int userId, String jwt) {
        HashMap<String, String> map = new HashMap<>();
        map.put("placeId", String.valueOf(plan.getPlaceId()));
        map.put("whenToGoMin", plan.getWhenToGoMin());
        map.put("whenToGoMax", plan.getWhenToGoMax());
        map.put("howLongMin", String.valueOf(plan.getHowLongMin()));
        map.put("howLongMax", String.valueOf(plan.getHowLongMax()));
        map.put("hotelLevel", String.valueOf(plan.getHotelLevel()));
        map.put("description", plan.getDescription());

        Response response = executePut(
                String.format("users/%d/trekkingplans/%d", userId, trekkingPlanId),
                map,
                jwt
        );
        validateResponse(response);
    }

    /**
     * put place id
     *
     * @param placeId       place id
     * @param name          name of place
     * @param coverImageUrl image of place
     */
    public void updatePlace(int placeId, String name, String coverImageUrl, int userId, String jwt) {
        Response response = executePut(
                String.format("users/%d/places/%d", userId, placeId),
                ImmutableMap.of(
                        "name", name,
                        "coverImageUrl", coverImageUrl
                ),
                jwt
        );
        validateResponse(response);
    }

    /**
     * put a like
     *
     * @param placeId id of place
     */
    public void like(int placeId, int userId, String jwt) {
        Response response = executePut(
                String.format("users/%d/places/%d/like", userId, placeId),
                ImmutableMap.of(),
                jwt
        );
        validateResponse(response);
    }

    /**
     * put a dislike
     *
     * @param placeId id of place
     */
    public void unlikePlace(int placeId, int userId, String jwt) {
        Response response = executePut(
                String.format("users/%d/places/%d/dislike", userId, placeId),
                ImmutableMap.of(),
                jwt
        );
        validateResponse(response);
    }

    /**
     * put a review
     *
     * @param placeId id of place
     * @param rate    user's rate
     * @param message user's message
     */
    public void review(int placeId, int rate, String message, int userId, String jwt) {
        Response response = executePut(
                String.format("users/%d/places/%d/review", userId, placeId),
                ImmutableMap.of(
                        "rate", String.valueOf(rate),
                        "message", message
                ),
                jwt
        );
        validateResponse(response);
    }

    /**
     * put photo id
     *
     * @param placeId id of place
     * @param photoId id of photo
     * @param caption user's caption
     */
    public void addPhotoCaption(int placeId, int photoId, String caption, int userId, String jwt) {
        Response response = executePut(
                String.format("users/%d/places/%d/photos/%d", userId, placeId, photoId),
                ImmutableMap.of(
                        "caption", caption
                ),
                jwt
        );
        validateResponse(response);
    }

    /**
     * put a tag
     *
     * @param placeId id of place
     * @param tag     a tag
     * @param type    type
     */
    public void addTag(int placeId, String tag, String type, int userId, String jwt) {
        Response response = executePut(
                String.format("users/%d/places/%d/tags", userId, placeId),
                ImmutableMap.of(
                        "tag", tag,
                        "type", type
                ),
                jwt
        );
        validateResponse(response);
    }

    /**
     * @param fromUserId from which user
     */
    public void acceptFriendRequest(int fromUserId, int userId, String jwt) {
        Response response = executePut(
                String.format("users/%d/friendrequests/%d/accept", userId, fromUserId),
                ImmutableMap.of(),
                jwt
        );
        validateResponse(response);
    }

    /**
     * delete trekking plan
     *
     * @param trekkingPlanId id of plan
     */
    public void deleteTrekkingPlan(int trekkingPlanId, int userId, String jwt) {
        Response response = executeDelete(
                String.format("users/%d/trekkingplans/%d", userId, trekkingPlanId),
                jwt
        );
        validateResponse(response);
    }

    /**
     * delete photo
     *
     * @param photoId id of photo
     * @param placeId id of place
     */
    public void deletePhoto(int photoId, int placeId, int userId, String jwt) {
        Response response = executeDelete(
                String.format("users/%d/places/%d/photos/%d", userId, placeId, photoId),
                jwt
        );
        validateResponse(response);
    }

    /**
     * delete friend request
     *
     * @param toUserId to which user
     */
    public void deleteFriendRequest(int toUserId, int userId, String jwt) {
        Response response = executeDelete(
                String.format("users/%d/friendrequests/%d", userId, toUserId),
                jwt
        );
        validateResponse(response);
    }

    /**
     * decline a friend request
     *
     * @param fromUserId from which user
     */
    public void declineFriendRequest(int fromUserId, int userId, String jwt) {
        Response response = executePut(
                String.format("users/%d/friendrequests/%d/decline", userId, fromUserId),
                ImmutableMap.of(),
                jwt
        );
        validateResponse(response);
    }

    public String uploadFile(File file, int userId, String jwt) {
        Response response = executePostFile(
                String.format("users/%d/uploadfile", userId),
                file,
                jwt
        );
        validateResponse(response);
        return parseResponseJsonBodyAsString(response, "url");
    }
}


