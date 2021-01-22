
package com.acculytixs.mobile.wayuparty.dtos;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlaceOrderRequest implements Serializable
{

    @SerializedName("userUUID")
    @Expose
    private String userUUID;
    @SerializedName("cartItems")
    @Expose
    private String cartItems;
    @SerializedName("paymentId")
    @Expose
    private String paymentId;
    @SerializedName("orderId")
    @Expose
    private String orderId;
    @SerializedName("signature")
    @Expose
    private String signature;
    private final static long serialVersionUID = -9030662834089316900L;

    public String getUserUUID() {
        return userUUID;
    }

    public void setUserUUID(String userUUID) {
        this.userUUID = userUUID;
    }

    public String getCartItems() {
        return cartItems;
    }

    public void setCartItems(String cartItems) {
        this.cartItems = cartItems;
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
