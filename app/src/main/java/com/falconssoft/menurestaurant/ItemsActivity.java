package com.falconssoft.menurestaurant;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.falconssoft.menurestaurant.models.Items;

import java.util.ArrayList;
import java.util.List;

import static com.falconssoft.menurestaurant.MainSetting.itemsList;

public class ItemsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TextView textViewCategoryName;
    private String categoryName;
    private List<Items> filteredList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        textViewCategoryName = (TextView) findViewById(R.id.group_name);
        Intent intent = getIntent();
        categoryName = intent.getStringExtra("categoryName");
        textViewCategoryName.setText(categoryName);

        recyclerView = findViewById(R.id.recyclerview_item_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        runAnimation(recyclerView, 0);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        chooseItemsAccordingCategory();
    }

    void chooseItemsAccordingCategory(){
        for (int i = 0; i< itemsList.size();i++){
            if (itemsList.get(i).getCategoryName().equals(categoryName))
                filteredList.add(itemsList.get(i));
        }

        AdapterItems adapter = new AdapterItems(this, filteredList);
        recyclerView.setAdapter(adapter);
    }

    private void runAnimation(RecyclerView recyclerView, int type) {
        Context context = recyclerView.getContext();
        LayoutAnimationController controller = null;
        if (type == 0) {
            controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_filldown);
            AdapterItems adapter = new AdapterItems(this, itemsList);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutAnimation(controller);
            recyclerView.getAdapter().notifyDataSetChanged();
            recyclerView.scheduleLayoutAnimation();
        }
    }

}

