package com.falconssoft.menurestaurant;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
    Context context;
    List<String> list;
    DatabaseHandler db;

    public RecyclerAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
        db = new DatabaseHandler(this.context);
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.categoty_layout, viewGroup, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder cViewHolder, final int i) {
        cViewHolder.categoryName.setText(list.get(i));
        cViewHolder.layMain.setId(i);
//        cViewHolder.categoryName.setText(list.get(i).getCategoryName());
//        cViewHolder.categoryImage.setImageDrawable(Drawable.createFromPath(pic.get(i)));


        cViewHolder.layMain.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                Log.e("item ...", "i" + v.getId() + "-->" + i + "===>" + list.get(i));

                Intent itemIntent = new Intent(context, ItemsActivity.class);
                itemIntent.putExtra("categoryName", list.get(i));
                context.startActivity(itemIntent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}