package com.phuongkhanh.youmetrips.presentation.components.home.profile;

import com.phuongkhanh.youmetrips.services.api.models.Friend;
import com.phuongkhanh.youmetrips.services.api.models.Profile;
import com.phuongkhanh.youmetrips.services.stores.AuthenticationStore;
import com.phuongkhanh.youmetrips.services.stores.HomeStore;

import java.util.List;

public interface ProfileService {
    Profile getUserProfile(int userId, String jwt);

    List<Friend> fetchAllFriends(int userId, String jwt);

    AuthenticationStore getAuthenticationStore();

    HomeStore getHomeStore();
}
