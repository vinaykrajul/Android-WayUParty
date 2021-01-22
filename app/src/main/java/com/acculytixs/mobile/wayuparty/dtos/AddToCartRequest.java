
package com.acculytixs.mobile.wayuparty.dtos;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddToCartRequest implements Serializable
{

    @SerializedName("userUUID")
    @Expose
    private String userUUID;
    @SerializedName("masterServiceUUID")
    @Expose
    private String masterServiceUUID;
    @SerializedName("vendorUUID")
    @Expose
    private String vendorUUID;
    @SerializedName("serviceOrderDate")
    @Expose
    private String serviceOrderDate;
    @SerializedName("timeslot")
    @Expose
    private String timeslot;
    @SerializedName("orderAmount")
    @Expose
    private String orderAmount;
    @SerializedName("quantity")
    @Expose
    private String quantity;
    @SerializedName("totalAmount")
    @Expose
    private String totalAmount;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("packageMenuItems")
    @Expose
    private String packageMenuItems;
    @SerializedName("surpriseDetails")
    @Expose
    private String surpriseDetails;
    @SerializedName("surpriseInstructions")
    @Expose
    private String surpriseInstructions;
    private final static long serialVersionUID = -46500031644262405L;

    public String getUserUUID() {
        return userUUID;
    }

    public void setUserUUID(String userUUID) {
        this.userUUID = userUUID;
    }

    public String getMasterServiceUUID() {
        return masterServiceUUID;
    }

    public void setMasterServiceUUID(String masterServiceUUID) {
        this.masterServiceUUID = masterServiceUUID;
    }

    public String getVendorUUID() {
        return vendorUUID;
    }

    public void setVendorUUID(String vendorUUID) {
        this.vendorUUID = vendorUUID;
    }

    public String getServiceOrderDate() {
        return serviceOrderDate;
    }

    public void setServiceOrderDate(String serviceOrderDate) {
        this.serviceOrderDate = serviceOrderDate;
    }

    public String getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(String timeslot) {
        this.timeslot = timeslot;
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPackageMenuItems() {
        return packageMenuItems;
    }

    public void setPackageMenuItems(String packageMenuItems) {
        this.packageMenuItems = packageMenuItems;
    }

    public String getSurpriseDetails() {
        return surpriseDetails;
    }

    public void setSurpriseDetails(String surpriseDetails) {
        this.surpriseDetails = surpriseDetails;
    }

    public String getSurpriseInstructions() {
        return surpriseInstructions;
    }

    public void setSurpriseInstructions(String surpriseInstructions) {
        this.surpriseInstructions = surpriseInstructions;
    }

}
