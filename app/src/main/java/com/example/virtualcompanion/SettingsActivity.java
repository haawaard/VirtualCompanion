package com.example.virtualcompanion;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.widget.SwitchCompat;

public class SettingsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settings);

        // ===============================
        // SOUND EFFECTS TOGGLE
        // ===============================

        SwitchCompat soundEffectsToggle = findViewById(R.id.soundEffectsToggle);

        if (soundEffectsToggle != null) {

            // Set initial state
            soundEffectsToggle.setChecked(MusicManager.isMusicEnabled());

            // Handle toggle changes
            soundEffectsToggle.setOnCheckedChangeListener((buttonView, isChecked) -> {

                MusicManager.setMusicEnabled(isChecked);

                if (isChecked) {
                    MusicManager.startMusic(this);
                } else {
                    MusicManager.pauseMusic();


            }
            });
        }

        // ===============================
        // BACK BUTTON
        // ===============================

        ImageView backButton = findViewById(R.id.backButton);

        if (backButton != null) {
            backButton.setOnClickListener(v -> finish());
        }

        // ===============================
        // BOTTOM NAVIGATION
        // ===============================

        ImageView navHome = findViewById(R.id.navHome);
        ImageView navQuests = findViewById(R.id.navQuests);
        ImageView navCustomize = findViewById(R.id.navCustomize);

        if (navHome != null) {
            navHome.setOnClickListener(v -> {
                startActivity(new Intent(
                        SettingsActivity.this,
                        MoodResultActivity.class
                ));
                finish();
            });
        }

        if (navQuests != null) {
            navQuests.setOnClickListener(v -> {
                startActivity(new Intent(
                        SettingsActivity.this,
                        QuestsActivity.class
                ));
                finish();
            });
        }

        if (navCustomize != null) {
            navCustomize.setOnClickListener(v -> {
                startActivity(new Intent(
                        SettingsActivity.this,
                        CustomTopActivity.class
                ));
                finish();
            });
        }
    }
}
