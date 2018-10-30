package com.example.festusojo.flashcard20;

import android.content.Intent;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

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
        });

        findViewById(R.id.PlusButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent myIntent) {
        if (requestCode == 100) { // this 100 needs to match the 100 we used when we called startActivityForResult!
            String string1 = myIntent.getExtras().getString("stringAnswer"); // 'string1' needs to match the key we used when we put the string in the Intent
            String string2 = myIntent.getExtras().getString("stringQuestion");
        }
    }
}
