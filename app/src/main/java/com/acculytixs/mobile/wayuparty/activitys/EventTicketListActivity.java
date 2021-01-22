package com.acculytixs.mobile.wayuparty.activitys;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.acculytixs.mobile.wayuparty.R;
import com.acculytixs.mobile.wayuparty.adapters.CategoryServicesListAdapter;
import com.acculytixs.mobile.wayuparty.adapters.EventTicketAdapter;
import com.acculytixs.mobile.wayuparty.application.WUPPreferences;
import com.acculytixs.mobile.wayuparty.application.WayUPartyConstants;
import com.acculytixs.mobile.wayuparty.dtos.EventTicketList;
import com.acculytixs.mobile.wayuparty.dtos.GetCartList;
import com.acculytixs.mobile.wayuparty.dtos.GetCategoryServiceList;
import com.acculytixs.mobile.wayuparty.dtos.GetOrderIDRequest;
import com.acculytixs.mobile.wayuparty.dtos.GetOrderIDResponse;
import com.acculytixs.mobile.wayuparty.dtos.PlaceEventOrderRequest;
import com.acculytixs.mobile.wayuparty.dtos.PlaceOrderRequest;
import com.acculytixs.mobile.wayuparty.fragments.CartFragment;
import com.acculytixs.mobile.wayuparty.fragments.OrdersFragment;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;
import com.razorpay.Checkout;
import com.razorpay.PaymentData;
import com.razorpay.PaymentResultWithDataListener;

import org.json.JSONObject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class EventTicketListActivity extends AppCompatActivity implements View.OnClickListener, PaymentResultWithDataListener {


    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    ProgressBar progressBar;
    EventTicketList getCategoryServiceList;
    EventTicketAdapter categoryServicesListAdapter;
    String name,quantity = "0",currency;
    TextView tv_proceed;
    GetOrderIDResponse getCartList;
    Checkout checkout;
    GetCartList getCartLists;
    String eventUUID,categoryType;
    Toolbar toolbar;
    FrameLayout frameLayout;
    private static final String TAG = EventTicketListActivity.class.getSimpleName();
    int amout;

    public EventTicketListActivity() {
        super();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_list);
        progressBar = (ProgressBar)findViewById(R.id.main_progress);
        toolbar = findViewById(R.id.toolbar);
        getCategoryServiceList = (EventTicketList)getIntent().getSerializableExtra("ticketList");
        eventUUID = getIntent().getStringExtra("eventUUID");
        categoryType = getIntent().getStringExtra("categoryType");
        toolbar.setTitle("Ticket Booking");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        recyclerView = (RecyclerView)findViewById(R.id.rv_category_list_india);
        frameLayout = (FrameLayout) findViewById(R.id.frame_cartlist);
        tv_proceed = (TextView) findViewById(R.id.tv_proceed);
        tv_proceed.setOnClickListener(this);
        linearLayoutManager = new LinearLayoutManager(EventTicketListActivity.this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        categoryServicesListAdapter = new EventTicketAdapter(EventTicketListActivity.this,getCategoryServiceList);
        recyclerView.setAdapter(categoryServicesListAdapter);
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("custom-message"));

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            String ItemName = intent.getStringExtra("item");
            String qty = intent.getStringExtra("quantity");
            amout = intent.getIntExtra("amount",0);
            currency = intent.getStringExtra("currency");
            name = ItemName;
            quantity = qty;
            Toast.makeText(EventTicketListActivity.this,ItemName +" "+qty ,Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_proceed:
                if(Integer.parseInt(quantity)>0){
                    if((WUPPreferences.getUserId() != null) && !(WUPPreferences.getUserId().isEmpty())) {
                        frameLayout.setVisibility(View.GONE);
                        progressBar.setVisibility(View.VISIBLE);
                        checkout = new Checkout();
                        checkout.setKeyID("rzp_test_frTSP8jWD932Xq");
                        Log.d("amnt", amout + "");
                        callOrderIDService(String.valueOf(amout * Integer.parseInt(quantity)), currency);
                    }else {
                        Intent intent = new Intent(EventTicketListActivity.this,LoginAcitvity.class);
                        startActivityForResult(intent,3);
                    }
                }else {
                    Toast.makeText(EventTicketListActivity.this,"please add ticket",Toast.LENGTH_SHORT).show();

                }
                break;
        }
    }

    public void callOrderIDService(String amount,String currency){

        String url = WayUPartyConstants.TEST_URL+WayUPartyConstants.URL_ORDERS;

        GetOrderIDRequest getOrderIDRequest = new GetOrderIDRequest();
        getOrderIDRequest.setCartAmount(amount);
        getOrderIDRequest.setCurrency(currency);

        Log.d("paramscheck", "" + url);

        Ion.with(this)
                .load("POST",url)
                .setLogging("jananetha", Log.DEBUG)
                .followRedirect(false)
                //.progressDialog(jananethaApplication.showProgress())
                .addHeader("X-Username", WUPPreferences.getUserName())
                .addHeader("X-Password", WUPPreferences.getPassword())
                .setJsonPojoBody(getOrderIDRequest)
                .as(new TypeToken<JsonObject>() {
                }).withResponse()
                .setCallback(new FutureCallback<Response<JsonObject>>() {
                    @Override
                    public void onCompleted(Exception e, Response<JsonObject> result) {
                        try {

                            String jsonString = new com.google.gson.Gson().toJson(result);
                            Log.d("jsonstring",jsonString);
                            getCartList = new Gson().fromJson(result.getResult(), new TypeToken<GetOrderIDResponse>() {
                            }.getType());

                            Checkout.preload(EventTicketListActivity.this.getApplicationContext());
                            checkout.setImage(R.drawable.logo);
                            final Activity activity = EventTicketListActivity.this;

                            /**
                             * Pass your payment options to the Razorpay Checkout as a JSONObject
                             */
                            try {
                                JSONObject options = new JSONObject();

                                options.put("name", "WayUParty");
                                options.put("description","Party like you have never before ");
                                options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
                                options.put("order_id", getCartList.getObject().getOrderId());//from response of step 3.
                                options.put("theme.color", "#c4ae6e");
                                options.put("currency", getCartList.getObject().getCurrency());
                                options.put("amount", getCartList.getObject().getAmount());//pass amount in currency subunits
                                options.put("prefill.email", WUPPreferences.getUserName());
                                options.put("prefill.contact",WUPPreferences.getMobileNum());
                                checkout.open(activity, options);
                                tv_proceed.setEnabled(true);
                                tv_proceed.setClickable(true);
                            } catch(Exception e1) {
                                Log.e(TAG, "Error in starting Razorpay Checkout", e1);
                                tv_proceed.setEnabled(true);
                                tv_proceed.setClickable(true);
                                //paymentProgress.setVisibility(View.GONE);
                                tv_proceed.setVisibility(View.VISIBLE);
                            }


                        }catch (Exception t){
                            t.printStackTrace();
                        }
                    }
                });
    }

    @Override
    public void onPaymentSuccess(String s, PaymentData paymentData) {
        try {
            callplaceOrder(paymentData.getPaymentId(), paymentData.getOrderId(), paymentData.getSignature());
        }catch (Exception e){
           e.printStackTrace();
        }

    }

    @Override
    public void onPaymentError(int i, String s, PaymentData paymentData) {
    }

    public void callplaceOrder(String paymentID,String orderId,String signature){

        String url = WayUPartyConstants.TEST_URL+WayUPartyConstants.URL_PLACE_EVENT_ORDER;

        PlaceEventOrderRequest placeOrderRequest = new PlaceEventOrderRequest();

        placeOrderRequest.setUserUUID(WUPPreferences.getUserId());
        placeOrderRequest.setVendorUUID(WUPPreferences.getVendorUUID());
        placeOrderRequest.setEventUUID(eventUUID);
        placeOrderRequest.setTicketType(name);
        placeOrderRequest.setCategoryType(categoryType);
        placeOrderRequest.setTicketAmount(String.valueOf(amout));
        placeOrderRequest.setQuantity(quantity);
        placeOrderRequest.setTimeslot(WUPPreferences.getTimeslot());
        placeOrderRequest.setCurrency(currency);
        placeOrderRequest.setOrderId(orderId);
        placeOrderRequest.setPaymentId(paymentID);
        placeOrderRequest.setSignature(signature);

        Ion.with(this)
                .load("POST",url)
                .setLogging("jananetha", Log.DEBUG)
                .followRedirect(false)
                //.progressDialog(jananethaApplication.showProgress())
                .addHeader("X-Username", WUPPreferences.getUserName())
                .addHeader("X-Password", WUPPreferences.getPassword())
                .setJsonPojoBody(placeOrderRequest)
                .as(new TypeToken<JsonObject>() {
                }).withResponse()
                .setCallback(new FutureCallback<Response<JsonObject>>() {
                    @Override
                    public void onCompleted(Exception e, Response<JsonObject> result) {
                        try {

                            String jsonString = new com.google.gson.Gson().toJson(result);
                            Log.d("jsonstring",jsonString);
                            Intent intent = new Intent(EventTicketListActivity.this, MainActivity.class);
                            intent.putExtra("fragment","orders");
                            startActivity(intent);
                            progressBar.setVisibility(View.GONE);

                        }catch (Exception t){
                            t.printStackTrace();
                        }
                    }
                });
    }
    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        //transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 3) {
            frameLayout.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
            checkout = new Checkout();
            checkout.setKeyID("rzp_test_frTSP8jWD932Xq");
            Log.d("amnt", amout + "");
            callOrderIDService(String.valueOf(amout * Integer.parseInt(quantity)), currency);
        }else {
        }
    }
}
