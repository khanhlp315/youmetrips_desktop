package com.phuongkhanh.youmetrips.presentation.windows;

import com.phuongkhanh.youmetrips.presentation.components.editprofile.EditProfileScreen;
import com.phuongkhanh.youmetrips.presentation.framework.JFXWindowBase;

import javax.inject.Inject;

public class EditProfileWindow extends JFXWindowBase {
    @Inject
    EditProfileWindow(final EditProfileScreen editProfileScreen){
        super(editProfileScreen);
    }
}
