package com.acculytixs.mobile.wayuparty.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.acculytixs.mobile.wayuparty.R;
import com.acculytixs.mobile.wayuparty.dtos.Datun;
import com.acculytixs.mobile.wayuparty.dtos.PackageMenuItem;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CustomizeParentItemAdapter extends RecyclerView.Adapter<CustomizeParentItemAdapter.ParentViewHolder> {

    // An object of RecyclerView.RecycledViewPool
    // is created to share the Views
    // between the child and
    // the parent RecyclerViews
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    private List<PackageMenuItem> itemList;
    Context context;
    ArrayList<String> itemUUIDs;

    public  CustomizeParentItemAdapter(Context context,List<PackageMenuItem> itemList,ArrayList<String> itemUUIDs) {
        this.itemList = itemList;
        this.context = context;
        this.itemUUIDs = itemUUIDs;
    }

    @NonNull
    @Override
    public ParentViewHolder onCreateViewHolder(
            @NonNull ViewGroup viewGroup,
            int i) {

        // Here we inflate the corresponding
        // layout of the parent item
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.customize_item, viewGroup, false);

        return new ParentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull ParentViewHolder parentViewHolder,
            int position) {

        // Create an instance of the ParentItem
        // class for the given position
        PackageMenuItem parentItem = itemList.get(position);

        // For the created instance,
        // get the title and set it
        // as the text for the TextView
        parentViewHolder.ParentItemTitle.setText(parentItem.getMenuItem());
        parentViewHolder.item_count.setText("Select Any "+parentItem.getItemsOffered());

        // Create a layout manager
        // to assign a layout
        // to the RecyclerView.

        // Here we have assigned the layout
        // as LinearLayout with vertical orientation
        LinearLayoutManager layoutManager = new LinearLayoutManager(parentViewHolder.ChildRecyclerView.getContext());

        // Since this is a nested layout, so
        // to define how many child items
        // should be prefetched when the
        // child RecyclerView is nested
        // inside the parent RecyclerView,
        // we use the following method
        layoutManager.setInitialPrefetchItemCount(parentItem.getMenuItemsList().size());

        // Create an instance of the child
        // item view adapter and set its
        // adapter, layout manager and RecyclerViewPool
        CustomizeChildItemAdapter childItemAdapter = new CustomizeChildItemAdapter(context, parentItem.getMenuItemsList(),Integer.parseInt(parentItem.getItemsOffered()),itemUUIDs);
        parentViewHolder.ChildRecyclerView.setLayoutManager(layoutManager);
        parentViewHolder.ChildRecyclerView.setAdapter(childItemAdapter);
        parentViewHolder.ChildRecyclerView.setRecycledViewPool(viewPool);
    }

    // This method returns the number
    // of items we have added in the
    // ParentItemList i.e. the number
    // of instances we have created
    // of the ParentItemList
    @Override
    public int getItemCount() {

        return itemList.size();
    }

    // This class is to initialize
    // the Views present in
    // the parent RecyclerView
    class ParentViewHolder extends RecyclerView.ViewHolder {

        private TextView ParentItemTitle,item_count;
        private RecyclerView ChildRecyclerView;

        ParentViewHolder(final View itemView) { super(itemView);

            ParentItemTitle = itemView.findViewById(R.id.parent_item_title);
            item_count = itemView.findViewById(R.id.select_count);
            ChildRecyclerView = itemView.findViewById(R.id.customize_child_recyclerview);
        }
    }
}
