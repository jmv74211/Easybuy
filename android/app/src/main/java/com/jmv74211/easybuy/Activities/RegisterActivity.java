package com.jmv74211.easybuy.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.jmv74211.easybuy.R;


public class RegisterActivity extends AppCompatActivity {

    private Toolbar appbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        appbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(appbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.signup));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }
}
