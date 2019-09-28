package com.falconssoft.menurestaurant;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.falconssoft.menurestaurant.models.Items;

import java.util.ArrayList;
import java.util.List;

public class AdapterItems extends RecyclerView.Adapter<AdapterItems.ItemsViewHolder> {
    private Context context;
    private LayoutInflater layout;
    private List<Items> list = new ArrayList<>();

    public AdapterItems(Context context, List<Items> list) {
        this.context = context;
        layout = LayoutInflater.from(context);
        this.list = list;
    }

    @NonNull
    @Override
    public ItemsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row, viewGroup, false);
        return new ItemsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterItems.ItemsViewHolder itemsViewHolder, int i) {

//        Items itemModel = items.get(i);
        itemsViewHolder.ItemName.setText(list.get(i).getItemName() + "");
        itemsViewHolder.Price.setText(list.get(i).getPrice() + "");
        if (list.get(i).getDescription().equals("null") || list.get(i).getDescription().equals(""))
            itemsViewHolder.Description.setText("No description");
        else
            itemsViewHolder.Description.setText(list.get(i).getDescription() + "");
//        Log.e("Item Bind", "" + itemModel.getItemName());
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ItemsViewHolder extends RecyclerView.ViewHolder {
        TextView ItemName, Price, Description;

        //        CircularImageView circularImageView ;
        public ItemsViewHolder(@NonNull View itemView) {
            super(itemView);
            ItemName = itemView.findViewById(R.id.textView_Nmae);
            Price = itemView.findViewById(R.id.text_price);
            Description = itemView.findViewById(R.id.textView_description);
//            Log.e("ItemsViewHolder", "y");
            //   circularImageView =itemView.findViewById(R.id.circular_image);
            Log.e("ItemsViewHolder", "y");
        }
    }

}

