package com.acculytixs.mobile.wayuparty.activitys;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.acculytixs.mobile.wayuparty.R;
import com.acculytixs.mobile.wayuparty.adapters.CategoryServicesListAdapter;
import com.acculytixs.mobile.wayuparty.adapters.RestaurantsAdapter;
import com.acculytixs.mobile.wayuparty.dtos.GetCategoryServiceList;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CategoryServiceListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    ProgressBar progressBar;
    GetCategoryServiceList getCategoryServiceList;
    CategoryServicesListAdapter categoryServicesListAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_service);
        progressBar = (ProgressBar)findViewById(R.id.main_progress);
        Toolbar toolbar = findViewById(R.id.toolbar);
        getCategoryServiceList = (GetCategoryServiceList)getIntent().getSerializableExtra("listdata");
        toolbar.setTitle(getCategoryServiceList.getData().get(0).getService());
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        recyclerView = (RecyclerView)findViewById(R.id.rv_category_list_india);
        linearLayoutManager = new LinearLayoutManager(CategoryServiceListActivity.this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        categoryServicesListAdapter = new CategoryServicesListAdapter(CategoryServiceListActivity.this,getCategoryServiceList);
        progressBar.setVisibility(View.GONE);
        recyclerView.setAdapter(categoryServicesListAdapter);


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
