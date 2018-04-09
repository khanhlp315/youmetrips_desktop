package com.phuongkhanh.youmetrips.presentation.framework;

public abstract class PresenterBase<T> implements Presenter<T> {
    private T _view;

    @Override
    public final T getView() {
        return _view;
    }

    @Override
    public final void setView( T view ) {
        _view = view;
        onAttachedView();
    }

    protected void onAttachedView() {

    }
}

