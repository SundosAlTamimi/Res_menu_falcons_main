package com.falconssoft.menurestaurant;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.falconssoft.menurestaurant.Models.Items;

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
        TextView group_name=(TextView)findViewById(R.id.group_name);
        group_name.setText(inta);
        itemList = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        loadItems();
        recyclerView = findViewById(R.id.recyclerview_item_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        AdapterItems adapter = new AdapterItems(this, itemList);
        recyclerView.setAdapter(adapter);
    }

    private void loadItems() {
        Items items=new Items();
        items.setItemName("Salad");
        items.setDescription("salad with some vigtable");
        items.setPrice(20);
        itemList.add(items);
        Items it2 = new Items();
        it2.setItemName("Frute");
        it2.setDescription("salad with some vigtable");
        it2.setPrice(20);
        itemList.add(it2);
        Log.e("items", "" + items.getItemName() + items.getPrice() + items.getDescription() + "\n list" + itemList);
    }
}
