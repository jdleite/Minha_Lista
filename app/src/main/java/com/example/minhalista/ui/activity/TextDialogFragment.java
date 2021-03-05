package com.example.minhalista.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.minhalista.R;
import com.example.minhalista.database.TaskDatabase;
import com.example.minhalista.database.dao.TaskDao;
import com.example.minhalista.model.Item;
import com.example.minhalista.model.Task;
import com.example.minhalista.ui.validator.StandardValidator;
import com.example.minhalista.ui.validator.ValidatorMyList;
import com.example.minhalista.ui.viewHolder.TaskViewHolder;

import java.util.ArrayList;
import java.util.List;

public class TextDialogFragment extends DialogFragment {

    private StartFields stardFields = new StartFields();
    private Context context;
    private TaskDao dao;
    private Task task;
    private Item item;
    private RefreshList refreshList;
    private final List<StandardValidator> validatorList = new ArrayList<>();

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_form, container, false);

        context = getActivity().getApplicationContext();
        TaskDatabase taskDatabase = TaskDatabase.getInstance(context);
        dao = taskDatabase.getTaskDatabase();


        getMethods();
        return view;
    }

    private void getMethods() {
        nameField();
        loadTask();
        btnSave();
        btnCancel();
    }

    private class StartFields {
        EditText edtName;
        Button btnSave, btnCancel;

    }

    private void nameField() {
        stardFields.edtName = view.findViewById(R.id.id_edt_task);
        addStandardValidator(stardFields.edtName);
    }


    private void setTask() {
        String name = stardFields.edtName.getText().toString();
        task.setTaskName(name);
    }

    private void setItem() {
        String name = stardFields.edtName.getText().toString();
        item.setItemName(name);
        item.setId_task(TaskViewHolder.GLOBAL_ID);
    }

    private void btnSave() {
        stardFields.btnSave = view.findViewById(R.id.id_btn_save);

        stardFields.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isValidForm = validField();
                if (isValidForm) {
                    finishForm();
                    refreshList.refresh();
                    getDialog().dismiss();
                }
            }
        });
    }

    private void btnCancel() {
        stardFields.btnCancel = view.findViewById(R.id.id_btn_cancel);

        stardFields.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            refreshList = (RefreshList) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "deve ser implementado AtualizaListener");
        }
    }

    public interface RefreshList {
        void refresh();
    }

    private void loadTask() {
        Bundle b = getArguments();

        if (ListItemActivity.i == 1) {
            if (b != null) {
                item = (Item) this.getArguments().getSerializable("item");
                fillFilds();
            } else {
                item = new Item();
            }
        } else {
            if (b != null) {
                task = (Task) this.getArguments().getSerializable("task");
                fillFilds();
            } else {
                task = new Task();
            }
        }

    }


    private void fillFilds() {
        if (ListItemActivity.i == 1) {
            stardFields.edtName.setText(item.getItemName());
        } else {

            stardFields.edtName.setText(task.getTaskName());
        }
    }

    private void finishForm() {
        if (ListItemActivity.i == 1) {
            setItem();
            if (item.validId()) {
                dao.itemUpdate(item);
            } else {
                dao.itemSave(item);
            }
        } else {
            setTask();
            if (task.validId()) {
                dao.update(task);
            } else {
                dao.save(task);
            }

        }
    }

    private void addStandardValidator(EditText editText) {
        StandardValidator validator = new StandardValidator(editText);
        validatorList.add(validator);

        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    validator.isValid();
                }
            }
        });
    }

    private boolean validField() {
        for (ValidatorMyList validatorMyList : validatorList) {
            if (!validatorMyList.isValid()) {
                return false;
            }
        }
        return true;
    }


}
