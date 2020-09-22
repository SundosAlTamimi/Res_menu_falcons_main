package com.falconssoft.menurestaurant;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.falconssoft.menurestaurant.models.Items;


import java.util.ArrayList;
import java.util.List;

public class AdapterItems extends RecyclerView.Adapter<ItemsViewHolder> {
    private ItemsActivity itemsActivity;
    private LayoutInflater layout;
    private List<Items> list = new ArrayList<>();

    public AdapterItems(ItemsActivity itemsActivity, List<Items> list) {
        this.itemsActivity = itemsActivity;
        layout = LayoutInflater.from(itemsActivity);
        this.list = list;
    }

    @NonNull
    @Override
    public ItemsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row, viewGroup, false);
        return new ItemsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemsViewHolder itemsViewHolder, final int i) {
//        Items itemModel = items.get(i);

        itemsViewHolder.itemName.setText(list.get(i).getItemName() + "");
        itemsViewHolder.price.setText(list.get(i).getPrice() + "");
        if (list.get(i).getDescription().equals("null") || list.get(i).getDescription().equals(""))
            itemsViewHolder.description.setText("No description");
        else
            itemsViewHolder.description.setText(list.get(i).getDescription() + "");

//        Log.e("Item Bind", "" + itemModel.getItemName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.item_row_increment:
//                quantityValue++;
//                itemsViewHolder.quantity.setText("" + quantityValue);
//                break;
//            case R.id.item_row_decrement:
//                if (quantityValue > 1)
//                    quantityValue--;
//                else
//                    Toast.makeText(itemView.getContext(), "Quantity can't be less than 1!", Toast.LENGTH_SHORT).show();
//                quantity.setText("" + quantityValue);
//                break;
//        }
//
//    }
}

