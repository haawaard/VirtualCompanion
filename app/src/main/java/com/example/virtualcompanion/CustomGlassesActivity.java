package com.example.virtualcompanion;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CustomGlassesActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load layout
        setContentView(R.layout.activity_custom_glasses);

        // ===============================
        // ITEMS RECYCLER VIEW
        // ===============================

        RecyclerView itemsRecyclerView =
                findViewById(R.id.itemsRecyclerViewGlasses);

        if (itemsRecyclerView != null) {

            LinearLayoutManager layoutManager =
                    new LinearLayoutManager(
                            this,
                            LinearLayoutManager.HORIZONTAL,
                            false
                    );

            itemsRecyclerView.setLayoutManager(layoutManager);

            // Different items for GLASSES accessories
            int[] itemImages = {
                    R.drawable.ic_cancel,
                    R.drawable.glasses_normal,
                    R.drawable.glasses_shades,
                    R.drawable.glasses_maloi,
                    R.drawable.glasses_heart
            };

            String[] itemPrices = {
                    "", "", "", "150",
                    "150"
            };

            ShopItemAdapter adapter =
                    new ShopItemAdapter(itemImages, itemPrices);

            itemsRecyclerView.setAdapter(adapter);
        }

        // ===============================
        // CATEGORY BUTTONS
        // ===============================

        LinearLayout category1 = findViewById(R.id.categoryButton1);
        LinearLayout category2 = findViewById(R.id.categoryButton2);
        LinearLayout category3 = findViewById(R.id.categoryButton3);
        LinearLayout category4 = findViewById(R.id.categoryButton4);

        // TOP
        if (category1 != null) {
            category1.setOnClickListener(v -> {
                startActivity(new Intent(
                        CustomGlassesActivity.this,
                        CustomTopActivity.class
                ));
                finish();
            });
        }

        // BOTTOM
        if (category2 != null) {
            category2.setOnClickListener(v -> {
                startActivity(new Intent(
                        CustomGlassesActivity.this,
                        CustomBottomActivity.class
                ));
                finish();
            });
        }

        // HAT
        if (category3 != null) {
            category3.setOnClickListener(v -> {
                startActivity(new Intent(
                        CustomGlassesActivity.this,
                        CustomHatActivity.class
                ));
                finish();
            });
        }

        // GLASSES (Current)
        if (category4 != null) {
            category4.setOnClickListener(v -> {
                // Stay here
            });
        }

        // ===============================
        // SETTINGS
        // ===============================

        ImageView settingsIcon = findViewById(R.id.settingsIcon);

        if (settingsIcon != null) {
            settingsIcon.setOnClickListener(v -> {
                startActivity(new Intent(
                        CustomGlassesActivity.this,
                        SettingsActivity.class
                ));
            });
        }

        // ===============================
        // BOTTOM NAV
        // ===============================

        ImageView navHome = findViewById(R.id.navHome);
        ImageView navQuests = findViewById(R.id.navQuests);
        ImageView navCustomize = findViewById(R.id.navCustomize);

        if (navHome != null) {
            navHome.setOnClickListener(v -> {
                startActivity(new Intent(
                        CustomGlassesActivity.this,
                        MoodResultActivity.class
                ));
                finish();
            });
        }

        if (navQuests != null) {
            navQuests.setOnClickListener(v -> {
                startActivity(new Intent(
                        CustomGlassesActivity.this,
                        QuestsActivity.class
                ));
                finish();
            });
        }

        if (navCustomize != null) {
            navCustomize.setOnClickListener(v -> {
                startActivity(new Intent(
                        CustomGlassesActivity.this,
                        CustomTopActivity.class
                ));
                finish();
            });
        }
    }
}