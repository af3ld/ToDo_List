package com.lclark.todolist;

import android.app.Activity;
import android.os.Bundle;

import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button leftButton = (Button) findViewById(R.id.main_activity_button_left);
        Button rightButton = (Button) findViewById(R.id.main_activity_button_right);
        Button midButton = (Button) findViewById(R.id.main_activity_button_middle);

    }

}

