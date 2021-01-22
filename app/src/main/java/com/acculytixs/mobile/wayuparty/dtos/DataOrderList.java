
package com.acculytixs.mobile.wayuparty.dtos;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataOrderList implements Serializable
{

    @SerializedName("clubName")
    @Expose
    private String clubName;
    @SerializedName("clubLocation")
    @Expose
    private String clubLocation;
    @SerializedName("orderDate")
    @Expose
    private String orderDate;
    @SerializedName("orderItems")
    @Expose
    private String orderItems;
    @SerializedName("orderRates")
    @Expose
    private String orderRates;
    @SerializedName("totalAmount")
    @Expose
    private Integer totalAmount;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("orderStatus")
    @Expose
    private String orderStatus;
    @SerializedName("canceledOrdersCount")
    @Expose
    private Integer canceledOrdersCount;
    @SerializedName("orderUUIDs")
    @Expose
    private String orderUUIDs;
    @SerializedName("qrCode")
    @Expose
    private String qrCode;
    @SerializedName("orderDateStatus")
    @Expose
    private String orderDateStatus;
    @SerializedName("orderItemsCanceled")
    @Expose
    private String orderItemsCanceled;
    @SerializedName("orderItemsReschedule")
    @Expose
    private String orderItemsReschedule;
    private final static long serialVersionUID = -5798158600867653236L;

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getClubLocation() {
        return clubLocation;
    }

    public void setClubLocation(String clubLocation) {
        this.clubLocation = clubLocation;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(String orderItems) {
        this.orderItems = orderItems;
    }

    public String getOrderRates() {
        return orderRates;
    }

    public void setOrderRates(String orderRates) {
        this.orderRates = orderRates;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getCanceledOrdersCount() {
        return canceledOrdersCount;
    }

    public void setCanceledOrdersCount(Integer canceledOrdersCount) {
        this.canceledOrdersCount = canceledOrdersCount;
    }

    public String getOrderUUIDs() {
        return orderUUIDs;
    }

    public void setOrderUUIDs(String orderUUIDs) {
        this.orderUUIDs = orderUUIDs;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getOrderDateStatus() {
        return orderDateStatus;
    }

    public void setOrderDateStatus(String orderDateStatus) {
        this.orderDateStatus = orderDateStatus;
    }

    public String getOrderItemsCanceled() {
        return orderItemsCanceled;
    }

    public void setOrderItemsCanceled(String orderItemsCanceled) {
        this.orderItemsCanceled = orderItemsCanceled;
    }

    public String getOrderItemsReschedule() {
        return orderItemsReschedule;
    }

    public void setOrderItemsReschedule(String orderItemsReschedule) {
        this.orderItemsReschedule = orderItemsReschedule;
    }

}
