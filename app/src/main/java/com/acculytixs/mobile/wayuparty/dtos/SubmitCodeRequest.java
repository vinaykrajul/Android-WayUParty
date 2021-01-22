
package com.acculytixs.mobile.wayuparty.dtos;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubmitCodeRequest implements Serializable
{

    @SerializedName("verificationUUID")
    @Expose
    private String verificationUUID;
    @SerializedName("verificationCode")
    @Expose
    private String verificationCode;
    @SerializedName("password")
    @Expose
    private String password;
    private final static long serialVersionUID = 9166548964500360527L;

    public String getVerificationUUID() {
        return verificationUUID;
    }

    public void setVerificationUUID(String verificationUUID) {
        this.verificationUUID = verificationUUID;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
