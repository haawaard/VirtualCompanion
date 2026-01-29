package com.example.virtualcompanion;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onResume() {
        super.onResume();

        // Always ensure music is running
        MusicManager.startMusic(this);
    }
}
