
package com.acculytixs.mobile.wayuparty.dtos;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SurpriseForList implements Serializable
{

    @SerializedName("surpriseUUID")
    @Expose
    private String surpriseUUID;
    @SerializedName("surpriseName")
    @Expose
    private String surpriseName;
    @SerializedName("surpriseType")
    @Expose
    private java.lang.Object surpriseType;
    private final static long serialVersionUID = -9029188942309333835L;

    public String getSurpriseUUID() {
        return surpriseUUID;
    }

    public void setSurpriseUUID(String surpriseUUID) {
        this.surpriseUUID = surpriseUUID;
    }

    public String getSurpriseName() {
        return surpriseName;
    }

    public void setSurpriseName(String surpriseName) {
        this.surpriseName = surpriseName;
    }

    public java.lang.Object getSurpriseType() {
        return surpriseType;
    }

    public void setSurpriseType(java.lang.Object surpriseType) {
        this.surpriseType = surpriseType;
    }

}
