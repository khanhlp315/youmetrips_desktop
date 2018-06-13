package com.phuongkhanh.youmetrips.services.stores;

import java.util.ArrayList;
import java.util.List;

/*
 * @author by LeVoGiaKhang
 */
public class StoreItem<T> implements java.io.Serializable {

    private T _item;
    private List<Wrapper<T, T>> _valueChangedListener;

    public StoreItem() {
        _valueChangedListener = new ArrayList<>();
    }

    public T getItem() {
        return _item;
    }

    public void setItem(T item) {
        T oldValue = _item;
        _item = item;

//        _valueChangedListener.forEach((function) -> {
//            System.out.println("hello");
//        });
    }

    public void addListener(Wrapper<T, T> value) {
        _valueChangedListener.add(value);
    }

    public void removeListener(Wrapper<T, T> value) {
        _valueChangedListener.remove(value);
    }

    public class Wrapper<A, B> {
        T _oldValue;
        T _newValue;

        public T getNewValue() {
            return _newValue;
        }

        public void setNewValue(T newValue) {
            _newValue = newValue;
        }

        public T getOldValue() {
            return _oldValue;
        }

        public void setOldValue(T oldValue) {
            _oldValue = oldValue;
        }
    }
}
