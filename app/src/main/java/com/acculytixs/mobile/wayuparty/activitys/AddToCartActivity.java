package com.acculytixs.mobile.wayuparty.activitys;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.acculytixs.mobile.wayuparty.R;
import com.acculytixs.mobile.wayuparty.adapters.CategoryServicesListAdapter;
import com.acculytixs.mobile.wayuparty.application.WUPPreferences;
import com.acculytixs.mobile.wayuparty.application.WayUPartyConstants;
import com.acculytixs.mobile.wayuparty.dtos.AddToCartRequest;
import com.acculytixs.mobile.wayuparty.dtos.AddToCartResponse;
import com.acculytixs.mobile.wayuparty.dtos.Datun;
import com.acculytixs.mobile.wayuparty.dtos.GetCartList;
import com.acculytixs.mobile.wayuparty.dtos.GetCategoryServiceList;
import com.acculytixs.mobile.wayuparty.dtos.LoginRegisteredUserResponse;
import com.acculytixs.mobile.wayuparty.dtos.LoginRequest;
import com.acculytixs.mobile.wayuparty.fragments.CartFragment;
import com.acculytixs.mobile.wayuparty.fragments.PickDateTimeDialogFragment;
import com.acculytixs.mobile.wayuparty.fragments.SingleChoiceDialogFragment;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import me.himanshusoni.quantityview.QuantityView;

public class AddToCartActivity extends AppCompatActivity implements QuantityView.OnQuantityChangeListener, View.OnClickListener , PickDateTimeDialogFragment.SingleChoiceListener {

    RecyclerView recyclerView;

    Datun datun;
    List<String> data= new ArrayList<>();
    List<String> formatdata= new ArrayList<>();
    CategoryServicesListAdapter categoryServicesListAdapter;
    TextView tv_tittle,tv_price,tv_pick_date,tv_pick_time,tv_date,tv_time,tv_surp_for,tv_surp_occasion;
    ImageView imageView;
    LinearLayout ll_date,ll_time,ll_surprise,ll_surprise_for,ll_surprise_occasion;
    Button btn_addcart,btn_customize;
    EditText et_comment;
    String date,time,quantity = "1",surprise_for = "",surprise_occasion ="",surprise_instructions;
    QuantityView quantityView;
    ProgressBar progressBar;
    Button positiveDialog;
    Button negativeDialog;
    AlertDialog.Builder dialogBuilder;
    AlertDialog alertDialog;
    int menCount,womenCount;
    int finalMenCount,finalWomenCount;
    String packageItemUUIDS ="";
    ArrayList<String> itemUUIDs = new ArrayList<>();

    public AddToCartActivity() {
        super();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_to_cart);
        progressBar = (ProgressBar) findViewById(R.id.main_progress);
        Toolbar toolbar = findViewById(R.id.toolbar);
        final QuantityView quantityViewDefault = (QuantityView) findViewById(R.id.quantityView_custom_2);
        quantityViewDefault.setOnQuantityChangeListener(this);
        datun = (Datun) getIntent().getSerializableExtra("data");
        toolbar.setTitle("Add to Cart");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setData();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onQuantityChanged(int oldQuantity, int newQuantity, boolean programmatically) {
        quantity = String.valueOf(newQuantity);
        Log.d("quant",quantity);
    }

    @Override
    public void onLimitReached() {

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void setData(){
        tv_tittle = (TextView) findViewById(R.id.tv_category_tittle);
        et_comment = (EditText) findViewById(R.id.etDescription);
        tv_surp_for = (TextView) findViewById(R.id.tv_select_surprise);
        tv_surp_occasion = (TextView) findViewById(R.id.tv_select_surprise_occasion);
        tv_price = (TextView) findViewById(R.id.tv_price);
        tv_pick_date = (TextView) findViewById(R.id.tv_pick_date);
        ll_date = (LinearLayout)findViewById(R.id.ll_pick_date) ;
        ll_date.setOnClickListener(this);
        tv_pick_time = (TextView) findViewById(R.id.tv_pick_time);
        ll_time = (LinearLayout)findViewById(R.id.ll_pick_time) ;
        ll_time.setOnClickListener(this);
        tv_date = (TextView) findViewById(R.id.tv_selected_date);
        tv_time = (TextView) findViewById(R.id.tv_selected_time);
        imageView = (ImageView)findViewById(R.id.thumb_image);
        btn_addcart = ( Button) findViewById(R.id.btn_addtocart);
        ll_surprise = (LinearLayout)findViewById(R.id.ll_surprise) ;
        ll_surprise_for = (LinearLayout)findViewById(R.id.ll_surp_for);
        ll_surprise_for.setOnClickListener(this);
        ll_surprise_occasion = (LinearLayout)findViewById(R.id.ll_surp_occasion) ;
        quantityView = (QuantityView)findViewById(R.id.quantityView_custom_2);
        ll_surprise_occasion.setOnClickListener(this);
        btn_addcart.setOnClickListener(this);
        btn_customize = (Button)findViewById(R.id.btn_customize);
        btn_customize.setOnClickListener(this);

        tv_tittle.setText(datun.getSubCategory());
        tv_price.setText("Rs."+String.valueOf(datun.getOfferPrice()));
        if (!TextUtils.isEmpty(datun.getServiceImage())) {
            Log.d("imagaeurl", "" + datun.getServiceImage());
            String url = WayUPartyConstants.TEST_URL+datun.getServiceImage();
            Picasso.with(AddToCartActivity.this)
                    .load(url)
                    .into(imageView);
        } else {
            //viewHolder.imageView.setImageResource(R.drawable.sample_image);
        }

        if(WUPPreferences.getServiceUUID().equals("Surprise")){
            ll_surprise.setVisibility(View.VISIBLE);
            quantityView.setMaxQuantity(1);

        }else {
            if(WUPPreferences.getServiceUUID().equals("Packages")){
                btn_customize.setVisibility(View.VISIBLE);
                quantityView.setMaxQuantity(1);
            }
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ll_pick_date:
                data.clear();
                formatdata.clear();
                for(int i=0;i<datun.getServiceDates().size();i++){
                    data.add(datun.getServiceDates().get(i).getServiceDate());
                    formatdata.add(datun.getServiceDates().get(i).getPassableDate());

                }
                DialogFragment singleChoiceDialog = new PickDateTimeDialogFragment(data,formatdata,"date");
                singleChoiceDialog.setCancelable(false);
                FragmentManager fm = (AddToCartActivity.this).getSupportFragmentManager();
                singleChoiceDialog.show(fm, "Single Choice Dialog");
                break;
            case R.id.ll_pick_time:
                data.clear();
                formatdata.clear();
                for(int i=0;i<datun.getTimeSlotList().size();i++){
                    data.add(datun.getTimeSlotList().get(i).getStartTime()+" to "+datun.getTimeSlotList().get(i).getEndTime());
                    formatdata.add(datun.getTimeSlotList().get(i).getStartTime()+" to "+datun.getTimeSlotList().get(i).getEndTime());

                }
                DialogFragment singleChoiceDialog1 = new PickDateTimeDialogFragment(data,formatdata,"time");
                singleChoiceDialog1.setCancelable(false);
                FragmentManager fm1 = (AddToCartActivity.this).getSupportFragmentManager();
                singleChoiceDialog1.show(fm1, "Single Choice Dialog");
                break;
            case R.id.btn_addtocart:
                btn_addcart.setClickable(false);
                validateDateAndTime();
                btn_addcart.setClickable(true);
                 break;
            case R.id.ll_surp_for:
                data.clear();
                formatdata.clear();
                for(int i=0;i<datun.getSurpriseForList().size();i++){
                    data.add(datun.getSurpriseForList().get(i).getSurpriseName());
                    formatdata.add(datun.getSurpriseForList().get(i).getSurpriseUUID());

                }
                DialogFragment singleChoiceDialog2 = new PickDateTimeDialogFragment(data,formatdata,"surp_for");
                singleChoiceDialog2.setCancelable(false);
                FragmentManager fm2 = (AddToCartActivity.this).getSupportFragmentManager();
                singleChoiceDialog2.show(fm2, "Single Choice Dialog");
                break;
            case R.id.ll_surp_occasion:
                data.clear();
                formatdata.clear();
                for(int i=0;i<datun.getSurpriseOccationList().size();i++){
                    data.add(datun.getSurpriseOccationList().get(i).getSurpriseName());
                    formatdata.add(datun.getSurpriseOccationList().get(i).getSurpriseUUID());

                }
                DialogFragment singleChoiceDialog3 = new PickDateTimeDialogFragment(data,formatdata,"surp_occasion");
                singleChoiceDialog3.setCancelable(false);
                FragmentManager fm3 = (AddToCartActivity.this).getSupportFragmentManager();
                singleChoiceDialog3.show(fm3, "Single Choice Dialog");

                break;
            case R.id.btn_customize:
                Intent intent = new Intent(AddToCartActivity.this,CustomizePackageActivity.class);
                intent.putExtra("data",datun);
                intent.putStringArrayListExtra("dataList",itemUUIDs);
                startActivityForResult(intent,4);
                break;



        }

    }


    @Override
    public void onPositiveButtonClicked(List<String> datun,List<String> formatdata, int position, String name) {

         switch (name){
             case "time":
                 tv_pick_time.setText(data.get(position));
                 time = formatdata.get(position);
                 break;
             case "date":
                 tv_pick_date.setText(data.get(position));
                 date = formatdata.get(position);
                 break;
             case "surp_for":
                 tv_surp_for.setText(data.get(position));
                 surprise_for = formatdata.get(position);
                 break;
             case "surp_occasion":
                 tv_surp_occasion.setText(data.get(position));
                 surprise_occasion = formatdata.get(position);
                 break;

         }
    }

    @Override
    public void onNegativeButtonClicked() {

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

            if(WUPPreferences.getServiceUUID().equals("Surprise")){
                validateSurpriseDetails();

            }else {
                if ((WUPPreferences.getUserId() != null) && !(WUPPreferences.getUserId().isEmpty())) {
                    Log.d("checck data uuid",WUPPreferences.getUserId());

                    if(WUPPreferences.getRationEnabled().equals("Y")){
                        if((finalMenCount+finalWomenCount)>=1) {
                            callAddToCartService();
                        }else {
                            showAlertDialog();
                        }
                        Log.d("allowed couunt",datun.getAllowed()+"");
                    }else {
                        callAddToCartService();
                    }

                } else {
                    Intent intent = new Intent(AddToCartActivity.this,LoginAcitvity.class);
                    startActivityForResult(intent,3);
                }
            }

        }
    }

    public void validateSurpriseDetails(){
        if(tv_surp_for.getText().equals("Select Surprise For")||tv_surp_occasion.getText().equals("Select Surprise Occasion")){
            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

            alertDialogBuilder.setMessage("Select Surprise For and Occasion");
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
           validateInstructions();

        }
    }

    public void validateInstructions(){
        Log.d("testing et comment",et_comment.getText().toString());
        if(et_comment.getText().toString().equals("")){
            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

            alertDialogBuilder.setMessage("Write Occasion Instructions");
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
            if ((WUPPreferences.getUserId() != null) && !(WUPPreferences.getUserId().isEmpty())) {
                Log.d("checck data uuid",WUPPreferences.getUserId());
                callAddToCartService();

            } else {
               Intent intent = new Intent(AddToCartActivity.this,LoginAcitvity.class);
                startActivityForResult(intent,3);

            }

        }
    }


    public void callAddToCartService(){

        String url = WayUPartyConstants.TEST_URL+WayUPartyConstants.URL_ADDTOCART;
         AddToCartRequest addToCartRequest = new AddToCartRequest();
           addToCartRequest.setUserUUID(WUPPreferences.getUserId());
           addToCartRequest.setMasterServiceUUID(datun.getMasterServiceUUID());
           addToCartRequest.setVendorUUID(WUPPreferences.getVendorUUID());
           addToCartRequest.setServiceOrderDate(date);
           addToCartRequest.setTimeslot(time);
           addToCartRequest.setOrderAmount(String.valueOf(datun.getOfferPrice()));
           addToCartRequest.setQuantity(quantity);
           addToCartRequest.setTotalAmount(String.valueOf(datun.getOfferPrice()*Integer.parseInt(quantity)));
           addToCartRequest.setCurrency(datun.getCurrency());
           addToCartRequest.setPackageMenuItems(packageItemUUIDS);
           String surpdet = surprise_for+","+surprise_occasion;
           addToCartRequest.setSurpriseDetails(surpdet);
           addToCartRequest.setSurpriseInstructions(et_comment.getText().toString());

        try {
            Ion.with(this)
                    .load("POST",url)
                    .setLogging("jananetha", Log.DEBUG)
                    .followRedirect(false)
                    .addHeader("X-Username", WUPPreferences.getUserName())
                    .addHeader("X-Password", WUPPreferences.getPassword())
                    .setJsonPojoBody(addToCartRequest)
                    .as(new TypeToken<JsonObject>() {
                    })
                    .withResponse()
                    .setCallback(new FutureCallback<Response<JsonObject>>() {
                        @Override
                        public void onCompleted(Exception e, Response<JsonObject> jsonArrayResponse) {
                            try {
                                String jsonString = new com.google.gson.Gson().toJson(jsonArrayResponse);
                                Log.d("responseCheck uuid", "" + jsonString);
                                AddToCartResponse addToCartResponse = new Gson().fromJson(jsonArrayResponse.getResult(), new TypeToken<AddToCartResponse>() {
                                }.getType());
                                // progressBar.setVisibility(View.GONE);
                                Log.d("jsonstring",addToCartResponse.getResponse()+"");
                                if(addToCartResponse.getResponse().equals("SUCCESS")){
                                   Intent intent = new Intent(AddToCartActivity.this,MainActivity.class);
                                   intent.putExtra("fragment","cart");
                                   startActivity(intent);
                                }else {
                                    final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddToCartActivity.this);

                                    alertDialogBuilder.setMessage(addToCartResponse.getResponseMessage());
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

                            } catch (Exception e1) {
                                e1.printStackTrace();

                            }


                        }
                    });
        } catch (Exception e) {

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 3) {
            callAddToCartService();
        }else {
            if(requestCode == 4){
                if ((WUPPreferences.getUserId() != null) && !(WUPPreferences.getUserId().isEmpty())) {
                    Log.d("checck data uuid",WUPPreferences.getUserId());
                    if(data!=null) {
                        Log.d("checck data", data.getStringExtra("data"));
                        packageItemUUIDS = data.getStringExtra("data");
                        itemUUIDs = data.getStringArrayListExtra("dataList");

                    }
                    callAddToCartService();

                } else {
                    Intent intent = new Intent(AddToCartActivity.this,LoginAcitvity.class);
                    startActivityForResult(intent,3);
                }
            }
        }
    }

    private void showAlertDialog(){
        dialogBuilder = new AlertDialog.Builder(AddToCartActivity.this);
        View layoutView = getLayoutInflater().inflate(R.layout.men_women_ratio_dialog, null);
        Button dialogButton = layoutView.findViewById(R.id.btn_addtocart);
        QuantityView quantityViewMen = layoutView.findViewById(R.id.men_count);
        quantityViewMen.setQuantity(menCount);
        quantityViewMen.setMaxQuantity(datun.getAllowed()*Integer.parseInt(quantity));
        QuantityView quantityViewWomen = layoutView.findViewById(R.id.women_count);
        quantityViewWomen.setQuantity(womenCount);
        quantityViewWomen.setMaxQuantity(datun.getAllowed()*Integer.parseInt(quantity));
        quantityViewWomen.setOnQuantityChangeListener(new QuantityView.OnQuantityChangeListener() {
            @Override
            public void onQuantityChanged(int oldQuantity, int newQuantity, boolean programmatically) {
                womenCount = newQuantity;
            }

            @Override
            public void onLimitReached() {

            }
        });
        quantityViewMen.setOnQuantityChangeListener(new QuantityView.OnQuantityChangeListener() {
            @Override
            public void onQuantityChanged(int oldQuantity, int newQuantity, boolean programmatically) {
               menCount = newQuantity;
            }

            @Override
            public void onLimitReached() {

            }
        });
        dialogBuilder.setView(layoutView);
        alertDialog = dialogBuilder.create();
        alertDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finalMenCount = menCount;
                finalWomenCount = womenCount;
                if((menCount+womenCount) > (datun.getAllowed()*Integer.parseInt(quantity))) {
                    Toast.makeText(alertDialog.getContext(), "Guests allowed : "+datun.getAllowed()*Integer.parseInt(quantity), Toast.LENGTH_SHORT).show();
                }else {
                    if((datun.getAllowed())*(Integer.parseInt(quantity))>1){
                        if( (menCount+womenCount) >= 1) {
                            if (womenCount >= (menCount - womenCount)) {
                                callAddToCartService();
                                alertDialog.dismiss();
                            } else {
                                Toast.makeText(alertDialog.getContext(), "Please select ration as Men:Women (2:1)", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(alertDialog.getContext(), "Please select count", Toast.LENGTH_SHORT).show();

                        }
                    }else {
                        if( (menCount+womenCount) >= 1) {
                            callAddToCartService();
                            alertDialog.dismiss();
                        }else {
                            Toast.makeText(alertDialog.getContext(), "Please select count", Toast.LENGTH_SHORT).show();

                        }
                    }

                }
            }
        });
    }

}
