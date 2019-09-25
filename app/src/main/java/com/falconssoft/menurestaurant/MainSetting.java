package com.falconssoft.menurestaurant;

import com.falconssoft.menurestaurant.models.Items;
import com.falconssoft.menurestaurant.models.Users;

import java.util.ArrayList;
import java.util.List;

public class MainSetting {

    public static String userName = "noWaiter";
    public static String userPassword = "0";

    public static List<Users> usersList = new ArrayList<>();
    public static List<String> categoryList = new ArrayList<>();
    public static List<Items> itemsList = new ArrayList<>();


    public MainSetting() {
    }
}
