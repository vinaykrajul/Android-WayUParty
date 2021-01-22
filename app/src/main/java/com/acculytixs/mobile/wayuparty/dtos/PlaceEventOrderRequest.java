
package com.acculytixs.mobile.wayuparty.dtos;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlaceEventOrderRequest implements Serializable
{

    @SerializedName("vendorUUID")
    @Expose
    private String vendorUUID;
    @SerializedName("userUUID")
    @Expose
    private String userUUID;
    @SerializedName("eventUUID")
    @Expose
    private String eventUUID;
    @SerializedName("ticketType")
    @Expose
    private String ticketType;
    @SerializedName("ticketAmount")
    @Expose
    private String ticketAmount;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("categoryType")
    @Expose
    private String categoryType;
    @SerializedName("quantity")
    @Expose
    private String quantity;
    @SerializedName("timeslot")
    @Expose
    private String timeslot;
    @SerializedName("paymentId")
    @Expose
    private String paymentId;
    @SerializedName("orderId")
    @Expose
    private String orderId;
    @SerializedName("signature")
    @Expose
    private String signature;
    private final static long serialVersionUID = -8255272381264224260L;

    public String getVendorUUID() {
        return vendorUUID;
    }

    public void setVendorUUID(String vendorUUID) {
        this.vendorUUID = vendorUUID;
    }

    public String getUserUUID() {
        return userUUID;
    }

    public void setUserUUID(String userUUID) {
        this.userUUID = userUUID;
    }

    public String getEventUUID() {
        return eventUUID;
    }

    public void setEventUUID(String eventUUID) {
        this.eventUUID = eventUUID;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public String getTicketAmount() {
        return ticketAmount;
    }

    public void setTicketAmount(String ticketAmount) {
        this.ticketAmount = ticketAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(String timeslot) {
        this.timeslot = timeslot;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

}
