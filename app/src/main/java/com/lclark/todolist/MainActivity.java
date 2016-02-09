package com.lclark.todolist;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



public class MainActivity extends Activity {

    private String[] currentDay = getResources().getStringArray(R.array.daysArray);
    private EditText editText = (EditText) findViewById(R.id.edit_text_main);
    private TextView textView = (TextView) findViewById(R.id.textView);
    private int today = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String title = String.format(getResources().getString(R.string.textview), currentDay[today]);
        textView.setText(title);
        editText.setHint(String.format(getResources().getString(R.string.hint), currentDay));


        Button leftButton = (Button) findViewById(R.id.main_activity_button_left);
        Button rightButton = (Button) findViewById(R.id.main_activity_button_right);
        Button midButton = (Button) findViewById(R.id.main_activity_button_middle);
        leftButton.setText(currentDay[daysFormat(today)[0]]);
        rightButton.setText(currentDay[daysFormat(today)[1]]);

        View.OnClickListener buttonHandler = new View.OnClickListener() {
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.main_activity_button_left:
                        today--;
                        recreate();
                        break;
                    case R.id.main_activity_button_right:
                        today++;
                        recreate();
                        break;
                    case R.id.main_activity_button_middle:
                        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                        pref.edit().putString("autoSave", editText.getText().toString()).apply();
                }
            }
        };
        leftButton.setOnClickListener(buttonHandler);
        midButton.setOnClickListener(buttonHandler);
        rightButton.setOnClickListener(buttonHandler);
    }

    public int[] daysFormat(int i){
        int[] arr = new int[3];
        arr[0] = (i > 0) ? i - 1 : 6;
        arr[1] = (i < 6) ? i + 1 : 0;
        return arr;
    }

}

