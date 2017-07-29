package com.st00.afir.mwk;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FamilyFragment extends Fragment {

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
    private MediaPlayer mMediaPlayer;
    private AudioManager audioManager;
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
    private MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };
    private String[] splitEnglishMembers, splitMiwokMembers;
    private List<Word> listOfMembers = new ArrayList<>();

    public FamilyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_all, container, false);
        //audioManager to request audio focus
        audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        splitEnglishMembers = englishFamilyMembers.split(";");
        splitMiwokMembers = miwokFamilyMembers.split("\n");

        listOfMembers.add(new Word(splitEnglishMembers[0], splitMiwokMembers[0], R.drawable.family_father, R.raw.family_father));
        listOfMembers.add(new Word(splitEnglishMembers[1], splitMiwokMembers[1], R.drawable.family_mother, R.raw.family_mother));
        listOfMembers.add(new Word(splitEnglishMembers[2], splitMiwokMembers[2], R.drawable.family_son, R.raw.family_son));
        listOfMembers.add(new Word(splitEnglishMembers[3], splitMiwokMembers[3], R.drawable.family_daughter, R.raw.family_daughter));
        listOfMembers.add(new Word(splitEnglishMembers[4], splitMiwokMembers[4], R.drawable.family_older_brother, R.raw.family_older_brother));
        listOfMembers.add(new Word(splitEnglishMembers[5], splitMiwokMembers[5], R.drawable.family_younger_brother, R.raw.family_younger_brother));
        listOfMembers.add(new Word(splitEnglishMembers[6], splitMiwokMembers[6], R.drawable.family_older_sister, R.raw.family_older_sister));
        listOfMembers.add(new Word(splitEnglishMembers[7], splitMiwokMembers[7], R.drawable.family_younger_sister, R.raw.family_younger_sister));
        listOfMembers.add(new Word(splitEnglishMembers[8], splitMiwokMembers[8], R.drawable.family_grandmother, R.raw.family_grandmother));
        listOfMembers.add(new Word(splitEnglishMembers[9], splitMiwokMembers[9], R.drawable.family_grandfather, R.raw.family_grandfather));

        WordAdapter adapter = new WordAdapter(getActivity(), listOfMembers, R.color.categoryFamily);
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Release the media player if it currently exists because we are about to
                // play a different sound file
                releaseMediaPlayer();

                // Get the {@link Word} object at the given position the user clicked on
                Word word = listOfMembers.get(i);

                // Request audio focus so in order to play the audio file. The app needs to play a
                // short audio file, so we will request audio focus with a short amount of time
                // with AUDIOFOCUS_GAIN_TRANSIENT.
                int result = audioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // We have audio focus now.

                    // Create and setup the {@link MediaPlayer} for the audio resource associated
                    // with the current word
                    mMediaPlayer = MediaPlayer.create(getActivity(), word.getAudio());

                    // Start the audio file
                    mMediaPlayer.start();

                    // Setup a listener on the media player, so that we can stop and release the
                    // media player once the sound has finished playing.
                    mMediaPlayer.setOnCompletionListener(mOnCompletionListener);
                }
            }
        });

        return rootView;
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

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}
