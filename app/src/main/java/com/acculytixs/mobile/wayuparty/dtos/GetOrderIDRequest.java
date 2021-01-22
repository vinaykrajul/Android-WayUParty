
package com.acculytixs.mobile.wayuparty.dtos;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetOrderIDRequest implements Serializable
{

    @SerializedName("cartAmount")
    @Expose
    private String cartAmount;
    @SerializedName("currency")
    @Expose
    private String currency;
    private final static long serialVersionUID = 2126036198241969235L;

    public String getCartAmount() {
        return cartAmount;
    }

    public void setCartAmount(String cartAmount) {
        this.cartAmount = cartAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

}
