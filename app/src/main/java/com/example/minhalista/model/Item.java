package com.example.minhalista.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "Item",
        foreignKeys = @ForeignKey(entity = Task.class,
                parentColumns = "id", childColumns = "id_task", onDelete = CASCADE))
public class Item implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String ItemName;
    private boolean check;
    private int id_task;

    public Item() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public int getId_task() {
        return id_task;
    }

    public void setId_task(int id_task) {
        this.id_task = id_task;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public boolean validId() {
        return id > 0;
    }
}
