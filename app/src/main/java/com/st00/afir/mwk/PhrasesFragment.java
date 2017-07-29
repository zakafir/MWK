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
public class PhrasesFragment extends Fragment {

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
    private String[] splitEnglishPhrases, splitMiwokPhrases;
    private List<Word> listOfPhrases = new ArrayList<>();

    public PhrasesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_all, container, false);
        //audioManager to request audio focus
        audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        splitEnglishPhrases = englishPhrases.split("\n");
        splitMiwokPhrases = miwokPhrases.split("\n");

        listOfPhrases.add(new Word(splitEnglishPhrases[0], splitMiwokPhrases[0], R.raw.phrase_where_are_you_going));
        listOfPhrases.add(new Word(splitEnglishPhrases[1], splitMiwokPhrases[1], R.raw.phrase_what_is_your_name));
        listOfPhrases.add(new Word(splitEnglishPhrases[2], splitMiwokPhrases[2], R.raw.phrase_my_name_is));
        listOfPhrases.add(new Word(splitEnglishPhrases[3], splitMiwokPhrases[3], R.raw.phrase_how_are_you_feeling));
        listOfPhrases.add(new Word(splitEnglishPhrases[4], splitMiwokPhrases[4], R.raw.phrase_im_feeling_good));
        listOfPhrases.add(new Word(splitEnglishPhrases[5], splitMiwokPhrases[5], R.raw.phrase_are_you_coming));
        listOfPhrases.add(new Word(splitEnglishPhrases[6], splitMiwokPhrases[6], R.raw.phrase_yes_im_coming));
        listOfPhrases.add(new Word(splitEnglishPhrases[7], splitMiwokPhrases[7], R.raw.phrase_im_coming));
        listOfPhrases.add(new Word(splitEnglishPhrases[8], splitMiwokPhrases[8], R.raw.phrase_lets_go));
        listOfPhrases.add(new Word(splitEnglishPhrases[9], splitMiwokPhrases[9], R.raw.phrase_come_here));

        WordAdapter adapter = new WordAdapter(getActivity(), listOfPhrases, R.color.categoryPhrases);
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Release the media player if it currently exists because we are about to
                // play a different sound file
                releaseMediaPlayer();

                // Get the {@link Word} object at the given position the user clicked on
                Word word = listOfPhrases.get(i);

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
