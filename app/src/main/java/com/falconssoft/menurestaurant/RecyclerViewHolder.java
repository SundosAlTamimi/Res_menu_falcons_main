package com.falconssoft.menurestaurant;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    TextView categoryName;
    ImageView categoryImage;
    LinearLayout layMain;

    public RecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        categoryName = itemView.findViewById(R.id.category_text);
        categoryImage = itemView.findViewById(R.id.category_imge);
        layMain = itemView.findViewById(R.id.layMain);
    }
}