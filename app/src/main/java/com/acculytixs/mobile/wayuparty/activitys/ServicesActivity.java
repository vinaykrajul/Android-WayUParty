package com.acculytixs.mobile.wayuparty.activitys;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.acculytixs.mobile.wayuparty.R;
import com.acculytixs.mobile.wayuparty.adapters.RestaurantsAdapter;
import com.acculytixs.mobile.wayuparty.adapters.ServicesGridAdapter;
import com.acculytixs.mobile.wayuparty.application.WUPPreferences;
import com.acculytixs.mobile.wayuparty.application.WayUPartyConstants;
import com.acculytixs.mobile.wayuparty.dtos.Datum;
import com.acculytixs.mobile.wayuparty.dtos.GetAllRestaurants;
import com.acculytixs.mobile.wayuparty.dtos.GetCategoryServiceList;
import com.acculytixs.mobile.wayuparty.dtos.GetServiceCategoryList;
import com.acculytixs.mobile.wayuparty.dtos.GetServicesList;
import com.acculytixs.mobile.wayuparty.fragments.SingleChoiceDialogFragment;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import static android.provider.Settings.Global.AIRPLANE_MODE_ON;

public class ServicesActivity extends AppCompatActivity implements SingleChoiceDialogFragment.SingleChoiceListener {
    GridView simpleGrid;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Name = "nameKey";
    SharedPreferences sharedpreferences;
    String vendorUUID;
    ProgressBar progressBar;
    private static final int MY_PERMISSIONS_REQUEST_FINE_LOCATION = 111;
    private static final int REQUEST_LOCATION = 1;

    LocationManager locationManager;
    String latitude, longitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        Toolbar toolbar = findViewById(R.id.toolbar);
        progressBar = findViewById(R.id.main_progress);
        toolbar.setTitle("Services");
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimary));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        vendorUUID = getIntent().getStringExtra("vendorUUID");
        callServicesByVendorUUID(vendorUUID);
        simpleGrid = (GridView) findViewById(R.id.gridview_dashboard);

        // getUserCurretLocation();
        // init GridView
        // Create an object of CustomAdapter and set Adapter to GirdView

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    public void  callServicesByVendorUUID(final String vendorUUID){
        WUPPreferences.saveVendorUUID(vendorUUID);
        final String url = WayUPartyConstants.TEST_URL+WayUPartyConstants.URL_GETSERVICESLIST_BYVENDORUUID+"?vendorUUID="+vendorUUID;
        Ion.with(this)
                .load("GET",url)
                .setLogging("wayuparty", Log.DEBUG)
                .followRedirect(false)
                .as(new TypeToken<JsonObject>() {
                }).withResponse()
                .setCallback(new FutureCallback<Response<JsonObject>>() {
                    @Override
                    public void onCompleted(Exception e, Response<JsonObject> result) {
                        try {
                            String jsonString = new com.google.gson.Gson().toJson(result);
                            Log.d("jsonstring",jsonString);
                            GetServicesList getServicesList = new Gson().fromJson(result.getResult(), new TypeToken<GetServicesList>() {
                            }.getType());
                            Log.d("jsonstring",getServicesList.getObject().getServicesList().get(3).getServiceName());
                            progressBar.setVisibility(View.GONE);
                            if(getServicesList.getObject().getServicesList() != null) {
                                if (getServicesList.getObject().getServicesList().size() > 0) {
                                    ServicesGridAdapter customAdapter = new ServicesGridAdapter(ServicesActivity.this,getServicesList,vendorUUID);
                                    simpleGrid.setAdapter(customAdapter);
                                   // Log.d("dbdata","count"+getAllRestaurants.getData().size());
                                }
                            }
                        }catch (Exception t){
                            t.printStackTrace();
                        }
                    }
                });
    }


    @Override
    public void onPositiveButtonClicked(GetServiceCategoryList getServiceCategoryList, int position) {
        callCategoryServicesList(vendorUUID,getServiceCategoryList.getData().get(position).getCategoryUUID());
    }

    @Override
    public void onNegativeButtonClicked() {

    }

    public void callCategoryServicesList(String vendorUUID,String categoryUUID){
        final String url = WayUPartyConstants.TEST_URL+WayUPartyConstants.URL_GETSCATEGORY_SERVICELIST+"?vendorUUID="+vendorUUID+"&categoryUUID="+categoryUUID;
        Ion.with(ServicesActivity.this)
                .load("GET",url)
                .setLogging("wayuparty", Log.DEBUG)
                .followRedirect(false)
                .as(new TypeToken<JsonObject>() {
                }).withResponse()
                .setCallback(new FutureCallback<Response<JsonObject>>() {
                    @Override
                    public void onCompleted(Exception e, Response<JsonObject> result) {
                        try {
                            String jsonString = new com.google.gson.Gson().toJson(result);
                            Log.d("jsonstring",jsonString);
                            GetCategoryServiceList getCategoryServiceList = new Gson().fromJson(result.getResult(), new TypeToken<GetCategoryServiceList>() {
                            }.getType());
                            Log.d("jsonstring",getCategoryServiceList.getData().size()+"");
                            // progressBar.setVisibility(View.GONE);
                            if(getCategoryServiceList.getData() != null) {
                                if (getCategoryServiceList.getData().size() > 0) {

                                    Intent intent = new Intent(ServicesActivity.this,CategoryServiceListActivity.class);
                                    intent.putExtra("listdata",getCategoryServiceList);
                                    startActivity(intent);

                                }else {
                                    final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ServicesActivity.this);

                                    alertDialogBuilder.setMessage("Service not available, sorry for inconvenience");
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
                                }
                            }
                        }catch (Exception t){
                            t.printStackTrace();
                        }
                    }
                });
    }
}
