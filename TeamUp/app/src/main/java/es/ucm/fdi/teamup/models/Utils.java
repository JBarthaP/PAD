package es.ucm.fdi.teamup.models;

import android.content.Intent;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

import kotlin.Triple;

public class Utils {
    public static int getInputValueAsInt(TextInputLayout input) {
        String inputString = input.getEditText().getText().toString();
        if (!inputString.equals("") || inputString.equals("0"))
            return Integer.parseInt(inputString);
        return -1;
    }

    public static String getInputValueAsString(TextInputLayout input) {
        String inputString = input.getEditText().getText().toString();
        return inputString;
    }

    public static String getActualDateString(){
        Calendar calendar = Calendar.getInstance();
        return (calendar.get(Calendar.DAY_OF_MONTH) + "/" + (calendar.get(Calendar.MONTH)+1) + "/" + calendar.get(Calendar.YEAR));
    }

    public Triple<Integer, Integer, Integer> getActualDate(){
        Calendar calendar = Calendar.getInstance();
        return new Triple<>(calendar.get(Calendar.DAY_OF_MONTH),calendar.get(Calendar.MONTH)+1,calendar.get(Calendar.YEAR));
    }
}
