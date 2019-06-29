package com.falconssoft.menurestaurant;


import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.azoft.carousellayoutmanager.CarouselLayoutManager;
import com.azoft.carousellayoutmanager.CarouselZoomPostLayoutListener;
import com.azoft.carousellayoutmanager.CenterScrollListener;
import com.falconssoft.menurestaurant.Models.Items;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    TextView UserNameText;

    public List<String> list = new ArrayList<>();
    List<String>pic = new ArrayList<>();
    int po;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_listview);
        Intent userName=getIntent();
        String users=userName.getStringExtra("userName");

        UserNameText=(TextView)findViewById(R.id.userName);

        UserNameText.setText(users);

        list.add("Burger");
        list.add("Burger");
        list.add("Burger");
        list.add("Burger");
        list.add("Burger");
        list.add("Burger");
        list.add("Burger");
        list.add("Burger");
        list.add("Burger");
        list.add("Burger");

        // vertical and cycle layout
        final CarouselLayoutManager layoutManager;
        layoutManager = new CarouselLayoutManager(CarouselLayoutManager.HORIZONTAL, true);
        layoutManager.setPostLayoutListener(new CarouselZoomPostLayoutListener());

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.categoryRecycler);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new TestAdapter(this, list));
        recyclerView.addOnScrollListener(new CenterScrollListener());

        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e("itemRec","");

            }
        });
//


    }
}


class CViewHolder extends RecyclerView.ViewHolder {

    TextView categoryName;
    ImageView categoryImage;
    LinearLayout layMain;

    public CViewHolder(@NonNull View itemView) {
        super(itemView);
        categoryName = itemView.findViewById(R.id.category_text);
        categoryImage = itemView.findViewById(R.id.category_imge);
         layMain=itemView.findViewById(R.id.layMain);

//        layMain.setOnClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.N)
//            @Override
//            public void onClick(View v) {
//              Log.e("item ...","i"+v.getId()+"-->"+);
//
//
//
//
//            }
//        });
}
}


class TestAdapter extends RecyclerView.Adapter<CViewHolder> {
    Context context;
    List<String> list;
DatabaseHandler db;

    public TestAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
        db=new DatabaseHandler(this.context);
    }

    @NonNull
    @Override
    public CViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.categoty_layout, viewGroup, false );
        return new CViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CViewHolder cViewHolder, final int i) {
        cViewHolder.categoryName.setText(list.get(i));
        cViewHolder.layMain.setId(i);
//        cViewHolder.categoryName.setText(list.get(i).getCategoryName());
//        cViewHolder.categoryImage.setImageDrawable(Drawable.createFromPath(pic.get(i)));


        cViewHolder.layMain.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                Log.e("item ...","i"+v.getId()+"-->"+i+"===>"+list.get(i));

                Intent itemIntent=new Intent(context,ItemsActivity.class);
                itemIntent.putExtra("categoryName",list.get(i));
                context.startActivity(itemIntent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}