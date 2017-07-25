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

        listOfMembers.add(new Word(splitEnglishMembers[0], splitMiwokMembers[0],R.drawable.family_father));
        listOfMembers.add(new Word(splitEnglishMembers[1], splitMiwokMembers[1],R.drawable.family_mother));
        listOfMembers.add(new Word(splitEnglishMembers[2], splitMiwokMembers[2],R.drawable.family_son));
        listOfMembers.add(new Word(splitEnglishMembers[3], splitMiwokMembers[3],R.drawable.family_daughter));
        listOfMembers.add(new Word(splitEnglishMembers[4], splitMiwokMembers[4],R.drawable.family_older_brother));
        listOfMembers.add(new Word(splitEnglishMembers[5], splitMiwokMembers[5],R.drawable.family_younger_brother));
        listOfMembers.add(new Word(splitEnglishMembers[6], splitMiwokMembers[6],R.drawable.family_older_sister));
        listOfMembers.add(new Word(splitEnglishMembers[7], splitMiwokMembers[7],R.drawable.family_younger_sister));
        listOfMembers.add(new Word(splitEnglishMembers[8], splitMiwokMembers[8],R.drawable.family_grandmother));
        listOfMembers.add(new Word(splitEnglishMembers[9], splitMiwokMembers[9],R.drawable.family_grandfather));

        WordAdapter adapter = new WordAdapter(this, listOfMembers);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
    }
}
