package com.st00.afir.mwk;

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
    private MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
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
                Word currentWord = (Word) adapterView.getItemAtPosition(i);
                releaseMediaPlayer();
                mMediaPlayer = MediaPlayer.create(getApplicationContext(),currentWord.getAudio());
                mMediaPlayer.start();
                mMediaPlayer.setOnCompletionListener(mOnCompletionListener);
            }
        });
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
        }
    }
}
