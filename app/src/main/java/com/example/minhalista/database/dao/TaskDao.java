package com.example.minhalista.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.minhalista.model.Item;
import com.example.minhalista.model.Task;

import java.util.List;

@Dao
public interface TaskDao {
    @Insert
    Long save(Task task);

    @Update
    void update(Task task);

    @Delete
    void delete(Task task);

    @Query("SELECT * FROM task ORDER BY ID DESC")
    List<Task> listAll();

    @Insert
    Long itemSave(Item item);

    @Update
    void itemUpdate(Item item);

    @Delete
    void deleteItem(Item item);

    @Query("SELECT * FROM item WHERE id_task = :id")
    List<Item> ItemListAll(int id);

    @Query(value = "update Item set `check` = :value where id = :id")
    void check(int id,int value);

    @Query("SELECT `check` from item where id = :id")
    boolean listCheckById(int id);


}
