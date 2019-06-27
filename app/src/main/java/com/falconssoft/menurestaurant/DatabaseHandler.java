package com.falconssoft.menurestaurant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.falconssoft.menurestaurant.Models.Items;
import com.falconssoft.menurestaurant.Models.Setting;
import com.falconssoft.menurestaurant.Models.Users;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    // Database Versions
    private static final int DATABASE_VERSION =1;

    // Database Name
    private static final String DATABASE_NAME = "RestMenuRepo";
    static SQLiteDatabase db;
       //___________________________________________________________________________________
    private static final String ITEMS = "ITEMS";

    private static final String CATEGORY_NAME = "CATEGORY_NAME";
    private static final String ITEM_NAME = "ITEM_NAME";
    private static final String PRICE = "PRICE";
    private static final String ITEM_BARCODE = "ITEM_BARCODE";
    private static final String DESCRIPTION = "DESCRIPTION";
    private static final String ITEM_PICTURE = "ITEM_PICTURE";
    private static final String CATEGORY_PICTURE = "CATEGORY_PICTURE";

    //___________________________________________________________________________________

    private static final String USERS = "USERS";

    private static final String USER_NAME = "USER_NAME";
    private static final String PASSWORD = "PASSWORD";

    //___________________________________________________________________________________
    private static final String SETTING = "SETTING";

    private static final String LANGUAGE_OPTION = "LANGUAGE_OPTION";
    private static final String IP_CONNECT = "IP_CONNECT";
//logo ,comp name
    //___________________________________________________________________________________

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Create tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        createTables(db);
    }

    void createTables(SQLiteDatabase db) {

        String CREATE_TABLE_ITEMS = "CREATE TABLE " + ITEMS + "("
                + CATEGORY_NAME + " TEXT,"
                + ITEM_NAME + " TEXT,"
                + ITEM_BARCODE + " TEXT,"
                + PRICE + " INTEGER,"
                + DESCRIPTION + " TEXT,"
                + ITEM_PICTURE + " TEXT,"
                + CATEGORY_PICTURE + " TEXT" + ")";
        db.execSQL(CREATE_TABLE_ITEMS);
        //___________________________________________________________________________________

        String CREATE_TABLE_USERS = "CREATE TABLE " + USERS + "("
                + USER_NAME + " TEXT,"
                + PASSWORD + " TEXT" + ")";
        db.execSQL(CREATE_TABLE_USERS);
        //___________________________________________________________________________________
        String CREATE_TABLE_SETTING = "CREATE TABLE " + SETTING + "("
                + LANGUAGE_OPTION + " TEXT,"
                + IP_CONNECT + " TEXT" + ")";
        db.execSQL(CREATE_TABLE_SETTING);
        //___________________________________________________________________________________

    }


    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

//        db.execSQL("ALTER TABLE PAY_METHOD ADD IS_POSTED INTEGER NOT NULL DEFAULT '0'");

    }


    public void addItem(Items items) {
        db = this.getReadableDatabase();
        ContentValues values = new ContentValues();

//        byte[] byteImage = {};
//        if (items.getPic() != null) {
//            ByteArrayOutputStream stream = new ByteArrayOutputStream();
//            items.getPic().compress(Bitmap.CompressFormat.PNG, 0, stream);
//            byteImage = stream.toByteArray();
//        }
        values.put(CATEGORY_NAME, items.getCategoryName());
        values.put(ITEM_NAME, items.getItemName());
        values.put(ITEM_BARCODE, items.getItemBarcode());
        values.put(PRICE, items.getPrice());
        values.put(DESCRIPTION, items.getDescription());
        values.put(ITEM_PICTURE, items.getItemPic());
        values.put(CATEGORY_PICTURE, items.getCategoryPic());

        db.insert(ITEMS, null, values);
        db.close();
    }

    public void addUser(Users users) {
        db = this.getReadableDatabase();
        ContentValues values = new ContentValues();

        values.put(USER_NAME,users.getUserName() );
        values.put(PASSWORD, users.getUserPassword());

        db.insert(USERS, null, values);
        db.close();
    }

    public void addSetting(Setting setting) {
        db = this.getReadableDatabase();
        ContentValues values = new ContentValues();

        values.put(USER_NAME,setting.getLanguageOption() );
        values.put(PASSWORD, setting.getIpConnection());

        db.insert(SETTING, null, values);
        db.close();
    }



    public List<Items> getAllItems() {
        List<Items> items = new ArrayList<Items>();

        String selectQuery = "SELECT  * FROM " + ITEMS;
        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Items item = new Items();

                item.setCategoryName(cursor.getString(0));
                item.setItemName(cursor.getString(1));
                item.setItemBarcode(Integer.parseInt(cursor.getString(2)));
                item.setPrice(Double.parseDouble(cursor.getString(3)));
                item.setDescription(cursor.getString(4));
                try {
                    item.setItemPic(cursor.getString(5));
                    item.setCategoryPic(cursor.getString(6));
                }catch (OutOfMemoryError e) {
                    e.getMessage();
                    Log.e("have error ..","1==out of memory ");
                }
//                if (cursor.getBlob(20).length == 0)
//                    item.setPic(null);
//                else
//                    item.setPic(BitmapFactory.decodeByteArray(cursor.getBlob(20), 0, cursor.getBlob(20).length));

                // Adding transaction to list

                items.add(item);
            } while (cursor.moveToNext());
        }
        return items;
    }


    public List<Users> getAllUSER() {
        List<Users> usersList = new ArrayList<Users>();

        String selectQuery = "SELECT  * FROM " + USERS;
        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Users users = new Users();

                users.setUserName(cursor.getString(0));
                users.setUserPassword(cursor.getString(1));

                usersList.add(users);
            } while (cursor.moveToNext());
        }
        return usersList;
    }


    public List<Setting> getAllSetting() {
        List<Setting> settingsList = new ArrayList<Setting>();

        String selectQuery = "SELECT  * FROM " + SETTING;
        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Setting setting = new Setting();

                setting.setLanguageOption(cursor.getString(0));
                setting.setIpConnection(cursor.getString(1));

                settingsList.add(setting);
            } while (cursor.moveToNext());
        }
        return settingsList;
    }


    public void deleteAllUsers() {
        db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + USERS + ";");
        db.close();
    }
    public List<Items> getAllItemsByCategory(String CatName) {
        List<Items> items = new ArrayList<Items>();

        String selectQuery = "SELECT  * FROM " + ITEMS +"WHERE CATEGORY_NAME " +" = '"+CatName +"'";
        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Items item = new Items();

                item.setCategoryName(cursor.getString(0));
                item.setItemName(cursor.getString(1));
                item.setItemBarcode(Integer.parseInt(cursor.getString(2)));
                item.setPrice(Double.parseDouble(cursor.getString(3)));
                item.setDescription(cursor.getString(4));
                try {
                    item.setItemPic(cursor.getString(5));
                    item.setCategoryPic(cursor.getString(6));
                }catch (OutOfMemoryError e) {
                    e.getMessage();
                    Log.e("have error ..","1==out of memory ");
                }
//                if (cursor.getBlob(20).length == 0)
//                    item.setPic(null);
//                else
//                    item.setPic(BitmapFactory.decodeByteArray(cursor.getBlob(20), 0, cursor.getBlob(20).length));

                // Adding transaction to list

                items.add(item);
            } while (cursor.moveToNext());
        }
        return items;
    }



}
