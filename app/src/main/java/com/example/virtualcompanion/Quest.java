package com.example.virtualcompanion;

public class Quest {
    private String title;
    private String description;
    private int reward;
    private int iconResId;

    public Quest(String title, String description, int reward, int iconResId) {
        this.title = title;
        this.description = description;
        this.reward = reward;
        this.iconResId = iconResId;
    }

    public Quest(String title, String description, int reward) {
        this.title = title;
        this.description = description;
        this.reward = reward;
        this.iconResId = R.drawable.ic_quests; // Default icon
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public int getIconResId() {
        return iconResId;
    }

    public void setIconResId(int iconResId) {
        this.iconResId = iconResId;
    }
}