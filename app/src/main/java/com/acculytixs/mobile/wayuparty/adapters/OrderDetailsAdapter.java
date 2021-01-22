package com.acculytixs.mobile.wayuparty.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.acculytixs.mobile.wayuparty.R;
import com.acculytixs.mobile.wayuparty.activitys.AddToCartActivity;
import com.acculytixs.mobile.wayuparty.activitys.MainActivity;
import com.acculytixs.mobile.wayuparty.activitys.OrderDetialsActivity;
import com.acculytixs.mobile.wayuparty.activitys.OrderRescheduleActivity;
import com.acculytixs.mobile.wayuparty.application.WUPPreferences;
import com.acculytixs.mobile.wayuparty.application.WayUPartyConstants;
import com.acculytixs.mobile.wayuparty.dtos.AddToCartResponse;
import com.acculytixs.mobile.wayuparty.dtos.DataOrderList;
import com.acculytixs.mobile.wayuparty.dtos.GetCartList;
import com.acculytixs.mobile.wayuparty.dtos.UserOrderListResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

public class OrderDetailsAdapter extends RecyclerView.Adapter<OrderDetailsAdapter.CartViewholder> {

    Context context;
    DataOrderList getCartList;
    ArrayList<String> orderItems ;
    ArrayList<String> orderRate ;
    ArrayList<String> orderStatus ;
    ArrayList<String> orderUUIDS ;
    ArrayList<String> itemsCancelStatus;
    ArrayList<String> itemsreschuduleStatus;
    public OrderDetailsAdapter(Context context, ArrayList<String> orderItems,ArrayList<String> orderRate,ArrayList<String> orderStatus, ArrayList<String> orderUUIDS, ArrayList<String> itemsCancelStatus, ArrayList<String> itemsreschuduleStatus) {
        this.context = context;
        this.getCartList =getCartList;
        this.orderItems = orderItems;
        this.orderRate = orderRate;
        this.orderStatus = orderStatus;
        this.orderUUIDS = orderUUIDS;
        this.itemsCancelStatus = itemsCancelStatus;
        this.itemsreschuduleStatus = itemsreschuduleStatus;
    }


    @NonNull
    @Override
    public OrderDetailsAdapter.CartViewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.order_detail_list,viewGroup,false);
        OrderDetailsAdapter.CartViewholder galleryViewholder = new OrderDetailsAdapter.CartViewholder(view);
        return galleryViewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderDetailsAdapter.CartViewholder viewHolder, final int i) {

        viewHolder.tv_tittle.setText(orderItems.get(i));
        viewHolder.tv_amount.setText("Rs."+String.valueOf(orderRate.get(i)));
        viewHolder.tv_status.setText(String.valueOf(orderStatus.get(i)));

        switch (orderStatus.get(i)){
            case "Pending":
                //viewHolder.linearLayout.setVisibility(View.VISIBLE);
                if(itemsCancelStatus.get(i).equals("Y")){
                    viewHolder.tv_items_cancel.setVisibility(View.VISIBLE);
                }else{
                    viewHolder.tv_items_cancel.setVisibility(View.GONE);
                }

                if(itemsreschuduleStatus.get(i).equals("Y")){
                    viewHolder.tv_items_reschedule.setVisibility(View.VISIBLE);
                }else{
                    viewHolder.tv_items_reschedule.setVisibility(View.GONE);
                }
                viewHolder.tv_items_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showDialog(orderUUIDS.get(i));
                    }
                });
                viewHolder.ll_status.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
                viewHolder.tv_items_reschedule.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startRescheduleActivity(orderUUIDS.get(i));
                    }
                });
                break;
            case "Canceled":
                if(itemsCancelStatus.get(i).equals("Y")){
                    viewHolder.tv_items_cancel.setVisibility(View.VISIBLE);
                }else{
                    viewHolder.tv_items_cancel.setVisibility(View.GONE);
                }

                if(itemsreschuduleStatus.get(i).equals("Y")){
                    viewHolder.tv_items_reschedule.setVisibility(View.VISIBLE);
                }else{
                    viewHolder.tv_items_reschedule.setVisibility(View.GONE);
                }
                viewHolder.tv_items_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showDialog(orderUUIDS.get(i));
                    }
                });
               // viewHolder.linearLayout.setVisibility(View.VISIBLE);
                viewHolder.ll_status.setBackgroundColor(context.getResources().getColor(R.color.already_have_ac_text_color));

                viewHolder.tv_items_reschedule.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startRescheduleActivity(orderUUIDS.get(i));
                    }
                });
                break;
            case "Approved":
                if(itemsCancelStatus.get(i).equals("Y")){
                    viewHolder.tv_items_cancel.setVisibility(View.VISIBLE);
                }else{
                    viewHolder.tv_items_cancel.setVisibility(View.GONE);
                }

                if(itemsreschuduleStatus.get(i).equals("Y")){
                    viewHolder.tv_items_reschedule.setVisibility(View.VISIBLE);
                }else{
                    viewHolder.tv_items_reschedule.setVisibility(View.GONE);
                }
                viewHolder.tv_items_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showDialog(orderUUIDS.get(i));
                    }
                });
                //viewHolder.linearLayout.setVisibility(View.VISIBLE);
                viewHolder.ll_status.setBackgroundColor(context.getResources().getColor(R.color.green));
                viewHolder.tv_items_reschedule.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       startRescheduleActivity(orderUUIDS.get(i));
                    }
                });
                break;

        }



    }


    @Override
    public int getItemCount() {
        return orderItems.size();
    }

    public static class CartViewholder extends RecyclerView.ViewHolder{

        TextView tv_tittle,tv_amount,tv_status;
        LinearLayout linearLayout,ll_status;
        ImageView tv_items_cancel,tv_items_reschedule;

        public CartViewholder(@NonNull View itemView) {
            super(itemView);
            tv_tittle = (TextView)itemView.findViewById(R.id.tv_item_name);
            tv_amount = (TextView)itemView.findViewById(R.id.tv_amount);
            tv_status = (TextView)itemView.findViewById(R.id.tv_status);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.ll_cancel_option);
            ll_status = (LinearLayout) itemView.findViewById(R.id.ll_status);
            tv_items_cancel = (ImageView)itemView.findViewById(R.id.items_cancelled);
            tv_items_reschedule = (ImageView)itemView.findViewById(R.id.items_reschedule);

        }
    }

    private void cancelOrder(String UUID) {

        final String url = WayUPartyConstants.TEST_URL+WayUPartyConstants.URL_CANCEL_ORDER+"?orderUUID="+UUID;


        Log.d("paramscheck", "" + url);

        Ion.with(context)
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
                            AddToCartResponse addToCartResponse = new Gson().fromJson(result.getResult(), new TypeToken<AddToCartResponse>() {
                            }.getType());
                            // progressBar.setVisibility(View.GONE);
                            //Log.d("jsonstring",getCartList.getData().size()+"");

                            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                            alertDialogBuilder.setMessage(addToCartResponse.getResponseMessage());
                            alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                    Intent intent = new Intent(context, MainActivity.class);
                                    intent.putExtra("fragment","orders");
                                    context.startActivity(intent);
                                    dialogInterface.cancel();
                                }
                            });
                            final AlertDialog alertDialog = alertDialogBuilder.create();
                            alertDialog.show();
                            final Button positiveButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                            LinearLayout.LayoutParams positiveButtonLL = (LinearLayout.LayoutParams) positiveButton.getLayoutParams();
                            positiveButtonLL.gravity = Gravity.CENTER;
                            positiveButton.setLayoutParams(positiveButtonLL);


                        }catch (Exception t){
                            t.printStackTrace();
                        }
                    }
                });
    }

  public void showDialog(final String uuid){
      final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

      alertDialogBuilder.setMessage("Are you sure want to cancel the item");
      alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialogInterface, int i) {
              dialogInterface.cancel();
          }
      });
      alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialogInterface, int i) {
              cancelOrder(uuid);
          }
      });
      final AlertDialog alertDialog = alertDialogBuilder.create();
      alertDialog.show();
      final Button positiveButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
      LinearLayout.LayoutParams positiveButtonLL = (LinearLayout.LayoutParams) positiveButton.getLayoutParams();
      positiveButtonLL.gravity = Gravity.CENTER;
      positiveButton.setLayoutParams(positiveButtonLL);
  }

   public void startRescheduleActivity(String UUID){
        Intent intent = new Intent(context, OrderRescheduleActivity.class);
        intent.putExtra("orderUUID",UUID);
        context.startActivity(intent);
   }

}
