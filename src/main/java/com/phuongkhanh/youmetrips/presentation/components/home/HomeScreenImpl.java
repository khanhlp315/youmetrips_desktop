package com.phuongkhanh.youmetrips.presentation.components.home;

import com.phuongkhanh.youmetrips.presentation.framework.FXMLScreen;
import javafx.fxml.Initializable;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeScreenImpl extends FXMLScreen
implements HomeScreen, Initializable {

    @Inject
    public HomeScreenImpl(HomePresenter presenter)
    {

    }

    @Override
    public void navigateToProfile() {

    }

    @Override
    public void navigateToFriendRequests() {

    }

    @Override
    public void navigateToPlans() {

    }

    @Override
    public void navigateToPlaces() {

    }

    @Override
    public void showError(String title, String message) {

    }

    @Override
    protected String fxmlPath() {
        return null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
