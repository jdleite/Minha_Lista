package com.example.minhalista.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.minhalista.R;
import com.example.minhalista.model.Task;
import com.example.minhalista.ui.listener.TaskListener;
import com.example.minhalista.ui.viewHolder.TaskViewHolder;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskViewHolder> {

    private final List<Task> taskList;
    private final TaskListener taskListener;

    public TaskAdapter(List<Task> taskList, TaskListener taskListener) {
        this.taskList = taskList;
        this.taskListener = taskListener;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_list_row, parent, false);
        return new TaskViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {

        Task task = taskList.get(position);
        holder.bindData(task, taskListener);

    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public void loadTask(List<Task> taskList){
        this.taskList.clear();
        this.taskList.addAll(taskList);
        notifyDataSetChanged();
    }
}
