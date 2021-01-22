
package com.acculytixs.mobile.wayuparty.dtos;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataEvents implements Serializable
{

    @SerializedName("eventName")
    @Expose
    private String eventName;
    @SerializedName("eventLocation")
    @Expose
    private String eventLocation;
    @SerializedName("date")
    @Expose
    private Integer date;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("day")
    @Expose
    private String day;
    @SerializedName("month")
    @Expose
    private String month;
    @SerializedName("eventImage")
    @Expose
    private String eventImage;
    @SerializedName("eventUUID")
    @Expose
    private String eventUUID;
    @SerializedName("eventDate")
    @Expose
    private String eventDate;
    @SerializedName("eventHost")
    @Expose
    private String eventHost;
    @SerializedName("eventTimeSlots")
    @Expose
    private String eventTimeSlots;
    @SerializedName("bookingsOpen")
    @Expose
    private Integer bookingsOpen;
    @SerializedName("dayDiff")
    @Expose
    private java.lang.Object dayDiff;
    private final static long serialVersionUID = 7953414692850704470L;

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getEventImage() {
        return eventImage;
    }

    public void setEventImage(String eventImage) {
        this.eventImage = eventImage;
    }

    public String getEventUUID() {
        return eventUUID;
    }

    public void setEventUUID(String eventUUID) {
        this.eventUUID = eventUUID;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventHost() {
        return eventHost;
    }

    public void setEventHost(String eventHost) {
        this.eventHost = eventHost;
    }

    public String getEventTimeSlots() {
        return eventTimeSlots;
    }

    public void setEventTimeSlots(String eventTimeSlots) {
        this.eventTimeSlots = eventTimeSlots;
    }

    public Integer getBookingsOpen() {
        return bookingsOpen;
    }

    public void setBookingsOpen(Integer bookingsOpen) {
        this.bookingsOpen = bookingsOpen;
    }

    public java.lang.Object getDayDiff() {
        return dayDiff;
    }

    public void setDayDiff(java.lang.Object dayDiff) {
        this.dayDiff = dayDiff;
    }

}
