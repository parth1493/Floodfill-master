package com.example.floodfill;

import android.widget.EditText;

public class UtilClass {

    public static boolean nullCheckEditTexr(EditText editText){
        if(editText.getText().toString().isEmpty()){
            editText.setError("Please enter value for"+editText.getHint().toString());
            return false;
        }
        return true;
    }
}
