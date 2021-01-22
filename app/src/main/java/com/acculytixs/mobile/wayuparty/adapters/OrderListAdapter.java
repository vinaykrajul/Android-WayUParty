package com.acculytixs.mobile.wayuparty.adapters;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.acculytixs.mobile.wayuparty.R;
import com.acculytixs.mobile.wayuparty.activitys.MainActivity;
import com.acculytixs.mobile.wayuparty.activitys.OrderDetialsActivity;
import com.acculytixs.mobile.wayuparty.application.WayUPartyConstants;
import com.acculytixs.mobile.wayuparty.dtos.DataOrderList;
import com.acculytixs.mobile.wayuparty.dtos.Datuc;
import com.acculytixs.mobile.wayuparty.dtos.GetCartList;
import com.acculytixs.mobile.wayuparty.dtos.UserOrderListResponse;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.CartViewholder> {

    Context context;
    UserOrderListResponse getCartList;
    ArrayList<DataOrderList> data ;
    public OrderListAdapter(Context context,UserOrderListResponse getCartList) {
        this.context = context;
        this.getCartList =getCartList;
        data = (ArrayList<DataOrderList>) getCartList.getData();
    }


    @NonNull
    @Override
    public OrderListAdapter.CartViewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.order_list_item,viewGroup,false);
        OrderListAdapter.CartViewholder galleryViewholder = new OrderListAdapter.CartViewholder(view);
        return galleryViewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderListAdapter.CartViewholder viewHolder, final int i) {

        viewHolder.tv_tittle.setText(data.get(i).getClubName());
        viewHolder.tv_amount.setText("Rs."+String.valueOf(data.get(i).getTotalAmount()));
        viewHolder.tv_city_name.setText(String.valueOf(data.get(i).getClubLocation()));

        if (data.get(i).getCanceledOrdersCount()>0){
            viewHolder.linearLayout.setVisibility(View.VISIBLE);
            viewHolder.tv_items_canceeled.setText(String.valueOf(data.get(i).getCanceledOrdersCount())+" Items Cancelled");
        }else {
            viewHolder.linearLayout.setVisibility(View.GONE);

        }

        viewHolder.tv_order_date.setText(data.get(i).getOrderDate());
        viewHolder.tv_order_items.setText(data.get(i).getOrderItems());

        viewHolder.ll_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, OrderDetialsActivity.class);
                intent.putExtra("orderData",data.get(i));
                context.startActivity(intent);
            }
        });
       /* if (!TextUtils.isEmpty(data.get(i).getServiceImage())) {
            Log.d("imagaeurl", "" + data.get(i).getServiceImage());
            String url = WayUPartyConstants.TEST_URL+data.get(i).getServiceImage();
            Picasso.with(context)
                    .load(url)
                    .into(viewHolder.imageView);
        } else {
            //viewHolder.imageView.setImageResource(R.drawable.sample_image);
        }*/


    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class CartViewholder extends RecyclerView.ViewHolder{

        TextView tv_tittle,tv_amount,tv_city_name,tv_items_canceeled,tv_order_date,tv_order_items;
        ImageView imageView;
        LinearLayout linearLayout,ll_parent;

        public CartViewholder(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView)itemView.findViewById(R.id.thumb_image);
            linearLayout = (LinearLayout)itemView.findViewById(R.id.ll_cancelleditems);
            ll_parent = (LinearLayout)itemView.findViewById(R.id.ll_parent);
            tv_tittle = (TextView)itemView.findViewById(R.id.tv_res_name);
            tv_amount = (TextView)itemView.findViewById(R.id.tv_res_amount);
            tv_city_name = (TextView)itemView.findViewById(R.id.tv_city_name);
            tv_items_canceeled = (TextView)itemView.findViewById(R.id.items_cancelled);
            tv_order_date = (TextView)itemView.findViewById(R.id.order_date);
            tv_order_items = (TextView)itemView.findViewById(R.id.tv_order_items);

        }
    }


}
