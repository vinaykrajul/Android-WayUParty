
package com.acculytixs.mobile.wayuparty.dtos;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Object implements Serializable
{

    @SerializedName("vendorName")
    @Expose
    private String vendorName;
    @SerializedName("vendorUUID")
    @Expose
    private String vendorUUID;
    @SerializedName("vendorEmail")
    @Expose
    private String vendorEmail;
    @SerializedName("vendorMobile")
    @Expose
    private String vendorMobile;
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
    @SerializedName("vendorCode")
    @Expose
    private String vendorCode;
    @SerializedName("vendorDescription")
    @Expose
    private String vendorDescription;
    @SerializedName("bestSellingItems")
    @Expose
    private String bestSellingItems;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("pincode")
    @Expose
    private String pincode;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("phoneNumber")
    @Expose
    private String phoneNumber;
    @SerializedName("vendorAddress")
    @Expose
    private String vendorAddress;
    @SerializedName("termsAndConditions")
    @Expose
    private String termsAndConditions;
    @SerializedName("categoriesList")
    @Expose
    private List<String> categoriesList = null;
    @SerializedName("facilitiesList")
    @Expose
    private List<String> facilitiesList = null;
    @SerializedName("musicList")
    @Expose
    private List<String> musicList = null;
    @SerializedName("cuisineList")
    @Expose
    private List<String> cuisineList = null;
    @SerializedName("workingHoursList")
    @Expose
    private List<WorkingHoursList> workingHoursList = null;
    @SerializedName("menuList")
    @Expose
    private List<String> menuList = null;
    @SerializedName("sliderList")
    @Expose
    private List<String> sliderList = null;
    @SerializedName("galleryList")
    @Expose
    private List<String> galleryList = null;
    @SerializedName("videoList")
    @Expose
    private List<String> videoList = null;
    @SerializedName("categories")
    @Expose
    private String categories;
    @SerializedName("facilities")
    @Expose
    private String facilities;
    @SerializedName("music")
    @Expose
    private String music;
    @SerializedName("cuisine")
    @Expose
    private String cuisine;
    @SerializedName("menus")
    @Expose
    private String menus;
    @SerializedName("sliders")
    @Expose
    private String sliders;
    @SerializedName("gallery")
    @Expose
    private String gallery;
    @SerializedName("videos")
    @Expose
    private String videos;
    @SerializedName("workingHours")
    @Expose
    private String workingHours;
    private final static long serialVersionUID = -2142714370649453507L;

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorUUID() {
        return vendorUUID;
    }

    public void setVendorUUID(String vendorUUID) {
        this.vendorUUID = vendorUUID;
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

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public String getVendorDescription() {
        return vendorDescription;
    }

    public void setVendorDescription(String vendorDescription) {
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getVendorAddress() {
        return vendorAddress;
    }

    public void setVendorAddress(String vendorAddress) {
        this.vendorAddress = vendorAddress;
    }

    public String getTermsAndConditions() {
        return termsAndConditions;
    }

    public void setTermsAndConditions(String termsAndConditions) {
        this.termsAndConditions = termsAndConditions;
    }

    public List<String> getCategoriesList() {
        return categoriesList;
    }

    public void setCategoriesList(List<String> categoriesList) {
        this.categoriesList = categoriesList;
    }

    public List<String> getFacilitiesList() {
        return facilitiesList;
    }

    public void setFacilitiesList(List<String> facilitiesList) {
        this.facilitiesList = facilitiesList;
    }

    public List<String> getMusicList() {
        return musicList;
    }

    public void setMusicList(List<String> musicList) {
        this.musicList = musicList;
    }

    public List<String> getCuisineList() {
        return cuisineList;
    }

    public void setCuisineList(List<String> cuisineList) {
        this.cuisineList = cuisineList;
    }

    public List<WorkingHoursList> getWorkingHoursList() {
        return workingHoursList;
    }

    public void setWorkingHoursList(List<WorkingHoursList> workingHoursList) {
        this.workingHoursList = workingHoursList;
    }

    public List<String> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<String> menuList) {
        this.menuList = menuList;
    }

    public List<String> getSliderList() {
        return sliderList;
    }

    public void setSliderList(List<String> sliderList) {
        this.sliderList = sliderList;
    }

    public List<String> getGalleryList() {
        return galleryList;
    }

    public void setGalleryList(List<String> galleryList) {
        this.galleryList = galleryList;
    }

    public List<String> getVideoList() {
        return videoList;
    }

    public void setVideoList(List<String> videoList) {
        this.videoList = videoList;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getFacilities() {
        return facilities;
    }

    public void setFacilities(String facilities) {
        this.facilities = facilities;
    }

    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public String getMenus() {
        return menus;
    }

    public void setMenus(String menus) {
        this.menus = menus;
    }

    public String getSliders() {
        return sliders;
    }

    public void setSliders(String sliders) {
        this.sliders = sliders;
    }

    public String getGallery() {
        return gallery;
    }

    public void setGallery(String gallery) {
        this.gallery = gallery;
    }

    public String getVideos() {
        return videos;
    }

    public void setVideos(String videos) {
        this.videos = videos;
    }

    public String getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }

}
