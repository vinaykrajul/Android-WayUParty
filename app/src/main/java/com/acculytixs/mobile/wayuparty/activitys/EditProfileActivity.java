package com.acculytixs.mobile.wayuparty.activitys;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.acculytixs.mobile.wayuparty.R;
import com.acculytixs.mobile.wayuparty.application.AndroidMultiPartEntity;
import com.acculytixs.mobile.wayuparty.application.ProfileImage;
import com.acculytixs.mobile.wayuparty.application.WUPPreferences;
import com.acculytixs.mobile.wayuparty.application.WayUPartyConstants;
import com.acculytixs.mobile.wayuparty.dtos.AddToCartRequest;
import com.acculytixs.mobile.wayuparty.dtos.AddToCartResponse;
import com.acculytixs.mobile.wayuparty.dtos.LoginRegisteredUserResponse;
import com.acculytixs.mobile.wayuparty.dtos.LoginRequest;
import com.acculytixs.mobile.wayuparty.dtos.SaveUserProfile;
import com.acculytixs.mobile.wayuparty.dtos.UploadFileResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.filter.Filter;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class EditProfileActivity extends AppCompatActivity implements View.OnClickListener {

    ProgressBar progressBar,profile_progress;
    ProfileImage profileImage;
    RadioGroup radioGroup;
    RadioButton male,female;
    String gender = "male";
    TextView tv_preffered_music,tv_preffer_dricks,tv_dob;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    EditText tv_name,tv_mobile;
    LoginRegisteredUserResponse loginResponse;
    LinearLayout linearLayout,ll_preffered_drinks,ll_preffered_music;
    TextView tv_save,tv_email;
    ImageView editProfileImage;
    List<Uri> mSelected;
    List<String> fixedLenghtList;
    List<String> fixedLenghtList1;

    long totalSize = 0;
    final ArrayList<String> selectitems = new ArrayList<>();
    final ArrayList<String> preferedDrinks = new ArrayList<>();
    String selectdrinks;
    String selectmusic ;
     boolean[] checkedColors;
    public static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1;


    // LogCat tag
    private static final String TAG = EditProfileActivity.class.getSimpleName();
    private Uri fileUri; // file url to store image/video
    UploadFileResponse myClass;
    String[] colors;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Edit Profile");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        tv_save = findViewById(R.id.tv_save);
        tv_save.setOnClickListener(this);
        progressBar = (ProgressBar)findViewById(R.id.main_progress);
        profile_progress = (ProgressBar)findViewById(R.id.profile_progress);
        linearLayout = (LinearLayout) findViewById(R.id.ll_data);
        ll_preffered_drinks = (LinearLayout) findViewById(R.id.ll_preffered_drinks);
        ll_preffered_drinks.setOnClickListener(this);
        ll_preffered_music = (LinearLayout) findViewById(R.id.ll_preffered_music);
        ll_preffered_music.setOnClickListener(this);
        inIt();
        loginResponse = (LoginRegisteredUserResponse) getIntent().getSerializableExtra("profileData");
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = day + "/" + month + "/" + year;
                tv_dob.setText(date);
            }
        };
        setData();

        /*if (ContextCompat.checkSelfPermission(this, // request permission when it is not granted.
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            Log.d("myAppName", "permission:WRITE_EXTERNAL_STORAGE: NOT granted!");
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);


            }
        }*/
    }
    public void inIt(){
        radioGroup = findViewById(R.id.radioGroup);
        male = findViewById(R.id.radioMale);
        female = findViewById(R.id.radioFemale);
        profileImage = (ProfileImage)findViewById(R.id.profil_image);
        editProfileImage = (ImageView) findViewById(R.id.edit_profile_image);
        editProfileImage.setOnClickListener(this);
        tv_name = (EditText) findViewById(R.id.tv_name);
        tv_mobile = (EditText)findViewById(R.id.tv_mobile_no);
        tv_email = (TextView)findViewById(R.id.tv_email);
        tv_dob = (TextView) findViewById(R.id.dob);
        tv_dob.setOnClickListener(this);
        tv_preffer_dricks = (TextView)findViewById(R.id.tv_preffere_drinks);
        tv_preffered_music = (TextView)findViewById(R.id.tv_preffered_music);

        if (radioGroup != null) {
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    String text = "You selected: ";
                    text += (R.id.radioMale == checkedId) ? "male" : "female";
                    gender = (R.id.radioMale == checkedId) ? "male" : "female";
                   // Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
                }
            });
        }


    }

    public void setData(){

        profileImage.bind(WayUPartyConstants.TEST_URL+loginResponse.getObject().getUserImage(),loginResponse.getObject().getFirstName(),false);
        tv_name.setText(loginResponse.getObject().getFirstName());
        tv_dob.setText(loginResponse.getObject().getDob());
        tv_mobile.setText(loginResponse.getObject().getUserMobile());
        tv_email.setText(loginResponse.getObject().getUserEmail());

        String orderitems = loginResponse.getObject().getPreferredDrinks();
        String [] orderitemsArray = orderitems.split(",");
        fixedLenghtList = Arrays.asList(orderitemsArray);

        String orderitems1 = loginResponse.getObject().getPreferredMusic();
        String [] orderitemsArray1 = orderitems1.split(",");
        fixedLenghtList1 = Arrays.asList(orderitemsArray1);


        tv_preffered_music.setText(fixedLenghtList1.size()+" Music Items Preffered");
        tv_preffer_dricks.setText(fixedLenghtList.size()+" Drinks Preffered");

        if(loginResponse.getObject().getGender().equals("male"))
            radioGroup.check(R.id.radioMale);
        else {
            radioGroup.check(R.id.radioFemale);
        }


    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_save:
                callAddToCartService();
                break;
            case R.id.edit_profile_image:

                if (ContextCompat.checkSelfPermission(this, // request permission when it is not granted.
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {

                    if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                        ActivityCompat.requestPermissions(this,
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);

                    } else {

                        // No explanation needed, we can request the permission.

                        ActivityCompat.requestPermissions(this,
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);

                    }
                }else{
                    Matisse.from(EditProfileActivity.this)
                            .choose(MimeType.ofImage())
                            .countable(true)
                            .maxSelectable(1)
                            .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                            .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                            .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                            .thumbnailScale(0.85f)
                            .imageEngine(new Glide4Engine())
                            .forResult(2);
                    //profile_progress.setVisibility(View.VISIBLE);

                }

                break;
            case R.id.ll_preffered_drinks:
                colors = new String[loginResponse.getObject().getPreferredDrinksList().size()];
                checkedColors = new boolean[loginResponse.getObject().getPreferredDrinksList().size()];

                for(int i =0;i<loginResponse.getObject().getPreferredDrinksList().size();i++){
                    colors[i] = loginResponse.getObject().getPreferredDrinksList().get(i);
                    for(int j =0;j<fixedLenghtList.size();j++) {
                        if (loginResponse.getObject().getPreferredDrinksList().get(i).equals(fixedLenghtList.get(j))) {
                            checkedColors[i] = true;
                            Log.d("true items",colors[i]);
                        }
                    }
                    //checkedColors[i]= true;
                }
                showAlertdialog("your preffered Drinks","drinks");
                break;
            case R.id.ll_preffered_music:
                colors = new String[loginResponse.getObject().getPreferredMusicList().size()];
                checkedColors = new boolean[loginResponse.getObject().getPreferredMusicList().size()];


                for(int i =0;i<loginResponse.getObject().getPreferredMusicList().size();i++){
                    colors[i] = loginResponse.getObject().getPreferredMusicList().get(i);

                    for(int j =0;j<fixedLenghtList1.size();j++) {
                        if (loginResponse.getObject().getPreferredMusicList().get(i).equals(fixedLenghtList1.get(j))) {
                            checkedColors[i] = true;
                        }
                    }
                    //checkedColors[i]= true;
                }
                showAlertdialog("Your preffered Music","music");
                break;
            case R.id.dob:
                showDatePicker();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // if the result is capturing Image
      if(requestCode == 2) {
            if (resultCode == RESULT_OK) {

                profile_progress.setVisibility(View.VISIBLE);

                mSelected = Matisse.obtainResult(data);

                // Display in the logs the selected items.
                // Outputs something like:
                // D/Matisse: mSelected: [
                //    content://media/external/images/media/26263,
                //    content://media/external/images/media/26264,
                //    content://media/external/images/media/26261
                // ]
                Log.d("Matisse", "mSelected: " + mSelected.get(0));
                Uri selectedImage = mSelected.get(0);
                //fileUri = selectedImage;
                String[] filePath = {MediaStore.Images.Media.DATA, MediaStore.Images.Media.MIME_TYPE};
                Cursor c = getContentResolver().query(selectedImage, filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                int mimeTypeColumnIndex = c.getColumnIndex(filePath[1]);
                String picturePath = c.getString(columnIndex);
                String mimeType = c.getString(mimeTypeColumnIndex);
                c.close();
                if (mimeType.startsWith("image")) {
                    //It's an image
                    Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
                    File mediaFile = new File(picturePath);
                    fileUri = Uri.fromFile(mediaFile);
                    Log.w("path of imag", picturePath + "");
                    //profileImage.setBitmapImage(thumbnail);
                    new UploadFileToServer().execute();
                } else if (mimeType.startsWith("video")) {
                }

            }


        }
    }

    private class UploadFileToServer extends AsyncTask<Void, Integer, String> {
        @Override
        protected void onPreExecute() {
            // setting progress bar to zero
           // progressBar.setProgress(0);
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
            // Making progress bar visible
           // progressBar.setVisibility(View.VISIBLE);
            //txtPercentage.setVisibility(View.VISIBLE);
            // updating progress bar value
            //progressBar.setProgress(progress[0]);

            // updating percentage value
            //txtPercentage.setText(String.valueOf(progress[0]) + "%");
        }

        @Override
        protected String doInBackground(Void... params) {
            return uploadFile();
        }

        @SuppressWarnings("deprecation")
        private String uploadFile() {
            String responseString = null;

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(WayUPartyConstants.TEST_URL+WayUPartyConstants.URL_UPLOAD_PROFILE_IMAGE);

            httppost.addHeader("X-Username",WUPPreferences.getUserName());
            httppost.addHeader("X-Password" , WUPPreferences.getPassword());

            try {
               AndroidMultiPartEntity entity = new AndroidMultiPartEntity(
                       new AndroidMultiPartEntity.ProgressListener() {
                            @Override
                            public void transferred(long num) {
                                publishProgress((int) ((num / (float) totalSize) * 100));

                            }
                       });
              File sourceFile = new File(fileUri.getPath());

              entity.addPart("video", new FileBody(sourceFile));
                Log.w("path name",fileUri.getPath()+"");


               totalSize = entity.getContentLength();
               httppost.setEntity(entity);

                // Making server call

                HttpResponse response = httpclient.execute(httppost);
                HttpEntity r_entity = response.getEntity();

                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == 200) {
                    // Server response
                    responseString = EntityUtils.toString(r_entity);
                } else {
                    responseString = "Error occurred! Http Status Code: "
                            + statusCode;
                }

            } catch (ClientProtocolException e) {
                responseString = e.toString();
            } catch (IOException e) {
                responseString = e.toString();
            }

            return responseString;

        }

        @Override
        protected void onPostExecute(String result) {


            try {
                JSONArray jsonArr = new JSONArray(result);
                JSONObject jsonObj = jsonArr.getJSONObject(0);
                Gson gson = new Gson();
                 myClass = gson.fromJson(jsonObj.toString(), UploadFileResponse.class);
                 profileImage.bind(WayUPartyConstants.TEST_URL+myClass.getFileURL(),tv_name.getText().toString(),false);
                 profile_progress.setVisibility(View.GONE);
                Log.e(TAG, "Response from server: dto " + myClass.getFileName().toString());
            }catch (Exception e){
                e.printStackTrace();
            }

            super.onPostExecute(result);
        }

    }

    public void callAddToCartService(){
        progressBar.setVisibility(View.VISIBLE);
        String url = WayUPartyConstants.TEST_URL+WayUPartyConstants.URL_SAVE_USER_PROFILE;

        SaveUserProfile saveUserProfile = new SaveUserProfile();
        saveUserProfile.setUserUUID(WUPPreferences.getUserId());
        saveUserProfile.setFirstName(tv_name.getText().toString());
        saveUserProfile.setLastName("");
        saveUserProfile.setGender(gender);
        saveUserProfile.setEmail(tv_email.getText().toString());
        saveUserProfile.setDob(tv_dob.getText().toString());
        saveUserProfile.setMobile(tv_mobile.getText().toString());
        if(selectmusic != null){
            saveUserProfile.setPreferredMusic(selectmusic);
            Log.d("final music",selectmusic);
        }else {
            saveUserProfile.setPreferredMusic(loginResponse.getObject().getPreferredMusic());
        }

        if(selectdrinks != null){
            saveUserProfile.setPreferredDrinks(selectdrinks);
            Log.d("final drinks",selectdrinks);
        }else {
            saveUserProfile.setPreferredDrinks(loginResponse.getObject().getPreferredDrinks());
        }

        //saveUserProfile.setPreferredDrinks(loginResponse.getObject().getPreferredDrinks());
        if(myClass != null){
            saveUserProfile.setProfileImageUrl(myClass.getFileURL());
            Log.d("url",myClass.getFileURL()+"");
        }else {
            saveUserProfile.setProfileImageUrl(loginResponse.getObject().getUserImage());
            Log.d("url", loginResponse.getObject().getUserImage() + "");
        }



        try {
            Ion.with(this)
                    .load("POST",url)
                    .setLogging("jananetha", Log.DEBUG)
                    .followRedirect(false)
                    .addHeader("X-Username", WUPPreferences.getUserName())
                    .addHeader("X-Password", WUPPreferences.getPassword())
                    .setJsonPojoBody(saveUserProfile)
                    .as(new TypeToken<JsonObject>() {
                    })
                    .withResponse()
                    .setCallback(new FutureCallback<Response<JsonObject>>() {
                        @Override
                        public void onCompleted(Exception e, Response<JsonObject> jsonArrayResponse) {
                            try {
                                String jsonString = new com.google.gson.Gson().toJson(jsonArrayResponse);
                                Log.d("response", "" + jsonString);
                                 progressBar.setVisibility(View.GONE);
                                 Intent intent = new Intent(EditProfileActivity.this,ProfileActivity.class);
                                 startActivity(intent);
                                 finish();

                            } catch (Exception e1) {
                                e1.printStackTrace();

                            }


                        }
                    });
        } catch (Exception e) {

        }
    }

    public void showAlertdialog(String tittle, final String type){

        Log.d("checkced items",checkedColors.length+"");

        AlertDialog.Builder builder = new AlertDialog.Builder(EditProfileActivity.this);
        final List<String> colorsList = Arrays.asList(colors);
        builder.setMultiChoiceItems(colors, checkedColors, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                // Update the current focused item's checked status
                checkedColors[which] = isChecked;

                // Get the current focused item
                //String currentItem = colorsList.get(which);

                // Notify the current action
            }
        });
        builder.setCancelable(false);

        // Set a title for alert dialog
        builder.setTitle(tittle);

        // Set the positive/yes button click listener
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                selectitems.clear();
                // Do something when click positive button
                //tv_preffer_dricks.setText("Your preferred colors..... \n");
                for (int i = 0; i<checkedColors.length; i++){
                    boolean checked = checkedColors[i];
                    if (checked) {
                        selectitems.add(colorsList.get(i));
                        if(type.equals("drinks")){
                            tv_preffer_dricks.setText(selectitems.size()+" Drinks Preffered");
                            StringBuilder builder = new StringBuilder("");
                            for(int j=0;j<selectitems.size();j++){
                                builder.append(selectitems.get(j)+",");
                            }
                            selectdrinks = builder.toString();
                            fixedLenghtList = selectitems;
                            Log.d("test",selectdrinks);
                        }else {
                           tv_preffered_music.setText(selectitems.size()+"Music items Preffered");
                            StringBuilder builder = new StringBuilder("");
                            for(int j=0;j<selectitems.size();j++){
                                builder.append(selectitems.get(j)+",");
                            }
                            selectmusic = builder.toString();
                            fixedLenghtList1 = selectitems;
                            Log.d("test",selectmusic);
                        }

                    }
                }
            }
        });

        // Set the neutral/cancel button click listener
        builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do something when click the neutral button
            }
        });

        AlertDialog dialog = builder.create();
        // Display the alert dialog on interface
        dialog.show();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE : {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Matisse.from(EditProfileActivity.this)
                            .choose(MimeType.ofImage())
                            .countable(true)
                            .maxSelectable(1)
                            .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                            .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                            .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                            .thumbnailScale(0.85f)
                            .imageEngine(new Glide4Engine())
                            .forResult(2);
                    profile_progress.setVisibility(View.VISIBLE);

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(getApplicationContext(), "Permission denied", Toast.LENGTH_SHORT).show();

                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    public void showDatePicker(){

        final Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR)-21;
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        cal.set(year,month,day);

        DatePickerDialog dialog = new DatePickerDialog(
                EditProfileActivity.this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                mDateSetListener,
                year,month,day);
        dialog.getDatePicker().setMaxDate(cal.getTimeInMillis());
        //dialog.getDatePicker().setYearRange(calendar.get(Calendar.YEAR), calendar.get(Calendar.YEAR) + 1);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();


    }

}
