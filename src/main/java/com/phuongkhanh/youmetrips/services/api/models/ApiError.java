package com.phuongkhanh.youmetrips.services.api.models;

import java.util.Map;

public class ApiError {
    private String _errorCode;
    private Map<String, String> _userMessageDict;
    private Map<String, String> _moreInformationDict;
    private Map<String, Object> _data;

    public String getErrorCode() {
        return _errorCode;
    }

    public void setErrorCode( final String errorCode ) {
        _errorCode = errorCode;
    }

    public Map<String, String> getUserMessageDict() {
        return _userMessageDict;
    }

    public void setUserMessageDict( final Map<String, String> userMessageDict ) {
        _userMessageDict = userMessageDict;
    }

    public Map<String, String> getMoreInformationDict() {
        return _moreInformationDict;
    }

    public void setMoreInformationDict( final Map<String, String> moreInformationDict ) {
        _moreInformationDict = moreInformationDict;
    }

    public Map<String, Object> getData() {
        return _data;
    }

    public void setData(Map<String, Object> data) {
        this._data = data;
    }
}
