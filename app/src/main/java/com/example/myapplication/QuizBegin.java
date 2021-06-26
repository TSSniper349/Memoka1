package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.widget.Toolbar;

public class QuizBegin extends AppCompatActivity {

    private Toolbar toolbar;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_begin);

        toolbar = findViewById(R.id.choice_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Trắc nghiệm");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        button = findViewById(R.id.start_choice);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz();
            }
        });

    }

    private void startQuiz() {
        Intent intent = new Intent(QuizBegin.this,ChoiceQuizActivity.class);
        startActivity(intent);
    }

}