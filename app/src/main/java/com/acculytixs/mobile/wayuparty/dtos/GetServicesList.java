
package com.acculytixs.mobile.wayuparty.dtos;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetServicesList implements Serializable
{

    @SerializedName("object")
    @Expose
    private DataServiceList object;
    @SerializedName("error")
    @Expose
    private java.lang.Object error;
    @SerializedName("response")
    @Expose
    private String response;
    @SerializedName("responseMessage")
    @Expose
    private java.lang.Object responseMessage;
    private final static long serialVersionUID = -6990049616272698298L;

    public DataServiceList getObject() {
        return object;
    }

    public void setObject(DataServiceList object) {
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

    public java.lang.Object getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(java.lang.Object responseMessage) {
        this.responseMessage = responseMessage;
    }

}
