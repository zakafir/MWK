package com.st00.afir.mwk;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class NumbersActivity extends AppCompatActivity {

    private static String LOG_TAG = NumbersActivity.class.getSimpleName();

    public static String englishNumbers = "one two three four five six seven eight nine ten";
    public static String miwokNumbers = "un deux trois quatre cinq six sept huit neuf dix";
    public String[] splitEnglishNumbers, splitMiwokNumbers;
    List<String> listOfEnglishNumbers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        splitEnglishNumbers = englishNumbers.split(" ");
        splitMiwokNumbers = miwokNumbers.split(" ");

        for(int i = 0; i< splitEnglishNumbers.length ; ++i) {
            listOfEnglishNumbers.add(""+i);
        }

    }
}
