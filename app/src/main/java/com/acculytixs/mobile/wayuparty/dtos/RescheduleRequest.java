
package com.acculytixs.mobile.wayuparty.dtos;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RescheduleRequest implements Serializable
{

    @SerializedName("orderUUID")
    @Expose
    private String orderUUID;
    @SerializedName("serviceOrderDate")
    @Expose
    private String serviceOrderDate;
    @SerializedName("serviceTimeSlot")
    @Expose
    private String serviceTimeSlot;
    private final static long serialVersionUID = -8523539505704117204L;

    public String getOrderUUID() {
        return orderUUID;
    }

    public void setOrderUUID(String orderUUID) {
        this.orderUUID = orderUUID;
    }

    public String getServiceOrderDate() {
        return serviceOrderDate;
    }

    public void setServiceOrderDate(String serviceOrderDate) {
        this.serviceOrderDate = serviceOrderDate;
    }

    public String getServiceTimeSlot() {
        return serviceTimeSlot;
    }

    public void setServiceTimeSlot(String serviceTimeSlot) {
        this.serviceTimeSlot = serviceTimeSlot;
    }

}
