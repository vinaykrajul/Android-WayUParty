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
import com.acculytixs.mobile.wayuparty.activitys.EventsDetailActivity;
import com.acculytixs.mobile.wayuparty.activitys.RestaurantDetailActivity;
import com.acculytixs.mobile.wayuparty.activitys.ServicesActivity;
import com.acculytixs.mobile.wayuparty.application.WayUPartyConstants;
import com.acculytixs.mobile.wayuparty.dtos.GetAllRestaurants;
import com.acculytixs.mobile.wayuparty.dtos.VendorEventsResonse;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class VendorEventsAdapter extends RecyclerView.Adapter<VendorEventsAdapter.RestaurantViewholder> {

    Context context;
    VendorEventsResonse getAllRestaurants;
    public VendorEventsAdapter(Context context,VendorEventsResonse getAllRestaurants) {
        this.context = context;
        this.getAllRestaurants =getAllRestaurants;
    }


    @NonNull
    @Override
    public VendorEventsAdapter.RestaurantViewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.vendor_events_row_item,viewGroup,false);
        VendorEventsAdapter.RestaurantViewholder galleryViewholder = new VendorEventsAdapter.RestaurantViewholder(view);
        return galleryViewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull VendorEventsAdapter.RestaurantViewholder viewHolder, final int i) {

        viewHolder.tv_day.setText(getAllRestaurants.getData().get(i).getDay());
        viewHolder.tv_date.setText(String.valueOf(getAllRestaurants.getData().get(i).getDate()));
        viewHolder.tv_month.setText(getAllRestaurants.getData().get(i).getMonth());
        viewHolder.tv_time.setText(getAllRestaurants.getData().get(i).getTime());
        viewHolder.tv_location.setText(getAllRestaurants.getData().get(i).getEventHost()+","+getAllRestaurants.getData().get(i).getEventLocation());
        if (!TextUtils.isEmpty(getAllRestaurants.getData().get(i).getEventImage())) {
            Log.d("imagaeurl", "" + getAllRestaurants.getData().get(i).getEventImage());
            String url = WayUPartyConstants.TEST_URL+getAllRestaurants.getData().get(i).getEventImage();
            Picasso.with(context)
                    .load(url)
                    .into(viewHolder.imageView);
        } else {
            //viewHolder.imageView.setImageResource(R.drawable.sample_image);
        }
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EventsDetailActivity.class);
                intent.putExtra("eventUUID",getAllRestaurants.getData().get(i).getEventUUID());
                context.startActivity(intent);
            }
        });


    }


    @Override
    public int getItemCount() {
        return getAllRestaurants.getData().size();
    }

    public static class RestaurantViewholder extends RecyclerView.ViewHolder{

        TextView tv_day,tv_date,tv_month,tv_time,tv_location;
        ImageView imageView;
        CardView cardView;

        public RestaurantViewholder(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView)itemView.findViewById(R.id.iv_event);
            tv_day = (TextView)itemView.findViewById(R.id.tv_day);
            tv_date = (TextView)itemView.findViewById(R.id.date);
            tv_location = (TextView)itemView.findViewById(R.id.tv_location);
            tv_month = (TextView)itemView.findViewById(R.id.month);
            tv_time = (TextView)itemView.findViewById(R.id.tv_time);
            cardView = (CardView) itemView.findViewById(R.id.cv_rest);

        }
    }
}
