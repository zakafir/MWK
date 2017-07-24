package com.st00.afir.mwk;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NumbersActivity extends AppCompatActivity {

    public static String numbers = "one two three four five six seven eight nine ten";
    public String[] splitNumbers;
    List<String> listOfEnglishNumbers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        splitNumbers = numbers.split(" ");
        //new way to add objects in a list
        Collections.addAll(listOfEnglishNumbers, splitNumbers);

        LinearLayout rootView = (LinearLayout)findViewById(R.id.rootViewNumbers);

        for(String number: listOfEnglishNumbers){
            //adding a TextView dynamically
            TextView wordView = new TextView(this);
            wordView.setText(number);
            rootView.addView(wordView);
        }

    }
}
