package com.falconssoft.menurestaurant.Models;

import java.sql.Blob;

public class Setting {

    private String ipConnection;
    private String restName;
    private Blob logoRest;

    public Setting() {

    }

    public Setting( String ipConnection,String restName) {
        this.ipConnection = ipConnection;
//        this.logoRest=logoRest;
        this.restName=restName;

    }

    public String getRestName() {
        return restName;
    }

    public void setRestName(String restName) {
        this.restName = restName;
    }

    public Blob getLogoRest() {
        return logoRest;
    }

    public void setLogoRest(Blob logoRest) {
        this.logoRest = logoRest;
    }

    public String getIpConnection() {
        return ipConnection;
    }

    public void setIpConnection(String ipConnection) {
        this.ipConnection = ipConnection;
    }
}
