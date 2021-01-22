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
import android.widget.TextView;

import com.acculytixs.mobile.wayuparty.R;
import com.acculytixs.mobile.wayuparty.activitys.AddToCartActivity;
import com.acculytixs.mobile.wayuparty.application.WayUPartyConstants;
import com.acculytixs.mobile.wayuparty.dtos.EventTicketList;
import com.acculytixs.mobile.wayuparty.dtos.GetCategoryServiceList;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;
import me.himanshusoni.quantityview.QuantityView;

public class EventTicketAdapter extends RecyclerView.Adapter<EventTicketAdapter.CategoryServicesListViewholder> {

    Context context;
    EventTicketList getCategoryServiceList;
    int selectedPosition=-1;
    public EventTicketAdapter(Context context,EventTicketList getCategoryServiceList) {
        this.context = context;
        this.getCategoryServiceList =getCategoryServiceList;
    }


    @NonNull
    @Override
    public EventTicketAdapter.CategoryServicesListViewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.row_event_ticket,viewGroup,false);
        EventTicketAdapter.CategoryServicesListViewholder galleryViewholder = new EventTicketAdapter.CategoryServicesListViewholder(view);
        return galleryViewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull final EventTicketAdapter.CategoryServicesListViewholder viewHolder, final int i) {

        viewHolder.tv_tittle.setText(getCategoryServiceList.getData().get(i).getTicketType());
        viewHolder.quantityView.setMaxQuantity(getCategoryServiceList.getData().get(i).getMaxBookingAllowed());
        viewHolder.quantityView.setOnQuantityChangeListener(new QuantityView.OnQuantityChangeListener() {
            @Override
            public void onQuantityChanged(int oldQuantity, int newQuantity, boolean programmatically) {
                Intent intent = new Intent("custom-message");
                //            intent.putExtra("quantity",Integer.parseInt(quantity.getText().toString()));
                intent.putExtra("quantity",String.valueOf(newQuantity));
                intent.putExtra("item", viewHolder.tv_tittle.getText().toString());
                intent.putExtra("amount",getCategoryServiceList.getData().get(i).getTicketAmount());
                intent.putExtra("currency",getCategoryServiceList.getData().get(i).getCurrency());

                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }

            @Override
            public void onLimitReached() {

            }
        });
        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //viewHolder.quantityView.setVisibility(View.VISIBLE);
                selectedPosition=i;
                notifyDataSetChanged();
            }
        });

        if(selectedPosition==i)
            viewHolder.quantityView.setVisibility(View.VISIBLE);
        else {
            viewHolder.quantityView.setVisibility(View.GONE);
            viewHolder.quantityView.setQuantity(0);
        }



    }


    @Override
    public int getItemCount() {
        return getCategoryServiceList.getData().size();
    }

    public static class CategoryServicesListViewholder extends RecyclerView.ViewHolder{

        TextView tv_tittle;
        LinearLayout linearLayout;
        QuantityView quantityView;
        CardView cardView;

        public CategoryServicesListViewholder(@NonNull View itemView) {
            super(itemView);
            tv_tittle = (TextView)itemView.findViewById(R.id.tv_ticket_tittle);
            quantityView = (QuantityView)itemView.findViewById(R.id.quantityView_ticket);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.mainlayoutprofile);
            cardView = (CardView) itemView.findViewById(R.id.cv_rest);

        }
    }
}
