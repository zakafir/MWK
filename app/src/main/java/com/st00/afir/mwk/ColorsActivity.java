package com.st00.afir.mwk;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ColorsActivity extends AppCompatActivity {

    private static String LOG_TAG = NumbersActivity.class.getSimpleName();
    private MediaPlayer mMediaPlayer;
    private AudioManager audioManager;
    private MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int i) {
            if (i == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    i == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                // The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a
                // short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
                // our app is allowed to continue playing sound but at a lower volume. We'll treat
                // both cases the same way because our app is playing short sound files.

                // Pause playback and reset player to the start of the file. That way, we can
                // play the word from the beginning when we resume playback.
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            } else if (i == AudioManager.AUDIOFOCUS_GAIN) {
                // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
                mMediaPlayer.start();
            } else if (i == AudioManager.AUDIOFOCUS_LOSS) {
                // The AUDIOFOCUS_LOSS case means we've lost audio focus and
                // Stop playback and clean up resources
                releaseMediaPlayer();
            }
        }
    };

    private static String englishColors = "red;green;brown;gray;black;white;dusty yellow;mustard yellow";
    private static String miwokColors = "weṭeṭṭi chokokki ṭakaakki ṭopoppi kululli kelelli ṭopiisә chiwiiṭә";
    private String[] splitEnglishColors, splitMiwokColors;
    private List<Word> listOfColors = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all);

        //audioManager to request audio focus
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        splitEnglishColors = englishColors.split(";");
        splitMiwokColors = miwokColors.split(" ");

        listOfColors.add(new Word(splitEnglishColors[0], splitMiwokColors[0],R.drawable.color_red,R.raw.color_red));
        listOfColors.add(new Word(splitEnglishColors[1], splitMiwokColors[1],R.drawable.color_green,R.raw.color_green));
        listOfColors.add(new Word(splitEnglishColors[2], splitMiwokColors[2],R.drawable.color_brown,R.raw.color_brown));
        listOfColors.add(new Word(splitEnglishColors[3], splitMiwokColors[3],R.drawable.color_gray,R.raw.color_gray));
        listOfColors.add(new Word(splitEnglishColors[4], splitMiwokColors[4],R.drawable.color_black,R.raw.color_black));
        listOfColors.add(new Word(splitEnglishColors[5], splitMiwokColors[5],R.drawable.color_white,R.raw.color_white));
        listOfColors.add(new Word(splitEnglishColors[6], splitMiwokColors[6],R.drawable.color_dusty_yellow,R.raw.color_dusty_yellow));
        listOfColors.add(new Word(splitEnglishColors[7], splitMiwokColors[7],R.drawable.color_mustard_yellow,R.raw.color_mustard_yellow));


        WordAdapter adapter = new WordAdapter(this,listOfColors,R.color.categoryColors);
        ListView listView = (ListView)findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Release the media player if it currently exists because we are about to
                // play a different sound file
                releaseMediaPlayer();

                // Get the {@link Word} object at the given position the user clicked on
                Word word = listOfColors.get(i);

                // Request audio focus so in order to play the audio file. The app needs to play a
                // short audio file, so we will request audio focus with a short amount of time
                // with AUDIOFOCUS_GAIN_TRANSIENT.
                int result = audioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // We have audio focus now.

                    // Create and setup the {@link MediaPlayer} for the audio resource associated
                    // with the current word
                    mMediaPlayer = MediaPlayer.create(ColorsActivity.this, word.getAudio());

                    // Start the audio file
                    mMediaPlayer.start();

                    // Setup a listener on the media player, so that we can stop and release the
                    // media player once the sound has finished playing.
                    mMediaPlayer.setOnCompletionListener(mOnCompletionListener);
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
            // Regardless of whether or not we were granted audio focus, abandon it. This also
            // unregisters the AudioFocusChangeListener so we don't get anymore callbacks.
            audioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}
