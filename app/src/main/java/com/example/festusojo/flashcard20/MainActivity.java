package com.example.festusojo.flashcard20;

import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.Flashcard_Answer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.Flashcard_Answer).setVisibility(View.VISIBLE);
                findViewById(R.id.correct_answer).setVisibility(View.INVISIBLE);
            }
        }

        findViewById(R.id.Flashcard_Answer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.AddCardActivity.setVisibility(View.INVISIBLE);
                findViewById(R.id.MainActivity).setVisibility(View.VISIBLE);
            }
        }

        findViewById(R.id.Flashcard_Answer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(EditText.this, EditText.class).setVisibility(View.VISIBLE);
                Intent i = new Intent(ActivityOne.this, ActivityTwo.class);
                startActivity(i);
            }
        }
        );
    }
}
