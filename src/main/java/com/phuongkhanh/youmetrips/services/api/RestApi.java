package com.phuongkhanh.youmetrips.services.api;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.phuongkhanh.youmetrips.services.api.exceptions.CouldNotConnectApiServerException;
import com.phuongkhanh.youmetrips.services.api.exceptions.CouldNotParseApiResponseBodyException;
import com.phuongkhanh.youmetrips.services.api.exceptions.UnknownApiResponseContentTypeException;
import com.phuongkhanh.youmetrips.services.api.models.Login;
import com.phuongkhanh.youmetrips.utils.CommonUtils;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

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
    private String _baseUrl;

    public RestApi(){
        _client = new ThreadLocal<OkHttpClient>() {
            @Override
            protected OkHttpClient initialValue() {
                return new OkHttpClient.Builder()
                        .connectTimeout( 10, TimeUnit.SECONDS )
                        .readTimeout( 10, TimeUnit.SECONDS )
                        .writeTimeout( 10, TimeUnit.SECONDS )
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

    public void setBaseUrl( final String baseUrl ) {
        if(CommonUtils.validateUrl(baseUrl))
        {
            _baseUrl = baseUrl;
        }
    }

    public String getBaseUrl() {
        return _baseUrl;
    }

    private String getUrl( final String path ) {
        return String.format( "%s/%s", _baseUrl, path );
    }

    private Response executePost(final String path, final Map<String, String> jsonMap ) {
        Request request = new Request.Builder()
                .url( getUrl( path ) )
                .post( RequestBody.create( MEDIA_TYPE_APPLICATION_JSON,
                        _gson.get().toJson( jsonMap ) ) )
                .build();

        try {
            return _client.get().newCall( request ).execute();
        }
        catch ( IOException e ) {
            throw new CouldNotConnectApiServerException();
        }
    }

    private Response executeGet( final String path ) {
        Request request = new Request.Builder()
                .url( getUrl( path ) )
                .build();

        try {
            return _client.get().newCall( request ).execute();
        }
        catch ( IOException e ) {
            throw new CouldNotConnectApiServerException();
        }
    }

    private <T> T parseResponseJsonBody( final Response response, final Class<T> clazz ) {
        if ( !APPLICATION_JSON.equals( response.header( CONTENT_TYPE ) ) ) {
            throw new UnknownApiResponseContentTypeException();
        }

        try {
            String json = requireNonNull( response.body() ).string();
            return requireNonNull( _gson.get().fromJson( json, clazz ) );
        }
        catch ( Throwable e ) {
            throw new CouldNotParseApiResponseBodyException();
        }
    }

    public Login login(String email, String password) {
        Response response = executePost( "login", ImmutableMap.of(
                "emailOrPhoneNumber", email,
                "password", password ) );
        return parseResponseJsonBody( response, Login.class );
    }
}
