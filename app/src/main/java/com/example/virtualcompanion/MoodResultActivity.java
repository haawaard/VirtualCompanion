package com.example.virtualcompanion;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MoodResultActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_mood_result);

        // Top Settings Icon
        ImageView settingsIcon = findViewById(R.id.settingsIcon);

        // Bottom Navigation
        ImageView navHome = findViewById(R.id.navHome);
        ImageView navTasks = findViewById(R.id.navQuests);
        ImageView navCustomize = findViewById(R.id.navCustomize);

        // Settings → SettingsActivity
        if (settingsIcon != null) {
            settingsIcon.setOnClickListener(v -> {

                Intent intent = new Intent(
                        MoodResultActivity.this,
                        SettingsActivity.class
                );

                startActivity(intent);
            });
        }

        // Home → MoodActivity
        if (navHome != null) {
            navHome.setOnClickListener(v -> {

                Intent intent = new Intent(
                        MoodResultActivity.this,
                        MoodActivity.class
                );

                startActivity(intent);
            });
        }

        // Quests → QuestsActivity
        if (navTasks != null) {
            navTasks.setOnClickListener(v -> {

                Intent intent = new Intent(
                        MoodResultActivity.this,
                        QuestsActivity.class
                );

                startActivity(intent);
            });
        }

        // Customize → CustomTopActivity
        if (navCustomize != null) {
            navCustomize.setOnClickListener(v -> {

                Intent intent = new Intent(
                        MoodResultActivity.this,
                        CustomTopActivity.class
                );

                startActivity(intent);
            });
        }
    }
}
