package com.acculytixs.mobile.wayuparty.application;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.StateSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.acculytixs.mobile.wayuparty.R;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import androidx.core.content.ContextCompat;

/**
 * Created by rbolli on 3/13/2017.
 */

public class ProfileImage extends FrameLayout {


    TextView textView;
    ImageView imageView;
    private float radius;
    private float mCorners;
    private Transformation roundedTransformation;

    // region Constructors
    public ProfileImage(Context context) {
        super(context);
        init(context, null);
    }

    public ProfileImage(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ProfileImage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }


    // region Helper Methods
    private void init(Context context, AttributeSet attrs) {
        if (!isInEditMode()) {
            LayoutInflater.from(getContext()).inflate(R.layout.avatar_view, this, true);
            findViews();
//            ButterKnife.bind(this);

            if (attrs != null) {
                TypedArray a = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.ProfileImage, 0, 0);
                try {
                    radius = a.getDimension(R.styleable.ProfileImage_profile_radius, 0);
                    mCorners = a.getDimension(R.styleable.ProfileImage_profile_corners, radius);
                } finally {
                    a.recycle();
                }
            }

            roundedTransformation = new RoundedTransformationBuilder()
                    .cornerRadius(mCorners)
//                    .borderWidthDp(1f)
//                    .borderColor(ContextCompat.getColor(getContext(), R.color.transparent))
                    .oval(false)
                    .build();

            // resize ImageView
            ViewGroup.LayoutParams imageViewLayoutParams = imageView.getLayoutParams();
            imageViewLayoutParams.width = (int) (radius * 2);
            imageViewLayoutParams.height = (int) (radius * 2);
            imageView.setLayoutParams(imageViewLayoutParams);

            // resize TextView
            ViewGroup.LayoutParams textViewLayoutParams = textView.getLayoutParams();
            textViewLayoutParams.width = (int) (radius * 2);
            textViewLayoutParams.height = (int) (radius * 2);
            textView.setLayoutParams(textViewLayoutParams);

            // resize TextView's fontSize
            int fontSize = (20 * (int) radius * 2) / WayUPartyApplication.dp2px(getContext(), 50);
            textView.setTextSize(fontSize);
        }
    }

    private void findViews() {
        imageView = (ImageView) findViewById(R.id.photo_iv);
        textView = (TextView) findViewById(R.id.initials_tv);
    }

    public void bind(String avatarUrl, String name, boolean clearCache) {


        if (avatarUrl != null && !TextUtils.isEmpty(avatarUrl.trim())) {
//        if ( avatarUrl.trim().length()>0) {

            if (clearCache) {
                Picasso.with(getContext())
                        .load(avatarUrl)
                        .memoryPolicy(MemoryPolicy.NO_CACHE)
                        .networkPolicy(NetworkPolicy.NO_CACHE)
                        .transform(roundedTransformation)
                        .resize((int) radius * 2, (int) radius * 2)
                        .centerCrop()
                        .into(imageView);
            } else {
                Picasso.with(getContext())
                        .load(avatarUrl)
                        .transform(roundedTransformation)
                        .resize((int) radius * 2, (int) radius * 2)
                        .centerCrop()
                        .into(imageView);
            }
        } else {
            nullify();
        }

        textView.setText(getInitials(name));

        // Set up background
        StateListDrawable stateListDrawable = new StateListDrawable();

        GradientDrawable defaultDrawable = (GradientDrawable) ContextCompat.getDrawable(getContext(), R.drawable.default_avatar_bg);
        //defaultDrawable.setColor(Util.getRandomColor(name));
        if (isClickable()) {
            GradientDrawable pressedDrawable = (GradientDrawable) ContextCompat.getDrawable(getContext(), R.drawable.pressed_avatar_bg);
            // pressedDrawable.setColor(DisplayUtility.getPressedAvatarBackgroundColor(user, getContext()));
            stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, pressedDrawable);
        }
        stateListDrawable.addState(StateSet.WILD_CARD, defaultDrawable);

        //textView.setBackground(stateListDrawable);

        textView.setBackgroundResource(R.drawable.default_avatar_bg);
        setTextBackgroundColor(ContextCompat.getColor(getContext(), R.color.cfcBorderColor));
        /*
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            textView.setBackgroundDrawable(R.drawable.default_avatar_bg);
        } else {
            textView.setBackgroundResource(R.drawable.default_avatar_bg);
        }*/
    }

    public void setTextBackgroundColor(int color) {
        GradientDrawable bgShape = (GradientDrawable) textView.getBackground();
        bgShape.setColor(color);
        // textView.setBackgroundColor(color);
    }

    private String getInitials(String name) {
        return TextUtils.isEmpty(name) ? "" : String.valueOf(name.charAt(0)).toUpperCase().trim();
    }

    public void nullify() {
        imageView.setImageDrawable(null);
        textView.setText("");
    }

    public void setDefaultImage(int resid) {
        imageView.setImageResource(resid);

    }

    public void setBitmapImage(Bitmap resid) {
        imageView.setImageBitmap(resid);

    }
}
