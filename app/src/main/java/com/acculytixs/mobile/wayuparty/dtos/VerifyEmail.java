
package com.acculytixs.mobile.wayuparty.dtos;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VerifyEmail implements Serializable
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
    @SerializedName("userUUID")
    @Expose
    private String userUUID;
    private final static long serialVersionUID = 8005029583674681985L;

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

    public String getUserUUID() {
        return userUUID;
    }

    public void setUserUUID(String userUUID) {
        this.userUUID = userUUID;
    }

}
