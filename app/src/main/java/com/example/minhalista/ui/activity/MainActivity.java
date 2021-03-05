package com.example.minhalista.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.minhalista.R;
import com.example.minhalista.database.TaskDatabase;
import com.example.minhalista.database.dao.TaskDao;
import com.example.minhalista.model.Task;
import com.example.minhalista.ui.adapter.TaskAdapter;
import com.example.minhalista.ui.listener.TaskListener;
import com.example.minhalista.ui.viewHolder.TaskViewHolder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TextDialogFragment.RefreshList {
    private TaskAdapter adapter;
    private List<Task> taskList = new ArrayList<>();
    private TaskListener listener;
    private RecyclerView recyclerView;
    private TaskDao dao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.id_recycler_view);
        TaskDatabase taskDatabase = TaskDatabase.getInstance(this);
        dao = taskDatabase.getTaskDatabase();


        fab();
        getListener();
    }

    private void getListener() {
        listener = new TaskListener() {
            @Override
            public void onEditClick(Task task) {
                openTaskEditMode(task);
            }

            @Override
            public void onDeleteClick(Task task) {
                dao.delete(task);
                onResume();

            }

            @Override
            public void getFormAddItem() {
                Intent i = new Intent(MainActivity.this, ListItemActivity.class);
                startActivity(i);
            }
        };
    }

    private void fab() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getFormFragment();


            }
        });
    }

    public void getFormFragment() {
        TextDialogFragment fragment = new TextDialogFragment();
        fragment.show(getSupportFragmentManager(), "note");
    }

    private void getList() {
        adapter = new TaskAdapter(taskList, listener);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.loadTask(dao.listAll());
    }

    @Override
    protected void onResume() {
        super.onResume();
        getList();
        ListItemActivity.i = 0;
        TaskViewHolder.GLOBAL_ID = 0;
    }

    @Override
    public void refresh() {
        onResume();
    }

    public void openTaskEditMode(Task task) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("task", task);
        TextDialogFragment fragment = new TextDialogFragment();
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().commit();
        fragment.show(getSupportFragmentManager(), "task");

    }


}