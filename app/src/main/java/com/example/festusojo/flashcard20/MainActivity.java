package com.example.festusojo.flashcard20;

import android.content.Intent;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    FlashcardDatabase flashcardDatabase;
    List<Flashcard> allFlashcards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //check if there is any data in our database to be displayed.
        flashcardDatabase = new FlashcardDatabase(getApplicationContext());
        allFlashcards = flashcardDatabase.getAllCards();
        //get input from user
        if(allFlashcards != null && allFlashcards.size() > 0) {
            ((TextView) findViewById(R.id.userQuestion)).setText(allFlashcards.get(0).getQuestion());
            ((TextView) findViewById(R.id.userAnswer)).setText(allFlashcards.get(0).getAnswer());
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.PlusButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                startActivityForResult(intent, 100);
            }
        });

        findViewById(R.id.Next).setOnClickListener(new View.OnClickListener() {
           @Override
                public void onClick(View v) {
                    findViewById(R.id.userQuestion).setVisibility(View.VISIBLE);
                    findViewById(R.id.userAnswer).setVisibility(View.INVISIBLE);
                }
            });
        };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent myIntent) {
        if (requestCode == 100) { // this 100 needs to match the 100 we used when we called startActivityForResult!
            String stringAnswer = myIntent.getExtras().getString("stringAnswer"); // 'string1' needs to match the key we used when we put the string in the Intent
            String stringQuestion = myIntent.getExtras().getString("stringQuestion");

            //also need to save that data into our database
            flashcardDatabase.insertCard(new Flashcard(stringQuestion, stringAnswer));

            allFlashcards = flashcardDatabase.getAllCards();
        }
    }
}
