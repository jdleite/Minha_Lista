package com.example.minhalista.ui.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.minhalista.R;
import com.example.minhalista.database.TaskDatabase;
import com.example.minhalista.database.dao.TaskDao;
import com.example.minhalista.model.Item;
import com.example.minhalista.ui.adapter.ItemAdapter;
import com.example.minhalista.ui.listener.ItemListener;
import com.example.minhalista.ui.viewHolder.TaskViewHolder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ListItemActivity extends AppCompatActivity implements TextDialogFragment.RefreshList {

    private ItemAdapter adapter;
    private RecyclerView recyclerView;
    private List<Item> itemList = new ArrayList<>();
    private ItemListener itemListener;
    private TaskDao dao;
    public static int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item);

        recyclerView = findViewById(R.id.id_recycler_view_item);
        TaskDatabase database = TaskDatabase.getInstance(this);
        dao = database.getTaskDatabase();


        getListener();
        getItemForm();
        i = 1;

    }



    private void getItemForm() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                TextDialogFragment fragment = new TextDialogFragment();
                fragment.show(getSupportFragmentManager(), "item");

            }
        });
    }

    private void getListener(){
        itemListener = new ItemListener() {
            @Override
            public void onEditClick(Item item) {
               openFragmentItemEditMode(item);
            }

            @Override
            public void onDeleteClick(Item item) {
                dao.deleteItem(item);
                refresh();
            }
        };
    }

    private void getList() {

       adapter = new ItemAdapter(itemList,itemListener);
       recyclerView.setAdapter(adapter);
       recyclerView.setLayoutManager(new LinearLayoutManager(this));
       adapter.loadItem(dao.ItemListAll(TaskViewHolder.GLOBAL_ID));

    }

    @Override
    protected void onResume() {
        super.onResume();
        getList();
    }

    @Override
    public void refresh() {
        onResume();
    }

    private void openFragmentItemEditMode(Item item){
        Bundle b = new Bundle();
        b.putSerializable("item",item);
        TextDialogFragment fragment = new TextDialogFragment();
        fragment.setArguments(b);
        getSupportFragmentManager().beginTransaction().commit();
        fragment.show(getSupportFragmentManager(),"item");
    }
}