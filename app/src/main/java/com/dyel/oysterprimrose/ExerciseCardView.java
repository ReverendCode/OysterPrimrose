package com.dyel.oysterprimrose;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.ViewGroup;
import android.widget.TextView;

public class ExerciseCardView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_card_view);
        //TODO: Extracts API data from EXTRA_MESSAGE;
        Intent intent = getIntent();
//        String message = intent.getStringExtra(addChangeExercise.EXTRA_MESSAGE);
        TextView textview = new TextView(this);
//        textview.setTextSize();
//        textview.setText(message);

//        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_display_message);
//        layout.addView(textview);
    }
    //TODO: Display API information to include the following:
    /*
    Data to be put into DB
    Title
    Image
    Description
    Comments (optional)
    Equipment
    Muscle Group
    * */
}
