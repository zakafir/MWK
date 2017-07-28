package com.st00.afir.mwk;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class PhrasesActivity extends AppCompatActivity {

    private static String LOG_TAG = NumbersActivity.class.getSimpleName();
    private MediaPlayer mMediaPlayer;
    private MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };


    private static String englishPhrases = "Where are you going?\n" +
            "What is your name?\n" +
            "My name is...\n" +
            "How are you feeling?\n" +
            "I’m feeling good.\n" +
            "Are you coming\n" +
            "Yes, I’m coming.\n" +
            "I’m coming.\n" +
            "Let’s go.\n" +
            "Come here.";

    private static String miwokPhrases = "minto wuksus\n" +
            "tinnә oyaase'nә\n" +
            "oyaaset...\n" +
            "michәksәs?\n" +
            "kuchi achit\n" +
            "әәnәs'aa?\n" +
            "hәә’ әәnәm\n" +
            "әәnәm\n" +
            "yoowutis\n" +
            "әnni'nem";

    private String[] splitEnglishPhrases, splitMiwokPhrases;
    private List<Word> listOfPhrases = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all);

        splitEnglishPhrases = englishPhrases.split("\n");
        splitMiwokPhrases = miwokPhrases.split("\n");

        listOfPhrases.add(new Word(splitEnglishPhrases[0], splitMiwokPhrases[0],R.raw.phrase_where_are_you_going));
        listOfPhrases.add(new Word(splitEnglishPhrases[1], splitMiwokPhrases[1],R.raw.phrase_what_is_your_name));
        listOfPhrases.add(new Word(splitEnglishPhrases[2], splitMiwokPhrases[2],R.raw.phrase_my_name_is));
        listOfPhrases.add(new Word(splitEnglishPhrases[3], splitMiwokPhrases[3],R.raw.phrase_how_are_you_feeling));
        listOfPhrases.add(new Word(splitEnglishPhrases[4], splitMiwokPhrases[4],R.raw.phrase_im_feeling_good));
        listOfPhrases.add(new Word(splitEnglishPhrases[5], splitMiwokPhrases[5],R.raw.phrase_are_you_coming));
        listOfPhrases.add(new Word(splitEnglishPhrases[6], splitMiwokPhrases[6],R.raw.phrase_yes_im_coming));
        listOfPhrases.add(new Word(splitEnglishPhrases[7], splitMiwokPhrases[7],R.raw.phrase_im_coming));
        listOfPhrases.add(new Word(splitEnglishPhrases[8], splitMiwokPhrases[8],R.raw.phrase_lets_go));
        listOfPhrases.add(new Word(splitEnglishPhrases[9], splitMiwokPhrases[9],R.raw.phrase_come_here));

        WordAdapter adapter = new WordAdapter(this, listOfPhrases,R.color.categoryPhrases);
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
        }
    }
}
