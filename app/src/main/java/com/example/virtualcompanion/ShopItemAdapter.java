package com.example.virtualcompanion;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ShopItemAdapter extends RecyclerView.Adapter<ShopItemAdapter.ViewHolder> {

    private final int[] itemImages;
    private final String[] itemPrices;

    public ShopItemAdapter(int[] itemImages, String[] itemPrices) {
        this.itemImages = itemImages;
        this.itemPrices = itemPrices;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_shop_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemImage.setImageResource(itemImages[position]);
        holder.itemPrice.setText(itemPrices[position]);
    }

    @Override
    public int getItemCount() {
        return itemImages.length;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImage;
        TextView itemPrice;

        ViewHolder(View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.itemImage);
            itemPrice = itemView.findViewById(R.id.itemPrice);
        }
    }
}