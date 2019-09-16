package com.falconssoft.menurestaurant.models;

import android.graphics.Bitmap;

public class Items {

    public String categoryName;
    public String itemName;
    public String itemBarcode;
    public Bitmap categoryPic;
    public String description;
    public double price; // F_D
    public Bitmap ItemPic;

    public Items() {

    }

//    "ItemOCode": "HM 4",
//            "ItemNameA": "HMR CaI?aiOi (2.44*1.22*0.008)",
//            "ItemNameE": "HMR CaI?aiOi (2.44*1.22*0.008)",
//            "ItemG": "CaCIOCE",
//            "ITEM_DESCRIPTION": null,
//            "F_D": "0"

    public Items(String itemBarcode, String itemName, String categoryName, String description, double price) {
        this.itemBarcode = itemBarcode;
        this.itemName = itemName;
        this.categoryName = categoryName;
        this.description = description;
        this.price = price;
    }

    public Items(String categoryName, String itemName, String itemBarcode, Bitmap categoryPic, String description, double price, Bitmap itemPic) {
        this.categoryName = categoryName;
        this.itemName = itemName;
        this.itemBarcode = itemBarcode;
        this.categoryPic = categoryPic;
        this.description = description;
        this.price = price;
        ItemPic = itemPic;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemBarcode() {
        return itemBarcode;
    }

    public void setItemBarcode(String itemBarcode) {
        this.itemBarcode = itemBarcode;
    }

    public Bitmap getCategoryPic() {
        return categoryPic;
    }

    public void setCategoryPic(Bitmap categoryPic) {
        this.categoryPic = categoryPic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Bitmap getItemPic() {
        return ItemPic;
    }

    public void setItemPic(Bitmap itemPic) {
        ItemPic = itemPic;
    }
}
