
package com.acculytixs.mobile.wayuparty.dtos;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServicesList implements Serializable
{

    @SerializedName("serviceId")
    @Expose
    private Integer serviceId;
    @SerializedName("serviceName")
    @Expose
    private String serviceName;
    @SerializedName("serviceDisplayName")
    @Expose
    private String serviceDisplayName;
    @SerializedName("serviceUUID")
    @Expose
    private String serviceUUID;
    @SerializedName("serviceImage")
    @Expose
    private String serviceImage;
    @SerializedName("isEntryRatioEnabled")
    @Expose
    private String isEntryRatioEnabled;
    @SerializedName("menRatio")
    @Expose
    private Integer menRatio;
    @SerializedName("womenRatio")
    @Expose
    private Integer womenRatio;
    private final static long serialVersionUID = 3137069940383460875L;

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceDisplayName() {
        return serviceDisplayName;
    }

    public void setServiceDisplayName(String serviceDisplayName) {
        this.serviceDisplayName = serviceDisplayName;
    }

    public String getServiceUUID() {
        return serviceUUID;
    }

    public void setServiceUUID(String serviceUUID) {
        this.serviceUUID = serviceUUID;
    }

    public String getServiceImage() {
        return serviceImage;
    }

    public void setServiceImage(String serviceImage) {
        this.serviceImage = serviceImage;
    }

    public String getIsEntryRatioEnabled() {
        return isEntryRatioEnabled;
    }

    public void setIsEntryRatioEnabled(String isEntryRatioEnabled) {
        this.isEntryRatioEnabled = isEntryRatioEnabled;
    }

    public Integer getMenRatio() {
        return menRatio;
    }

    public void setMenRatio(Integer menRatio) {
        this.menRatio = menRatio;
    }

    public Integer getWomenRatio() {
        return womenRatio;
    }

    public void setWomenRatio(Integer womenRatio) {
        this.womenRatio = womenRatio;
    }
}
