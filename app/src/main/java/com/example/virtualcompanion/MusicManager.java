package com.example.virtualcompanion;

import android.content.Context;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.util.Log;

public class MusicManager {

    private static final String TAG = "MusicManager";

    // Consistent volume level for all music tracks (0.0f to 1.0f)
    private static final float MUSIC_VOLUME = 0.5f; // 50% volume - adjust this value as needed

    private static MediaPlayer mediaPlayer;
    private static boolean isMusicEnabled = true;
    private static boolean isInitialized = false;
    private static int currentTrack = -1;
    private static int previousTrack = -1;
    private static int previousPosition = 0; // Save playback position

    // Music track constants
    public static final int TRACK_BACKGROUND = R.raw.background_music;

    // Mood-specific quest music
    public static final int TRACK_QUEST_HAPPY = R.raw.quest_happy;
    public static final int TRACK_QUEST_SAD = R.raw.quest_sad;
    public static final int TRACK_QUEST_ANGRY = R.raw.quest_angry;
    public static final int TRACK_QUEST_ANXIOUS = R.raw.quest_anxious;
    public static final int TRACK_QUEST_NEUTRAL = R.raw.quest_neutral;

    /**
     * Check if a resource exists
     */
    private static boolean resourceExists(Context context, int resId) {
        try {
            context.getResources().openRawResource(resId);
            return true;
        } catch (Resources.NotFoundException e) {
            return false;
        }
    }

    /**
     * Check if a track is a quest track
     */
    private static boolean isQuestTrack(int trackId) {
        return trackId == TRACK_QUEST_HAPPY ||
                trackId == TRACK_QUEST_SAD ||
                trackId == TRACK_QUEST_ANGRY ||
                trackId == TRACK_QUEST_ANXIOUS ||
                trackId == TRACK_QUEST_NEUTRAL;
    }

    /**
     * Get appropriate quest music based on mood
     */
    public static int getQuestTrackForMood(String mood) {
        if (mood == null) {
            Log.w(TAG, "Mood is null, using neutral track");
            return TRACK_QUEST_NEUTRAL;
        }

        Log.d(TAG, "Getting track for mood: " + mood);

        switch (mood.toLowerCase().trim()) {
            case "happy":
                Log.d(TAG, "Returning TRACK_QUEST_HAPPY");
                return TRACK_QUEST_HAPPY;
            case "sad":
                Log.d(TAG, "Returning TRACK_QUEST_SAD");
                return TRACK_QUEST_SAD;
            case "angry":
                Log.d(TAG, "Returning TRACK_QUEST_ANGRY");
                return TRACK_QUEST_ANGRY;
            case "anxious":
                Log.d(TAG, "Returning TRACK_QUEST_ANXIOUS");
                return TRACK_QUEST_ANXIOUS;
            case "neutral":
                Log.d(TAG, "Returning TRACK_QUEST_NEUTRAL");
                return TRACK_QUEST_NEUTRAL;
            default:
                Log.w(TAG, "Unknown mood '" + mood + "', using neutral track");
                return TRACK_QUEST_NEUTRAL;
        }
    }

    /**
     * Start quest music and save the current track to restore later
     */
    public static synchronized void startQuestMusic(Context context, String mood) {
        // Save what's currently playing before switching to quest music
        if (currentTrack != -1 && !isQuestTrack(currentTrack)) {
            previousTrack = currentTrack;
            // Save current playback position
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                previousPosition = mediaPlayer.getCurrentPosition();
                Log.d(TAG, "Saved previous track: " + previousTrack + " at position: " + previousPosition);
            }
        }

        int questTrack = getQuestTrackForMood(mood);

        // Check if the quest track exists, if not use background music as fallback
        if (!resourceExists(context, questTrack)) {
            Log.w(TAG, "Quest track " + questTrack + " not found, using background music as fallback");
            questTrack = TRACK_BACKGROUND;
        }

        startMusic(context, questTrack);
    }

    /**
     * Restore the music that was playing before the quest
     */
    public static synchronized void restorePreQuestMusic(Context context) {
        if (previousTrack != -1) {
            Log.d(TAG, "Restoring pre-quest track: " + previousTrack + " at position: " + previousPosition);
            startMusicAtPosition(context, previousTrack, previousPosition);
            previousTrack = -1;
            previousPosition = 0;
        } else {
            Log.d(TAG, "No previous track saved, starting background music");
            startMusic(context, TRACK_BACKGROUND);
        }
    }

    /**
     * Start music at a specific position (for seamless restoration)
     */
    private static synchronized void startMusicAtPosition(Context context, int trackResId, int position) {
        if (!isMusicEnabled) {
            Log.d(TAG, "Music is disabled, not starting");
            return;
        }

        try {
            // Stop current music
            if (isInitialized) {
                stopMusicInternal();
            }

            Log.d(TAG, "Creating MediaPlayer for track: " + trackResId + " at position: " + position);

            mediaPlayer = MediaPlayer.create(
                    context.getApplicationContext(),
                    trackResId
            );

            if (mediaPlayer == null) {
                Log.e(TAG, "Failed to create MediaPlayer for track: " + trackResId);
                return;
            }

            Log.d(TAG, "MediaPlayer created successfully");

            // Loop forever
            mediaPlayer.setLooping(true);

            // Set consistent volume for all tracks
            mediaPlayer.setVolume(MUSIC_VOLUME, MUSIC_VOLUME);
            Log.d(TAG, "Volume set to: " + MUSIC_VOLUME);

            // Seek to saved position
            if (position > 0 && position < mediaPlayer.getDuration()) {
                mediaPlayer.seekTo(position);
                Log.d(TAG, "Seeked to position: " + position);
            }

            currentTrack = trackResId;
            isInitialized = true;

            mediaPlayer.start();
            Log.d(TAG, "Music started for track: " + trackResId);

        } catch (Exception e) {
            Log.e(TAG, "Error starting music at position: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Initialize and start music with specific track
     */
    public static synchronized void startMusic(Context context, int trackResId) {

        if (!isMusicEnabled) {
            Log.d(TAG, "Music is disabled, not starting");
            return;
        }

        try {
            // If same track is already playing, do nothing
            if (isInitialized && currentTrack == trackResId && mediaPlayer != null && mediaPlayer.isPlaying()) {
                Log.d(TAG, "Same track already playing: " + trackResId);
                return;
            }

            // Stop current music if different track
            if (isInitialized && currentTrack != trackResId) {
                Log.d(TAG, "Switching from track " + currentTrack + " to " + trackResId);
                stopMusicInternal();
            }

            if (!isInitialized || currentTrack != trackResId) {

                Log.d(TAG, "Creating MediaPlayer for track: " + trackResId);

                mediaPlayer = MediaPlayer.create(
                        context.getApplicationContext(),
                        trackResId
                );

                if (mediaPlayer == null) {
                    Log.e(TAG, "Failed to create MediaPlayer for track: " + trackResId);
                    return;
                }

                Log.d(TAG, "MediaPlayer created successfully");

                // Loop forever
                mediaPlayer.setLooping(true);

                // Set consistent volume for all tracks
                mediaPlayer.setVolume(MUSIC_VOLUME, MUSIC_VOLUME);
                Log.d(TAG, "Volume set to: " + MUSIC_VOLUME);

                // Extra safety
                mediaPlayer.setOnCompletionListener(mp -> {
                    mp.seekTo(0);
                    mp.start();
                });

                currentTrack = trackResId;
                isInitialized = true;
            }

            if (!mediaPlayer.isPlaying()) {
                mediaPlayer.start();
                Log.d(TAG, "Music started for track: " + trackResId);
            }

        } catch (Exception e) {
            Log.e(TAG, "Error starting music: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Start default background music
     */
    public static synchronized void startMusic(Context context) {
        Log.d(TAG, "Starting default background music");
        startMusic(context, TRACK_BACKGROUND);
    }

    /**
     * Pause music
     */
    public static synchronized void pauseMusic() {
        try {
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                Log.d(TAG, "Music paused");
            }
        } catch (Exception e) {
            Log.e(TAG, "Error pausing music: " + e.getMessage());
        }
    }

    /**
     * Resume music
     */
    public static synchronized void resumeMusic() {
        try {
            if (mediaPlayer != null && !mediaPlayer.isPlaying() && isMusicEnabled) {
                mediaPlayer.start();
                Log.d(TAG, "Music resumed");
            }
        } catch (Exception e) {
            Log.e(TAG, "Error resuming music: " + e.getMessage());
        }
    }

    /**
     * Internal method to stop music without resetting flags
     */
    private static synchronized void stopMusicInternal() {
        try {
            if (mediaPlayer != null) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
                mediaPlayer.release();
                mediaPlayer = null;
                isInitialized = false;
                Log.d(TAG, "Music stopped internally");
            }
        } catch (Exception e) {
            Log.e(TAG, "Error stopping music internally: " + e.getMessage());
        }
    }

    /**
     * Stop fully (when app exits or goes to background)
     */
    public static synchronized void stopMusic() {
        stopMusicInternal();
        currentTrack = -1;
        previousTrack = -1;
        previousPosition = 0;
        Log.d(TAG, "Music stopped completely");
    }

    /**
     * Enable / Disable music
     */
    public static void setMusicEnabled(boolean enabled) {
        isMusicEnabled = enabled;
        Log.d(TAG, "Music enabled: " + enabled);

        if (!enabled) {
            pauseMusic();
        } else {
            resumeMusic();
        }
    }

    public static boolean isMusicEnabled() {
        return isMusicEnabled;
    }

    /**
     * Check if playing
     */
    public static boolean isPlaying() {
        return mediaPlayer != null && mediaPlayer.isPlaying();
    }

    /**
     * Get current track
     */
    public static int getCurrentTrack() {
        return currentTrack;
    }

    /**
     * Set volume dynamically (optional - for future use)
     * @param volume Volume level from 0.0f (silent) to 1.0f (full)
     */
    public static synchronized void setVolume(float volume) {
        if (mediaPlayer != null) {
            float clampedVolume = Math.max(0.0f, Math.min(1.0f, volume));
            mediaPlayer.setVolume(clampedVolume, clampedVolume);
            Log.d(TAG, "Volume changed to: " + clampedVolume);
        }
    }
}