package com.st00.afir.mwk;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class NumbersActivity extends AppCompatActivity {

    private static String LOG_TAG = NumbersActivity.class.getSimpleName();

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

        for(int i = 0; i< splitEnglishNumbers.length ; ++i) {
            listOfNumbers.add(new Word(splitEnglishNumbers[i],splitMiwokNumbers[i]));
        }

        WordAdapter adapter = new WordAdapter(this, listOfNumbers);
        ListView myListView = (ListView)findViewById(R.id.list);
        myListView.setAdapter(adapter);

    }
}
