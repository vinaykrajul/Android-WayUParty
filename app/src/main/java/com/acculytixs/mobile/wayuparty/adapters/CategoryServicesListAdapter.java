package com.acculytixs.mobile.wayuparty.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.acculytixs.mobile.wayuparty.R;
import com.acculytixs.mobile.wayuparty.activitys.AddToCartActivity;
import com.acculytixs.mobile.wayuparty.activitys.RestaurantDetailActivity;
import com.acculytixs.mobile.wayuparty.activitys.ServicesActivity;
import com.acculytixs.mobile.wayuparty.application.WayUPartyConstants;
import com.acculytixs.mobile.wayuparty.dtos.GetCategoryServiceList;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class CategoryServicesListAdapter extends RecyclerView.Adapter<CategoryServicesListAdapter.CategoryServicesListViewholder> {

    Context context;
    GetCategoryServiceList getCategoryServiceList;
    public CategoryServicesListAdapter(Context context,GetCategoryServiceList getCategoryServiceList) {
        this.context = context;
        this.getCategoryServiceList =getCategoryServiceList;
    }


    @NonNull
    @Override
    public CategoryServicesListAdapter.CategoryServicesListViewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.row_category_service,viewGroup,false);
        CategoryServicesListAdapter.CategoryServicesListViewholder galleryViewholder = new CategoryServicesListAdapter.CategoryServicesListViewholder(view);
        return galleryViewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryServicesListAdapter.CategoryServicesListViewholder viewHolder, final int i) {

        viewHolder.tv_tittle.setText(getCategoryServiceList.getData().get(i).getSubCategory());
        viewHolder.tv_amount.setText("Rs."+String.valueOf(getCategoryServiceList.getData().get(i).getOfferPrice()));
        viewHolder.tv_discount.setText(String.valueOf(getCategoryServiceList.getData().get(i).getActualPrice()));
        viewHolder.tv_discount.setPaintFlags(viewHolder.tv_discount.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        Double vl = (getCategoryServiceList.getData().get(i).getActualPrice())-(getCategoryServiceList.getData().get(i).getOfferPrice());
        viewHolder.tv_save.setText("Save Rs."+String.valueOf(vl));
        viewHolder.tv_guest.setText("Guests Allowed :"+String.valueOf(getCategoryServiceList.getData().get(i).getAllowed()));
        viewHolder.tv_availability.setText("Availability :"+convertDate(getCategoryServiceList.getData().get(i).getServiceStartDate())+"-"+convertDate(getCategoryServiceList.getData().get(i).getServiceEndDate()));
        if (!TextUtils.isEmpty(getCategoryServiceList.getData().get(i).getServiceImage())) {
            Log.d("imagaeurl", "" + getCategoryServiceList.getData().get(i).getServiceImage());
            String url = WayUPartyConstants.TEST_URL+getCategoryServiceList.getData().get(i).getServiceImage();
            Picasso.with(context)
                    .load(url)
                    .into(viewHolder.imageView);
        } else {
            //viewHolder.imageView.setImageResource(R.drawable.sample_image);
        }
       /* viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
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
        });*/
        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AddToCartActivity.class);
                intent.putExtra("data",getCategoryServiceList.getData().get(i));
                context.startActivity(intent);
            }
        });



    }


    @Override
    public int getItemCount() {
        return getCategoryServiceList.getData().size();
    }

    public static class CategoryServicesListViewholder extends RecyclerView.ViewHolder{

        TextView tv_tittle,tv_amount,tv_discount,tv_guest,tv_availability,tv_save;
        ImageView imageView;
        LinearLayout linearLayout;
        CardView cardView;

        public CategoryServicesListViewholder(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView)itemView.findViewById(R.id.thumb_image);
            tv_tittle = (TextView)itemView.findViewById(R.id.tv_category_tittle);
            tv_discount = (TextView)itemView.findViewById(R.id.tv_discount);
            tv_save = (TextView)itemView.findViewById(R.id.tv_save);
            tv_amount = (TextView)itemView.findViewById(R.id.tv_price);
            tv_guest = (TextView)itemView.findViewById(R.id.tv_Guests);
            tv_availability = (TextView)itemView.findViewById(R.id.tv_availabilty);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.mainlayoutprofile);
            cardView = (CardView) itemView.findViewById(R.id.cv_rest);

        }
    }
    public String convertDate(String cdate){
        SimpleDateFormat month_date = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String actualDate = cdate;
        String month_name = "";
        try {
            Date date = sdf.parse(actualDate);
            month_name = month_date.format(date);
            System.out.println("Month :" + month_name);

        }catch (Exception e){

        }


        return month_name;

    }
}
