package com.falconssoft.menurestaurant;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ItemsViewHolder extends RecyclerView.ViewHolder {
    TextView itemName, price, description;

    //        CircularImageView circularImageView ;
    public ItemsViewHolder(@NonNull View itemView) {
        super(itemView);
        itemName = itemView.findViewById(R.id.textView_Nmae);
        price = itemView.findViewById(R.id.text_price);
        description = itemView.findViewById(R.id.textView_description);


//            Log.e("ItemsViewHolder", "y");
        //   circularImageView =itemView.findViewById(R.id.circular_image);
        Log.e("ItemsViewHolder", "y");
    }


}

