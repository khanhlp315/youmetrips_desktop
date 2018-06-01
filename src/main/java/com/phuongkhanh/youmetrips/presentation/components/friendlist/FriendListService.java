package com.phuongkhanh.youmetrips.presentation.components.friendlist;

import com.phuongkhanh.youmetrips.services.api.models.Friend;
import com.phuongkhanh.youmetrips.services.stores.AuthenticationStore;
import com.phuongkhanh.youmetrips.services.stores.HomeStore;

import java.util.List;

public interface FriendListService {
    public List<Friend> fetchAllFriends(int userId, String jwt);

    public AuthenticationStore getAuthenticationStore();

    public HomeStore getHomeStore();
}
