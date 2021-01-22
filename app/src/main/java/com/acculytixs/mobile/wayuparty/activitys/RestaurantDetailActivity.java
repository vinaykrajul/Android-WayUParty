package com.acculytixs.mobile.wayuparty.activitys;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.acculytixs.mobile.wayuparty.R;
import com.acculytixs.mobile.wayuparty.adapters.RSPager;
import com.acculytixs.mobile.wayuparty.adapters.RestaurantDetailsAdapter;
import com.acculytixs.mobile.wayuparty.adapters.RestaurantsAdapter;
import com.acculytixs.mobile.wayuparty.application.WayUPartyConstants;
import com.acculytixs.mobile.wayuparty.dtos.Datum;
import com.acculytixs.mobile.wayuparty.dtos.GetAllRestaurants;
import com.acculytixs.mobile.wayuparty.dtos.GetVendorInfo;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import me.relex.circleindicator.CircleIndicator;

public class RestaurantDetailActivity  extends AppCompatActivity {

    RSPager myPager;
    ViewPager viewPager;
    CircleIndicator circleIndicator;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    RestaurantDetailsAdapter qaAadapter;
    Datum datum;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        datum = (Datum) getIntent().getSerializableExtra("RestData");
        toolbar.setTitle(datum.getVendorName());
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimary));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        viewPager = findViewById(R.id.view_pager);
        circleIndicator = findViewById(R.id.circle);
        recyclerView = (RecyclerView)findViewById(R.id.rv_qa);
        linearLayoutManager = new LinearLayoutManager(RestaurantDetailActivity.this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        getVendorInfo();



    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void getVendorInfo(){
        final String url = WayUPartyConstants.TEST_URL+WayUPartyConstants.URL_GETVEDORS_INFO+"?vendorUUID="+datum.getVendorUUID();
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
                            GetVendorInfo getVendorInfo = new Gson().fromJson(result.getResult(), new TypeToken<GetVendorInfo>() {
                            }.getType());
                            //progressBar.setVisibility(View.GONE);

                                Log.d("dbdata","count"+getVendorInfo.getObject().getState());
                                myPager = new RSPager(RestaurantDetailActivity.this,getVendorInfo);
                                viewPager.setAdapter(myPager);
                                circleIndicator.setViewPager(viewPager);
                                qaAadapter = new RestaurantDetailsAdapter(RestaurantDetailActivity.this,getVendorInfo);
                                recyclerView.setAdapter(qaAadapter);
                            //loadDataFromLocal();
                        }catch (Exception t){
                            t.printStackTrace();
                        }
                    }
                });
    }
}
