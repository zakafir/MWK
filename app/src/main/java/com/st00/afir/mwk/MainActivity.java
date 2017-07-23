package com.st00.afir.mwk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvFamily,tvPhrases,tvColors,tvNumbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvNumbers = (TextView)findViewById(R.id.numbers);
        tvColors = (TextView)findViewById(R.id.colors);
        tvPhrases = (TextView)findViewById(R.id.phrases);
        tvFamily = (TextView)findViewById(R.id.family_members);

        //button events
        tvNumbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, NumbersActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });

        tvColors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, ColorsActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });

        tvPhrases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, PhrasesActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });

        tvFamily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, FamilyActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });
    }
}
