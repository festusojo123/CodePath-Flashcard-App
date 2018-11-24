package com.example.festusojo.flashcard20;

import android.animation.Animator;
import android.app.MediaRouteButton;
import android.content.Intent;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import static android.view.ViewAnimationUtils.*;

public class MainActivity extends AppCompatActivity {

    FlashcardDatabase flashcardDatabase;
    List<Flashcard> allFlashcards;
    private MediaRouteButton questionSideView;

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
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });

        findViewById(R.id.Next).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
           findViewById(R.id.userQuestion).setVisibility(View.VISIBLE);
           findViewById(R.id.userAnswer).setVisibility(View.INVISIBLE);
           final Animation leftOutAnim = AnimationUtils.loadAnimation(v.getContext(), R.anim.left_out);
           final Animation rightInAnim = AnimationUtils.loadAnimation(v.getContext(), R.anim.right_in);

               leftOutAnim.setAnimationListener(new Animation.AnimationListener() {
                   @Override
                   public void onAnimationStart(Animation animation) {
                       // this method is called when the animation first starts
                       findViewById(R.id.userQuestion).startAnimation(leftOutAnim);
                   }

                   @Override
                   public void onAnimationEnd(Animation animation) {
                       findViewById(R.id.userQuestion).startAnimation(rightInAnim);
                   }

                   @Override
                   public void onAnimationRepeat(Animation animation) {
                       // we don't need to worry about this method
                   }
               });
           }
            });

        View answerSideView = findViewById(R.id.userAnswer);

        // get the center for the clipping circle
        int cx = answerSideView.getWidth() / 2;
        int cy = answerSideView.getHeight() / 2;

        // get the final radius for the clipping circle
        float finalRadius = (float) Math.hypot(cx, cy);

        // create the animator for this view (the start radius is zero)
        Animator anim = createCircularReveal(answerSideView, cx, cy, 0f, finalRadius);

        // hide the question and show the answer to prepare for playing the animation!
        questionSideView.setVisibility(View.INVISIBLE);
        answerSideView.setVisibility(View.VISIBLE);

        anim.setDuration(3000);
        anim.start();
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
