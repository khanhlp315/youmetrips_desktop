package com.phuongkhanh.youmetrips.presentation.components.trekkingplace.trekking_place_photos;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.phuongkhanh.youmetrips.presentation.components.trekkingplace.trekking_place_hashtags.TrekkingPlaceHashtagsScreenImpl;
import com.phuongkhanh.youmetrips.presentation.framework.FXMLScreen;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;

import javax.inject.Inject;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class TrekkingPlacePhotosScreenImpl extends FXMLScreen
        implements TrekkingPlacePhotosScreen, Initializable {

    private TrekkingPlacePhotosPresenter _presenter;
    private List<Image> _listChosenImage;
    private List<File> _listChosenFile;

    @FXML
    private JFXButton _btnNext;

    @FXML
    private JFXListView _listView;

    @Inject
    public TrekkingPlacePhotosScreenImpl(TrekkingPlacePhotosPresenter presenter) {
        _presenter = presenter;
        _presenter.setView(this);
        _listView.setOrientation(Orientation.VERTICAL);
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
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Error");
        alert.setContentText(message);
        alert.showAndWait();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void addImage(File imageFile) {
        Image image = null;
        try {
            image = new Image(new FileInputStream(imageFile));
        } catch (IOException e) {
            showError("This is not an image", e.getMessage());
        }

        if (image != null) {
            _listChosenImage.add(image);
            _listChosenFile.add(imageFile);

            if(!isListEmpty(_listChosenFile))
                _presenter.onCoverPhotoUpdated(_listChosenFile.get(0));

            _listView.setItems(FXCollections.observableArrayList(_listChosenImage.stream().map(file -> {
                AnchorPane pane = new AnchorPane();
                Rectangle rectangle;
                double ratio = 1;
                if (_listChosenImage.indexOf(file) == 0) {
                    ratio = 1 / (file.getWidth() / (getWindow().getStage().getWidth() - 50));
                    rectangle = new Rectangle(file.getWidth() * ratio, file.getHeight() * ratio);
                } else {
                    if (file.getWidth() > (getWindow().getStage().getWidth() - 50) / 3) {
                        ratio = 1 / (file.getWidth() / ((getWindow().getStage().getWidth() - 50) / 3));
                    }
                    rectangle = new Rectangle(file.getWidth() * ratio, file.getHeight() * ratio);
                }
                rectangle.setFill(new ImagePattern(file));
                pane.getChildren().add(rectangle);
//                pane.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
//                    @Override
//                    public void handle(MouseEvent event) {
//                        System.out.println(imageFile);
//                        _presenter.requestRemoveImage(imageFile);
//                    }
//                });
                return pane;
            }).collect(Collectors.toList())));
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void removeImage(File imageFile) {
        _listChosenImage.remove(imageFile);
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
    public void onNavigateToHashtags() {
        ArrayList<File> listPhotos = new ArrayList<>();
        if (_listChosenFile.size() > 1) {
            for (int i = 1; i <= _listChosenFile.size() - 1; i++) {
                listPhotos.add(_listChosenFile.get(i));
            }
        }
        _presenter.requestToNavigateToHashtags(_listChosenFile.get(0), listPhotos);
    }

    @FXML
    public void onAddImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Chon Hinh");
        File file = fileChooser.showOpenDialog(getWindow().getStage());
        if (file != null) {
            _presenter.pickImage(file);
        }
    }

    @FXML
    public void onRemoveImage() {

    }

    @Override
    protected String fxmlPath() {
        return "/view/create_place/step3.fxml";
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        _listChosenImage = new ArrayList<>();
        _listChosenFile = new ArrayList<>();
    }

    private boolean isListEmpty(List<File> listFile)
    {
        return listFile.size() == 0;
    }
}
