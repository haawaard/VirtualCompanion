package com.example.virtualcompanion;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CustomTopActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Must match layout filename
        setContentView(R.layout.activity_custom_top);

        // ===============================
        // ITEMS RECYCLER VIEW
        // ===============================

        RecyclerView itemsRecyclerView =
                findViewById(R.id.itemsRecyclerViewTop);

        if (itemsRecyclerView != null) {

            LinearLayoutManager layoutManager =
                    new LinearLayoutManager(
                            this,
                            LinearLayoutManager.HORIZONTAL,
                            false
                    );

            itemsRecyclerView.setLayoutManager(layoutManager);

            int[] itemImages = {
                    R.drawable.ic_cancel,
                    R.drawable.top_boy_flannel,
                    R.drawable.top_girl_pink,
                    R.drawable.top_boy_floral,
                    R.drawable.top_girl_plaid,
                    R.drawable.top_boy_quarterzip,
                    R.drawable.top_girl_cardigan,
                    R.drawable.top_boy_leather,
                    R.drawable.top_girl_dress,
                    R.drawable.top_boy_tuxedo
            };

            String[] itemPrices = {
                    "", "", "", "",
                    "", "150", "150", "200",
                    "250", "250"
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

        // TOP (Current Screen)
        if (category1 != null) {
            category1.setOnClickListener(v -> {
                // Stay here
            });
        }

        // BOTTOM
        if (category2 != null) {
            category2.setOnClickListener(v -> {
                startActivity(new Intent(
                        CustomTopActivity.this,
                        CustomBottomActivity.class
                ));
                finish();
            });
        }

        // HAT
        if (category3 != null) {
            category3.setOnClickListener(v -> {
                startActivity(new Intent(
                        CustomTopActivity.this,
                        CustomHatActivity.class
                ));
                finish();
            });
        }

        // GLASSES
        if (category4 != null) {
            category4.setOnClickListener(v -> {
                startActivity(new Intent(
                        CustomTopActivity.this,
                        CustomGlassesActivity.class
                ));
                finish();
            });
        }

        // ===============================
        // SETTINGS ICON
        // ===============================

        ImageView settingsIcon = findViewById(R.id.settingsIcon);

        if (settingsIcon != null) {
            settingsIcon.setOnClickListener(v -> {
                startActivity(new Intent(
                        CustomTopActivity.this,
                        SettingsActivity.class
                ));
            });
        }

        // ===============================
        // BOTTOM NAVIGATION
        // ===============================

        ImageView navHome = findViewById(R.id.navHome);
        ImageView navQuests = findViewById(R.id.navQuests);
        ImageView navCustomize = findViewById(R.id.navCustomize);

        // Home
        if (navHome != null) {
            navHome.setOnClickListener(v -> {
                startActivity(new Intent(
                        CustomTopActivity.this,
                        MoodResultActivity.class
                ));
                finish();
            });
        }

        // Quests
        if (navQuests != null) {
            navQuests.setOnClickListener(v -> {
                startActivity(new Intent(
                        CustomTopActivity.this,
                        QuestsActivity.class
                ));
                finish();
            });
        }

        // Customize (Current)
        if (navCustomize != null) {
            navCustomize.setOnClickListener(v -> {
                // Stay here
            });
        }
    }
}
