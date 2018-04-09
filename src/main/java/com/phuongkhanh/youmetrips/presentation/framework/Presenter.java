package com.phuongkhanh.youmetrips.presentation.framework;

public interface Presenter<T> {
    T getView();

    void setView( T view );
}
