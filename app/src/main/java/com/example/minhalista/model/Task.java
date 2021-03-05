package com.example.minhalista.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity

public class Task  implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String taskName;


    public Task() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskName() {
        return taskName;
    }

    public boolean validId(){
        return id > 0;
    }
}
