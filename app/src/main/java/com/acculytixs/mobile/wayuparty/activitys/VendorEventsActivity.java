package com.acculytixs.mobile.wayuparty.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.acculytixs.mobile.wayuparty.R;
import com.acculytixs.mobile.wayuparty.adapters.RestaurantsAdapter;
import com.acculytixs.mobile.wayuparty.adapters.VendorEventsAdapter;
import com.acculytixs.mobile.wayuparty.application.ProfileImage;
import com.acculytixs.mobile.wayuparty.application.WUPPreferences;
import com.acculytixs.mobile.wayuparty.application.WayUPartyConstants;
import com.acculytixs.mobile.wayuparty.dtos.GetAllRestaurants;
import com.acculytixs.mobile.wayuparty.dtos.LoginRegisteredUserResponse;
import com.acculytixs.mobile.wayuparty.dtos.LoginRequest;
import com.acculytixs.mobile.wayuparty.dtos.VendorEventsResonse;
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

public class VendorEventsActivity extends AppCompatActivity implements View.OnClickListener {
    ProgressBar progressBar;
    RecyclerView restaurant_rv;
    LinearLayoutManager linearLayoutManager;
    VendorEventsAdapter vendorEventsAdapter;
    String vendorUUID;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_events);
        Toolbar toolbar = findViewById(R.id.toolbar);;
        toolbar.setTitle("Events");
        vendorUUID = getIntent().getStringExtra("vendorUUID");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        inIt();
        callVendorEvents(vendorUUID);
    }

    public void callVendorEvents(String vendorUUID){

        String url = WayUPartyConstants.TEST_URL+WayUPartyConstants.URL_VENDOR_EVENTS+"?vendorUUID="+vendorUUID;


        try {
            Ion.with(this)
                    .load("GET",url)
                    .setLogging("jananetha", Log.DEBUG)
                    .followRedirect(false)
                    .addHeader("X-Username", WUPPreferences.getUserName())
                    .addHeader("X-Password", WUPPreferences.getPassword())
                    .as(new TypeToken<JsonObject>() {
                    })
                    .withResponse()
                    .setCallback(new FutureCallback<Response<JsonObject>>() {
                        @Override
                        public void onCompleted(Exception e, Response<JsonObject> jsonArrayResponse) {
                            try {
                                String jsonString = new com.google.gson.Gson().toJson(jsonArrayResponse);
                                //NewsFeed newsFeed = jsonString;
                                 Log.i("response",jsonString);
                                VendorEventsResonse getAllRestaurants = new Gson().fromJson(jsonArrayResponse.getResult(), new TypeToken<VendorEventsResonse>() {
                                }.getType());
                                progressBar.setVisibility(View.GONE);
                                if(getAllRestaurants.getData() != null) {
                                    if (getAllRestaurants.getData().size() > 0) {
                                        vendorEventsAdapter = new VendorEventsAdapter(VendorEventsActivity.this,getAllRestaurants);
                                        restaurant_rv.setAdapter(vendorEventsAdapter);
                                        Log.d("dbdata","count"+getAllRestaurants.getData().size());
                                    }
                                }else {
                                    progressBar.setVisibility(View.GONE);
                                }

                            } catch (Exception e1) {
                                e1.printStackTrace();

                            }


                        }
                    });
        } catch (Exception e) {

        }
    }
    public void inIt(){

        progressBar =(ProgressBar)findViewById(R.id.main_progress);
        restaurant_rv = (RecyclerView)findViewById(R.id.rv_events);
        linearLayoutManager = new LinearLayoutManager(VendorEventsActivity.this,LinearLayoutManager.VERTICAL,false);
        restaurant_rv.setLayoutManager(linearLayoutManager);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btn_login:
                Intent intent = new Intent(VendorEventsActivity.this,LoginAcitvity.class);
                startActivity(intent);
                finish();
                break;
        }

    }


}
