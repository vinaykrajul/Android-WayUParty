package com.acculytixs.mobile.wayuparty.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.acculytixs.mobile.wayuparty.R;
import com.acculytixs.mobile.wayuparty.activitys.AddToCartActivity;
import com.acculytixs.mobile.wayuparty.activitys.LoginAcitvity;
import com.acculytixs.mobile.wayuparty.activitys.MainActivity;
import com.acculytixs.mobile.wayuparty.adapters.CartListAdapter;
import com.acculytixs.mobile.wayuparty.adapters.RestaurantsAdapter;
import com.acculytixs.mobile.wayuparty.application.WUPPreferences;
import com.acculytixs.mobile.wayuparty.application.WayUPartyConstants;
import com.acculytixs.mobile.wayuparty.dtos.GetAllRestaurants;
import com.acculytixs.mobile.wayuparty.dtos.GetCartList;
import com.acculytixs.mobile.wayuparty.dtos.GetOrderIDRequest;
import com.acculytixs.mobile.wayuparty.dtos.GetOrderIDResponse;
import com.acculytixs.mobile.wayuparty.dtos.PlaceOrderRequest;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;
import com.razorpay.Checkout;
import com.razorpay.PaymentData;
import com.razorpay.PaymentResultListener;
import com.razorpay.PaymentResultWithDataListener;

import org.json.JSONObject;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static cn.jzvd.JZMediaManager.TAG;

public class CartFragment extends Fragment implements View.OnClickListener{

    ProgressBar progressBar;
    RecyclerView restaurant_rv;
    LinearLayoutManager linearLayoutManager;
    CartListAdapter cartListAdapter;
    TextView tv_items_amount,tv_total_amount,tv_item_count,tv_browse_rest,tv_place_order,paymentProgress;
    LinearLayout ll_empty,ll_cartlist,ll_placeorder;
    GetOrderIDResponse getCartList;
    Checkout checkout;
    GetCartList getCartLists;
    int amout;

    private static final String TAG = MainActivity.class.getSimpleName();


    @Override
    public Lifecycle getLifecycle() {
        return super.getLifecycle();
    }

    @NonNull
    @Override
    public LifecycleOwner getViewLifecycleOwner() {
        return super.getViewLifecycleOwner();
    }

    @NonNull
    @Override
    public LiveData<LifecycleOwner> getViewLifecycleOwnerLiveData() {
        return super.getViewLifecycleOwnerLiveData();
    }

    public CartFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        progressBar =(ProgressBar)view.findViewById(R.id.main_progress);
        paymentProgress =(TextView) view.findViewById(R.id.sync);
        restaurant_rv = (RecyclerView)view.findViewById(R.id.rv_cart_items);
        tv_items_amount = (TextView) view.findViewById(R.id.tv_items_price);
        tv_total_amount = (TextView)view.findViewById(R.id.tv_total_amnt);
        tv_place_order = (TextView)view.findViewById(R.id.tv_place_order);
        tv_place_order.setOnClickListener(this);
        tv_item_count = (TextView)view.findViewById(R.id.tv_item_count);
        ll_empty = (LinearLayout) view.findViewById(R.id.ll_emptycart);
        ll_cartlist = (LinearLayout)view.findViewById(R.id.ll_cart_list);
        ll_placeorder = (LinearLayout)view.findViewById(R.id.ll_placeorder);
        tv_browse_rest = (TextView)view.findViewById(R.id.tv_browse_restaurants);
        tv_browse_rest.setOnClickListener(this);
        linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        restaurant_rv.setLayoutManager(linearLayoutManager);
        getUserCartList();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("method","onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("method","onResume");
        paymentProgress.setVisibility(View.GONE);
        ll_placeorder.setVisibility(View.VISIBLE);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("method","onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("method","onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("method","onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("method","onDetach");
    }

    public static CartFragment newInstance() {
        CartFragment fragment = new CartFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public void getUserCartList() {

        final String url = WayUPartyConstants.TEST_URL+WayUPartyConstants.URL_GET_USER_CAARTLIST+"?userUUID="+WUPPreferences.getUserId();


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
                             getCartLists = new Gson().fromJson(result.getResult(), new TypeToken<GetCartList>() {
                            }.getType());
                           // progressBar.setVisibility(View.GONE);
                            //Log.d("jsonstring",getCartList.getData().size()+"");

                            progressBar.setVisibility(View.GONE);
                            if(getCartLists.getData() != null) {
                                if (getCartLists.getData().size() > 0) {
                                    ll_cartlist.setVisibility(View.VISIBLE);
                                    cartListAdapter = new CartListAdapter(getActivity(),getCartLists);
                                    restaurant_rv.setAdapter(cartListAdapter);
                                    tv_item_count.setText("Items :"+getCartLists.getData().size());
                                    int total_amount = 0;
                                    for(int i=0;i<getCartLists.getData().size();i++){
                                        total_amount = total_amount+getCartLists.getData().get(i).getTotalAmount();

                                    }
                                    amout = total_amount;
                                    tv_items_amount.setText("Rs."+total_amount);
                                    tv_total_amount.setText("Rs."+total_amount);

                                }else {
                                    ll_empty.setVisibility(View.VISIBLE);
                                }
                            }else {
                                ll_empty.setVisibility(View.VISIBLE);
                            }

                        }catch (Exception t){
                            t.printStackTrace();
                            ll_empty.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_browse_restaurants:
                Intent intent = new Intent(getContext(), MainActivity.class);
                intent.putExtra("fragment","home");
                startActivity(intent);
                break;
            case R.id.tv_place_order:
                if ((WUPPreferences.getUserId() != null) && !(WUPPreferences.getUserId().isEmpty())) {
                    tv_place_order.setEnabled(false);
                    tv_place_order.setClickable(false);
                    paymentProgress.setVisibility(View.VISIBLE);
                    ll_placeorder.setVisibility(View.GONE);
                    startPayment();
                }else {
                    Intent login = new Intent(getActivity(), LoginAcitvity.class);
                    startActivityForResult(login,3);
                }
                break;
        }

    }

    public void startPayment(){
        checkout = new Checkout();
        checkout.setKeyID("rzp_test_frTSP8jWD932Xq");
        Log.d("amnt",amout+"");
        callOrderIDService(String.valueOf(amout),"INR");

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

                            Checkout.preload(getActivity().getApplicationContext());
                            checkout.setImage(R.drawable.logo);
                            final Activity activity = getActivity();

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
                                options.put("prefill.email", WUPPreferences.getEmail());
                                options.put("prefill.contact",WUPPreferences.getMobileNum());
                                checkout.open(activity, options);
                                tv_place_order.setEnabled(true);
                                tv_place_order.setClickable(true);
                            } catch(Exception e1) {
                                Log.e(TAG, "Error in starting Razorpay Checkout", e1);
                                tv_place_order.setEnabled(true);
                                tv_place_order.setClickable(true);
                                paymentProgress.setVisibility(View.GONE);
                                ll_placeorder.setVisibility(View.VISIBLE);
                            }


                        }catch (Exception t){
                            t.printStackTrace();
                        }
                    }
                });
    }


    public void callplaceOrder(String paymentID,String orderId,String signature){

        String url = WayUPartyConstants.TEST_URL+WayUPartyConstants.URL_PLACE_ORDER;

        PlaceOrderRequest placeOrderRequest = new PlaceOrderRequest();

        placeOrderRequest.setUserUUID(WUPPreferences.getUserId());

        //String cartitems = "";
        StringBuilder builder = new StringBuilder("");
        for(int i=0;i<getCartLists.getData().size();i++){
            builder.append(getCartLists.getData().get(i).getCartUUID()+",");
        }

        String text = builder.toString();
        Log.d("test",text);

        placeOrderRequest.setCartItems(text);
        placeOrderRequest.setPaymentId(paymentID);
        placeOrderRequest.setOrderId(orderId);
        placeOrderRequest.setSignature(signature);

        Log.d("paramscheck", "" + url);

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

                            // progressBar.setVisibility(View.GONE);
                            //Log.d("jsonstring",getCartList.getData().size()+"");

                        }catch (Exception t){
                            t.printStackTrace();
                        }
                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 3) {
            if ((WUPPreferences.getUserId() != null) && !(WUPPreferences.getUserId().isEmpty())) {
                tv_place_order.setEnabled(false);
                tv_place_order.setClickable(false);
                paymentProgress.setVisibility(View.VISIBLE);
                ll_placeorder.setVisibility(View.GONE);
                startPayment();
            }else {
                Intent login = new Intent(getActivity(), LoginAcitvity.class);
                startActivityForResult(login,3);
            }
        }
    }

}
