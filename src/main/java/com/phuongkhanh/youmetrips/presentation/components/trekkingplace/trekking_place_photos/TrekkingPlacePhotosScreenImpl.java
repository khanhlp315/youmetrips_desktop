package com.phuongkhanh.youmetrips.presentation.components.trekkingplace.trekking_place_photos;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.phuongkhanh.youmetrips.presentation.components.trekkingplace.trekking_place_hashtags.TrekkingPlaceHashtagsScreenImpl;
import com.phuongkhanh.youmetrips.presentation.framework.FXMLScreen;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javax.inject.Inject;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TrekkingPlacePhotosScreenImpl extends FXMLScreen
        implements TrekkingPlacePhotosScreen, Initializable {

    private TrekkingPlacePhotosPresenter _presenter;
    private List<File> _listChosenImage;

    @FXML
    private JFXButton _btnNext;

    @FXML
    private JFXListView _listView;

    @Inject
    public TrekkingPlacePhotosScreenImpl(TrekkingPlacePhotosPresenter presenter) {
        _presenter = presenter;
        _presenter.setView(this);
    }

    @Override
    public void showContinue() {
        _btnNext.setDisable(false);
    }

    @Override
    public void hideContinue() {
        _btnNext.setDisable(true);
    }

    @Override
    public void showError(String title, String message) {

    }

    @Override
    public void addImage(File image) {
        _listChosenImage.add(image);
        _listView.setItems(FXCollections.observableArrayList(_listChosenImage));
    }

    @Override
    public void removeImage(File image) {
        _listChosenImage.remove(1);
        _listView.setItems(FXCollections.observableArrayList(_listChosenImage));
    }

    @Override
    public void changeCoverImage(File image) {

    }

    @Override
    public void removeCoverImage() {

    }

    @Override
    public void navigateToHashTags() {
        navigate(TrekkingPlaceHashtagsScreenImpl.class);
    }

    @FXML
    public void onNavigateToHashtags()
    {
        _presenter.requestToNavigateToHashtags();
    }

    @FXML
    public void onAddImage()
    {
        //_presenter.pickImage();
    }

    @FXML
    public void onRemoveImage()
    {

    }

    @Override
    protected String fxmlPath() {
        return "/view/create_place/step3.fxml";
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        _listChosenImage = new ArrayList<>();
    }
}
