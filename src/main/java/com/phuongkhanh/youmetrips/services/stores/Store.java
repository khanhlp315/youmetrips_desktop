package com.phuongkhanh.youmetrips.services.stores;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import static com.phuongkhanh.youmetrips.services.api.utils.Constants.APP_DATA;
import static com.phuongkhanh.youmetrips.utils.CommonUtils.getLocalFilePath;

/*
 * @author by LeVoGiaKhang
 */
public class Store {
    private Map<String, StoreItem> _items;

    public Store() {
        _items = new HashMap<>();
    }

    @SuppressWarnings("unchecked")
    public Object getItem(String key) {
        return _items.containsKey(key)
                ?  _items.get(key).getItem()
                : null;
    }

    @SuppressWarnings("unchecked")
    public void setItem(String key, Object value) {
        if (_items.containsKey(key)) {
            _items.get(key).setItem(value);
        } else {
            _items.put(key, new StoreItem());
            _items.get(key).setItem(value);
        }
    }


    public void save(String fileName) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(getLocalFilePath(fileName));
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(_items);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @SuppressWarnings("unchecked")
    public void load(String fileName) {
        File f = new File(getLocalFilePath(fileName));
        if(f.exists()) {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(getLocalFilePath(fileName));
                ObjectInputStream ois = new ObjectInputStream(fis);
                _items = (Map) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

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
