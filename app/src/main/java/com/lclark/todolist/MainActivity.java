package com.lclark.todolist;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

    private static final String TAG = "main_layout";
    private SharedPreferences pref;
    private String[] currentDay;
    private EditText editText;
    private Spinner spinner;
    private int today;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        pref = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        spinner = (Spinner) findViewById(R.id.days_spinner);
        currentDay = getResources().getStringArray(R.array.daysArray);
        editText = (EditText) findViewById(R.id.edit_text_main);
        today = 0;

        final Button leftButton = (Button) findViewById(R.id.main_activity_button_left);
        final Button rightButton = (Button) findViewById(R.id.main_activity_button_right);
        Button midButton = (Button) findViewById(R.id.main_activity_button_middle);


        ArrayAdapter<String> days = new ArrayAdapter<String>(MainActivity.this, R.layout.spinner_layout, currentDay);
        days.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(days);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                for (int i = 0; i < currentDay.length; i++) {
                    if (currentDay[i] == spinner.getSelectedItem().toString()) {
                        today = i;
                        setDaysText(leftButton, rightButton, spinner);
                        readBackText();
                        break;
                    }
                }
            };

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });



        setDaysText(leftButton, rightButton, spinner);

        View.OnClickListener buttonHandler = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.main_activity_button_left:
                        today = daysFormat(today - 1);
                        setDaysText(leftButton, rightButton, spinner);
                        readBackText();
                        break;

                    case R.id.main_activity_button_right:
                        today = daysFormat(today + 1);
                        setDaysText(leftButton, rightButton, spinner);
                        readBackText();
                        break;

                    case R.id.main_activity_button_middle:
                        pref.edit().putString(currentDay[today], editText.getText().toString()).commit();
                        Context context = getApplicationContext();
                        String toastText = String.format(getResources().getString(R.string.toastMessage), currentDay[today]);
                        Toast toast = Toast.makeText(context, toastText, Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.TOP, 0,0);
                        toast.show();
                }
            }
        };

    leftButton.setOnClickListener(buttonHandler);
    midButton.setOnClickListener(buttonHandler);
    rightButton.setOnClickListener(buttonHandler);
    }

    public int daysFormat(int i){
        switch (i){
            case -1:
                return 6;
            case 7:
                return 0;
            default:
                return i;
        }
    }

    public void setDaysText(Button leftButton, Button rightButton, Spinner spinner){
        leftButton.setText(String.format(getResources().getString(R.string.outerButtonTitle), currentDay[daysFormat(today - 1)]));
        rightButton.setText(String.format(getResources().getString(R.string.outerButtonTitle), currentDay[daysFormat(today + 1)]));
        editText.setHint(String.format(getString(R.string.hint), currentDay[today]));
        spinner.setSelection(today);

        Log.i(TAG, currentDay[today] + ": " + Integer.toString(today));
    }

    public void readBackText(){
        if(pref.getString(currentDay[today], "").length() > 0){
            editText.setText(pref.getString(currentDay[today], ""));
        } else {
          editText.setText("");
        }
    }

}

