package com.acculytixs.mobile.wayuparty.adapters;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.acculytixs.mobile.wayuparty.R;
import com.acculytixs.mobile.wayuparty.application.WayUPartyConstants;
import com.acculytixs.mobile.wayuparty.dtos.GetVendorInfo;
import com.squareup.picasso.Picasso;

import androidx.viewpager.widget.PagerAdapter;

public class RSPager extends PagerAdapter {

    private Context context;
    GetVendorInfo getVendorInfo;
    public RSPager(Context context,GetVendorInfo getVendorInfo) {
        this.context = context;
        this.getVendorInfo = getVendorInfo;
    }
    /*
    This callback is responsible for creating a page. We inflate the layout and set the drawable
    to the ImageView based on the position. In the end we add the inflated layout to the parent
    container .This method returns an object key to identify the page view, but in this example page view
    itself acts as the object key
    */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.pager_item, null);
        ImageView imageView = view.findViewById(R.id.image);
        if(getVendorInfo.getObject().getSliderList().size()> 0) {
            if (!TextUtils.isEmpty(getVendorInfo.getObject().getSliderList().get(position))) {
                Log.d("imagaeurl", "" + getVendorInfo.getObject().getSliderList().get(position));
                String url = WayUPartyConstants.TEST_URL + getVendorInfo.getObject().getSliderList().get(position);
                Picasso.with(context)
                        .load(url)
                        .into(imageView);
            }
        }else {
            //imageView.setImageResource(R.drawable.sample_image);
        }
        container.addView(view);
        return view;
    }
    /*
    This callback is responsible for destroying a page. Since we are using view only as the
    object key we just directly remove the view from parent container
    */
    @Override
    public void destroyItem(ViewGroup container, int position, Object view) {
        container.removeView((View) view);
    }
    /*
    Returns the count of the total pages
    */
    @Override
    public int getCount() {
        return 3;
    }
    /*
    Used to determine whether the page view is associated with object key returned by instantiateItem.
    Since here view only is the key we return view==object
    */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return object == view;
    }

}
