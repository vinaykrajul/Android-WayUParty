
package com.acculytixs.mobile.wayuparty.dtos;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datuc implements Serializable
{

    @SerializedName("masterServiceId")
    @Expose
    private Integer masterServiceId;
    @SerializedName("serviceName")
    @Expose
    private String serviceName;
    @SerializedName("orderAmount")
    @Expose
    private Integer orderAmount;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("totalAmount")
    @Expose
    private Integer totalAmount;
    @SerializedName("cartUUID")
    @Expose
    private String cartUUID;
    @SerializedName("serviceOrderDate")
    @Expose
    private String serviceOrderDate;
    @SerializedName("timeSlot")
    @Expose
    private String timeSlot;
    @SerializedName("serviceImage")
    @Expose
    private String serviceImage;
    @SerializedName("currency")
    @Expose
    private String currency;
    private final static long serialVersionUID = 4404919788465572077L;

    public Integer getMasterServiceId() {
        return masterServiceId;
    }

    public void setMasterServiceId(Integer masterServiceId) {
        this.masterServiceId = masterServiceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Integer getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Integer orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getCartUUID() {
        return cartUUID;
    }

    public void setCartUUID(String cartUUID) {
        this.cartUUID = cartUUID;
    }

    public String getServiceOrderDate() {
        return serviceOrderDate;
    }

    public void setServiceOrderDate(String serviceOrderDate) {
        this.serviceOrderDate = serviceOrderDate;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public String getServiceImage() {
        return serviceImage;
    }

    public void setServiceImage(String serviceImage) {
        this.serviceImage = serviceImage;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

}
