package com.st00.afir.mwk;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class NumbersActivity extends AppCompatActivity {

    private static String LOG_TAG = NumbersActivity.class.getSimpleName();

    public static String englishNumbers = "one two three four five six seven eight nine ten";
    public static String miwokNumbers = "lutti otiiko tolookosu oyyisa massokka temmokka kenekaku kawinta wo’e na’aacha";
    public String[] splitEnglishNumbers, splitMiwokNumbers;
    List<Word> listOfWords = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        splitEnglishNumbers = englishNumbers.split(" ");
        splitMiwokNumbers = miwokNumbers.split(" ");

        for(int i = 0; i< splitEnglishNumbers.length ; ++i) {
            listOfWords.add(new Word(splitEnglishNumbers[i],splitMiwokNumbers[i]));
        }

        WordAdapter adapter = new WordAdapter(this, listOfWords);
        ListView myListView = (ListView)findViewById(R.id.rootViewNumbers);
        myListView.setAdapter(adapter);

    }
}
