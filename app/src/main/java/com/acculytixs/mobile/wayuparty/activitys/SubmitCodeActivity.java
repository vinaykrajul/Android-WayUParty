package com.acculytixs.mobile.wayuparty.activitys;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.acculytixs.mobile.wayuparty.R;
import com.acculytixs.mobile.wayuparty.application.WUPPreferences;
import com.acculytixs.mobile.wayuparty.application.WayUPartyConstants;
import com.acculytixs.mobile.wayuparty.dtos.LoginRegisteredUserResponse;
import com.acculytixs.mobile.wayuparty.dtos.LoginRequest;
import com.acculytixs.mobile.wayuparty.dtos.SubmitCodeRequest;
import com.acculytixs.mobile.wayuparty.dtos.SubmitCodeResponse;
import com.acculytixs.mobile.wayuparty.dtos.VerifyEmailResponse;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;
import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Required;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SubmitCodeActivity extends AppCompatActivity implements View.OnClickListener ,Validator.ValidationListener{

    @Required(order = 1, messageResId = R.string.validation_username)
    private EditText etVerificationCode;
    @Required(order = 2, messageResId = R.string.validation_password)
    private EditText etPassword;

    @Required(order = 3, messageResId = R.string.validation_password)
    private EditText etConfirmPassword;
    private Validator validator;
    public boolean isValidationDone = false;
    TextView tv_login_signup;
    TextView tv_forgot_password;
    TextInputLayout textInputLayout;
    int flag =0;
    ImageView btn_submit_code,gLogin;
    ImageView btn_login,btn_sign_up;
    ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.submit_code_activity);
        inIt();

    }
    public void inIt(){
        progressBar = findViewById(R.id.main_progress);
         btn_submit_code = findViewById(R.id.btn_submit_code);
         btn_submit_code.setOnClickListener(this);
        etVerificationCode = (EditText)findViewById(R.id.etVerificationCode);

        etPassword = (EditText)findViewById(R.id.etPassword);
        etConfirmPassword = (EditText)findViewById(R.id.etConfirmPassword);


        etVerificationCode.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_NEXT || actionId == EditorInfo.IME_NULL) {

                    etVerificationCode.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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
        etVerificationCode.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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
                            new AlertDialog.Builder(SubmitCodeActivity.this)
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
                            new AlertDialog.Builder(SubmitCodeActivity.this)
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
       /* if (!NetWorkInfo.haveNetworkConnection(SubmitCodeActivity.this)) {
            return false;
        }*/
        return true;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btn_submit_code:
                getValidator().validate();
                if (!isValidationDone) {
                    return;
                }
                callLoginService(etVerificationCode.getText().toString().replaceAll(" ",""),etPassword.getText().toString().replaceAll(" ",""),etConfirmPassword.getText().toString().replaceAll(" ",""));

                break;
        }

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
    public void callLoginService(final String username, final String password,final String ConfirmPassword){
        progressBar.setVisibility(View.VISIBLE);
        String url = WayUPartyConstants.TEST_URL+WayUPartyConstants.URL_RESET_PASSWORD;
        SubmitCodeRequest submitCodeRequest = new SubmitCodeRequest();
        submitCodeRequest.setVerificationCode(username);
        submitCodeRequest.setVerificationUUID(WUPPreferences.getVerificationUUID());
        submitCodeRequest.setPassword(password);

        try {
            Ion.with(this)
                    .load("POST",url)
                    .setLogging("jananetha", Log.DEBUG)
                    .followRedirect(false)
                    .addHeader("X-Username", username)
                    .addHeader("X-Password", password)
                    .setJsonPojoBody(submitCodeRequest)
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
                                SubmitCodeResponse loginResponse = new Gson().fromJson(jsonArrayResponse.getResult(), new TypeToken<SubmitCodeResponse>() {
                                }.getType());
                                Log.d("responseCheck", "" + loginResponse.getResponseMessage());
                                progressBar.setVisibility(View.GONE);
                                if(loginResponse.getResponseMessage().equals("Operation executed successfully")){
                                    Intent intent = new Intent(SubmitCodeActivity.this,LoginAcitvity.class);
                                    startActivity(intent);
                                    finish();

                                }else {
                                    final androidx.appcompat.app.AlertDialog.Builder alertDialogBuilder = new androidx.appcompat.app.AlertDialog.Builder(SubmitCodeActivity.this);

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

}
