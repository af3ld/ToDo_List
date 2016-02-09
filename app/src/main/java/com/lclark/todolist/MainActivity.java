package com.lclark.todolist;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

    private String[] currentDay = getResources().getStringArray(R.array.daysArray);
    private EditText editText = (EditText) findViewById(R.id.edit_text_main);
    private TextView textView = (TextView) findViewById(R.id.textView);
    private int today = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String title = String.format(getResources().getString(R.string.textviewString), currentDay[today]);
        textView.setText(title);
        editText.setHint(String.format(getResources().getString(R.string.hint), currentDay));


        Button leftButton = (Button) findViewById(R.id.main_activity_button_left);
        Button rightButton = (Button) findViewById(R.id.main_activity_button_right);
        Button midButton = (Button) findViewById(R.id.main_activity_button_middle);
        leftButton.setText(String.format(getResources().getString(R.string.outerButtonTitle), currentDay[daysFormat(today)[0]]));
        rightButton.setText(String.format(getResources().getString(R.string.outerButtonTitle), currentDay[daysFormat(today)[1]]));

        View.OnClickListener buttonHandler = new View.OnClickListener() {
            @Override
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
                        pref.edit().putString(currentDay[today], editText.getText().toString()).commit();
                        Context context = getApplicationContext();
                        String toastText = String.format(getResources().getString(R.string.toastMessage), currentDay[today]);
                        Toast toast = Toast.makeText(context, toastText, Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.BOTTOM, 0,0);
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


}

