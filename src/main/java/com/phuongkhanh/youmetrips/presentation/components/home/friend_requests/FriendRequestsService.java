package com.phuongkhanh.youmetrips.presentation.components.home.friend_requests;

import com.phuongkhanh.youmetrips.services.api.models.FriendRequest;
import com.phuongkhanh.youmetrips.services.stores.AuthenticationStore;
import com.phuongkhanh.youmetrips.services.stores.HomeStore;

import java.util.List;

public interface FriendRequestsService {
    AuthenticationStore getAuthenticationStore();

    void acceptRequest(int fromUserId, int userId, String jwt);
    void declineRequest(int fromUserId, int userId, String jwt);

    List<FriendRequest> fetchAllFriendRequests(int userId, String jwt);

    HomeStore getHomeStore();
}
