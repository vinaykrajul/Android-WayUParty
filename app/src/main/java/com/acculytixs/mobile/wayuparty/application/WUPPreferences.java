package com.acculytixs.mobile.wayuparty.application;

import android.content.Context;
import android.content.SharedPreferences;

public class WUPPreferences {
    public static SharedPreferences.Editor getEditor() {
        return getSharedPreferences().edit();
    }

    public static SharedPreferences getSharedPreferences() {
        return WayUPartyApplication.getInstance().getSharedPreferences(WayUPartyConstants.WAYUPARTY_PREF, Context.MODE_PRIVATE);
    }

    public static String getStringFromSharedPrefrences(String key) {
        return getSharedPreferences().getString(key, "");
    }

    private static void putStringToSharedPreferences(String key, String value) {
        getEditor().putString(key, value).apply();       //editor.commit();
    }

    public static boolean getBooleanFromSharedPrefrences(String key) {
        return getSharedPreferences().getBoolean(key, false);
    }

    private static void putBooleanToSharedPreferences(String key, Boolean value) {
        getEditor().putBoolean(key, value).apply();       //editor.commit();
    }

    public static void saveUserId(String userid) {
        putStringToSharedPreferences(WayUPartyConstants.USER_UUID, userid);
    }

    public static String getUserId() {
        return getStringFromSharedPrefrences(WayUPartyConstants.USER_UUID);
    }

    public static void saveVendorUUID(String vendorUUId) {
        putStringToSharedPreferences(WayUPartyConstants.VENDOR_UUID, vendorUUId);
    }

    public static String getVendorUUID() {
        return getStringFromSharedPrefrences(WayUPartyConstants.VENDOR_UUID);
    }

    public static void saveServiceUUID(String serviceUUID) {
        putStringToSharedPreferences(WayUPartyConstants.SERVICE_UUID, serviceUUID);
    }

    public static String getServiceUUID() {
        return getStringFromSharedPrefrences(WayUPartyConstants.SERVICE_UUID);
    }

    public static void saveUserName(String userNamae) {
        putStringToSharedPreferences(WayUPartyConstants.SAVE_USERNAME, userNamae);
    }

    public static String getUserName() {
        return getStringFromSharedPrefrences(WayUPartyConstants.SAVE_USERNAME);
    }

    public static void savePasswrod(String password) {
        putStringToSharedPreferences(WayUPartyConstants.SAVE_PASSWORD, password);
    }

    public static String getPassword() {
        return getStringFromSharedPrefrences(WayUPartyConstants.SAVE_PASSWORD);
    }


    public static void saveRatioEnabled(String ratioEnabled) {
       putStringToSharedPreferences(WayUPartyConstants.SAVE_RATIO_ENABLED,ratioEnabled);
    }

    public static String  getRationEnabled() {
        return getStringFromSharedPrefrences(WayUPartyConstants.SAVE_RATIO_ENABLED);
    }


    public static void saveTimeSlot(String timeslot) {
        putStringToSharedPreferences(WayUPartyConstants.SAVE_TIME_SLOT,timeslot);
    }

    public static String  getTimeslot() {
        return getStringFromSharedPrefrences(WayUPartyConstants.SAVE_TIME_SLOT);
    }

    public static void saveMobileNum(String mobile) {
        putStringToSharedPreferences(WayUPartyConstants.SAVE_MOBILE_NUM,mobile);
    }

    public static String  getMobileNum() {
        return getStringFromSharedPrefrences(WayUPartyConstants.SAVE_MOBILE_NUM);
    }

    public static void saveEmail(String email) {
        putStringToSharedPreferences(WayUPartyConstants.SAVE_EMAIL_NUM,email);
    }

    public static String  getEmail() {
        return getStringFromSharedPrefrences(WayUPartyConstants.SAVE_EMAIL_NUM);
    }

    public static void saveVerificationUUID(String uuid) {
        putStringToSharedPreferences(WayUPartyConstants.SAVE_VERIFICATION_UUID,uuid);
    }

    public static String  getVerificationUUID() {
        return getStringFromSharedPrefrences(WayUPartyConstants.SAVE_VERIFICATION_UUID);
    }

}
