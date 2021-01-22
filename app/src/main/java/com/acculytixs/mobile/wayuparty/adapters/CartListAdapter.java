package com.acculytixs.mobile.wayuparty.adapters;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.acculytixs.mobile.wayuparty.R;
import com.acculytixs.mobile.wayuparty.activitys.AddToCartActivity;
import com.acculytixs.mobile.wayuparty.activitys.MainActivity;
import com.acculytixs.mobile.wayuparty.activitys.RestaurantDetailActivity;
import com.acculytixs.mobile.wayuparty.activitys.ServicesActivity;
import com.acculytixs.mobile.wayuparty.application.WUPPreferences;
import com.acculytixs.mobile.wayuparty.application.WayUPartyConstants;
import com.acculytixs.mobile.wayuparty.dtos.Datuc;
import com.acculytixs.mobile.wayuparty.dtos.GetAllRestaurants;
import com.acculytixs.mobile.wayuparty.dtos.GetCartList;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.CartViewholder> {

    Context context;
    GetCartList getCartList;
    ArrayList<Datuc> data ;
    public CartListAdapter(Context context,GetCartList getCartList) {
        this.context = context;
        this.getCartList =getCartList;
        data = (ArrayList<Datuc>) getCartList.getData();
}


    @NonNull
    @Override
    public CartListAdapter.CartViewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.cart_item,viewGroup,false);
        CartListAdapter.CartViewholder galleryViewholder = new CartListAdapter.CartViewholder(view);
        return galleryViewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull CartListAdapter.CartViewholder viewHolder, final int i) {

        viewHolder.tv_tittle.setText(data.get(i).getServiceName());
        viewHolder.tv_amount.setText("Rs."+String.valueOf(data.get(i).getOrderAmount()));
        viewHolder.tv_quantity.setText("Quantity : "+String.valueOf(data.get(i).getQuantity()));
        viewHolder.tv_final_price.setText("Amount : Rs."+String.valueOf(data.get(i).getTotalAmount()));
        viewHolder.tv_date.setText(data.get(i).getServiceOrderDate());
        viewHolder.tv_time.setText(data.get(i).getTimeSlot());
        if (!TextUtils.isEmpty(data.get(i).getServiceImage())) {
            Log.d("imagaeurl", "" + data.get(i).getServiceImage());
            String url = WayUPartyConstants.TEST_URL+data.get(i).getServiceImage();
            Picasso.with(context)
                    .load(url)
                    .into(viewHolder.imageView);
        } else {
            //viewHolder.imageView.setImageResource(R.drawable.sample_image);
        }
        viewHolder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             removeCartItem(i);
            }
        });


    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class CartViewholder extends RecyclerView.ViewHolder{

        TextView tv_tittle,tv_amount,tv_quantity,tv_final_price,tv_date,tv_time;
        ImageView imageView;
        RelativeLayout relativeLayout;
        CardView cardView;
        Button remove;

        public CartViewholder(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView)itemView.findViewById(R.id.thumb_image);
            tv_tittle = (TextView)itemView.findViewById(R.id.tv_category_tittle);
            tv_amount = (TextView)itemView.findViewById(R.id.tv_price);
            tv_quantity = (TextView)itemView.findViewById(R.id.tv_quantity);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.first_rl);
            cardView = (CardView) itemView.findViewById(R.id.cv_rest);
            tv_final_price = (TextView)itemView.findViewById(R.id.tv_amount);
            tv_date = (TextView)itemView.findViewById(R.id.tv_date);
            tv_time = (TextView)itemView.findViewById(R.id.tv_time);
            remove = (Button) itemView.findViewById(R.id.btn_remove);


        }
    }

    private void removeCartItem(final int pos) {

        final String url = WayUPartyConstants.TEST_URL+WayUPartyConstants.URL_REMOVE_CART_ITEM+"?cartUUID="+data.get(pos).getCartUUID();


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
                            Intent intent = new Intent(context,MainActivity.class);
                            intent.putExtra("fragment","cart");
                            context.startActivity(intent);

                        }catch (Exception t){
                            t.printStackTrace();
                        }
                    }
                });
    }

}
