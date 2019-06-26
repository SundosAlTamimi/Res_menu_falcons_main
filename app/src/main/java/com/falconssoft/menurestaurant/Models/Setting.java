package com.falconssoft.menurestaurant.Models;

public class Setting {

    private String languageOption;
    private String ipConnection;


    public Setting() {

    }

    public Setting(String languageOption, String ipConnection) {
        this.languageOption = languageOption;
        this.ipConnection = ipConnection;
    }

    public String getLanguageOption() {
        return languageOption;
    }

    public void setLanguageOption(String languageOption) {
        this.languageOption = languageOption;
    }

    public String getIpConnection() {
        return ipConnection;
    }

    public void setIpConnection(String ipConnection) {
        this.ipConnection = ipConnection;
    }
}
