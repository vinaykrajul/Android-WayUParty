
package com.acculytixs.mobile.wayuparty.dtos;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TimeSlotListReschedule implements Serializable
{

    @SerializedName("timeSlot")
    @Expose
    private String timeSlot;
    private final static long serialVersionUID = -7062666494945176909L;

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

}
