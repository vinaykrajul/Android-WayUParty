package com.acculytixs.mobile.wayuparty.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.acculytixs.mobile.wayuparty.R;
import com.acculytixs.mobile.wayuparty.activitys.MainActivity;
import com.acculytixs.mobile.wayuparty.adapters.CartListAdapter;
import com.acculytixs.mobile.wayuparty.adapters.OrderListAdapter;
import com.acculytixs.mobile.wayuparty.application.WUPPreferences;
import com.acculytixs.mobile.wayuparty.application.WayUPartyConstants;
import com.acculytixs.mobile.wayuparty.dtos.GetCartList;
import com.acculytixs.mobile.wayuparty.dtos.UserOrderListResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class OrdersFragment  extends Fragment implements View.OnClickListener {

    ProgressBar progressBar;
    RecyclerView restaurant_rv;
    LinearLayoutManager linearLayoutManager;
    OrderListAdapter cartListAdapter;
    TextView tv_items_amount,tv_total_amount,tv_item_count,tv_browse_rest;
    LinearLayout ll_empty,ll_cartlist;

    public OrdersFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_orders, container, false);
        progressBar =(ProgressBar)view.findViewById(R.id.main_progress);
        restaurant_rv = (RecyclerView)view.findViewById(R.id.rv_order_items);
        ll_empty = (LinearLayout) view.findViewById(R.id.ll_emptycart);
        ll_cartlist = (LinearLayout)view.findViewById(R.id.ll_cart_list);
        tv_browse_rest = (TextView)view.findViewById(R.id.tv_browse_restaurants);
        tv_browse_rest.setOnClickListener(this);
        linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        restaurant_rv.setLayoutManager(linearLayoutManager);
        getUserOrderList();
        return view;
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_browse_restaurants:
                Intent intent = new Intent(getContext(), MainActivity.class);
                intent.putExtra("fragment","home");
                startActivity(intent);
                break;
        }
    }

    private void getUserOrderList() {

        final String url = WayUPartyConstants.TEST_URL+WayUPartyConstants.URL_GET_USER_ORDER_LIST+"?userUUID="+ WUPPreferences.getUserId();


        Log.d("paramscheck", "" + url);

        Ion.with(this)
                .load("POST",url)
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
                            UserOrderListResponse getCartList = new Gson().fromJson(result.getResult(), new TypeToken<UserOrderListResponse>() {
                            }.getType());
                            // progressBar.setVisibility(View.GONE);
                            //Log.d("jsonstring",getCartList.getData().size()+"");

                            progressBar.setVisibility(View.GONE);
                            if(getCartList.getData() != null) {
                                if (getCartList.getData().size() > 0) {
                                    ll_cartlist.setVisibility(View.VISIBLE);
                                    cartListAdapter = new OrderListAdapter(getActivity(),getCartList);
                                    restaurant_rv.setAdapter(cartListAdapter);

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
}
