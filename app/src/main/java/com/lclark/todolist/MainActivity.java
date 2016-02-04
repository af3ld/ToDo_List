package com.lclark.todolist;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
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
