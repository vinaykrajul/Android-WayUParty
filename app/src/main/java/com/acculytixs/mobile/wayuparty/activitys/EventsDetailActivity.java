package com.acculytixs.mobile.wayuparty.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.acculytixs.mobile.wayuparty.R;
import com.acculytixs.mobile.wayuparty.adapters.VendorEventsAdapter;
import com.acculytixs.mobile.wayuparty.application.WUPPreferences;
import com.acculytixs.mobile.wayuparty.application.WayUPartyConstants;
import com.acculytixs.mobile.wayuparty.dtos.EventDetailsResponse;
import com.acculytixs.mobile.wayuparty.dtos.VendorEventsResonse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;
import com.squareup.picasso.Picasso;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class EventsDetailActivity extends AppCompatActivity implements View.OnClickListener {
    ProgressBar progressBar;
    String eventUUID;
    TextView tv_timimgs,tv_address,tv_music,tv_hours,tv_launguage,tv_age,tv_book,tv_amount,tv_event_typ;
    ImageView im_banner;
    LinearLayout ll_caart_item;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_details_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);;
        toolbar.setTitle("Event Details");
        eventUUID = getIntent().getStringExtra("eventUUID");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        inIt();
        callVendorEvents(eventUUID);
    }

    public void callVendorEvents(String vendorUUID){

        String url = WayUPartyConstants.TEST_URL+WayUPartyConstants.URL_EVENT_DETAILS+"?eventUUID="+vendorUUID;


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
                                EventDetailsResponse getAllRestaurants = new Gson().fromJson(jsonArrayResponse.getResult(), new TypeToken<EventDetailsResponse>() {
                                }.getType());
                                progressBar.setVisibility(View.GONE);
                                ll_caart_item.setVisibility(View.VISIBLE);
                                setData(getAllRestaurants);


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
        ll_caart_item =(LinearLayout) findViewById(R.id.ll_cart_item);
        tv_address =(TextView)findViewById(R.id.tv_location);
        tv_music =(TextView)findViewById(R.id.tv_music);
        tv_hours =(TextView)findViewById(R.id.tv_hours);
        tv_launguage =(TextView)findViewById(R.id.tv_language);
        tv_age =(TextView)findViewById(R.id.tv_age);
        tv_amount =(TextView)findViewById(R.id.tv_amount);
        tv_event_typ =(TextView)findViewById(R.id.tv_event_type);
        im_banner =(ImageView)findViewById(R.id.iv_event_baneer);
        tv_book =(TextView)findViewById(R.id.tv_book);
        tv_book.setOnClickListener(this);



    }
    public void setData(EventDetailsResponse getAllRestaurants){

        tv_address.setText(getAllRestaurants.getObject().getEventHost()+","+getAllRestaurants.getObject().getAddress());
        tv_music.setText(getAllRestaurants.getObject().getMusicType());
        tv_hours.setText(getAllRestaurants.getObject().getDuration());
        tv_launguage.setText(getAllRestaurants.getObject().getLanguage());
        tv_age.setText(getAllRestaurants.getObject().getAge());
        tv_event_typ.setText(getAllRestaurants.getObject().getEventType());
        tv_event_typ.setSelected(true);
        tv_amount.setText(getAllRestaurants.getObject().getMinimumStartingAmount());
        if (!TextUtils.isEmpty(getAllRestaurants.getObject().getEventImage())) {
            Log.d("imagaeurl", "" + getAllRestaurants.getObject().getEventImage());
            String url = WayUPartyConstants.TEST_URL+getAllRestaurants.getObject().getEventImage();
            Picasso.with(EventsDetailActivity.this)
                    .load(url)
                    .into(im_banner);
        } else {
            //viewHolder.imageView.setImageResource(R.drawable.sample_image);
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.tv_book:
                Intent intent = new Intent(EventsDetailActivity.this,EventTimeSlotsActivity.class);
              intent.putExtra("eventUUID",eventUUID);
              startActivity(intent);
                break;
        }

    }

}
