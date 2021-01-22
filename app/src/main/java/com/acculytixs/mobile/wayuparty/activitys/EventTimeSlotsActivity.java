package com.acculytixs.mobile.wayuparty.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.acculytixs.mobile.wayuparty.R;
import com.acculytixs.mobile.wayuparty.adapters.ServicesGridAdapter;
import com.acculytixs.mobile.wayuparty.adapters.TimeSlotsAdapter;
import com.acculytixs.mobile.wayuparty.application.WUPPreferences;
import com.acculytixs.mobile.wayuparty.application.WayUPartyConstants;
import com.acculytixs.mobile.wayuparty.dtos.EventDetailsResponse;
import com.acculytixs.mobile.wayuparty.dtos.EventTicketList;
import com.acculytixs.mobile.wayuparty.dtos.EventTimeSlotResponse;
import com.acculytixs.mobile.wayuparty.fragments.PickDateTimeDialogFragment;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class EventTimeSlotsActivity extends AppCompatActivity implements View.OnClickListener, PickDateTimeDialogFragment.SingleChoiceListener {
    ProgressBar progressBar;
     String eventUUID = "";
    TextView tv_book,tv_amount,tv_event_typ;
    ImageView im_banner;
    LinearLayout ll_caart_item;
    GridView gridView;

    public EventTimeSlotsActivity() {
        super();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_time_slot_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);;
        toolbar.setTitle("Event Time Slots");
        eventUUID = getIntent().getStringExtra("eventUUID");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        inIt();
        callVendorEvents(eventUUID);
    }

    public void callVendorEvents(final String vendorUUID){

        String url = WayUPartyConstants.TEST_URL+WayUPartyConstants.URL_EVENT_TIME_SLOTS+"?eventUUID="+vendorUUID;


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
                                EventTimeSlotResponse getAllRestaurants = new Gson().fromJson(jsonArrayResponse.getResult(), new TypeToken<EventTimeSlotResponse>() {
                                }.getType());
                                progressBar.setVisibility(View.GONE);
                                ll_caart_item.setVisibility(View.VISIBLE);
                                setData(getAllRestaurants);
                                if(getAllRestaurants.getObject().getTimeSlots() != null) {
                                    if (getAllRestaurants.getObject().getTimeSlots().size() > 0) {
                                        TimeSlotsAdapter customAdapter = new TimeSlotsAdapter(EventTimeSlotsActivity.this,getAllRestaurants,vendorUUID);
                                        gridView.setAdapter(customAdapter);
                                        // Log.d("dbdata","count"+getAllRestaurants.getData().size());
                                    }
                                }
                                //setData(getAllRestaurants);


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
        tv_event_typ =(TextView)findViewById(R.id.tv_event_type);
        gridView =(GridView) findViewById(R.id.gridview_timslot);



    }
    public void setData(EventTimeSlotResponse getAllRestaurants){

        tv_event_typ.setText(getAllRestaurants.getObject().getEventDate());

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


                break;
        }

    }

    @Override
    public void onPositiveButtonClicked(List<String> datun, List<String> formatdata, int position, String name) {
          callEventTickets(eventUUID,formatdata.get(position));
    }

    @Override
    public void onNegativeButtonClicked() {

    }

    public void callEventTickets(final String eventUUID,final String categoryType ){

        String url = WayUPartyConstants.TEST_URL+WayUPartyConstants.URL_EVENT_TICKETS+"?eventUUID="+eventUUID+"&categoryType="+categoryType;


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
                              //  EventTimeSlotResponse getAllRestaurants = new Gson().fromJson(jsonArrayResponse.getResult(), new TypeToken<EventTimeSlotResponse>() {
                                //setData(getAllRestaurants);
                                EventTicketList getAllRestaurants = new Gson().fromJson(jsonArrayResponse.getResult(), new TypeToken<EventTicketList>() {
                                }.getType());
                                Intent intent = new Intent(EventTimeSlotsActivity.this,EventTicketListActivity.class);
                                intent.putExtra("ticketList",getAllRestaurants);
                                intent.putExtra("eventUUID",eventUUID);
                                intent.putExtra("categoryType",categoryType);
                                startActivity(intent);

                            } catch (Exception e1) {
                                e1.printStackTrace();

                            }


                        }
                    });
        } catch (Exception e) {

        }
    }
}
