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
import com.acculytixs.mobile.wayuparty.dtos.Datun;
import com.acculytixs.mobile.wayuparty.dtos.LoginRegisteredUserResponse;
import com.acculytixs.mobile.wayuparty.dtos.LoginRequest;
import com.facebook.CallbackManager;
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
import androidx.appcompat.widget.Toolbar;

public class LoginAcitvity extends AppCompatActivity implements View.OnClickListener,Validator.ValidationListener {

    @Required(order = 1, messageResId = R.string.validation_username)
    private EditText etUsername;
    @Required(order = 2, messageResId = R.string.validation_password)
    private EditText etPassword;

    @Required(order = 3, messageResId = R.string.validation_number)
    private EditText etMobile;
    private Validator validator;
    ProgressBar progressBar;
    public boolean isValidationDone = false;
    TextView tv_login_signup;
    TextView tv_forgot_password;
    TextInputLayout textInputLayout;
    int flag =0;
    ImageView fbLogin,gLogin;
    ImageView btn_login,btn_sign_up;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        btn_login = (ImageView) findViewById(R.id.btn_login);
        btn_sign_up = (ImageView) findViewById(R.id.btn_signup);
        tv_forgot_password = (TextView) findViewById(R.id.tv_forgot_password);
        tv_forgot_password.setOnClickListener(this);
        btn_sign_up.setOnClickListener(this);
        btn_login.setOnClickListener(this);
        inIt();

    }
    public void inIt(){

        progressBar = findViewById(R.id.main_progress);

        etUsername = (EditText)findViewById(R.id.etUserId);

        etPassword = (EditText)findViewById(R.id.etPassword);


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
                            new AlertDialog.Builder(LoginAcitvity.this)
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
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btn_login:
                getValidator().validate();
                if (!isValidationDone) {
                    return;
                }
                callLoginService(etUsername.getText().toString().replaceAll(" ",""),etPassword.getText().toString().replaceAll(" ",""));

                break;
            case  R.id.btn_signup:
                Intent intent = new Intent(LoginAcitvity.this,SignUpActivity.class);
                startActivity(intent);
                finish();
                break;
            case  R.id.tv_forgot_password:
                Intent intent1 = new Intent(LoginAcitvity.this,ForgotPasswordActivity.class);
                startActivity(intent1);
                finish();
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
    public void callLoginService(final String username, final String password){
        progressBar.setVisibility(View.VISIBLE);
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
                                LoginRegisteredUserResponse loginResponse = new Gson().fromJson(jsonArrayResponse.getResult(), new TypeToken<LoginRegisteredUserResponse>() {
                                }.getType());
                                Log.d("responseCheck", "" + loginResponse.getResponse());
                                Log.d("responseCheck uuid", "" + loginResponse.getObject().getUserUUID());

                                //Toast.makeText(LoginActivity.this,loginResponse.getFirstName(),Toast.LENGTH_LONG).show();
                               progressBar.setVisibility(View.GONE);
                                if((loginResponse.getObject().getUserUUID()!=null)&&!(loginResponse.getObject().getUserUUID().isEmpty())){
                                    WUPPreferences.saveUserId(loginResponse.getObject().getUserUUID());
                                    WUPPreferences.saveUserName(username);
                                    WUPPreferences.savePasswrod(password);
                                    WUPPreferences.saveEmail(loginResponse.getObject().getUserEmail());
                                    WUPPreferences.saveMobileNum(loginResponse.getObject().getUserMobile());
                                    Log.d("responseCheck uuid", "" + WUPPreferences.getUserId());
                                    Intent returnIntent = new Intent();
                                    setResult(3,returnIntent);
                                    finish();
                                }else {
                                    final androidx.appcompat.app.AlertDialog.Builder alertDialogBuilder = new androidx.appcompat.app.AlertDialog.Builder(LoginAcitvity.this);

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
