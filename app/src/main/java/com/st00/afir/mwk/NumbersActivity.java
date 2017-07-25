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

        //here we deleted the for loop, because we need to add images to each item
        listOfNumbers.add(new Word(splitEnglishNumbers[0], splitMiwokNumbers[0]));
        listOfNumbers.add(new Word(splitEnglishNumbers[1], splitMiwokNumbers[1]));
        listOfNumbers.add(new Word(splitEnglishNumbers[2], splitMiwokNumbers[2]));
        listOfNumbers.add(new Word(splitEnglishNumbers[3], splitMiwokNumbers[3]));
        listOfNumbers.add(new Word(splitEnglishNumbers[4], splitMiwokNumbers[4]));
        listOfNumbers.add(new Word(splitEnglishNumbers[5], splitMiwokNumbers[5]));
        listOfNumbers.add(new Word(splitEnglishNumbers[6], splitMiwokNumbers[6]));
        listOfNumbers.add(new Word(splitEnglishNumbers[7], splitMiwokNumbers[7]));
        listOfNumbers.add(new Word(splitEnglishNumbers[8], splitMiwokNumbers[8]));
        listOfNumbers.add(new Word(splitEnglishNumbers[9], splitMiwokNumbers[9]));


        WordAdapter adapter = new WordAdapter(this, listOfNumbers);
        ListView myListView = (ListView) findViewById(R.id.list);
        myListView.setAdapter(adapter);

    }
}
