package com.acculytixs.mobile.wayuparty.adapters;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.acculytixs.mobile.wayuparty.R;
import com.acculytixs.mobile.wayuparty.activitys.RestaurantDetailActivity;
import com.acculytixs.mobile.wayuparty.activitys.ServicesActivity;
import com.acculytixs.mobile.wayuparty.application.WayUPartyConstants;
import com.acculytixs.mobile.wayuparty.dtos.GetAllRestaurants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class RestaurantsAdapter extends RecyclerView.Adapter<RestaurantsAdapter.RestaurantViewholder> {

    Context context;
    GetAllRestaurants getAllRestaurants;
    public RestaurantsAdapter(Context context,GetAllRestaurants getAllRestaurants) {
        this.context = context;
        this.getAllRestaurants =getAllRestaurants;
    }


    @NonNull
    @Override
    public RestaurantViewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.restaurant_item_row,viewGroup,false);
        RestaurantsAdapter.RestaurantViewholder galleryViewholder = new RestaurantsAdapter.RestaurantViewholder(view);
        return galleryViewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewholder viewHolder, final int i) {

        viewHolder.tv_rest_name.setText(getAllRestaurants.getData().get(i).getVendorName());
        viewHolder.tv_address.setText(getAllRestaurants.getData().get(i).getLocation());
        viewHolder.tv_amount.setText("Cost for two Rs."+getAllRestaurants.getData().get(i).getCostForTwoPeople());
        if (!TextUtils.isEmpty(getAllRestaurants.getData().get(i).getVendorProfileImg())) {
            Log.d("imagaeurl", "" + getAllRestaurants.getData().get(i).getVendorProfileImg());
            String url = WayUPartyConstants.TEST_URL+getAllRestaurants.getData().get(i).getVendorProfileImg();
            Picasso.with(context)
                    .load(url)
                    .into(viewHolder.imageView);
        } else {
            //viewHolder.imageView.setImageResource(R.drawable.sample_image);
        }
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, RestaurantDetailActivity.class);
                intent.putExtra("RestData",getAllRestaurants.getData().get(i));
                context.startActivity(intent);
            }
        });
        viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ServicesActivity.class);
                intent.putExtra("vendorUUID",getAllRestaurants.getData().get(i).getVendorUUID());
                context.startActivity(intent);
            }
        });



    }


    @Override
    public int getItemCount() {
        return getAllRestaurants.getData().size();
    }

    public static class RestaurantViewholder extends RecyclerView.ViewHolder{

        TextView tv_rest_name,tv_amount,tv_address;
        ImageView imageView;
        RelativeLayout relativeLayout;
        CardView cardView;

        public RestaurantViewholder(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView)itemView.findViewById(R.id.iv_restuarant);
            tv_rest_name = (TextView)itemView.findViewById(R.id.tv_res_name);
            tv_amount = (TextView)itemView.findViewById(R.id.tv_amount);
            tv_address = (TextView)itemView.findViewById(R.id.tv_location);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.first_rl);
            cardView = (CardView) itemView.findViewById(R.id.cv_rest);

        }
    }
}
