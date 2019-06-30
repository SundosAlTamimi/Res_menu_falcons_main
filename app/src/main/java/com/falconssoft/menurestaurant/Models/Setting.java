package com.falconssoft.menurestaurant.Models;

import android.graphics.Bitmap;

import java.sql.Blob;

public class Setting {

    private String ipConnection;
    private String restName;
    private Bitmap logoRest;

    public Setting() {

    }

    public Setting( String ipConnection,String restName,Bitmap logoRest) {
        this.ipConnection = ipConnection;
        this.logoRest=logoRest;
        this.restName=restName;

    }

    public String getRestName() {
        return restName;
    }

    public void setRestName(String restName) {
        this.restName = restName;
    }

    public Bitmap getLogoRest() {
        return logoRest;
    }

    public void setLogoRest(Bitmap logoRest) {
        this.logoRest = logoRest;
    }

    public String getIpConnection() {
        return ipConnection;
    }

    public void setIpConnection(String ipConnection) {
        this.ipConnection = ipConnection;
    }
}
