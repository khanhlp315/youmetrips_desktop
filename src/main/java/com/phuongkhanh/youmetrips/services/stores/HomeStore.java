package com.phuongkhanh.youmetrips.services.stores;

import com.phuongkhanh.youmetrips.services.api.models.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class HomeStore {
   private static final String PLACES = "PLACES";
   private static final String RELEVANT_PLANS = "RELEVANT_PLANS";
   private static final String PLAN_DETAILS = "PLAN_DETAILS";
   private static final String PLACE_DETAILS = "PLACE_DETAILS";
   private static final String FRIEND_REQUESTS = "FRIEND_REQUESTS";
   private static final String FRIENDS = "FRIENDS";
   private static final String COUNTRIES = "COUNTRIES";
   private static final String COMMENTS = "COMMENTS";
   private static final String CREATING_PLACE = "CREATING_PLACE";
   private Store _store;


    @SuppressWarnings("unchecked")
    public HomeStore()
   {
       _store = new Store();
       _store.setItem(PLACES, null);
       _store.setItem(RELEVANT_PLANS, null);
       _store.setItem(FRIEND_REQUESTS, null);
       _store.setItem(FRIENDS, null);
       _store.setItem(COUNTRIES, null);
       _store.setItem(PLAN_DETAILS, new HashMap<Integer, PlanDetails>());
       _store.setItem(PLACE_DETAILS, new HashMap<Integer, PlaceDetails>());
       _store.setItem(COMMENTS, new HashMap<Integer, List<Comment>>());
       _store.setItem(CREATING_PLACE, new CreatePlace());
   }
    @SuppressWarnings("unchecked")
    public void storePlans(List<RelevantPlan> plans){
        _store.setItem(RELEVANT_PLANS, plans);
    }

    @SuppressWarnings("unchecked")
    public void storePlaces(List<Place> places){
        _store.setItem(PLACES, places);
    }

    @SuppressWarnings("unchecked")
    public void storeFriendRequests(List<FriendRequest> requests) {
        _store.setItem(FRIEND_REQUESTS, requests);
    }

    @SuppressWarnings("unchecked")
    public void storeFriends(List<Friend> friends) {
        _store.setItem(FRIENDS, friends);
    }

    @SuppressWarnings("unchecked")
    public void storeCountries(List<Country> countries) {
        _store.setItem(COUNTRIES, countries);
    }

    public void storeCreatePlace(CreatePlace place){
        _store.setItem(CREATING_PLACE, place);
    }

    @SuppressWarnings("unchecked")
    public void addPlaceDetails(PlaceDetails placeDetails){
        HashMap map = (HashMap)_store.getItem(PLACE_DETAILS);
        map.replace(placeDetails.getId(), placeDetails);
    }

    @SuppressWarnings("unchecked")
    public void addPlanDetails(PlanDetails planDetails){
        HashMap map = (HashMap)_store.getItem(PLAN_DETAILS);
        map.replace(planDetails.getId(), planDetails);
    }

    @SuppressWarnings("unchecked")
    public void storeComments(List<Comment> comments, int planId){
        HashMap map = (HashMap)_store.getItem(COMMENTS);
        map.replace(planId, comments);
    }

    public PlaceDetails getPlaceDetails(int id){
        HashMap map = (HashMap)_store.getItem(PLACE_DETAILS);

        if(!map.containsKey(id)){
            return null;
        }
        else {
            return (PlaceDetails) map.get(id);
        }
    }

    public PlanDetails getPlanDetails(int id){
        HashMap map = (HashMap)_store.getItem(PLAN_DETAILS);

        if(!map.containsKey(id)){
            return null;
        }
        else {
            return (PlanDetails) map.get(id);
        }
    }

    public CreatePlace getCreatePlace()
    {
        return  (CreatePlace)_store.getItem(CREATING_PLACE);
    }

    @SuppressWarnings("unchecked")
    public List<Comment> getComments(int planId){
        HashMap map = (HashMap)_store.getItem(COMMENTS);

        if(!map.containsKey(planId)){
            return null;
        }
        else {
            return (List<Comment>) map.get(planId);
        }
    }

    @SuppressWarnings("unchecked")
    public List<Place> getAllPlaces(){
        ArrayList<Place> places = (ArrayList<Place>)_store.getItem(PLACES);
        if(places == null){
            return null;
        }
        return (ArrayList<Place>)places.clone();
    }

    @SuppressWarnings("unchecked")
    public List<RelevantPlan> getAllRelevantPlans(){
        ArrayList<RelevantPlan> relevantPlans = (ArrayList<RelevantPlan>)_store.getItem(RELEVANT_PLANS);
        if(relevantPlans == null){
            return null;
        }
        return (ArrayList<RelevantPlan>) relevantPlans.clone();
    }

    @SuppressWarnings("unchecked")
    public List<FriendRequest> getAllFriendRequests() {
        ArrayList<FriendRequest> friendRequests = (ArrayList<FriendRequest>)_store.getItem(FRIEND_REQUESTS);
        if(friendRequests == null){
            return null;
        }
        return (ArrayList<FriendRequest>) friendRequests.clone();
    }

    @SuppressWarnings("unchecked")
    public List<Country> getAllCountries() {
        ArrayList<Country> countries = (ArrayList<Country>)_store.getItem(COUNTRIES);
        if(countries == null){
            return null;
        }
        return (ArrayList<Country>) countries.clone();
    }

    @SuppressWarnings("unchecked")
    public void removeRequest(int fromId){
        List<FriendRequest> requests =  (List<FriendRequest>)_store.getItem(FRIEND_REQUESTS);
        requests.removeIf(request -> request.getUserId() == fromId);
    }

    @SuppressWarnings("unchecked")
    public List<Friend> getAllFriends() {
        ArrayList<Friend> friends = (ArrayList<Friend>)_store.getItem(FRIENDS);
        if(friends == null){
            return null;
        }
        return (ArrayList<Friend>) friends.clone() ;
    }

    public void clearData() {
        _store.setItem(PLACES, null);
        _store.setItem(RELEVANT_PLANS, null);
        _store.setItem(FRIEND_REQUESTS, null);
        _store.setItem(FRIENDS, null);
        _store.setItem(COUNTRIES, null);
        _store.setItem(PLACE_DETAILS, new HashMap<Integer, PlaceDetails>());
        _store.setItem(PLAN_DETAILS, new HashMap<Integer, PlanDetails>());
        _store.setItem(COMMENTS, new HashMap<Integer, List<Comment>>());
    }
}
