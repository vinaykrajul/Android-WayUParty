
package com.acculytixs.mobile.wayuparty.dtos;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Reschedule implements Serializable
{

    @SerializedName("serviceStartDate")
    @Expose
    private String serviceStartDate;
    @SerializedName("serviceEndDate")
    @Expose
    private String serviceEndDate;
    @SerializedName("serviceTimeSlots")
    @Expose
    private String serviceTimeSlots;
    @SerializedName("timeSlotList")
    @Expose
    private List<TimeSlotListReschedule> timeSlotList = null;
    @SerializedName("serviceDates")
    @Expose
    private List<ServiceDate> serviceDates = null;
    private final static long serialVersionUID = 7165983843428656639L;

    public String getServiceStartDate() {
        return serviceStartDate;
    }

    public void setServiceStartDate(String serviceStartDate) {
        this.serviceStartDate = serviceStartDate;
    }

    public String getServiceEndDate() {
        return serviceEndDate;
    }

    public void setServiceEndDate(String serviceEndDate) {
        this.serviceEndDate = serviceEndDate;
    }

    public String getServiceTimeSlots() {
        return serviceTimeSlots;
    }

    public void setServiceTimeSlots(String serviceTimeSlots) {
        this.serviceTimeSlots = serviceTimeSlots;
    }

    public List<TimeSlotListReschedule> getTimeSlotList() {
        return timeSlotList;
    }

    public void setTimeSlotList(List<TimeSlotListReschedule> timeSlotList) {
        this.timeSlotList = timeSlotList;
    }

    public List<ServiceDate> getServiceDates() {
        return serviceDates;
    }

    public void setServiceDates(List<ServiceDate> serviceDates) {
        this.serviceDates = serviceDates;
    }

}
