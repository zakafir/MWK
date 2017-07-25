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

        for(int i = 0; i< splitEnglishColors.length ; ++i) {
            listOfColors.add(new Word(splitEnglishColors[i],splitMiwokColors[i]));
        }

        WordAdapter adapter = new WordAdapter(this,listOfColors);
        ListView listView = (ListView)findViewById(R.id.list);
        listView.setAdapter(adapter);
    }
}
