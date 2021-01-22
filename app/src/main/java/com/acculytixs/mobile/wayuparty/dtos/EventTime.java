
package com.acculytixs.mobile.wayuparty.dtos;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EventTime implements Serializable
{

    @SerializedName("eventDate")
    @Expose
    private String eventDate;
    @SerializedName("timeSlots")
    @Expose
    private List<TimeSlot> timeSlots = null;
    @SerializedName("eventUUID")
    @Expose
    private String eventUUID;
    private final static long serialVersionUID = 8494684055999806840L;

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public List<TimeSlot> getTimeSlots() {
        return timeSlots;
    }

    public void setTimeSlots(List<TimeSlot> timeSlots) {
        this.timeSlots = timeSlots;
    }

    public String getEventUUID() {
        return eventUUID;
    }

    public void setEventUUID(String eventUUID) {
        this.eventUUID = eventUUID;
    }

}
