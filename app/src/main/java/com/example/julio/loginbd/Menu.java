package com.example.julio.loginbd;

import android.content.Intent;
import android.support.v4.text.TextDirectionHeuristicCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Menu extends AppCompatActivity {

    private TextView txtmensaje;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        txtmensaje=(TextView)findViewById(R.id.txtmensaje);
        Intent intent = getIntent();
        String username=intent.getStringExtra(MainActivity.USER_NAME);

        txtmensaje.setText("Bienvenido usuario" +username);
    }
}
