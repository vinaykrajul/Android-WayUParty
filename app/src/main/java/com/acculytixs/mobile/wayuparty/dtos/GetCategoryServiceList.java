
package com.acculytixs.mobile.wayuparty.dtos;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetCategoryServiceList implements Serializable
{

    @SerializedName("data")
    @Expose
    private List<Datun> data = null;
    @SerializedName("response")
    @Expose
    private String response;
    @SerializedName("responseMessage")
    @Expose
    private java.lang.Object responseMessage;
    @SerializedName("recordsTotal")
    @Expose
    private Integer recordsTotal;
    @SerializedName("recordsFiltered")
    @Expose
    private Integer recordsFiltered;
    private final static long serialVersionUID = 3938132290494046950L;

    public List<Datun> getData() {
        return data;
    }

    public void setData(List<Datun> data) {
        this.data = data;
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

    public Integer getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(Integer recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public Integer getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(Integer recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

}
