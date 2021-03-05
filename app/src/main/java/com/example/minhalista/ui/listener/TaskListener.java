package com.example.minhalista.ui.listener;

import com.example.minhalista.model.Task;

public interface TaskListener {
    void onEditClick(Task task);

    void onDeleteClick(Task task);

    void getFormAddItem();
}
