package com.st00.afir.mwk;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class PhrasesActivity extends AppCompatActivity {

    private static String LOG_TAG = NumbersActivity.class.getSimpleName();


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

        for (int i = 0; i < splitEnglishPhrases.length; ++i) {
            listOfPhrases.add(new Word(splitEnglishPhrases[i], splitMiwokPhrases[i]));
        }

        WordAdapter adapter = new WordAdapter(this, listOfPhrases,R.color.categoryPhrases);
        ListView myListView = (ListView) findViewById(R.id.list);
        myListView.setAdapter(adapter);

    }
}
