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

        for (int i = 0; i < splitEnglishMembers.length; ++i) {
            listOfMembers.add(new Word(splitEnglishMembers[i], splitMiwokMembers[i]));
        }

        WordAdapter adapter = new WordAdapter(this, listOfMembers);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
    }
}
