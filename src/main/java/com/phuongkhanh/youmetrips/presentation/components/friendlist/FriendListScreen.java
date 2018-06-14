package com.phuongkhanh.youmetrips.presentation.components.friendlist;

import com.phuongkhanh.youmetrips.presentation.framework.JFXScreen;
import com.phuongkhanh.youmetrips.services.api.models.Friend;

import java.util.List;

public interface FriendListScreen extends JFXScreen {
    void navigateBack();

    void updateFriends(List<Friend> friends);
}
