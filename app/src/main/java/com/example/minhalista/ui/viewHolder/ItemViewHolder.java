package com.example.minhalista.ui.viewHolder;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.minhalista.R;
import com.example.minhalista.database.TaskDatabase;
import com.example.minhalista.database.dao.TaskDao;
import com.example.minhalista.model.Item;
import com.example.minhalista.ui.listener.ItemListener;

public class ItemViewHolder extends RecyclerView.ViewHolder {
    TextView txtItem;
    Context context;
    CheckBox checkBox;
    ImageView imgClose;
    TaskDao dao;

    public ItemViewHolder(@NonNull View itemView, Context context) {
        super(itemView);

        TaskDatabase taskDatabase = TaskDatabase.getInstance(context);
        dao = taskDatabase.getTaskDatabase();
        txtItem = itemView.findViewById(R.id.id_txt_item);
        this.context = context;
        imgClose = itemView.findViewById(R.id.id_img_close);
        checkBox = itemView.findViewById(R.id.checkBox);
    }

    public void bindData(Item item, ItemListener listener) {
        txtItem.setText(item.getItemName());


      if (dao.listCheckById(item.getId())){
          checkBox.setChecked(true);
      }


        txtItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onEditClick(item);
            }
        });

        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onDeleteClick(item);
            }
        });



        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            if (dao.listCheckById(item.getId())){
                checkBox.setChecked(false);
                dao.check(item.getId(),0);
            }else {
                checkBox.setChecked(true);
                dao.check(item.getId(),1);
            }

            }
        });

    }
}
