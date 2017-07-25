package com.st00.afir.mwk;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ColorsActivity extends AppCompatActivity {

    private static String LOG_TAG = NumbersActivity.class.getSimpleName();


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

        listOfColors.add(new Word(splitEnglishColors[0], splitMiwokColors[0]));
        listOfColors.add(new Word(splitEnglishColors[1], splitMiwokColors[1]));
        listOfColors.add(new Word(splitEnglishColors[2], splitMiwokColors[2]));
        listOfColors.add(new Word(splitEnglishColors[3], splitMiwokColors[3]));
        listOfColors.add(new Word(splitEnglishColors[4], splitMiwokColors[4]));
        listOfColors.add(new Word(splitEnglishColors[5], splitMiwokColors[5]));
        listOfColors.add(new Word(splitEnglishColors[6], splitMiwokColors[6]));
        listOfColors.add(new Word(splitEnglishColors[7], splitMiwokColors[7]));


        WordAdapter adapter = new WordAdapter(this,listOfColors);
        ListView listView = (ListView)findViewById(R.id.list);
        listView.setAdapter(adapter);
    }
}
