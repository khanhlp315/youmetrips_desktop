package com.phuongkhanh.youmetrips.presentation.components.plandetails;

import com.phuongkhanh.youmetrips.presentation.controls.CommentCell;
import com.phuongkhanh.youmetrips.presentation.framework.FXMLScreen;
import com.phuongkhanh.youmetrips.services.api.models.Comment;
import com.phuongkhanh.youmetrips.services.api.models.PlanDetails;
import com.phuongkhanh.youmetrips.utils.CommonUtils;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import javax.inject.Inject;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class PlanDetailsScreenImpl extends FXMLScreen
        implements PlanDetailsScreen, Initializable {

    PlanDetailsPresenter _presenter;

    @FXML
    private Text _txtUsername;

    @FXML
    private Circle _cirAva;

    @FXML
    private Text _txtPlaceName;

    @FXML
    private ImageView _ivPlaceImage;

    @FXML
    private Text _lblFromDate;

    @FXML
    private Text _lblToDate;

    @FXML
    private Text _lblFromHowLong;

    @FXML
    private Text _lblToHowLong;

    @FXML
    private Label _lblStar;

    @FXML
    private ImageView _mapImage;

    @FXML
    private Text _txtNumOfComments;

    @FXML
    private ListView _lvComments;

    @FXML
    private Circle _cirUserAva;

    @FXML
    private TextField _tfComment;

    @Inject
    public PlanDetailsScreenImpl(PlanDetailsPresenter presenter) {
        _presenter = presenter;
        _presenter.setView(this);
        _presenter.fetchAvatar();
        _presenter.fetchPlanDetails(_presenter.getPlanId());
        _presenter.fetchComments(_presenter.getPlanId());
        _presenter.fetchAvatar();
    }

    @Override
    public void updateMapUrl(String mapUrl) {
        Image image = new Image(mapUrl == null? CommonUtils.getNeutralAvatar(): mapUrl.replace(" ", "%20"), true);
        image.progressProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.doubleValue() == 1.0){
                _mapImage.setImage(image);
            }
        });
    }

    @Override
    public void updateComments(List<Comment> comments) {
        _txtNumOfComments.setText(comments.size() + "");
        _lvComments.setItems(FXCollections.observableArrayList(comments.stream().map(CommentCell::new).collect(Collectors.toList())));
    }

    @Override
    public void updateUserAvatar(String avatar) {
        Image image = new Image(avatar == null? CommonUtils.getNeutralAvatar(): avatar, true);
        image.progressProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.doubleValue() == 1.0){
                _cirUserAva.setFill(new ImagePattern(image));
            }
        });
    }

    @Override
    public void resetCommentTextField() {
        _tfComment.setText("");
    }

    @Override
    public void updatePlanDetails(PlanDetails planDetails) {
        _txtUsername.setText(planDetails.getUserFirstName() + " " + planDetails.getUserLastName());
        _lblFromDate.setText(planDetails.getWhenToGoMin().toString());
        _lblToDate.setText(planDetails.getWhenToGoMax().toString());
        _lblFromHowLong.setText(planDetails.getHowLongMin() + " day(s)");
        _lblToHowLong.setText(planDetails.getHowLongMax() + " day(s)");
        _lblStar.setText(planDetails.getHotelLevel()+ "");
        _txtPlaceName.setText(planDetails.getPlace().getName());

        Image placeImage = new Image(planDetails.getPlace().getCoverImageUrl() == null? CommonUtils.getNeutralAvatar(): planDetails.getPlace().getCoverImageUrl(), true);
        placeImage.progressProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.doubleValue() == 1.0){
                _ivPlaceImage.setImage(placeImage);
            }
        });
        Image avatarImage = new Image(planDetails.getUserAvatarUrl() == null? CommonUtils.getNeutralAvatar(): planDetails.getUserAvatarUrl(), true);
        avatarImage.progressProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.doubleValue() == 1.0){
                _cirUserAva.setFill(new ImagePattern(avatarImage));
            }
        });
    }

    @Override
    protected String fxmlPath() {
        return "/view/home/plan_details_view.fxml";
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void comment(){
        _presenter.postComment(_tfComment.getText(), _presenter.getPlanId());
    }
}
