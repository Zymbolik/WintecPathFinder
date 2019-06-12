package com.example.assignment3.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.assignment3.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editText = findViewById(R.id.UserNameInput);


        Button SaveButton = findViewById(R.id.UserNameButton);
        SaveButton.setOnClickListener(this::saveName);

    }
    public void saveName(View v){
        Intent intent = new Intent(this, HomeScreen.class);
        startActivity(intent);
    }
}
