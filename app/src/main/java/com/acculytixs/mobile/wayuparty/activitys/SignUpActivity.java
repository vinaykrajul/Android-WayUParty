package com.acculytixs.mobile.wayuparty.activitys;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.acculytixs.mobile.wayuparty.R;
import com.acculytixs.mobile.wayuparty.application.WUPPreferences;
import com.acculytixs.mobile.wayuparty.application.WayUPartyConstants;
import com.acculytixs.mobile.wayuparty.dtos.LoginRegisteredUserResponse;
import com.acculytixs.mobile.wayuparty.dtos.LoginRequest;
import com.acculytixs.mobile.wayuparty.dtos.SignUpRequest;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;
import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Required;

import java.util.Calendar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener,Validator.ValidationListener{

    @Required(order = 1, messageResId = R.string.validation_username)
    private EditText etUsername;
    @Required(order = 2, messageResId = R.string.validation_password)
    private EditText etPassword;
    @Required(order = 4, messageResId = R.string.validation_email)
    private EditText etEmail;
    @Required(order = 5, messageResId = R.string.validation_confirm_password)
    private EditText etConfirmPassword;
    @Required(order = 3, messageResId = R.string.validation_number)
    private EditText etMobile;
    private Validator validator;
    public boolean isValidationDone = false;

    ImageView btn_sign_up;
    TextView tv_dob;
    RadioGroup radioGroup;
    RadioButton male,female;
    String gender = "male";
    ProgressBar progressBar;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);
        inIt();
    }

    public void inIt(){
        progressBar = findViewById(R.id.main_progress);
        radioGroup = findViewById(R.id.radioGroup);
        male = findViewById(R.id.radioMale);
        female = findViewById(R.id.radioFemale);
        tv_dob = (TextView) findViewById(R.id.btn_dob);
        tv_dob.setOnClickListener(this);
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d("this", "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = day + "/" + month + "/" + year;
                tv_dob.setText(date);
            }
        };

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

        etUsername = (EditText)findViewById(R.id.etUserId);
        btn_sign_up = (ImageView) findViewById(R.id.btn_signup);
        btn_sign_up.setOnClickListener(this);
        etPassword = (EditText)findViewById(R.id.etPassword);
        etConfirmPassword = (EditText)findViewById(R.id.etconfirmPassword);
        etEmail = (EditText)findViewById(R.id.etemail);
        etMobile = (EditText)findViewById(R.id.etMobile);


        etUsername.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_NEXT || actionId == EditorInfo.IME_NULL) {

                    etUsername.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                        @Override
                        public void onFocusChange(View v, boolean hasFocus) {
                            if (hasFocus) {
                                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);


                            } else {


                            }
                        }
                    });
                    /*etUsername.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                        @Override
                        public void onFocusChange(View v, boolean hasFocus) {
                            if (!hasFocus) {
                                hideKeyboard(v);
                            }
                        }
                    });*/
                }
                return false;
            }
        });
        etUsername.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);

                }
            }
        });
        etPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        etPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_NULL) {
                    try {
                        // onSignInClick(textView);
                        if (checkNetwork()) {

                        } else {
                            new AlertDialog.Builder(SignUpActivity.this)
                                    .setTitle("Message")
                                    .setMessage("Please connect to a working network")
                                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    })
                                    .show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return false;
            }
        });
        etMobile.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        etMobile.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_NULL) {
                    try {
                        // onSignInClick(textView);
                        if (checkNetwork()) {

                        } else {
                            new AlertDialog.Builder(SignUpActivity.this)
                                    .setTitle("Message")
                                    .setMessage("Please connect to a working network")
                                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    })
                                    .show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return false;
            }
        });

        etEmail.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_NEXT || actionId == EditorInfo.IME_NULL) {

                    etUsername.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                        @Override
                        public void onFocusChange(View v, boolean hasFocus) {
                            if (hasFocus) {
                                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);


                            } else {


                            }
                        }
                    });
                    /*etUsername.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                        @Override
                        public void onFocusChange(View v, boolean hasFocus) {
                            if (!hasFocus) {
                                hideKeyboard(v);
                            }
                        }
                    });*/
                }
                return false;
            }
        });
        etEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);

                }
            }
        });
        etConfirmPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        etConfirmPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_NULL) {
                    try {
                        // onSignInClick(textView);
                        if (checkNetwork()) {

                        } else {
                            new AlertDialog.Builder(SignUpActivity.this)
                                    .setTitle("Message")
                                    .setMessage("Please connect to a working network")
                                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    })
                                    .show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return false;
            }
        });


    }
    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
    public boolean checkNetwork() throws Exception {
       /* if (!NetWorkInfo.haveNetworkConnection(LoginAcitvity.this)) {
            return false;
        }*/
        return true;
    }
    @Override
    public void onValidationSucceeded() {
        isValidationDone = true;
    }
    public Validator getValidator() {
        //register validation on view
        validator = new Validator(this);
        //register validation listener
        validator.setValidationListener(this);
        return validator;
    }
    @Override
    public void onValidationFailed(View failedView, Rule<?> failedRule) {
        int mesgResId = this.getResources()
                .getIdentifier(failedRule.getFailureMessage(), "string",getPackageName());

        onValidationFailed(this, failedView, failedRule, mesgResId);
    }
    public  void onValidationFailed(Context context, View failedView, Rule<?> failedRule, int mesgResId) {
        String message;

        if (mesgResId > 0) {
            message = context.getResources().getString(mesgResId);
        } else {
            message = failedRule.getFailureMessage();
        }
        if (failedView instanceof EditText) {
//            failedView.requestFocus();
//            ((EditText) failedView).setText(message);
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        } else if (failedView instanceof Spinner) {
            //displayErrorDialog((FragmentActivity) context, message, false);
            //Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        } else if (failedView instanceof Button) {
            //displayErrorDialog((FragmentActivity) context, message, false);
            //Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        } else if (failedView instanceof CheckBox) {
            //displayErrorDialog((FragmentActivity) context, message, false);
            //Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_signup:
                getValidator().validate();
                if (!isValidationDone) {
                    return;
                }
                callSignUpService(etUsername.getText().toString().replaceAll(" ",""),etPassword.getText().toString().replaceAll(" ",""),etEmail.getText().toString().replaceAll(" ",""),etMobile.getText().toString().replaceAll(" ",""));

                break;
            case  R.id.btn_dob:
                showDatePicker();
                break;
        }
    }

    public void callSignUpService(final String username,final String password,final String email,final String mobile){
        progressBar.setVisibility(View.VISIBLE);
        String url = WayUPartyConstants.TEST_URL+WayUPartyConstants.URL_SIGN_UP;
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setLoginUserName(username);
        signUpRequest.setEmail(email);
        signUpRequest.setMobile(mobile);
        signUpRequest.setPassword(password);
        signUpRequest.setDob(tv_dob.getText().toString());
        signUpRequest.setGender(gender);


        try {
            Ion.with(this)
                    .load("POST",url)
                    .setLogging("jananetha", Log.DEBUG)
                    .followRedirect(false)
                    .addHeader("X-Username", username)
                    .addHeader("X-Password", password)
                    .setJsonPojoBody(signUpRequest)
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
                                LoginRegisteredUserResponse loginResponse = new Gson().fromJson(jsonArrayResponse.getResult(), new TypeToken<LoginRegisteredUserResponse>() {
                                }.getType());
                                Log.d("responseCheck", "" + loginResponse.getResponse());
                                Log.d("responseCheck uuid", "" + loginResponse.getObject().getUserUUID());

                                //Toast.makeText(LoginActivity.this,loginResponse.getFirstName(),Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.GONE);
                                if((loginResponse.getObject().getUserUUID()!=null)&&!(loginResponse.getObject().getUserUUID().isEmpty())){
                                    Intent intent = new Intent(SignUpActivity.this,LoginAcitvity.class);
                                    startActivity(intent);
                                    finish();
                                }else {
                                    final androidx.appcompat.app.AlertDialog.Builder alertDialogBuilder = new androidx.appcompat.app.AlertDialog.Builder(SignUpActivity.this);

                                    alertDialogBuilder.setMessage(loginResponse.getResponseMessage());
                                    alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            dialogInterface.cancel();
                                        }
                                    });
                                    final androidx.appcompat.app.AlertDialog alertDialog = alertDialogBuilder.create();
                                    alertDialog.show();
                                    final Button positiveButton = alertDialog.getButton(androidx.appcompat.app.AlertDialog.BUTTON_POSITIVE);
                                    LinearLayout.LayoutParams positiveButtonLL = (LinearLayout.LayoutParams) positiveButton.getLayoutParams();
                                    positiveButtonLL.gravity = Gravity.CENTER;
                                    positiveButton.setLayoutParams(positiveButtonLL);
                                }

                            } catch (Exception e1) {
                                e1.printStackTrace();

                            }


                        }
                    });
        } catch (Exception e) {

        }
    }

    public void showDatePicker(){

        final Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR)-21;
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        cal.set(year,month,day);

        DatePickerDialog dialog = new DatePickerDialog(
                SignUpActivity.this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                mDateSetListener,
                year,month,day);
        dialog.getDatePicker().setMaxDate(cal.getTimeInMillis());
        //dialog.getDatePicker().setYearRange(calendar.get(Calendar.YEAR), calendar.get(Calendar.YEAR) + 1);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();


    }
}
