package com.example.minhalista.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.minhalista.R;
import com.example.minhalista.model.Item;
import com.example.minhalista.ui.listener.ItemListener;
import com.example.minhalista.ui.viewHolder.ItemViewHolder;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {
    private final List<Item> itemList;
    private final ItemListener itemListener;

    public ItemAdapter(List<Item> itemList, ItemListener listener) {
        this.itemList = itemList;
        this.itemListener = listener;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_item_row, parent, false);
        return new ItemViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.bindData(item, itemListener);

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void loadItem(List<Item> itemList) {
        this.itemList.clear();
        this.itemList.addAll(itemList);

    }
}
