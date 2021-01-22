
package com.acculytixs.mobile.wayuparty.dtos;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PackageMenuItem implements Serializable
{

    @SerializedName("menuItem")
    @Expose
    private String menuItem;
    @SerializedName("menuItemUUID")
    @Expose
    private String menuItemUUID;
    @SerializedName("itemsOffered")
    @Expose
    private String itemsOffered;
    @SerializedName("menuItemsList")
    @Expose
    private List<MenuItemsList> menuItemsList = null;
    private final static long serialVersionUID = 7073923371231262787L;

    public String getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(String menuItem) {
        this.menuItem = menuItem;
    }

    public String getMenuItemUUID() {
        return menuItemUUID;
    }

    public void setMenuItemUUID(String menuItemUUID) {
        this.menuItemUUID = menuItemUUID;
    }

    public String getItemsOffered() {
        return itemsOffered;
    }

    public void setItemsOffered(String itemsOffered) {
        this.itemsOffered = itemsOffered;
    }

    public List<MenuItemsList> getMenuItemsList() {
        return menuItemsList;
    }

    public void setMenuItemsList(List<MenuItemsList> menuItemsList) {
        this.menuItemsList = menuItemsList;
    }

}
