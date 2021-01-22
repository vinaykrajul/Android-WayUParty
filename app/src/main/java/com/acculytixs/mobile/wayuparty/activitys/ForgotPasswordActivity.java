package com.acculytixs.mobile.wayuparty.activitys;

import android.app.Activity;
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
import com.acculytixs.mobile.wayuparty.dtos.VerifyEmailResponse;
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

public class ForgotPasswordActivity extends AppCompatActivity implements Validator.ValidationListener{

    @Required(order = 1, messageResId = R.string.validation_verify_email)
    private EditText etVerifyEmail;
    ImageView btn_verifyEmail;
    private Validator validator;
    public boolean isValidationDone = false;
    ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password_activity);
        etVerifyEmail = (EditText)findViewById(R.id.etVerifyEmail);
        progressBar = findViewById(R.id.main_progress);
        btn_verifyEmail = (ImageView) findViewById(R.id.btn_verify_email);
        btn_verifyEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getValidator().validate();
                if (!isValidationDone) {
                    return;
                }
                callLoginService(etVerifyEmail.getText().toString().replaceAll(" ",""));

            }
        });
        etVerifyEmail.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_NEXT || actionId == EditorInfo.IME_NULL) {

                    etVerifyEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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
        etVerifyEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);

                }
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
    public void callLoginService(final String email){
        progressBar.setVisibility(View.VISIBLE);
        String url = WayUPartyConstants.TEST_URL+WayUPartyConstants.URL_VERIFY_EMAIL+"?email="+email;

        try {
            Ion.with(this)
                    .load("POST",url)
                    .setLogging("jananetha", Log.DEBUG)
                    .followRedirect(false)
                    .addHeader("X-Username", WUPPreferences.getUserName())
                    .addHeader("X-Password", WUPPreferences.getPassword())
                    .as(new TypeToken<JsonObject>() {
                    })
                    .withResponse()
                    .setCallback(new FutureCallback<Response<JsonObject>>() {
                        @Override
                        public void onCompleted(Exception e, Response<JsonObject> jsonArrayResponse) {
                            try {
                                String jsonString = new com.google.gson.Gson().toJson(jsonArrayResponse);
                                VerifyEmailResponse loginResponse = new Gson().fromJson(jsonArrayResponse.getResult(), new TypeToken<VerifyEmailResponse>() {
                                }.getType());
                                Log.d("responseCheck", "" + loginResponse.getResponseMessage());
                               progressBar.setVisibility(View.GONE);
                                if(!(loginResponse.getObject().getVerificationUUID().isEmpty())||(loginResponse.getObject().getVerificationUUID() != null)){
                                    WUPPreferences.saveVerificationUUID(loginResponse.getObject().getVerificationUUID());
                                    Intent intent1 = new Intent(ForgotPasswordActivity.this,SubmitCodeActivity.class);
                                    startActivity(intent1);
                                    finish();
                                }else {
                                    final androidx.appcompat.app.AlertDialog.Builder alertDialogBuilder = new androidx.appcompat.app.AlertDialog.Builder(ForgotPasswordActivity.this);

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
}
