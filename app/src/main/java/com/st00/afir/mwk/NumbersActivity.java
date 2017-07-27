package com.st00.afir.mwk;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class NumbersActivity extends AppCompatActivity {

    private static String LOG_TAG = NumbersActivity.class.getSimpleName();
    private MediaPlayer mMediaPlayer;
    private MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    private static String englishNumbers = "one two three four five six seven eight nine ten";
    private static String miwokNumbers = "lutti otiiko tolookosu oyyisa massokka temmokka kenekaku kawinta wo’e na’aacha";
    private String[] splitEnglishNumbers, splitMiwokNumbers;
    private List<Word> listOfNumbers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all);

        splitEnglishNumbers = englishNumbers.split(" ");
        splitMiwokNumbers = miwokNumbers.split(" ");

        //here we deleted the for loop, because we need to add images to each item
        listOfNumbers.add(new Word(splitEnglishNumbers[0], splitMiwokNumbers[0], R.drawable.number_one,R.raw.number_one));
        listOfNumbers.add(new Word(splitEnglishNumbers[1], splitMiwokNumbers[1], R.drawable.number_two,R.raw.number_two));
        listOfNumbers.add(new Word(splitEnglishNumbers[2], splitMiwokNumbers[2], R.drawable.number_three,R.raw.number_three));
        listOfNumbers.add(new Word(splitEnglishNumbers[3], splitMiwokNumbers[3], R.drawable.number_four,R.raw.number_four));
        listOfNumbers.add(new Word(splitEnglishNumbers[4], splitMiwokNumbers[4], R.drawable.number_five,R.raw.number_five));
        listOfNumbers.add(new Word(splitEnglishNumbers[5], splitMiwokNumbers[5], R.drawable.number_six,R.raw.number_six));
        listOfNumbers.add(new Word(splitEnglishNumbers[6], splitMiwokNumbers[6], R.drawable.number_seven,R.raw.number_seven));
        listOfNumbers.add(new Word(splitEnglishNumbers[7], splitMiwokNumbers[7], R.drawable.number_eight,R.raw.number_eight));
        listOfNumbers.add(new Word(splitEnglishNumbers[8], splitMiwokNumbers[8], R.drawable.number_nine,R.raw.number_nine));
        listOfNumbers.add(new Word(splitEnglishNumbers[9], splitMiwokNumbers[9], R.drawable.number_ten,R.raw.number_ten));


        WordAdapter adapter = new WordAdapter(this, listOfNumbers,R.color.categoryNumbers);
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
