package com.phuongkhanh.youmetrips.presentation.components.login;

public interface LoginService {
    void loginWithFacebook(String token);
    void loginWithEmailPassword(String email, String password);
    void signup(String emailOrPhone, String password, String firstName, String lastName);
}
