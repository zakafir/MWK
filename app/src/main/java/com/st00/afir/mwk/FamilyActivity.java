package com.st00.afir.mwk;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class FamilyActivity extends AppCompatActivity {

    private static String LOG_TAG = NumbersActivity.class.getSimpleName();
    private MediaPlayer mMediaPlayer;
    private MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    private static String englishFamilyMembers = "father;mother;son;daughter;older brother;" +
            "younger brother;older sister;younger sister;grandmother;grandfather";
    private static String miwokFamilyMembers = "әpә\n" +
            "әṭa\n" +
            "angsi\n" +
            "tune\n" +
            "taachi\n" +
            "chalitti\n" +
            "teṭe\n" +
            "kolliti\n" +
            "ama\n" +
            "paapa\n";
    private String[] splitEnglishMembers, splitMiwokMembers;
    private List<Word> listOfMembers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all);

        splitEnglishMembers = englishFamilyMembers.split(";");
        splitMiwokMembers = miwokFamilyMembers.split("\n");

        listOfMembers.add(new Word(splitEnglishMembers[0], splitMiwokMembers[0],R.drawable.family_father,R.raw.family_father));
        listOfMembers.add(new Word(splitEnglishMembers[1], splitMiwokMembers[1],R.drawable.family_mother,R.raw.family_mother));
        listOfMembers.add(new Word(splitEnglishMembers[2], splitMiwokMembers[2],R.drawable.family_son,R.raw.family_son));
        listOfMembers.add(new Word(splitEnglishMembers[3], splitMiwokMembers[3],R.drawable.family_daughter,R.raw.family_daughter));
        listOfMembers.add(new Word(splitEnglishMembers[4], splitMiwokMembers[4],R.drawable.family_older_brother,R.raw.family_older_brother));
        listOfMembers.add(new Word(splitEnglishMembers[5], splitMiwokMembers[5],R.drawable.family_younger_brother,R.raw.family_younger_brother));
        listOfMembers.add(new Word(splitEnglishMembers[6], splitMiwokMembers[6],R.drawable.family_older_sister,R.raw.family_older_sister));
        listOfMembers.add(new Word(splitEnglishMembers[7], splitMiwokMembers[7],R.drawable.family_younger_sister,R.raw.family_younger_sister));
        listOfMembers.add(new Word(splitEnglishMembers[8], splitMiwokMembers[8],R.drawable.family_grandmother,R.raw.family_grandmother));
        listOfMembers.add(new Word(splitEnglishMembers[9], splitMiwokMembers[9],R.drawable.family_grandfather,R.raw.family_grandfather));

        WordAdapter adapter = new WordAdapter(this, listOfMembers,R.color.categoryFamily);
        ListView listView = (ListView) findViewById(R.id.list);
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
