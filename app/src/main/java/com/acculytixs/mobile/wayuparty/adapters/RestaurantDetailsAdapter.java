package com.acculytixs.mobile.wayuparty.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.acculytixs.mobile.wayuparty.R;
import com.acculytixs.mobile.wayuparty.activitys.TermsAndConditionsAcitivity;
import com.acculytixs.mobile.wayuparty.application.CustomDialog;
import com.acculytixs.mobile.wayuparty.dtos.GetVendorInfo;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RestaurantDetailsAdapter extends RecyclerView.Adapter<RestaurantDetailsAdapter.RDAViewHolder> implements DataAdapter.RecyclerViewItemClickListener {

    Context context;
    GetVendorInfo getVendorInfo;
    CustomDialog customDialog;
    ArrayList<String> list = new ArrayList<>();
    String[] menulist = {"Basic","Address","Category","Menu","Facilities","Music","Cuisine","Working Hours","Terms & Conditions","Gallery","Review&Ratings"};
    int[] icons={R.drawable.basic,R.drawable.adress,R.drawable.category,R.drawable.nav_menu_icon,R.drawable.facilities,R.drawable.music,R.drawable.cuisine,R.drawable.workinghrs,R.drawable.terms,R.drawable.gallery,R.drawable.ratings};

    public RestaurantDetailsAdapter(Context context,GetVendorInfo getVendorInfo) {
        this.context = context;
        this.getVendorInfo = getVendorInfo;


    }

    @NonNull
    @Override
    public RestaurantDetailsAdapter.RDAViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_restadapter, viewGroup, false);
        RestaurantDetailsAdapter.RDAViewHolder departmentSubCategoriesViewHolder = new RestaurantDetailsAdapter.RDAViewHolder(view);
        return departmentSubCategoriesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RestaurantDetailsAdapter.RDAViewHolder viewHolder, final int i) {

        viewHolder.tv_tittle.setText(menulist[i]);
        viewHolder.iv_indicator.setImageResource(icons[i]);
        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.clear();
                switch (i){
                    case 0:
                        list.add("Cost for Two :  "+getVendorInfo.getObject().getCostForTwoPeople());
                        list.add("Vendor Capacity :  "+getVendorInfo.getObject().getVendorCapacity());
                        list.add("Establishment Year : "+getVendorInfo.getObject().getEstablishedYear());
                        list.add("Best selling Items : "+getVendorInfo.getObject().getBestSellingItems());
                        list.add("Email: "+getVendorInfo.getObject().getVendorEmail());
                        DataAdapter dataAdapter = new DataAdapter(list, context,menulist[i]);
                        customDialog = new CustomDialog(context, dataAdapter,menulist[i]);
                        customDialog.show();
                        customDialog.setCanceledOnTouchOutside(true);
                        break;
                    case 1:
                        list.add("Phone No  : "+getVendorInfo.getObject().getPhoneNumber());
                        list.add("Address line  : "+getVendorInfo.getObject().getVendorAddress());
                        list.add("Location  : "+getVendorInfo.getObject().getLocation());
                        DataAdapter dataAdapter1 = new DataAdapter(list, context,menulist[i]);
                        customDialog = new CustomDialog(context, dataAdapter1,menulist[i]);
                        customDialog.show();
                        customDialog.setCanceledOnTouchOutside(true);
                        break;
                    case 2:
                        if(getVendorInfo.getObject().getCategoriesList()!= null && !getVendorInfo.getObject().getCategoriesList().isEmpty()&& getVendorInfo.getObject().getCategoriesList().size()>0) {
                            for (int i = 0; i < getVendorInfo.getObject().getCategoriesList().size(); i++) {
                                list.add(getVendorInfo.getObject().getCategoriesList().get(i));
                            }
                        }
                        DataAdapter dataAdapter2 = new DataAdapter(list, context,menulist[i]);
                        customDialog = new CustomDialog(context, dataAdapter2,menulist[i]);
                        customDialog.show();
                        customDialog.setCanceledOnTouchOutside(true);
                        break;
                    case 3:
                        if(getVendorInfo.getObject().getMenuList()!= null && !getVendorInfo.getObject().getMenuList().isEmpty()&& getVendorInfo.getObject().getMenuList().size()>0) {
                            for (int i = 0; i < getVendorInfo.getObject().getMenuList().size(); i++) {
                                list.add(getVendorInfo.getObject().getMenuList().get(i));
                            }
                        }
                        DataAdapter dataAdapter3 = new DataAdapter(list, context,menulist[i]);
                        customDialog = new CustomDialog(context, dataAdapter3,menulist[i]);
                        customDialog.show();
                        customDialog.setCanceledOnTouchOutside(true);
                        break;
                    case 4:
                        if(getVendorInfo.getObject().getFacilitiesList()!= null && !getVendorInfo.getObject().getFacilitiesList().isEmpty()&& getVendorInfo.getObject().getFacilitiesList().size()>0) {
                            for (int i = 0; i < getVendorInfo.getObject().getFacilitiesList().size(); i++) {
                                list.add(getVendorInfo.getObject().getFacilitiesList().get(i));
                            }
                        }
                        DataAdapter dataAdapter4 = new DataAdapter(list, context,menulist[i]);
                        customDialog = new CustomDialog(context, dataAdapter4,menulist[i]);
                        customDialog.show();
                        customDialog.setCanceledOnTouchOutside(true);
                        break;
                    case 5:
                        if(getVendorInfo.getObject().getMusicList()!= null && !getVendorInfo.getObject().getMusicList().isEmpty()&& getVendorInfo.getObject().getMenuList().size()>0) {
                            for (int i = 0; i < getVendorInfo.getObject().getMusicList().size(); i++) {
                                list.add(getVendorInfo.getObject().getMusicList().get(i));
                            }
                        }
                        DataAdapter dataAdapter5 = new DataAdapter(list, context,menulist[i]);
                        customDialog = new CustomDialog(context, dataAdapter5,menulist[i]);
                        customDialog.show();
                        customDialog.setCanceledOnTouchOutside(true);
                        break;
                    case 6:
                        if(getVendorInfo.getObject().getCuisineList()!= null && !getVendorInfo.getObject().getCuisineList().isEmpty()&& getVendorInfo.getObject().getCuisineList().size()>0) {
                            for (int i = 0; i < getVendorInfo.getObject().getCuisineList().size(); i++) {
                                list.add(getVendorInfo.getObject().getCuisineList().get(i));
                            }
                        }
                        DataAdapter dataAdapter6 = new DataAdapter(list, context,menulist[i]);
                        customDialog = new CustomDialog(context, dataAdapter6,menulist[i]);
                        customDialog.show();
                        customDialog.setCanceledOnTouchOutside(true);
                        break;
                    case 7:
                        if(getVendorInfo.getObject().getWorkingHoursList()!= null && !getVendorInfo.getObject().getWorkingHoursList().isEmpty()&& getVendorInfo.getObject().getWorkingHoursList().size()>0) {
                            for (int i = 0; i < getVendorInfo.getObject().getWorkingHoursList().size(); i++) {
                                list.add(getVendorInfo.getObject().getWorkingHoursList().get(i).getWorkingDay()+" : "+getVendorInfo.getObject().getWorkingHoursList().get(i).getStartTime()+" to "+getVendorInfo.getObject().getWorkingHoursList().get(i).getEndTime());
                            }
                        }
                        DataAdapter dataAdapter7 = new DataAdapter(list, context,menulist[i]);
                        customDialog = new CustomDialog(context, dataAdapter7,menulist[i]);
                        customDialog.show();
                        customDialog.setCanceledOnTouchOutside(true);
                        break;
                    case 9:
                        if(getVendorInfo.getObject().getGalleryList()!= null && !getVendorInfo.getObject().getGalleryList().isEmpty()&& getVendorInfo.getObject().getGalleryList().size()>0) {
                            for (int i = 0; i < getVendorInfo.getObject().getGalleryList().size(); i++) {
                                list.add(getVendorInfo.getObject().getGalleryList().get(i));
                            }
                        }
                        DataAdapter dataAdapter9 = new DataAdapter(list, context,menulist[i]);
                        customDialog = new CustomDialog(context, dataAdapter9,menulist[i]);
                        customDialog.show();
                        customDialog.setCanceledOnTouchOutside(true);
                        break;
                    case 8:
                       /* Intent intent = new Intent(context, TermsAndConditionsAcitivity.class);
                        intent.putExtra("vendorUUID",getVendorInfo.getObject().getVendorUUID());
                        context.startActivity(intent);*/
                        break;
                }



            }
        });
    }


    @Override
    public int getItemCount() {
        return menulist.length;
    }

    @Override
    public void clickOnItem(String data) {

        if (customDialog != null){
            customDialog.dismiss();
        }
    }

    public static class RDAViewHolder extends RecyclerView.ViewHolder {

        TextView tv_tittle;
        LinearLayout linearLayout;
        ImageView iv_indicator;

        public RDAViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.ll_deparmetns);
            tv_tittle = (TextView) itemView.findViewById(R.id.tv_service);
            iv_indicator = (ImageView) itemView.findViewById(R.id.im_profile);

        }
    }

}
