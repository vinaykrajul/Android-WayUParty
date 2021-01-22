
package com.acculytixs.mobile.wayuparty.dtos;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datun implements Serializable
{

    @SerializedName("serviceId")
    @Expose
    private Integer serviceId;
    @SerializedName("vendorId")
    @Expose
    private Integer vendorId;
    @SerializedName("service")
    @Expose
    private String service;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("subCategory")
    @Expose
    private String subCategory;
    @SerializedName("actualPrice")
    @Expose
    private Double actualPrice;
    @SerializedName("offerPrice")
    @Expose
    private Double offerPrice;
    @SerializedName("minimumOrder")
    @Expose
    private Double minimumOrder;
    @SerializedName("discountValue")
    @Expose
    private Double discountValue;
    @SerializedName("discountType")
    @Expose
    private String discountType;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("serviceStartDate")
    @Expose
    private String serviceStartDate;
    @SerializedName("serviceEndDate")
    @Expose
    private String serviceEndDate;
    @SerializedName("masterServiceUUID")
    @Expose
    private String masterServiceUUID;
    @SerializedName("serviceImage")
    @Expose
    private String serviceImage;
    @SerializedName("serviceTimeSlots")
    @Expose
    private String serviceTimeSlots;
    @SerializedName("timeSlotList")
    @Expose
    private List<TimeSlotList> timeSlotList = null;
    @SerializedName("serviceDates")
    @Expose
    private List<ServiceDate> serviceDates = null;
    @SerializedName("packageMenuItems")
    @Expose
    private List<PackageMenuItem> packageMenuItems = null;
    @SerializedName("surpriseForList")
    @Expose
    private List<SurpriseForList> surpriseForList = null;
    @SerializedName("surpriseOccationList")
    @Expose
    private List<SurpriseOccationList> surpriseOccationList = null;

    @SerializedName("allowed")
    @Expose
    private Integer allowed;
    @SerializedName("termsAndConditions")
    @Expose
    private String termsAndConditions;
    @SerializedName("startDate")
    @Expose
    private String startDate;
    @SerializedName("endDate")
    @Expose
    private String endDate;
    @SerializedName("description")
    @Expose
    private java.lang.Object description;
    @SerializedName("serviceOffer")
    @Expose
    private java.lang.Object serviceOffer;
    @SerializedName("categoryId")
    @Expose
    private java.lang.Object categoryId;
    @SerializedName("subCategoryId")
    @Expose
    private java.lang.Object subCategoryId;
    @SerializedName("eventLocation")
    @Expose
    private java.lang.Object eventLocation;
    @SerializedName("musicGenre")
    @Expose
    private java.lang.Object musicGenre;
    @SerializedName("artist")
    @Expose
    private java.lang.Object artist;
    @SerializedName("packageMenu")
    @Expose
    private String packageMenu;
    @SerializedName("guestEntryTime")
    @Expose
    private String guestEntryTime;
    private final static long serialVersionUID = 4983201815066374499L;

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public Double getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(Double actualPrice) {
        this.actualPrice = actualPrice;
    }

    public Double getOfferPrice() {
        return offerPrice;
    }

    public void setOfferPrice(Double offerPrice) {
        this.offerPrice = offerPrice;
    }

    public Double getMinimumOrder() {
        return minimumOrder;
    }

    public void setMinimumOrder(Double minimumOrder) {
        this.minimumOrder = minimumOrder;
    }

    public Double getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(Double discountValue) {
        this.discountValue = discountValue;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

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

    public String getMasterServiceUUID() {
        return masterServiceUUID;
    }

    public void setMasterServiceUUID(String masterServiceUUID) {
        this.masterServiceUUID = masterServiceUUID;
    }

    public String getServiceImage() {
        return serviceImage;
    }

    public void setServiceImage(String serviceImage) {
        this.serviceImage = serviceImage;
    }

    public String getServiceTimeSlots() {
        return serviceTimeSlots;
    }

    public void setServiceTimeSlots(String serviceTimeSlots) {
        this.serviceTimeSlots = serviceTimeSlots;
    }

    public List<TimeSlotList> getTimeSlotList() {
        return timeSlotList;
    }

    public void setTimeSlotList(List<TimeSlotList> timeSlotList) {
        this.timeSlotList = timeSlotList;
    }

    public List<ServiceDate> getServiceDates() {
        return serviceDates;
    }

    public void setServiceDates(List<ServiceDate> serviceDates) {
        this.serviceDates = serviceDates;
    }

    public List<PackageMenuItem> getPackageMenuItems() {
        return packageMenuItems;
    }

    public void setPackageMenuItems(List<PackageMenuItem> packageMenuItems) {
        this.packageMenuItems = packageMenuItems;
    }

    public List<SurpriseForList> getSurpriseForList() {
        return surpriseForList;
    }

    public void setSurpriseForList(List<SurpriseForList> surpriseForList) {
        this.surpriseForList = surpriseForList;
    }

    public List<SurpriseOccationList> getSurpriseOccationList() {
        return surpriseOccationList;
    }

    public void setSurpriseOccationList(List<SurpriseOccationList> surpriseOccationList) {
        this.surpriseOccationList = surpriseOccationList;
    }

    public Integer getAllowed() {
        return allowed;
    }

    public void setAllowed(Integer allowed) {
        this.allowed = allowed;
    }

    public String getTermsAndConditions() {
        return termsAndConditions;
    }

    public void setTermsAndConditions(String termsAndConditions) {
        this.termsAndConditions = termsAndConditions;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public java.lang.Object getDescription() {
        return description;
    }

    public void setDescription(java.lang.Object description) {
        this.description = description;
    }

    public java.lang.Object getServiceOffer() {
        return serviceOffer;
    }

    public void setServiceOffer(java.lang.Object serviceOffer) {
        this.serviceOffer = serviceOffer;
    }

    public java.lang.Object getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(java.lang.Object categoryId) {
        this.categoryId = categoryId;
    }

    public java.lang.Object getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(java.lang.Object subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public java.lang.Object getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(java.lang.Object eventLocation) {
        this.eventLocation = eventLocation;
    }

    public java.lang.Object getMusicGenre() {
        return musicGenre;
    }

    public void setMusicGenre(java.lang.Object musicGenre) {
        this.musicGenre = musicGenre;
    }

    public java.lang.Object getArtist() {
        return artist;
    }

    public void setArtist(java.lang.Object artist) {
        this.artist = artist;
    }

    public String getPackageMenu() {
        return packageMenu;
    }

    public void setPackageMenu(String packageMenu) {
        this.packageMenu = packageMenu;
    }

    public String getGuestEntryTime() {
        return guestEntryTime;
    }

    public void setGuestEntryTime(String guestEntryTime) {
        this.guestEntryTime = guestEntryTime;
    }

}
