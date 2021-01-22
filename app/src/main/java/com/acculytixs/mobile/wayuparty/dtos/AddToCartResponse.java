
package com.acculytixs.mobile.wayuparty.dtos;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddToCartResponse implements Serializable
{

    @SerializedName("object")
    @Expose
    private java.lang.Object object;
    @SerializedName("error")
    @Expose
    private java.lang.Object error;
    @SerializedName("response")
    @Expose
    private String response;
    @SerializedName("responseMessage")
    @Expose
    private String responseMessage;
    private final static long serialVersionUID = -6926120405382419954L;

    public java.lang.Object getObject() {
        return object;
    }

    public void setObject(java.lang.Object object) {
        this.object = object;
    }

    public java.lang.Object getError() {
        return error;
    }

    public void setError(java.lang.Object error) {
        this.error = error;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

}
