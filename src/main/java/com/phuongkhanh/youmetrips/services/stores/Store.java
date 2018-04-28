package com.phuongkhanh.youmetrips.services.stores;

import java.util.HashMap;
import java.util.Map;

/*
 * @author by LeVoGiaKhang
 */
public class Store<T> {
    private Map<String, StoreItem> _items;

    public Store() {
        _items = new HashMap<>();
    }

    @SuppressWarnings("unchecked")
    public T getItem(String key) {
        return _items.containsKey(key)
                ? (T) _items.get(key).getItem()
                : null;
    }

    @SuppressWarnings("unchecked")
    public void setItem(String key, T value) {
        if (_items.containsKey(key)) {
            _items.get(key).setItem(value);
        } else {
            _items.put(key, new StoreItem());
            _items.get(key).setItem(value);
        }
    }

    @SuppressWarnings("unchecked")
    public void addListener(String key, StoreItem.Wrapper value) {
        _items.get(key).addListener(value);
    }

    @SuppressWarnings("unchecked")
    public void removeListener(String key, StoreItem.Wrapper value) {
        _items.get(key).removeListener(value);
    }

}
