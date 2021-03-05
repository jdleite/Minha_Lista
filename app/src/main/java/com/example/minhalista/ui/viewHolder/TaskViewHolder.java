package com.example.minhalista.ui.viewHolder;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.minhalista.R;
import com.example.minhalista.model.Task;
import com.example.minhalista.ui.listener.TaskListener;

public class TaskViewHolder extends RecyclerView.ViewHolder {

    private TextView txtTaskName;
    private Context context;
    private CardView cardView;
    private ImageView imgEdit;
    public static int GLOBAL_ID = 0;


    public TaskViewHolder(@NonNull View itemView, Context context) {
        super(itemView);

        txtTaskName = itemView.findViewById(R.id.id_task_name);
        cardView = itemView.findViewById(R.id.id_card_view);
        imgEdit = itemView.findViewById(R.id.id_img_edit);
        this.context = context;
    }


    public void bindData(Task task, TaskListener listener) {
        txtTaskName.setText(String.valueOf(task.getTaskName()));


        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onEditClick(task);
            }
        });

        cardView.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                new AlertDialog
                        .Builder(context)
                        .setTitle(R.string.remove_title)
                        .setMessage(R.string.remove_message)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                listener.onDeleteClick(task);
                            }
                        })
                        .setNeutralButton(R.string.no, null)
                        .show();


                return true;
            }
        });

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                GLOBAL_ID = task.getId();
                listener.getFormAddItem();

            }
        });


    }
}
