package com.falconssoft.menurestaurant;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.falconssoft.menurestaurant.models.Items;
import com.falconssoft.menurestaurant.models.Users;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import static com.falconssoft.menurestaurant.MainSetting.categoryList;
import static com.falconssoft.menurestaurant.MainSetting.itemsList;
import static com.falconssoft.menurestaurant.MainSetting.usersList;

public class MenuPresenter implements Response.Listener<JSONObject>, Response.ErrorListener {

    private String urlUsers = "";
    private UTF8GetUserData utf8GetUserData;
    private LoginActivity loginActivity;

    private String urlCategory = "http://10.0.0.214/MENU_WEB_SERVICE/import.php?";
    private UTF_8CategoriesAndItems jsonObjectRequest;
    private CategoryActivity categoryActivity;

    private Context context;
    private RequestQueue requestQueue;
    private DatabaseHandler databaseHandler;
    public static int definValue = 0;


    public MenuPresenter(DatabaseHandler databaseHandler, CategoryActivity categoryActivity) {
        this.databaseHandler = databaseHandler;
        this.categoryActivity = categoryActivity;
//        this.context = context;
        requestQueue = Volley.newRequestQueue(categoryActivity);
    }

    public MenuPresenter(DatabaseHandler databaseHandler, LoginActivity loginActivity) {
        this.databaseHandler = databaseHandler;
        this.loginActivity = loginActivity;
//        this.context = context;
        requestQueue = Volley.newRequestQueue(loginActivity);
    }

    //********************************************** Users *************************************************

    public void getUsersData() {
        utf8GetUserData = new UTF8GetUserData(Request.Method.POST, urlUsers, null, this, this);
        requestQueue.add(utf8GetUserData);

    }

    public class UTF8GetUserData extends JsonRequest<JSONObject> {

        public UTF8GetUserData(int method, String url, String requestBody, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
            super(method, url, requestBody, listener, errorListener);
        }

        @Override
        protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
            try {
                String utf8String = new String(response.data, "UTF-8");
                return Response.success(new JSONObject(utf8String), HttpHeaderParser.parseCacheHeaders(response));
            } catch (UnsupportedEncodingException e) {
                // log error
                return Response.error(new ParseError(e));
            } catch (JSONException e) {
                // log error
                return Response.error(new ParseError(e));
            }
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.e("presenter: users", "" + error);
        Toast.makeText(context, "Please check your internet connection!", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onResponse(JSONObject response) {
        databaseHandler.deleteAllUsers();
        usersList.clear();
        // fill database from cloud

    }

    //********************************************** CategoriesAndItems *************************************************

    public void getCategoriesAndItems() {
        jsonObjectRequest = new UTF_8CategoriesAndItems(Request.Method.POST, urlCategory
                , null, new ResponsesOfCategoriesAndItems(), new ResponsesOfCategoriesAndItems());
        requestQueue.add(jsonObjectRequest);

    }

    class UTF_8CategoriesAndItems extends JsonRequest<JSONObject> {
        public UTF_8CategoriesAndItems(int method, String url, @Nullable String requestBody, Response.Listener<JSONObject> listener, @Nullable Response.ErrorListener errorListener) {
            super(method, url, requestBody, listener, errorListener);
        }

        @Override
        protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
            try {
                String utf8String = new String(response.data, "UTF-8");
                return Response.success(new JSONObject(utf8String), HttpHeaderParser.parseCacheHeaders(response));
            } catch (UnsupportedEncodingException e) {
                return Response.error(new ParseError(e));
            } catch (JSONException e) {
                return Response.error(new ParseError(e));
            }
        }
    }

    class ResponsesOfCategoriesAndItems implements Response.Listener<JSONObject>, Response.ErrorListener {
        @Override
        public void onErrorResponse(VolleyError error) {
            definValue = 1;
            Log.e("presenter: ", "category & items: " + error);
            new SaveToDatabase().execute();

        }

        @Override
        public void onResponse(JSONObject response) {
            definValue = 0;
            try {

                itemsList.clear();
                categoryList.clear();
                usersList.clear();
                databaseHandler.deleteAllItems();
                databaseHandler.deleteAllUsers();

                Log.e("response ", response.toString());
                JSONArray groubName = response.getJSONArray("GROUPS");
                for (int i = 0; i < groubName.length(); i++) {
                    JSONObject jsonObject = groubName.getJSONObject(i);
                    Log.e("groub ", jsonObject.get("Desc_Name").toString());
                    categoryList.add(jsonObject.get("Desc_Name").toString());
                }

                JSONArray itemName = response.getJSONArray("ITEMS");
//                Log.e("length" , "" + itemName.length());
                Log.e("itemName", "" + itemName.toString());
                for (int i = 0; i < itemName.length(); i++) {
                    JSONObject jsonObject = itemName.getJSONObject(i);
                    Log.e("item ", "" + jsonObject.getDouble("F_D"));
                    Items items = new Items(jsonObject.getString("ItemOCode")
                            , jsonObject.getString("ItemNameA")
                            , jsonObject.getString("ItemG")
                            , jsonObject.getString("ITEM_DESCRIPTION")
                            , jsonObject.getDouble("F_D"));
                    itemsList.add(items);
                }

                JSONArray usersName = response.getJSONArray("USERS");
                for (int i = 0; i < usersName.length(); i++) {
                    JSONObject jsonObject = usersName.getJSONObject(i);
                    Users users = new Users(jsonObject.getString("USERNAME")
                            , jsonObject.getString("APass"));
                    usersList.add(users);
                }

                new SaveToDatabase().execute();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    class SaveToDatabase extends AsyncTask<String, String, String> {
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
//            categoryActivity.fillCategories();
        }

        @Override
        protected String doInBackground(String... strings) {
            if (definValue == 0) {
                for (int i = 0; i < itemsList.size(); i++) {
                    databaseHandler.addItem(itemsList.get(i));
                }

                for (int i = 0; i < usersList.size(); i++) {
                    databaseHandler.addUser(usersList.get(i));
                }
            }else  if (definValue == 1){
                categoryList = databaseHandler.getAllCategories();
                itemsList = databaseHandler.getAllItems();
            }
            return "done";
        }
    }
}
















