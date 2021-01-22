package com.acculytixs.mobile.wayuparty.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.acculytixs.mobile.wayuparty.R;
import com.acculytixs.mobile.wayuparty.dtos.MenuItemsList;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;


public class CustomizeChildItemAdapter extends RecyclerView.Adapter<CustomizeChildItemAdapter.ChildViewHolder> {

    private List<MenuItemsList> ChildItemList;
    int count ;
    Context context;
    int limit;
    ArrayList<String> itemUUIDs;
    // Constuctor
    CustomizeChildItemAdapter(Context context,List<MenuItemsList> childItemList,int limit, ArrayList<String> itemUUIDs)
    {
        this.ChildItemList = childItemList;
        this.context = context;
        this.limit = limit;
        this.itemUUIDs = itemUUIDs;
        Log.d("limit check",limit+"");
    }

    @NonNull
    @Override
    public ChildViewHolder onCreateViewHolder(
            @NonNull ViewGroup viewGroup,
            int i)
    {

        // Here we inflate the corresponding
        // layout of the child item
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.customize_child_item, viewGroup, false);

        return new ChildViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ChildViewHolder childViewHolder, final int position)
    {

        // Create an instance of the ChildItem
        // class for the given position
        final MenuItemsList childItem = ChildItemList.get(position);

        // For the created instance, set title.
        // No need to set the image for
        // the ImageViews because we have
        // provided the source for the images
        // in the layout file itself
        childViewHolder.ChildItemTitle.setText(childItem.getItemName());


        CompoundButton.OnCheckedChangeListener checkedChangeListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton cb, boolean b) {
                if (count == limit && b) {
                    cb.setChecked(false);
                    Toast.makeText(context,
                           "Select Any "+limit, Toast.LENGTH_LONG).show();
                } else if (b) {
                    Intent intent = new Intent("custom-message");

                    intent.putExtra("packageitemsUUIDs",childItem.getItemUUID());
                    LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                    count++;
                   // CharSequence myCheck = childViewHolder.ChildItemTitle.getText();
                } else if (!b) {
                    count--;
                }
            }
        };

        childViewHolder.ChildItemTitle.setTag(childItem);

        childViewHolder.ChildItemTitle.setOnCheckedChangeListener(checkedChangeListener);

    }

    @Override
    public int getItemCount()
    {

        // This method returns the number
        // of items we have added
        // in the ChildItemList
        // i.e. the number of instances
        // of the ChildItemList
        // that have been created
        return ChildItemList.size();
    }

    // This class is to initialize
    // the Views present
    // in the child RecyclerView
    class ChildViewHolder extends RecyclerView.ViewHolder {

        CheckBox ChildItemTitle;

        ChildViewHolder(View itemView) {
            super(itemView);
            ChildItemTitle = itemView.findViewById(R.id.cb_tittle);
        }
    }@Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


}
