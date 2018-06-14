package com.phuongkhanh.youmetrips.presentation.components.trekingplan.trekking_plan_place;

import com.phuongkhanh.youmetrips.presentation.framework.PresenterBase;
import com.phuongkhanh.youmetrips.services.api.models.CreatePlace;
import com.phuongkhanh.youmetrips.services.api.models.CreatePlan;
import com.phuongkhanh.youmetrips.services.api.models.Place;
import com.phuongkhanh.youmetrips.services.api.models.PlaceDetails;
import com.phuongkhanh.youmetrips.services.stores.AuthenticationStore;
import com.phuongkhanh.youmetrips.services.stores.HomeStore;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class TrekkingPlanPlacePresenter extends PresenterBase<TrekkingPlanPlaceScreen> {

    private final TrekkingPlanPlaceService _service;

    List<Place> _places;

    String _searchText;

    @Inject
    public TrekkingPlanPlacePresenter(TrekkingPlanPlaceService service) {
        _service = service;
        _places = new ArrayList<Place>();
    }

    public void fetchPlaces() {
        assert (getView() != null);

        Task<List<Place>> task = new Task<List<Place>>() {
            @Override
            protected List<Place> call() {
                return _doFetchPlaces();
            }

            @Override
            protected void failed() {
                _onFetchPlacesFailed(getException());
            }
        };
        task.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, event -> {
            List<Place> places = task.getValue();
            _onFetchPlacesSucceeded(places);
        });
        new Thread(task).start();
    }

    private List<Place> _doFetchPlaces() {
        AuthenticationStore authenticationStore = _service.getAuthenticationStore();

        _places = _service.fetchPlaces(authenticationStore.getUserId(), authenticationStore.getJwt());
        return _places;
    }

    public void onSearchTextChanged(String text) {
        assert (getView() != null);

        _searchText = text;
        getView().updatePlaces(_filterPlaces());
    }

    private List<Place> _filterPlaces() {
        if (_searchText == null || _searchText.trim() == "") {
            return null;
        }
        List<Place> filteredPlaces = new ArrayList<Place>();

        for (Place place : _places) {
            if (place.getName().toLowerCase().trim().contains(_searchText.toLowerCase().trim())) {
                filteredPlaces.add(place);
            }
        }
        return filteredPlaces;
    }

    private void _onFetchPlacesSucceeded(List<Place> places) {
        getView().updatePlaces(_filterPlaces());
    }

    private void _onFetchPlacesFailed(Throwable throwable) {

    }

    public void requestNavigateToCreateTrekkingPlace() {
        assert (getView() != null);

        getView().navigateToCreateTrekkingPlace();
    }

    public void requestNavigateToTime()
    {
        assert (getView() != null);
        HomeStore homeStore = _service.getHomeStore();
        CreatePlan plan;
        if(homeStore.getCreatePlan() == null)
            plan = new CreatePlan();
        else plan = homeStore.getCreatePlan();
        plan.setPlaceId(0);
        homeStore.storeCreatePlan(plan);
        getView().navigateToTime();
    }

    public void onSelectedPlaceUpdated(Place place) {
        assert (getView() != null);

        if (_isValidPlace(place)) {
            getView().showContinue();
        } else {
            getView().hideContinue();
        }

        if (place == null) {
            return;
        }

        getView().updateMapUrl(_service.getMapUrl(null));

        HomeStore homeStore = _service.getHomeStore();

        PlaceDetails placeDetails = homeStore.getPlaceDetails(place.getId());

        if (placeDetails != null) {
            getView().updateMapUrl(_service.getMapUrl(placeDetails.getLocation()));
            return;
        }

        Task<String> task = new Task<String>() {
            @Override
            protected String call() {
                return _doGetLocation(place.getId());
            }

            @Override
            protected void failed() {
                _onGetLocationFailed(getException());
            }
        };
        task.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, event -> {
            String location = task.getValue();
            _onGetLocationSucceeded(location);
        });
        new Thread(task).start();
    }

    private String _doGetLocation(int placeId) {
        AuthenticationStore authenticationStore = _service.getAuthenticationStore();
        return _service.getPlaceDetails(authenticationStore.getUserId(), placeId, authenticationStore.getJwt()).getLocation();
    }

    private void _onGetLocationSucceeded(String location) {
        getView().updateMapUrl(_service.getMapUrl(location));
    }

    private void _onGetLocationFailed(Throwable throwable) {
    }

    private boolean _isValidPlace(Place place) {
        return place != null;
    }
}
