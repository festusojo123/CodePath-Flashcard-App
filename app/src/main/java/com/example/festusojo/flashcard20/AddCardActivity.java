package com.example.festusojo.flashcard20;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        findViewById(R.id.PlusButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCardActivity.class)
                MainActivity.this.startActivity(intent);
            }
        })

        findViewById(R.id.Cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.SaveMe).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddCardActivity.this, MainActivity.class);
                AddCardActivity.this.startActivityForResult(intent, 100);

                Intent myIntent = new Intent(); // create a new Intent, this is where we will put our data
                myIntent.putExtra("stringAnswer", "((EditText) findViewById(R.id.userAnswer)).getText().toString()");
                myIntent.putExtra("stringQuestion", "((EditText) findViewById(R.id.userQuestion)).getText().toString()");
                setResult(RESULT_OK, myIntent); // set result code and bundle data for response
                finish();;
            }
        });
    }
}
