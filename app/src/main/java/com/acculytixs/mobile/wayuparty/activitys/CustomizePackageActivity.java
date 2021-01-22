package com.acculytixs.mobile.wayuparty.activitys;

import android.app.SearchManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.acculytixs.mobile.wayuparty.R;
import com.acculytixs.mobile.wayuparty.adapters.CustomizeParentItemAdapter;
import com.acculytixs.mobile.wayuparty.dtos.Datun;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CustomizePackageActivity extends AppCompatActivity implements View.OnClickListener {

    Datun datun;
    TextView btn_confirm;
    ArrayList<String> itemUUIDs = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customize_package_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        btn_confirm = findViewById(R.id.ll_confirm_package);
        btn_confirm.setOnClickListener(this);
        datun = (Datun)getIntent().getSerializableExtra("data");
        itemUUIDs = getIntent().getStringArrayListExtra("dataList");
        toolbar.setTitle(datun.getSubCategory());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        RecyclerView ParentRecyclerViewItem = findViewById(R.id.rv_customize_tittles);

        // Initialise the Linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(CustomizePackageActivity.this);

        // Pass the arguments
        // to the parentItemAdapter.
        // These arguments are passed
        // using a method ParentItemList()
        CustomizeParentItemAdapter parentItemAdapter = new CustomizeParentItemAdapter(CustomizePackageActivity.this, datun.getPackageMenuItems(),itemUUIDs);

        // Set the layout manager
        // and adapter for items
        // of the parent recyclerview
        ParentRecyclerViewItem.setAdapter(parentItemAdapter);
        ParentRecyclerViewItem.setLayoutManager(layoutManager);
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("custom-message"));
        itemUUIDs.clear();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.menu_main, menu);
        Log.d("called","menu");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menuSearch) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ll_confirm_package:
                Intent returnIntent = new Intent();
                returnIntent.putExtra("data",String.join(",",itemUUIDs));
                returnIntent.putStringArrayListExtra("dataList",itemUUIDs);
                setResult(4,returnIntent);
                finish();
                break;

        }
    }

    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            String ItemUUID = intent.getStringExtra("packageitemsUUIDs");
               itemUUIDs.add(ItemUUID);

            Toast.makeText(CustomizePackageActivity.this,ItemUUID ,Toast.LENGTH_SHORT).show();
        }
    };
}

