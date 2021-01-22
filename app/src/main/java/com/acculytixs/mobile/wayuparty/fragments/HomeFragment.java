package com.acculytixs.mobile.wayuparty.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.acculytixs.mobile.wayuparty.R;
import com.acculytixs.mobile.wayuparty.adapters.RestaurantsAdapter;
import com.acculytixs.mobile.wayuparty.application.WayUPartyConstants;
import com.acculytixs.mobile.wayuparty.dtos.Datum;
import com.acculytixs.mobile.wayuparty.dtos.GetAllRestaurants;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HomeFragment extends Fragment {

    ProgressBar progressBar;
    RecyclerView restaurant_rv;
    LinearLayoutManager linearLayoutManager;
    RestaurantsAdapter restaurantsAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        progressBar =(ProgressBar)view.findViewById(R.id.main_progress);
        restaurant_rv = (RecyclerView)view.findViewById(R.id.rv_restuarants);
        linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        restaurant_rv.setLayoutManager(linearLayoutManager);
        getAllRegisteredRestaurants();
        return view;
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public void getAllRegisteredRestaurants(){
        final String url = WayUPartyConstants.TEST_URL+WayUPartyConstants.URL_GETALLREGISTERED_RESTAURANTSLIST+"?offset="+WayUPartyConstants.OFFSET+"&"+"limit="+WayUPartyConstants.LIMIT;
        Ion.with(this)
                .load("GET",url)
                .setLogging("jananetha", Log.DEBUG)
                .followRedirect(false)
                .as(new TypeToken<JsonObject>() {
        }).withResponse()
                .setCallback(new FutureCallback<Response<JsonObject>>() {
                    @Override
                    public void onCompleted(Exception e, Response<JsonObject> result) {
                        try {
                            String jsonString = new com.google.gson.Gson().toJson(result);
                            Log.d("jsonstring",jsonString);
                            GetAllRestaurants getAllRestaurants = new Gson().fromJson(result.getResult(), new TypeToken<GetAllRestaurants>() {
                            }.getType());
                            progressBar.setVisibility(View.GONE);
                            if(getAllRestaurants.getData() != null) {
                                if (getAllRestaurants.getData().size() > 0) {
                                    restaurantsAdapter = new RestaurantsAdapter(getActivity(),getAllRestaurants);
                                    restaurant_rv.setAdapter(restaurantsAdapter);
                                    for(int i =0;i<getAllRestaurants.getData().size();i++){
                                        //contactArrayList.add(departmentSubCategories.getData().get(i));
                                        //Log.d("check valus",getAllRestaurants.getData().get(i).getOfficerName()+departmentSubCategories.getData().get(i).getContactType());
                                    }
                                    Log.d("dbdata","count"+getAllRestaurants.getData().size());
                                }
                            }
                            //loadDataFromLocal();
                        }catch (Exception t){
                            t.printStackTrace();
                        }
                    }
                });
    }

}
