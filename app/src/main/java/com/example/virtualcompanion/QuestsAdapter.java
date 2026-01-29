package com.example.virtualcompanion;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class QuestsAdapter extends RecyclerView.Adapter<QuestsAdapter.QuestViewHolder> {

    private List<Quest> questsList;
    private OnQuestClickListener listener;

    // Interface for click events
    public interface OnQuestClickListener {
        void onQuestClick(Quest quest, int position);
    }

    public QuestsAdapter(List<Quest> questsList) {
        this.questsList = questsList;
    }

    public QuestsAdapter(List<Quest> questsList, OnQuestClickListener listener) {
        this.questsList = questsList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public QuestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the item_quest.xml layout
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_quest, parent, false);
        return new QuestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestViewHolder holder, int position) {
        Quest quest = questsList.get(position);

        // Bind data to views
        holder.questIcon.setImageResource(quest.getIconResId());
        holder.questTitle.setText(quest.getTitle());
        holder.questDescription.setText(quest.getDescription());
        holder.questReward.setText("+" + quest.getReward());

        // Set click listener
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onQuestClick(quest, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return questsList.size();
    }

    // ViewHolder class
    public static class QuestViewHolder extends RecyclerView.ViewHolder {
        ImageView questIcon;
        TextView questTitle;
        TextView questDescription;
        TextView questReward;

        public QuestViewHolder(@NonNull View itemView) {
            super(itemView);

            // Find views from item_quest.xml
            questIcon = itemView.findViewById(R.id.questIcon);
            questTitle = itemView.findViewById(R.id.questTitle);
            questDescription = itemView.findViewById(R.id.questDescription);
            questReward = itemView.findViewById(R.id.questReward);
        }
    }

    // Method to update the list
    public void updateQuests(List<Quest> newQuests) {
        this.questsList = newQuests;
        notifyDataSetChanged();
    }
}