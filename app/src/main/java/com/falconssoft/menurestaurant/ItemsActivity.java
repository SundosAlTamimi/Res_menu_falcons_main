package com.falconssoft.menurestaurant;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.falconssoft.menurestaurant.Models.Items;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ItemsActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RequestQueue requestQueue;
    Context context;
    Items items;
    String inta;
    DatabaseHandler mydatabase;
    ArrayList<Items> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);
        Intent intent = getIntent();
        inta = intent.getStringExtra("categoryName");
        TextView group_name = (TextView) findViewById(R.id.group_name);
        group_name.setText(inta);
        itemList = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        loadItems();
        recyclerView = findViewById(R.id.recyclerview_item_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        runAnimation(recyclerView,0);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        AdapterItems adapter = new AdapterItems(this, itemList);
        recyclerView.setAdapter(adapter);
    }

    private void runAnimation(RecyclerView recyclerView, int type) {
     Context context=recyclerView.getContext();
        LayoutAnimationController controller=null;
        if(type==0)
        {
            controller= AnimationUtils.loadLayoutAnimation(context,R.anim.layout_filldown);
            AdapterItems adapter = new AdapterItems(context, itemList);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutAnimation(controller);
                recyclerView.getAdapter().notifyDataSetChanged();
                recyclerView.scheduleLayoutAnimation();
        }
    }

    private void loadItems() {
//        String url ="http://10.0.0.185/Api/items.php";
//        JsonObjectRequest stringRequest =new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse( JSONObject jsonObject) {
//                try {
//
//                    Log.e("onResponse: ", "" + jsonObject);
//                    JSONArray array = jsonObject.getJSONArray("items_list");
//                    for (int i = 0; i < array.length(); i++) {
//                        JSONObject object = array.getJSONObject(i);
//                        Items items = new Items();
//                        items.setItemName(object.getString("name"));
//                        items.setDescription(object.getString("descripotion"));
//                        items.setPrice(Double.parseDouble(object.getString("price")));
//                        itemList.add(items);
//                        Log.e( "list: " ,""+itemList.get(i).getItemName());
//
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                AdapterItems adapter = new AdapterItems(context, itemList);
//                recyclerView.setAdapter(adapter);
//
//            }
//
//
//        }
//
//                ,new Response.ErrorListener(){
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.e( "onErrorResponse: " ,""+ error);
//            }
//
//        });
//        MySingleton.getmInstance(context).addToRequestQueue(stringRequest);
//    }
        Items items = new Items();
        items.setItemName("Salad");
        items.setDescription("salad with some vegetable");
        items.setPrice(20);
        itemList.add(items);
        Items it2 = new Items();
        it2.setItemName("Frute");
        it2.setDescription("salad with some vegetable");
        it2.setPrice(20);
        itemList.add(it2);
        Items it3 = new Items();
        it3.setItemName("Frute");
        it3.setDescription("salad with some vegetable");
        it3.setPrice(20);
        itemList.add(it3);
        Items it4= new Items();
        it4.setItemName("Frute");
        it4.setDescription("salad with some vegetable");
        it4.setPrice(20);
        itemList.add(it4);
        Log.e("items", "" + items.getItemName() + items.getPrice() + items.getDescription() + "\n list" + itemList);
    }
}

