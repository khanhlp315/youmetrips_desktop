package com.phuongkhanh.youmetrips.services.api;

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
                        jsonMap != null? _gson.get().toJson(jsonMap): "{}"))
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
                        jsonMap != null? _gson.get().toJson(jsonMap): "{}"))
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

        _store.storeSignupData(_currentUser.getUserId(),_currentUser.getConfirmToken(),_currentUser.getResendConfirmationCodeToken());
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

    public void sendConfirmationCode(String confirmationCode)
    {
        Response response = executePost("users/" + _store.getUserId() + "/confirmnewuser", ImmutableMap.of(
                "confirmationCode", confirmationCode), _store.getConfirmToken());
        validateResponse(response);
        parseResponseJsonBody(response, Login.class);
    }

    public void resendConfirmationCOde()
    {
        Response response = executePost("users/" + _store.getUserId() + "/resendnewuserconfirmationcode", null, _store.getResendConfirmationCodeToken());
        validateResponse(response);
        parseResponseJsonBody(response, Login.class);
    }

    public void sendEmailToResetPassword(String email)
    {
        Response response = executePost("requestrecoverycode",  ImmutableMap.of(
                "emailOrPhoneNumber", email));
        validateResponse(response);
        _userIdToResetPassword = String.valueOf(parseResponseJsonBody(response, UserResetPassword.class).getUserId());
        _store.storeUserId(Integer.valueOf(_userIdToResetPassword));
    }

    public void sendCodeToResetPassword(String recoveryCode)
    {
        Response response = executePost("users/" + _store.getUserId() + "/requestresetpasswordtoken", ImmutableMap.of(
                "recoveryCode", recoveryCode));
        validateResponse(response);
        _userTokenToResetPassword = parseResponseJsonBody(response, UserResetPassword.class).getUserToken();
        _store.storeResetPasswordToken(_userTokenToResetPassword);
    }

    public void sendPasswordToResetPassword(String newPassword)
    {
        Response response = executePost("users/" + _store.getUserId() + "/resetpassword", ImmutableMap.of(
                "newPassword", newPassword), _store.getResetPasswordToken());
        validateResponse(response);
    }

    public void resendCodeToResetPassword()
    {

    }
}

