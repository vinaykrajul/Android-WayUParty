package com.acculytixs.mobile.wayuparty.adapters;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.acculytixs.mobile.wayuparty.R;
import com.acculytixs.mobile.wayuparty.activitys.AddToCartActivity;
import com.acculytixs.mobile.wayuparty.activitys.EventTimeSlotsActivity;
import com.acculytixs.mobile.wayuparty.activitys.VendorEventsActivity;
import com.acculytixs.mobile.wayuparty.application.WUPPreferences;
import com.acculytixs.mobile.wayuparty.application.WayUPartyConstants;
import com.acculytixs.mobile.wayuparty.dtos.EventCategoriesList;
import com.acculytixs.mobile.wayuparty.dtos.EventTimeSlotResponse;
import com.acculytixs.mobile.wayuparty.dtos.GetServiceCategoryList;
import com.acculytixs.mobile.wayuparty.dtos.GetServicesList;
import com.acculytixs.mobile.wayuparty.fragments.PickDateTimeDialogFragment;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;


public class TimeSlotsAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflter;
    LinearLayout linearLayout;
    EventTimeSlotResponse getAllRestaurants;
    String vendorUUID;
    List<String> data= new ArrayList<>();
    List<String> formatdata= new ArrayList<>();

    public TimeSlotsAdapter(Context applicationContext, EventTimeSlotResponse getAllRestaurants,String vendorUUID) {
        this.context = applicationContext;
        this.getAllRestaurants = getAllRestaurants;
        this.vendorUUID = vendorUUID;
        inflter = (LayoutInflater.from(context));
    }

    public TimeSlotsAdapter() {
        super();
    }

    @Override
    public int getCount() {
        return getAllRestaurants.getObject().getTimeSlots().size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.row_time_slot, null);// inflate the layout
        linearLayout = (LinearLayout)view.findViewById(R.id.main_lllayout);
        TextView textView = (TextView)view.findViewById(R.id.tv_time); // get the reference of ImageView
        //icon.setImageResource(logos[i]);
        textView.setText(getAllRestaurants.getObject().getTimeSlots().get(i).getTimeSlot());
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WUPPreferences.saveTimeSlot(getAllRestaurants.getObject().getTimeSlots().get(i).getTimeSlot());
                callVendorEvents(getAllRestaurants.getObject().getEventUUID());
            }
        });

        return view;
    }

    public void callVendorEvents(final String vendorUUID){

        String url = WayUPartyConstants.TEST_URL+WayUPartyConstants.URL_EVENT_CAATEGORIES+"?eventUUID="+vendorUUID;


        try {
            Ion.with(context)
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


                                EventCategoriesList getAllRestaurants = new Gson().fromJson(jsonArrayResponse.getResult(), new TypeToken<EventCategoriesList>() {
                                }.getType());
                                if(getAllRestaurants.getData() != null) {
                                    if (getAllRestaurants.getData().size() > 0) {
                                        data.clear();
                                        formatdata.clear();
                                        for(int i=0;i<getAllRestaurants.getData().size();i++){
                                            data.add(getAllRestaurants.getData().get(i).getCategoryName());
                                            formatdata.add(getAllRestaurants.getData().get(i).getCategoryType());

                                        }
                                        DialogFragment singleChoiceDialog = new PickDateTimeDialogFragment(data,formatdata,"date");
                                        singleChoiceDialog.setCancelable(false);
                                        FragmentManager fm =  ((AppCompatActivity) context).getSupportFragmentManager();
                                        singleChoiceDialog.show(fm, "Single Choice Dialog");

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


}
