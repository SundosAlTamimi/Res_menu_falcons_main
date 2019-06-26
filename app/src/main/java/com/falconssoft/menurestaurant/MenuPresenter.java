package com.falconssoft.menurestaurant;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class MenuPresenter implements Response.Listener<JSONObject>, Response.ErrorListener{

    private Context context;
    private DatabaseHandler databaseHandler;

    private String urlUsers = "";
    private RequestQueue requestQueue;
    private UTF8GetUserData utf8GetUserData;

    public MenuPresenter(Context context) {
        this.context = context;
        this.requestQueue = Volley.newRequestQueue(context);
        this.databaseHandler = new DatabaseHandler(context);
    }

    public void getUsersData() {
        utf8GetUserData = new UTF8GetUserData(Request.Method.POST, urlUsers, null, this , this);
        requestQueue.add(utf8GetUserData);

    }

    public class UTF8GetUserData extends JsonRequest<JSONObject>{

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
        Log.e("presenter:users", "" + error);
        Toast.makeText(context, "Please check your internet connection!", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onResponse(JSONObject response) {
        databaseHandler.deleteAllUsers();
        // fill database from cloud

    }















}
