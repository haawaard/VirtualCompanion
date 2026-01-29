package com.example.virtualcompanion;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class CustomizeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_customize);

        // Get Done button
        MaterialButton doneButton = findViewById(R.id.doneButton);

        // When clicked, go to MoodActivity
        doneButton.setOnClickListener(v -> {

            Intent intent = new Intent(
                    CustomizeActivity.this,
                    MoodActivity.class
            );

            startActivity(intent);
            finish();
        });
    }
}
