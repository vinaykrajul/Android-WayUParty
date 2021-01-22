
package com.acculytixs.mobile.wayuparty.dtos;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MenuItemsList implements Serializable
{

    @SerializedName("itemName")
    @Expose
    private String itemName;
    @SerializedName("itemUUID")
    @Expose
    private String itemUUID;
    private final static long serialVersionUID = 3207119347009163769L;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemUUID() {
        return itemUUID;
    }

    public void setItemUUID(String itemUUID) {
        this.itemUUID = itemUUID;
    }

}
