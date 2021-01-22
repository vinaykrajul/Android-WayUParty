
package com.acculytixs.mobile.wayuparty.dtos;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EventTicketData implements Serializable
{

    @SerializedName("ticketType")
    @Expose
    private String ticketType;
    @SerializedName("ticketAmount")
    @Expose
    private Integer ticketAmount;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("maxBookingAllowed")
    @Expose
    private Integer maxBookingAllowed;
    @SerializedName("eventUUID")
    @Expose
    private String eventUUID;
    private final static long serialVersionUID = 6419722766669949215L;

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public Integer getTicketAmount() {
        return ticketAmount;
    }

    public void setTicketAmount(Integer ticketAmount) {
        this.ticketAmount = ticketAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getMaxBookingAllowed() {
        return maxBookingAllowed;
    }

    public void setMaxBookingAllowed(Integer maxBookingAllowed) {
        this.maxBookingAllowed = maxBookingAllowed;
    }

    public String getEventUUID() {
        return eventUUID;
    }

    public void setEventUUID(String eventUUID) {
        this.eventUUID = eventUUID;
    }

}
