package com.example.minhalista.ui.validator;

import android.widget.EditText;

public class StandardValidator implements ValidatorMyList {
    private static final String CAMPO_OBRIGATORIO = "Campo Obrigatorio";
    private final EditText editText;

    public StandardValidator(EditText editText){
        this.editText = editText;
    }

    private boolean validatorField(){
        String edtNote = editText.getText().toString();
        if(edtNote.trim().isEmpty()){
            editText.setError(CAMPO_OBRIGATORIO);
            return false;
        }
        return true;
    }

    public void removeError(){
        editText.setError(null);
    }

    @Override
    public boolean isValid() {

        if (!validatorField()) return false;
        removeError();
        return true;
    }
}
