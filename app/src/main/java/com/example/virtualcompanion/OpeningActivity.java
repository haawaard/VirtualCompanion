package com.example.virtualcompanion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class OpeningActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enable fullscreen
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        DatabaseManager.get(this); // Initialize database
        setContentView(R.layout.activity_opening);

        MusicManager.startMusic(this);


        // Get main layout
        ConstraintLayout mainLayout = findViewById(R.id.main);

        // Click anywhere to continue
        mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Go to Customize screen
                Intent intent = new Intent(
                        OpeningActivity.this,
                        CustomizeActivity.class
                );

                startActivity(intent);
                finish(); // prevent going back
            }
        });
    }
}
