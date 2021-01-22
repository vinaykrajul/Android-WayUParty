package com.acculytixs.mobile.wayuparty.activitys;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.acculytixs.mobile.wayuparty.R;
import com.acculytixs.mobile.wayuparty.adapters.OrderListAdapter;
import com.acculytixs.mobile.wayuparty.application.WUPPreferences;
import com.acculytixs.mobile.wayuparty.application.WayUPartyConstants;
import com.acculytixs.mobile.wayuparty.dtos.UserOrderListResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class TermsAndConditionsAcitivity  extends AppCompatActivity {

 String vendorUUID;
 TextView tvtittle;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);
        Toolbar toolbar = findViewById(R.id.toolbar);
        tvtittle = findViewById(R.id.tv_category_tittle);
        vendorUUID = getIntent().getStringExtra("vendorUUID");
        toolbar.setTitle("Terms And Conditions");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getUserOrderList();
    }


    private void getUserOrderList() {

        final String url = WayUPartyConstants.TEST_URL+WayUPartyConstants.URL_TERMS_CONDITIONS+"?vendorUUID="+vendorUUID;


        Log.d("paramscheck", "" + url);

        Ion.with(this)
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
                            Log.d("jsonstring",jsonString);
                            // progressBar.setVisibility(View.GONE);
                            //Log.d("jsonstring",getCartList.getData().size()+"");

                        }catch (Exception t){
                            t.printStackTrace();

                        }
                    }
                });
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
