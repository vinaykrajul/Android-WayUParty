package com.acculytixs.mobile.wayuparty.activitys;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.acculytixs.mobile.wayuparty.R;
import com.acculytixs.mobile.wayuparty.adapters.OrderDetailsAdapter;
import com.acculytixs.mobile.wayuparty.adapters.RSPager;
import com.acculytixs.mobile.wayuparty.adapters.RestaurantDetailsAdapter;
import com.acculytixs.mobile.wayuparty.application.WayUPartyConstants;
import com.acculytixs.mobile.wayuparty.dtos.DataOrderList;
import com.acculytixs.mobile.wayuparty.dtos.Datum;
import com.acculytixs.mobile.wayuparty.dtos.GetVendorInfo;
import com.acculytixs.mobile.wayuparty.dtos.UserOrderListResponse;
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
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import me.relex.circleindicator.CircleIndicator;

public class OrderDetialsActivity  extends AppCompatActivity {

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    OrderDetailsAdapter qaAadapter;
    DataOrderList datum;
    ImageView imageView;
    TextView tv_total_amount;
    ArrayList<String> orderItems = new ArrayList<>();
    ArrayList<String> orderRates = new ArrayList<>();
    ArrayList<String> orderStaus = new ArrayList<>();
    ArrayList<String> orderUUIDS= new ArrayList<>();
    ArrayList<String> itemsCancelStatus= new ArrayList<>();
    ArrayList<String> itemsRescheduleStatus= new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        datum = (DataOrderList) getIntent().getSerializableExtra("orderData");
        toolbar.setTitle("Order Details");
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimary));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        recyclerView = (RecyclerView)findViewById(R.id.rv_cart_items);
        linearLayoutManager = new LinearLayoutManager(OrderDetialsActivity.this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        imageView = (ImageView)findViewById(R.id.QR_code);
        tv_total_amount = (TextView) findViewById(R.id.tv_total_amnt);
        tv_total_amount.setText(String.valueOf(datum.getTotalAmount()));

          if (!TextUtils.isEmpty(datum.getQrCode())) {
            Log.d("imagaeurl", "" + datum.getQrCode());
            String url = WayUPartyConstants.TEST_URL+datum.getQrCode();
            Picasso.with(OrderDetialsActivity.this)
                    .load(url)
                    .into(imageView);
        } else {
           imageView.setImageResource(R.drawable.qr_code);
        }

          setData();
          qaAadapter = new OrderDetailsAdapter(OrderDetialsActivity.this,orderItems,orderRates,orderStaus,orderUUIDS,itemsCancelStatus,itemsRescheduleStatus);
          recyclerView.setAdapter(qaAadapter);


    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void setData(){
        String orderitems = datum.getOrderItems();
        String [] orderitemsArray = orderitems.split(",");
        List<String> fixedLenghtList = Arrays.asList(orderitemsArray);

        String orderitems1 = datum.getOrderRates();
        String [] orderitemsArray1 = orderitems1.split(",");
        List<String> fixedLenghtList1 = Arrays.asList(orderitemsArray1);

        String orderitems2 = datum.getOrderStatus();
        String [] orderitemsArray2 = orderitems2.split(",");
        List<String> fixedLenghtList2 = Arrays.asList(orderitemsArray2);

        String orderitems3 = datum.getOrderUUIDs();
        String [] orderitemsArray3 = orderitems3.split(",");
        List<String> fixedLenghtList3 = Arrays.asList(orderitemsArray3);

        String orderitems4 = datum.getOrderItemsCanceled();
        String [] orderitemsArray4 = orderitems4.split(",");
        List<String> fixedLenghtList4 = Arrays.asList(orderitemsArray4);

        String orderitems5 = datum.getOrderItemsReschedule();
        String [] orderitemsArray5 = orderitems5.split(",");
        List<String> fixedLenghtList5 = Arrays.asList(orderitemsArray5);


        orderItems = new ArrayList<String>(fixedLenghtList);
        orderRates =  new ArrayList<String>(fixedLenghtList1);
        orderStaus =  new ArrayList<String>(fixedLenghtList2);
        orderUUIDS =  new ArrayList<String>(fixedLenghtList3);
        itemsCancelStatus = new ArrayList<>(fixedLenghtList4);
        itemsRescheduleStatus = new ArrayList<>(fixedLenghtList5);


    }
}
