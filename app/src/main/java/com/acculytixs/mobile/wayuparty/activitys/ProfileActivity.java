package com.acculytixs.mobile.wayuparty.activitys;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.acculytixs.mobile.wayuparty.R;
import com.acculytixs.mobile.wayuparty.application.ProfileImage;
import com.acculytixs.mobile.wayuparty.application.WUPPreferences;
import com.acculytixs.mobile.wayuparty.application.WayUPartyConstants;
import com.acculytixs.mobile.wayuparty.dtos.Datun;
import com.acculytixs.mobile.wayuparty.dtos.LoginRegisteredUserResponse;
import com.acculytixs.mobile.wayuparty.dtos.LoginRequest;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import me.himanshusoni.quantityview.QuantityView;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    ProgressBar progressBar;
    ProfileImage profileImage;
    ImageView edit_image;
    TextView tv_name,tv_mobile,tv_dob,tv_preffered_music,tv_preffer_dricks,tv_email;
    LoginRegisteredUserResponse loginResponse;
    LinearLayout linearLayout,ll_emtpy;
    Button login;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = findViewById(R.id.toolbar);;
        toolbar.setTitle("Profile");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        progressBar = (ProgressBar)findViewById(R.id.main_progress);
        linearLayout = (LinearLayout) findViewById(R.id.ll_data);
        ll_emtpy = (LinearLayout) findViewById(R.id.ll_emptycart);
        login = (Button) findViewById(R.id.btn_login);
        login.setOnClickListener(this);
         inIt();
        callLoginService(WUPPreferences.getUserName(),WUPPreferences.getPassword());
    }

    public void callLoginService(final String username, final String password){

        String url = WayUPartyConstants.TEST_URL+WayUPartyConstants.URL_LOGIN_REGISTERD_USER;
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUserName(username);
        loginRequest.setPassword(password);

        try {
            Ion.with(this)
                    .load("POST",url)
                    .setLogging("jananetha", Log.DEBUG)
                    .followRedirect(false)
                    .addHeader("X-Username", username)
                    .addHeader("X-Password", password)
                    .setJsonPojoBody(loginRequest)
                    .as(new TypeToken<JsonObject>() {
                    })
                    .withResponse()
                    .setCallback(new FutureCallback<Response<JsonObject>>() {
                        @Override
                        public void onCompleted(Exception e, Response<JsonObject> jsonArrayResponse) {
                            try {
                                String jsonString = new com.google.gson.Gson().toJson(jsonArrayResponse);
                                //NewsFeed newsFeed = jsonString;
                                // Log.i(TAG,jsonString);
                                 loginResponse = new Gson().fromJson(jsonArrayResponse.getResult(), new TypeToken<LoginRegisteredUserResponse>() {
                                }.getType());
                                Log.d("responseCheck", "" + loginResponse.getResponse());
                                Log.d("responseCheck uuid", "" + loginResponse.getObject().getUserUUID());

                                //Toast.makeText(LoginActivity.this,loginResponse.getFirstName(),Toast.LENGTH_LONG).show();

                                if((loginResponse.getObject().getUserUUID()!=null)&&!(loginResponse.getObject().getUserUUID().isEmpty())){
                                    WUPPreferences.saveUserId(loginResponse.getObject().getUserUUID());
                                    WUPPreferences.saveUserName(username);
                                    WUPPreferences.savePasswrod(password);
                                    WUPPreferences.saveMobileNum(loginResponse.getObject().getUserMobile());
                                    WUPPreferences.saveEmail(loginResponse.getObject().getUserEmail());
                                    progressBar.setVisibility(View.GONE);
                                    linearLayout.setVisibility(View.VISIBLE);
                                    setData();
                                    /*Log.d("responseCheck uuid", "" + WUPPreferences.getUserId());
                                    Intent returnIntent = new Intent();
                                    setResult(3,returnIntent);
                                    finish();*/
                                }else {
                                    progressBar.setVisibility(View.GONE);
                                    ll_emtpy.setVisibility(View.VISIBLE);

                                }

                            } catch (Exception e1) {
                                e1.printStackTrace();

                            }


                        }
                    });
        } catch (Exception e) {

        }
    }
    public void inIt(){

        profileImage = (ProfileImage)findViewById(R.id.profil_image);
        edit_image = (ImageView) findViewById(R.id.edit_profile);
        edit_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ProfileActivity.this,EditProfileActivity.class);
                i.putExtra("profileData",loginResponse);
                startActivity(i);
                finish();
            }
        });
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_mobile = (TextView)findViewById(R.id.tv_mobile_no);
        tv_email = (TextView)findViewById(R.id.tv_email);
        tv_dob = (TextView)findViewById(R.id.dob);
        tv_preffer_dricks = (TextView)findViewById(R.id.tv_preffere_drinks);
        tv_preffered_music = (TextView)findViewById(R.id.tv_preffered_music);

    }

    public void setData(){

        profileImage.bind(WayUPartyConstants.TEST_URL+loginResponse.getObject().getUserImage(),loginResponse.getObject().getFirstName(),false);
        tv_name.setText(loginResponse.getObject().getFirstName());
        tv_dob.setText(loginResponse.getObject().getDob());
        tv_mobile.setText(loginResponse.getObject().getUserMobile());
        tv_email.setText(loginResponse.getObject().getUserEmail());

        tv_preffered_music.setText(loginResponse.getObject().getPreferredMusic());
        tv_preffer_dricks.setText(loginResponse.getObject().getPreferredDrinks());



    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btn_login:
                Intent intent = new Intent(ProfileActivity.this,LoginAcitvity.class);
                startActivity(intent);
                finish();
                break;
        }

    }
}