package com.example.minhalista.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.minhalista.database.dao.TaskDao;
import com.example.minhalista.model.Item;
import com.example.minhalista.model.Task;

@Database(entities = {Task.class, Item.class},version = 1,exportSchema = false)
public abstract class TaskDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "list";

    public abstract TaskDao getTaskDatabase();

    public static TaskDatabase getInstance(Context context){

        return Room.databaseBuilder(context,TaskDatabase.class,DATABASE_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }
}
