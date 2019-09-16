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
import com.falconssoft.menurestaurant.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterItems extends RecyclerView.Adapter<AdapterItems.ItemsViewHolder> {
    private Context context;
    private List<Items> items;
    LayoutInflater layout;

    public AdapterItems(Context context, List<Items> items) {
        this.context = context;
        this.items = items;
        layout = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ItemsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_row, viewGroup, false);
        AdapterItems.ItemsViewHolder viewHolder = new AdapterItems.ItemsViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterItems.ItemsViewHolder itemsViewHolder, int i) {

        Items itemModel = items.get(i);
        itemsViewHolder.ItemName.setText(itemModel.getItemName() + "");
        itemsViewHolder.Dscription.setText(itemModel.getDescription() + "");
        itemsViewHolder.Price.setText(itemModel.getPrice() + "");
//        Log.e("Item Bind", "" + itemModel.getItemName());
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ItemsViewHolder extends RecyclerView.ViewHolder {
        TextView ItemName, Price, Dscription;
//        CircularImageView circularImageView ;
        public ItemsViewHolder(@NonNull View itemView) {
            super(itemView);
            ItemName = itemView.findViewById(R.id.textView_Nmae);
            Price = itemView.findViewById(R.id.text_price);
            Dscription = itemView.findViewById(R.id.textView_description);
//            Log.e("ItemsViewHolder", "y");
         //   circularImageView =itemView.findViewById(R.id.circular_image);
            Log.e("ItemsViewHolder", "y");
        }
    }

}

