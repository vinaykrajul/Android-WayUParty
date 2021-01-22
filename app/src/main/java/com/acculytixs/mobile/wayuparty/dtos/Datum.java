
package com.acculytixs.mobile.wayuparty.dtos;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum implements Serializable
{

    @SerializedName("vendorId")
    @Expose
    private Object vendorId;
    @SerializedName("vendorName")
    @Expose
    private String vendorName;
    @SerializedName("vendorEmail")
    @Expose
    private String vendorEmail;
    @SerializedName("vendorMobile")
    @Expose
    private String vendorMobile;
    @SerializedName("vendorUUID")
    @Expose
    private String vendorUUID;
    @SerializedName("vendorProfileImg")
    @Expose
    private String vendorProfileImg;
    @SerializedName("establishedYear")
    @Expose
    private Integer establishedYear;
    @SerializedName("vendorCapacity")
    @Expose
    private Integer vendorCapacity;
    @SerializedName("costForTwoPeople")
    @Expose
    private Integer costForTwoPeople;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("vendorCode")
    @Expose
    private String vendorCode;
    @SerializedName("vendorDescription")
    @Expose
    private Object vendorDescription;
    @SerializedName("bestSellingItems")
    @Expose
    private String bestSellingItems;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("country")
    @Expose
    private Object country;
    @SerializedName("state")
    @Expose
    private Object state;
    @SerializedName("city")
    @Expose
    private Object city;
    @SerializedName("pincode")
    @Expose
    private Object pincode;
    @SerializedName("latitude")
    @Expose
    private Object latitude;
    @SerializedName("longitude")
    @Expose
    private Object longitude;
    @SerializedName("phoneNumber")
    @Expose
    private Object phoneNumber;
    @SerializedName("vendorAddress")
    @Expose
    private Object vendorAddress;
    private final static long serialVersionUID = 5920581796952900336L;

    public Object getVendorId() {
        return vendorId;
    }

    public void setVendorId(Object vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorEmail() {
        return vendorEmail;
    }

    public void setVendorEmail(String vendorEmail) {
        this.vendorEmail = vendorEmail;
    }

    public String getVendorMobile() {
        return vendorMobile;
    }

    public void setVendorMobile(String vendorMobile) {
        this.vendorMobile = vendorMobile;
    }

    public String getVendorUUID() {
        return vendorUUID;
    }

    public void setVendorUUID(String vendorUUID) {
        this.vendorUUID = vendorUUID;
    }

    public String getVendorProfileImg() {
        return vendorProfileImg;
    }

    public void setVendorProfileImg(String vendorProfileImg) {
        this.vendorProfileImg = vendorProfileImg;
    }

    public Integer getEstablishedYear() {
        return establishedYear;
    }

    public void setEstablishedYear(Integer establishedYear) {
        this.establishedYear = establishedYear;
    }

    public Integer getVendorCapacity() {
        return vendorCapacity;
    }

    public void setVendorCapacity(Integer vendorCapacity) {
        this.vendorCapacity = vendorCapacity;
    }

    public Integer getCostForTwoPeople() {
        return costForTwoPeople;
    }

    public void setCostForTwoPeople(Integer costForTwoPeople) {
        this.costForTwoPeople = costForTwoPeople;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public Object getVendorDescription() {
        return vendorDescription;
    }

    public void setVendorDescription(Object vendorDescription) {
        this.vendorDescription = vendorDescription;
    }

    public String getBestSellingItems() {
        return bestSellingItems;
    }

    public void setBestSellingItems(String bestSellingItems) {
        this.bestSellingItems = bestSellingItems;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Object getCountry() {
        return country;
    }

    public void setCountry(Object country) {
        this.country = country;
    }

    public Object getState() {
        return state;
    }

    public void setState(Object state) {
        this.state = state;
    }

    public Object getCity() {
        return city;
    }

    public void setCity(Object city) {
        this.city = city;
    }

    public Object getPincode() {
        return pincode;
    }

    public void setPincode(Object pincode) {
        this.pincode = pincode;
    }

    public Object getLatitude() {
        return latitude;
    }

    public void setLatitude(Object latitude) {
        this.latitude = latitude;
    }

    public Object getLongitude() {
        return longitude;
    }

    public void setLongitude(Object longitude) {
        this.longitude = longitude;
    }

    public Object getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Object phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Object getVendorAddress() {
        return vendorAddress;
    }

    public void setVendorAddress(Object vendorAddress) {
        this.vendorAddress = vendorAddress;
    }

}
