package com.acculytixs.mobile.wayuparty.adapters;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.acculytixs.mobile.wayuparty.R;
import com.acculytixs.mobile.wayuparty.application.WayUPartyConstants;
import com.acculytixs.mobile.wayuparty.dtos.GetVendorInfo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    ArrayList<String> list = new ArrayList<>();
    GetVendorInfo getVendorInfo;
    RecyclerViewItemClickListener recyclerViewItemClickListener;
    Context context;
    String tittle;
    String[] menulist = {"Basic","Address","Category","Menu","Facilities","Music","Cuisine","Working Hours","Terms & Conditions","Gallery","Review&Ratings"};


    public DataAdapter( ArrayList<String> list, Context context,String tittle) {
        this.context = context;
        this.list = list;
        this.tittle=tittle;
        //this.getVendorInfo = getVendorInfo;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = null;
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(context);
        switch(tittle){
            case "Basic":
                 v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_dialog_item, parent, false);
                viewHolder = new FruitViewHolder(v);
                break;
            case "Address":
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_dialog_item, parent, false);
                viewHolder = new FruitViewHolder(v);
                break;
            case "Category":
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_dialog_item, parent, false);
                viewHolder = new FruitViewHolder(v);

                break;
            case "Menu":
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_dialog_menu, parent, false);
                viewHolder = new MenuViewHolder(v);
                break;
            case "Facilities":
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_dialog_item, parent, false);
                viewHolder = new FruitViewHolder(v);
                break;
            case "Music":
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_dialog_item, parent, false);
                viewHolder = new FruitViewHolder(v);
                break;
            case "Cuisine":
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_dialog_item, parent, false);
                viewHolder = new FruitViewHolder(v);
                break;
            case "Working Hours":
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_dialog_item, parent, false);
                viewHolder = new FruitViewHolder(v);
                break;
            case "Terms & Conditions":
                break;
            case "Gallery":
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_dialog_menu, parent, false);
                viewHolder = new MenuViewHolder(v);
                break;
            case "Review&Ratings":
                break;


        }

        //FruitViewHolder vh = new FruitViewHolder(v);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

         if(holder instanceof FruitViewHolder){
             final FruitViewHolder fruitViewHolder = (FruitViewHolder) holder;
             switch(tittle){
                 case "Basic":
                     fruitViewHolder.mTextView.setText(list.get(position));
                     break;
                 case "Address":
                     fruitViewHolder.mTextView.setText(list.get(position));
                     break;
                 case "Category":
                     fruitViewHolder.mTextView.setText(list.get(position));
                     break;
                 case "Menu":

                     break;
                 case "Facilities":
                     fruitViewHolder.mTextView.setText(list.get(position));
                     break;
                 case "Music":
                     fruitViewHolder.mTextView.setText(list.get(position));
                     break;
                 case "Cuisine":
                     fruitViewHolder.mTextView.setText(list.get(position));
                     break;
                 case "Working Hours":
                     fruitViewHolder.mTextView.setText(list.get(position));
                     break;
                 case "Terms & Conditions":
                     break;
                 case "Gallery":
                     break;
                 case "Review&Ratings":
                     break;


             }
         }else {
             if(holder instanceof MenuViewHolder){
                 final MenuViewHolder menuViewHolder = (MenuViewHolder) holder;
                 if (!TextUtils.isEmpty(list.get(position))) {
                     Log.d("imagaeurl", "" + list.get(position));
                     String url = WayUPartyConstants.TEST_URL+list.get(position);
                     Picasso.with(context)
                             .load(url)
                             .into(menuViewHolder.imageView);
                 } else {
                     //viewHolder.imageView.setImageResource(R.drawable.sample_image);
                 }

             }
         }

    }


    @Override
    public int getItemCount() {
        return list.size();
    }



    public  class FruitViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView mTextView;

        public FruitViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.textView);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //recyclerViewItemClickListener.clickOnItem(list.get(this.getAdapterPosition()));

        }
    }
    public  class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView imageView;

        public MenuViewHolder(View v) {
            super(v);
            imageView = (ImageView) v.findViewById(R.id.image_view);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //recyclerViewItemClickListener.clickOnItem(list.get(this.getAdapterPosition()));

        }
    }

    public interface RecyclerViewItemClickListener {
        void clickOnItem(String data);
    }
}
