package com.st00.afir.mwk;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class FamilyActivity extends AppCompatActivity {

    private static String LOG_TAG = NumbersActivity.class.getSimpleName();

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
    private String[] splitEnglishMembers, splitMiwokMembers;
    private List<Word> listOfMembers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all);

        splitEnglishMembers = englishFamilyMembers.split(";");
        splitMiwokMembers = miwokFamilyMembers.split("\n");

        listOfMembers.add(new Word(splitEnglishMembers[0], splitMiwokMembers[0]));
        listOfMembers.add(new Word(splitEnglishMembers[1], splitMiwokMembers[1]));
        listOfMembers.add(new Word(splitEnglishMembers[2], splitMiwokMembers[2]));
        listOfMembers.add(new Word(splitEnglishMembers[3], splitMiwokMembers[3]));
        listOfMembers.add(new Word(splitEnglishMembers[4], splitMiwokMembers[4]));
        listOfMembers.add(new Word(splitEnglishMembers[5], splitMiwokMembers[5]));
        listOfMembers.add(new Word(splitEnglishMembers[6], splitMiwokMembers[6]));
        listOfMembers.add(new Word(splitEnglishMembers[7], splitMiwokMembers[7]));
        listOfMembers.add(new Word(splitEnglishMembers[8], splitMiwokMembers[8]));
        listOfMembers.add(new Word(splitEnglishMembers[9], splitMiwokMembers[9]));

        WordAdapter adapter = new WordAdapter(this, listOfMembers);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
    }
}
