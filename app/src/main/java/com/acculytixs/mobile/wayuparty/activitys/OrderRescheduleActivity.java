package com.acculytixs.mobile.wayuparty.activitys;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.acculytixs.mobile.wayuparty.R;
import com.acculytixs.mobile.wayuparty.adapters.OrderDetailsAdapter;
import com.acculytixs.mobile.wayuparty.application.WUPPreferences;
import com.acculytixs.mobile.wayuparty.application.WayUPartyConstants;
import com.acculytixs.mobile.wayuparty.dtos.DataOrderList;
import com.acculytixs.mobile.wayuparty.dtos.GetRescheduleDetailResponse;
import com.acculytixs.mobile.wayuparty.dtos.RescheduleRequest;
import com.acculytixs.mobile.wayuparty.fragments.PickDateTimeDialogFragment;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class OrderRescheduleActivity  extends AppCompatActivity implements View.OnClickListener,PickDateTimeDialogFragment.SingleChoiceListener{

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    OrderDetailsAdapter qaAadapter;
    String datum;
    TextView tv_pick_date,tv_pick_time;
    LinearLayout ll_pick_date,ll_pick_time;
    List<String> data= new ArrayList<>();
    List<String> formatdata= new ArrayList<>();
    GetRescheduleDetailResponse getRescheduleDetailResponse;
    String date,time;
    LinearLayout linearLayout,ll_reschcedule;
    ProgressBar progressBar;

    public OrderRescheduleActivity() {
        super();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reschedule_order_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        ll_pick_date = findViewById(R.id.ll_pick_date);
        tv_pick_date = findViewById(R.id.tv_pick_date);
        tv_pick_time = findViewById(R.id.tv_pick_time);
        ll_pick_time = findViewById(R.id.ll_pick_time);
        linearLayout = findViewById(R.id.ll_cart_list);
        ll_reschcedule = findViewById(R.id.tv_reschedule);
        ll_reschcedule.setOnClickListener(this);
        progressBar = findViewById(R.id.main_progress);
        ll_pick_date.setOnClickListener(this);
        ll_pick_time.setOnClickListener(this);
        datum =  getIntent().getStringExtra("orderUUID");
        toolbar.setTitle("Reschedule Order");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        rescheduleOrder(datum);

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void rescheduleOrder(String UUID){
        final String url = WayUPartyConstants.TEST_URL+WayUPartyConstants.URL_GET_RESCHEDULE_DETAILS+"?orderUUID="+UUID;


        Log.d("paramscheck", "" + url);

        Ion.with(OrderRescheduleActivity.this)
                .load("GET",url)
                .setLogging("jananetha", Log.DEBUG)
                .followRedirect(false)
                //.progressDialog(jananethaApplication.showProgress())
                .addHeader("X-Username", WUPPreferences.getUserName())
                .addHeader("X-Password", WUPPreferences.getPassword())
                .as(new TypeToken<JsonObject>() {
                }).withResponse()
                .setCallback(new FutureCallback<Response<JsonObject>>() {
                    @Override
                    public void onCompleted(Exception e, Response<JsonObject> result) {
                        try {

                            String jsonString = new com.google.gson.Gson().toJson(result);
                            Log.d("reschedule response",jsonString);
                             getRescheduleDetailResponse = new Gson().fromJson(result.getResult(), new TypeToken<GetRescheduleDetailResponse>() {
                            }.getType());
                            Log.d("reschedule response",getRescheduleDetailResponse.getObject().getTimeSlotList().size()+"");
                             linearLayout.setVisibility(View.VISIBLE);
                             progressBar.setVisibility(View.GONE);
                        }catch (Exception t){
                            t.printStackTrace();
                        }
                    }
                });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ll_pick_date:
                data.clear();
                formatdata.clear();
                for(int i=0;i<getRescheduleDetailResponse.getObject().getServiceDates().size();i++){
                    data.add(getRescheduleDetailResponse.getObject().getServiceDates().get(i).getServiceDate());
                    formatdata.add(getRescheduleDetailResponse.getObject().getServiceDates().get(i).getPassableDate());

                }
                DialogFragment singleChoiceDialog = new PickDateTimeDialogFragment(data,formatdata,"date");
                singleChoiceDialog.setCancelable(false);
                FragmentManager fm = (OrderRescheduleActivity.this).getSupportFragmentManager();
                singleChoiceDialog.show(fm, "Single Choice Dialog");
                break;
            case R.id.ll_pick_time:
                data.clear();
                formatdata.clear();
                for(int i=0;i<getRescheduleDetailResponse.getObject().getTimeSlotList().size();i++){
                    data.add(getRescheduleDetailResponse.getObject().getTimeSlotList().get(i).getTimeSlot());
                    formatdata.add(getRescheduleDetailResponse.getObject().getTimeSlotList().get(i).getTimeSlot());

                }
                DialogFragment singleChoiceDialog1 = new PickDateTimeDialogFragment(data,formatdata,"time");
                singleChoiceDialog1.setCancelable(false);
                FragmentManager fm1 = (OrderRescheduleActivity.this).getSupportFragmentManager();
                singleChoiceDialog1.show(fm1, "Single Choice Dialog");
                break;
            case R.id.tv_reschedule:
               validateDateAndTime();
        }
    }

    @Override
    public void onPositiveButtonClicked(List<String> datun, List<String> formatdata, int position, String name) {
        switch (name){
            case "time":
                tv_pick_time.setText(data.get(position));
                time = formatdata.get(position);
                break;
            case "date":
                tv_pick_date.setText(data.get(position));
                date = formatdata.get(position);
                break;


        }
    }

    @Override
    public void onNegativeButtonClicked() {

    }

    public void callRescheduleService(){
        final String url = WayUPartyConstants.TEST_URL+WayUPartyConstants.URL_RESCHEDULE_ORDER;

        RescheduleRequest rescheduleRequest = new RescheduleRequest();
        rescheduleRequest.setOrderUUID(datum);
        rescheduleRequest.setServiceOrderDate(date);
        rescheduleRequest.setServiceTimeSlot(time);
        Log.d("paramscheck", "" + url);

        Ion.with(OrderRescheduleActivity.this)
                .load("POST",url)
                .setLogging("jananetha", Log.DEBUG)
                .followRedirect(false)
                //.progressDialog(jananethaApplication.showProgress())
                .addHeader("X-Username", WUPPreferences.getUserName())
                .addHeader("X-Password", WUPPreferences.getPassword())
                .setJsonPojoBody(rescheduleRequest)
                .as(new TypeToken<JsonObject>() {
                }).withResponse()
                .setCallback(new FutureCallback<Response<JsonObject>>() {
                    @Override
                    public void onCompleted(Exception e, Response<JsonObject> result) {
                        try {

                            String jsonString = new com.google.gson.Gson().toJson(result);
                            Log.d("reschedule response",jsonString);

                            Intent intent = new Intent(OrderRescheduleActivity.this, MainActivity.class);
                            intent.putExtra("fragment","orders");
                            startActivity(intent);
                             finish();
                        }catch (Exception t){
                            t.printStackTrace();
                        }
                    }
                });
    }

    public void validateDateAndTime(){
        if(tv_pick_date.getText().equals("Pick a Date")||tv_pick_time.getText().equals("Pick a Time Slot")){
            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

            alertDialogBuilder.setMessage("Select date and time to proceed");
            alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            final AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
            final Button positiveButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
            LinearLayout.LayoutParams positiveButtonLL = (LinearLayout.LayoutParams) positiveButton.getLayoutParams();
            positiveButtonLL.gravity = Gravity.CENTER;
            positiveButton.setLayoutParams(positiveButtonLL);
        }else {
            callRescheduleService();
            }
    }
}
