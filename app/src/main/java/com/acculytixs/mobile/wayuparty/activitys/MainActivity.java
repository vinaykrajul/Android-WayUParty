package com.acculytixs.mobile.wayuparty.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.acculytixs.mobile.wayuparty.R;
import com.acculytixs.mobile.wayuparty.adapters.CartListAdapter;
import com.acculytixs.mobile.wayuparty.application.WUPPreferences;
import com.acculytixs.mobile.wayuparty.application.WayUPartyApplication;
import com.acculytixs.mobile.wayuparty.application.WayUPartyConstants;
import com.acculytixs.mobile.wayuparty.dtos.GetCartList;
import com.acculytixs.mobile.wayuparty.dtos.PlaceOrderRequest;
import com.acculytixs.mobile.wayuparty.fragments.CartFragment;
import com.acculytixs.mobile.wayuparty.fragments.HomeFragment;
import com.acculytixs.mobile.wayuparty.fragments.OrdersFragment;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;
import com.razorpay.PaymentData;
import com.razorpay.PaymentResultWithDataListener;

import java.util.List;

public class MainActivity extends AppCompatActivity implements PaymentResultWithDataListener {

    BottomNavigationView navigation;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle t;
    private NavigationView navDrawer;
    Toolbar toolbar;
    WayUPartyApplication wayUPartyApplication;
    String type;
    GetCartList getCartLists;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wayUPartyApplication = new WayUPartyApplication();
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        removePaddingFromNavigationItem();
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Clubs");
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimary));
        toolbar.setNavigationIcon(R.drawable.nav_menu_icon);
        type = getIntent().getStringExtra("fragment");
        setSupportActionBar(toolbar);

        if(!(type == null)){
           loadFragmentByIntent(type);
        }else {
            loadFragment(new HomeFragment());
        }
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        t = new ActionBarDrawerToggle(this, drawerLayout,R.string.app_name, R.string.app_name);

        drawerLayout.addDrawerListener(t);
        t.syncState();
        navDrawer = (NavigationView)findViewById(R.id.navigationView);





        navDrawer.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                Fragment fragment;
                switch(id)
                {
                    case R.id.nav_menu_home:
                        drawerLayout.closeDrawer(Gravity.LEFT);
                        fragment = new HomeFragment();
                        toolbar.setTitle("Restaurents");
                        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimary));
                        loadFragment(fragment);
                        break;
                    case R.id.nav_menu_user:
                        drawerLayout.closeDrawer(Gravity.LEFT);
                        Intent intent = new Intent(MainActivity.this,ProfileActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_menu_signout:
                        showLogoutDialog();
                        break;

                    default:
                        return true;
                }


                return true;

            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(Gravity.LEFT);
                if((WUPPreferences.getUserId() != null) && !(WUPPreferences.getUserId().isEmpty())){
                    navDrawer.getMenu().findItem(R.id.nav_menu_signout).setVisible(true);
                }else {
                    navDrawer.getMenu().findItem(R.id.nav_menu_signout).setVisible(false);
                }


            }
        });
    }
    public void removePaddingFromNavigationItem() {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) navigation.getChildAt(0);

        for (int i = 0; i < menuView.getChildCount(); i++) {
            BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
            View activeLabel = item.findViewById(R.id.largeLabel);
            if (activeLabel instanceof TextView) {
                activeLabel.setPadding(0, 0, 0, 0);
            }
        }
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = new HomeFragment();
                    toolbar.setTitle("Clubs");
                    toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimary));
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_cart:
                    fragment = new CartFragment();
                    toolbar.setTitle("Cart");
                    toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimary));
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_orders:
                    fragment = new OrdersFragment();
                    toolbar.setTitle("Orders");
                    toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimary));
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        //transaction.addToBackStack(null);
        transaction.commit();
    }

    public void loadFragmentByIntent(String type){
        switch (type){
            case "cart":
                Fragment fragment;
                fragment = new CartFragment();
                toolbar.setTitle("Cart");
                toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimary));
                loadFragment(fragment);
                navigation.setSelectedItemId(R.id.navigation_cart);
                break;
            case "home":
                Fragment fragment1;
                fragment1 = new HomeFragment();
                toolbar.setTitle("Clubs");
                toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimary));
                loadFragment(fragment1);
                navigation.setSelectedItemId(R.id.navigation_home);
                break;
            case "orders":
                Fragment fragment2;
                fragment2 = new OrdersFragment();
                toolbar.setTitle("Orders");
                toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimary));
                loadFragment(fragment2);
                navigation.setSelectedItemId(R.id.navigation_orders);
                break;

        }
    }
    private boolean removeFragFromBackStack() {
        try {
            FragmentManager manager = getSupportFragmentManager();
            List<Fragment> fragsList = manager.getFragments();
            if (fragsList.size() == 0) {
                return true;
            }
            manager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void clearFragmentBackStack() {
        FragmentManager fm = getSupportFragmentManager();
        for (int i = 0; i < fm.getBackStackEntryCount() - 1; i++) {
            fm.popBackStack();
        }
    }
    public void getUserCartList(final String paymentID, final String orderId, final String signature) {

        final String url = WayUPartyConstants.TEST_URL+WayUPartyConstants.URL_GET_USER_CAARTLIST+"?userUUID="+ WUPPreferences.getUserId();


        Log.d("paramscheck", "" + url);

        Ion.with(this)
                .load("GET",url)
                .setLogging("jananetha", Log.DEBUG)
                .followRedirect(false)
                //.progressDialog(jananethaApplication.showProgress())
                .addHeader("X-Username", WUPPreferences.getUserName())
                .addHeader("X-Password",WUPPreferences.getPassword())
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
                            if(getCartLists.getData() != null) {
                                if (getCartLists.getData().size() > 0) {
                                    int total_amount = 0;
                                    for(int i=0;i<getCartLists.getData().size();i++){
                                        total_amount = total_amount+getCartLists.getData().get(i).getTotalAmount();
                                        callplaceOrder(paymentID,orderId,signature);

                                    }


                                }else {

                                }
                            }else {

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
                            Fragment fragment;
                            fragment = new CartFragment();
                            toolbar.setTitle("Cart");
                            toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimary));
                            loadFragment(fragment);
                            navigation.setSelectedItemId(R.id.navigation_cart);
                            // progressBar.setVisibility(View.GONE);
                            //Log.d("jsonstring",getCartList.getData().size()+"");

                        }catch (Exception t){
                            t.printStackTrace();
                        }
                    }
                });
    }

    @Override
    public void onPaymentSuccess(String s, PaymentData paymentData) {
        getUserCartList(paymentData.getPaymentId(),paymentData.getOrderId(),paymentData.getSignature());
    }

    @Override
    public void onPaymentError(int i, String s, PaymentData paymentData) {

    }

    public void showLogoutDialog(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);

        // Setting Dialog Title
        alertDialog.setTitle("Logout");

        // Setting Dialog Message
        alertDialog.setMessage("Are you sure you want logout");

        // Setting Icon to Dialog
        //alertDialog.setIcon(R.drawable.delete);

        // Setting Positive "Yes" Button
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {
                SharedPreferences preferences = WUPPreferences.getSharedPreferences();
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.apply();
                Intent intent = new Intent(MainActivity.this,LoginAcitvity.class);
                startActivity(intent);
                drawerLayout.closeDrawer(Gravity.LEFT);
                //finish();

                // Write your code here to invoke YES event
                //Toast.makeText(getApplicationContext(), "You clicked on YES", Toast.LENGTH_SHORT).show();
            }
        });

        // Setting Negative "NO" Button
        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to invoke NO event
                //Toast.makeText(getApplicationContext(), "You clicked on NO", Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }
}
