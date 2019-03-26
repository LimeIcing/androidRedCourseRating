package com.example.redcourserating;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = "MainActivity";
    private EditText fieldUsername, fieldPassword;
    private Button btnLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate() called");

        init();
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, "Button press registered");
        if (fieldUsername.getText().toString().isEmpty() ||
                fieldPassword.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please fill out both fields", Toast.LENGTH_SHORT)
                    .show();
        } else {
            Intent intent = new Intent(this, CourseListActivity.class);
            startActivity(intent);
        }
    }

    private void init() {
        Log.d(TAG, "Initializing...");

        fieldUsername = findViewById(R.id.fieldUsername);
        fieldPassword = findViewById(R.id.fieldPassword);
        btnLogIn = findViewById(R.id.btnLogIn);
        btnLogIn.setOnClickListener(this);
    }
}
