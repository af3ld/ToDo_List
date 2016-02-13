package com.lclark.todolist;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

    private static final String TAG = "main_layout";
    private SharedPreferences pref;
    private String[] currentDay;
    private EditText editText;
    private TextView textView;
    private int today;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        pref = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        textView = (TextView) findViewById(R.id.textView);
        currentDay = getResources().getStringArray(R.array.daysArray);
        editText = (EditText) findViewById(R.id.edit_text_main);
        today = 0;


        final Button leftButton = (Button) findViewById(R.id.main_activity_button_left);
        final Button rightButton = (Button) findViewById(R.id.main_activity_button_right);
        Button midButton = (Button) findViewById(R.id.main_activity_button_middle);

        setDaysText(leftButton, rightButton);

        View.OnClickListener buttonHandler = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.main_activity_button_left:
                        today--;
                        Log.i(TAG, currentDay[today] + ": " + Integer.toString(today));
                        setDaysText(leftButton, rightButton);
                        readBackText();
                        break;

                    case R.id.main_activity_button_right:
                        today++;
                        setDaysText(leftButton, rightButton);
                        Log.i(TAG, currentDay[today] + ": " + Integer.toString(today));
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

    public int[] daysFormat(int i){
        int[] arr = new int[2];
        arr[0] = (i > 0) ? i - 1 : 6;
        arr[1] = (i < 6) ? i + 1 : 0;
        return arr;
    }

    public void setDaysText(Button leftButton, Button rightButton){
        leftButton.setText(String.format(getResources().getString(R.string.outerButtonTitle), currentDay[daysFormat(today)[0]]));
        rightButton.setText(String.format(getResources().getString(R.string.outerButtonTitle), currentDay[daysFormat(today)[1]]));
        textView.setText(String.format(getString(R.string.textviewString), currentDay[today]));
        editText.setHint(String.format(getString(R.string.hint), currentDay[today]));
    }

    public void readBackText(){
        if(pref.getString(currentDay[today], "").length() > 0){
            editText.setText(pref.getString(currentDay[today], ""));
        } else {
          editText.setText("");
        }
    }

}

