
package com.acculytixs.mobile.wayuparty.dtos;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderID implements Serializable
{

    @SerializedName("amount")
    @Expose
    private Integer amount;
    @SerializedName("orderId")
    @Expose
    private String orderId;
    @SerializedName("receipt")
    @Expose
    private String receipt;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("cartAmount")
    @Expose
    private java.lang.Object cartAmount;
    private final static long serialVersionUID = -4613052507441790837L;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public java.lang.Object getCartAmount() {
        return cartAmount;
    }

    public void setCartAmount(java.lang.Object cartAmount) {
        this.cartAmount = cartAmount;
    }

}
