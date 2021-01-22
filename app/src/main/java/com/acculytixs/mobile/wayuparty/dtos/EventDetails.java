
package com.acculytixs.mobile.wayuparty.dtos;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EventDetails implements Serializable
{

    @SerializedName("eventName")
    @Expose
    private String eventName;
    @SerializedName("eventType")
    @Expose
    private String eventType;
    @SerializedName("eventHost")
    @Expose
    private String eventHost;
    @SerializedName("eventDate")
    @Expose
    private String eventDate;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("age")
    @Expose
    private String age;
    @SerializedName("minimumStartingAmount")
    @Expose
    private String minimumStartingAmount;
    @SerializedName("eventImage")
    @Expose
    private String eventImage;
    @SerializedName("duration")
    @Expose
    private String duration;
    @SerializedName("musicType")
    @Expose
    private String musicType;
    @SerializedName("eventUUID")
    @Expose
    private String eventUUID;
    @SerializedName("eventTickets")
    @Expose
    private String eventTickets;
    @SerializedName("eventTimeSlots")
    @Expose
    private String eventTimeSlots;
    @SerializedName("currency")
    @Expose
    private String currency;
    private final static long serialVersionUID = -6588249333695753852L;

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventHost() {
        return eventHost;
    }

    public void setEventHost(String eventHost) {
        this.eventHost = eventHost;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getMinimumStartingAmount() {
        return minimumStartingAmount;
    }

    public void setMinimumStartingAmount(String minimumStartingAmount) {
        this.minimumStartingAmount = minimumStartingAmount;
    }

    public String getEventImage() {
        return eventImage;
    }

    public void setEventImage(String eventImage) {
        this.eventImage = eventImage;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getMusicType() {
        return musicType;
    }

    public void setMusicType(String musicType) {
        this.musicType = musicType;
    }

    public String getEventUUID() {
        return eventUUID;
    }

    public void setEventUUID(String eventUUID) {
        this.eventUUID = eventUUID;
    }

    public String getEventTickets() {
        return eventTickets;
    }

    public void setEventTickets(String eventTickets) {
        this.eventTickets = eventTickets;
    }

    public String getEventTimeSlots() {
        return eventTimeSlots;
    }

    public void setEventTimeSlots(String eventTimeSlots) {
        this.eventTimeSlots = eventTimeSlots;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

}
