package com.acculytixs.mobile.wayuparty.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.acculytixs.mobile.wayuparty.R;
import com.acculytixs.mobile.wayuparty.activitys.ServicesActivity;
import com.acculytixs.mobile.wayuparty.activitys.VendorEventsActivity;
import com.acculytixs.mobile.wayuparty.application.WUPPreferences;
import com.acculytixs.mobile.wayuparty.application.WayUPartyConstants;
import com.acculytixs.mobile.wayuparty.dtos.GetServiceCategoryList;
import com.acculytixs.mobile.wayuparty.dtos.GetServicesList;
import com.acculytixs.mobile.wayuparty.fragments.SingleChoiceDialogFragment;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;


public class ServicesGridAdapter extends BaseAdapter  {

    Context context;
    LayoutInflater inflter;
    LinearLayout linearLayout;
    GetServicesList getServicesList;
    GetServiceCategoryList getServiceCategoryList;
    String vendorUUID;

    public ServicesGridAdapter(Context applicationContext, GetServicesList getServicesList,String vendorUUID) {
        this.context = applicationContext;
        this.getServicesList = getServicesList;
        this.vendorUUID = vendorUUID;
        inflter = (LayoutInflater.from(context));
    }

    public ServicesGridAdapter() {
        super();
    }

    @Override
    public int getCount() {
        return getServicesList.getObject().getServicesList().size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.row_services_items, null);// inflate the layout
        linearLayout = (LinearLayout)view.findViewById(R.id.main_lllayout);
        ImageView icon = (ImageView) view.findViewById(R.id.icon);
        TextView textView = (TextView)view.findViewById(R.id.tv_name); // get the reference of ImageView
        //icon.setImageResource(logos[i]);
        if (!TextUtils.isEmpty(getServicesList.getObject().getServicesList().get(i).getServiceImage())) {
            Log.d("imagaeurl", "" + getServicesList.getObject().getServicesList().get(i).getServiceImage());
            String url = WayUPartyConstants.TEST_URL+getServicesList.getObject().getServicesList().get(i).getServiceImage();
            Picasso.with(context)
                    .load(url)
                    .into(icon);
        } else {
            //viewHolder.imageView.setImageResource(R.drawable.sample_image);
        }
        textView.setText(getServicesList.getObject().getServicesList().get(i).getServiceName());// set logo images
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(getServicesList.getObject().getServicesList().get(i).getServiceDisplayName().equals("Events")){
                     Intent intent = new Intent(context, VendorEventsActivity.class);
                     intent.putExtra("vendorUUID",vendorUUID);
                     context.startActivity(intent);

                }else
                {
                    callServicesByVendorUUID(getServicesList.getObject().getServicesList().get(i).getServiceUUID());
                    WUPPreferences.saveServiceUUID(getServicesList.getObject().getServicesList().get(i).getServiceDisplayName());
                    WUPPreferences.saveRatioEnabled(getServicesList.getObject().getServicesList().get(i).getIsEntryRatioEnabled());

                }

            }
        });

        return view;
    }


    public void callServicesByVendorUUID(String vendorUUID){
        final String url = WayUPartyConstants.TEST_URL+WayUPartyConstants.URL_GETSERVICECATEGORYLIST_BYSERVICEUUID+"?serviceUUID="+vendorUUID;
        Ion.with(context)
                .load("GET",url)
                .setLogging("wayuparty", Log.DEBUG)
                .followRedirect(false)
                .as(new TypeToken<JsonObject>() {
                }).withResponse()
                .setCallback(new FutureCallback<Response<JsonObject>>() {
                    @Override
                    public void onCompleted(Exception e, Response<JsonObject> result) {
                        try {
                            String jsonString = new com.google.gson.Gson().toJson(result);
                            Log.d("jsonstring",jsonString);
                             getServiceCategoryList = new Gson().fromJson(result.getResult(), new TypeToken<GetServiceCategoryList>() {
                            }.getType());
                            Log.d("jsonstring",getServiceCategoryList.getData().size()+"");
                           // progressBar.setVisibility(View.GONE);
                            if(getServicesList.getObject().getServicesList() != null) {
                                if (getServicesList.getObject().getServicesList().size() > 0) {
                                    DialogFragment singleChoiceDialog = new SingleChoiceDialogFragment(getServiceCategoryList);
                                    singleChoiceDialog.setCancelable(false);
                                    FragmentManager fm = ((FragmentActivity)context).getSupportFragmentManager();
                                    singleChoiceDialog.show(fm, "Single Choice Dialog");
                                }
                            }
                        }catch (Exception t){
                            t.printStackTrace();
                        }
                    }
                });
    }


}
