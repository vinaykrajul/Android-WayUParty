
package com.acculytixs.mobile.wayuparty.dtos;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Objects implements Serializable
{
    @SerializedName("loginUserName")
    @Expose
    private String loginUserName;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("userUUID")
    @Expose
    private String userUUID;
    @SerializedName("userEmail")
    @Expose
    private String userEmail;
    @SerializedName("userMobile")
    @Expose
    private String userMobile;
    @SerializedName("userImage")
    @Expose
    private String userImage;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("dob")
    @Expose
    private String dob;
    @SerializedName("preferredDrinks")
    @Expose
    private String preferredDrinks;
    @SerializedName("preferredMusic")
    @Expose
    private String preferredMusic;
    @SerializedName("preferredDrinksList")
    @Expose
    private List<String> preferredDrinksList = null;
    @SerializedName("preferredMusicList")
    @Expose
    private List<String> preferredMusicList = null;
    private final static long serialVersionUID = 6700399713851268190L;

    public String getLoginUserName() {
        return loginUserName;
    }

    public void setLoginUserName(String loginUserName) {
        this.loginUserName = loginUserName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserUUID() {
        return userUUID;
    }

    public void setUserUUID(String userUUID) {
        this.userUUID = userUUID;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPreferredDrinks() {
        return preferredDrinks;
    }

    public void setPreferredDrinks(String preferredDrinks) {
        this.preferredDrinks = preferredDrinks;
    }

    public String getPreferredMusic() {
        return preferredMusic;
    }

    public void setPreferredMusic(String preferredMusic) {
        this.preferredMusic = preferredMusic;
    }

    public List<String> getPreferredDrinksList() {
        return preferredDrinksList;
    }

    public void setPreferredDrinksList(List<String> preferredDrinksList) {
        this.preferredDrinksList = preferredDrinksList;
    }

    public List<String> getPreferredMusicList() {
        return preferredMusicList;
    }

    public void setPreferredMusicList(List<String> preferredMusicList) {
        this.preferredMusicList = preferredMusicList;
    }

}
