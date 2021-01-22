
package com.acculytixs.mobile.wayuparty.dtos;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataEventCategories implements Serializable
{

    @SerializedName("categoryName")
    @Expose
    private String categoryName;
    @SerializedName("minimumCost")
    @Expose
    private Integer minimumCost;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("eventUUID")
    @Expose
    private String eventUUID;
    @SerializedName("categoryType")
    @Expose
    private String categoryType;
    private final static long serialVersionUID = 4430337304819594283L;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getMinimumCost() {
        return minimumCost;
    }

    public void setMinimumCost(Integer minimumCost) {
        this.minimumCost = minimumCost;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getEventUUID() {
        return eventUUID;
    }

    public void setEventUUID(String eventUUID) {
        this.eventUUID = eventUUID;
    }

    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }

}
