package com.falconssoft.menurestaurant.Models;

public class Items {

    public String categoryName;
    public String itemName;
    public int itemBarcode;
    public String categoryPic;
    public String description;
    public double price;
    public String ItemPic;

    public Items() {

    }

    public Items(String categoryName, String itemName, int itemBarcode, String categoryPic, String description, double price, String itemPic) {
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

    public int getItemBarcode() {
        return itemBarcode;
    }

    public void setItemBarcode(int itemBarcode) {
        this.itemBarcode = itemBarcode;
    }

    public String getCategoryPic() {
        return categoryPic;
    }

    public void setCategoryPic(String categoryPic) {
        this.categoryPic = categoryPic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price+"$";
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getItemPic() {
        return ItemPic;
    }

    public void setItemPic(String itemPic) {
        ItemPic = itemPic;
    }
}
