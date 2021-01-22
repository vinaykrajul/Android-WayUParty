
package com.acculytixs.mobile.wayuparty.dtos;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServiceDate implements Serializable
{

    @SerializedName("passableDate")
    @Expose
    private String passableDate;
    @SerializedName("serviceDate")
    @Expose
    private String serviceDate;
    private final static long serialVersionUID = 6014065976810637610L;

    public String getPassableDate() {
        return passableDate;
    }

    public void setPassableDate(String passableDate) {
        this.passableDate = passableDate;
    }

    public String getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(String serviceDate) {
        this.serviceDate = serviceDate;
    }

}
