package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FillQuiz extends AppCompatActivity {

    private Button button;
    private EditText editText;
    private Toolbar toolbar;

    private int questionCounter;
    private int questionCountTotal;
    private int score;

    private List<CardInfo> questionList;
    private CardInfo currentQuestion;

    private TextView textViewQuestion;

    ArrayList<String> word = new ArrayList<String>();
    ArrayList<String> definition = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_quiz);

        toolbar = findViewById(R.id.quiz_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Điền từ");

        textViewQuestion = findViewById(R.id.question_view);
        editText = findViewById(R.id.answer_view);

        CardDbHelper dbHelper = new CardDbHelper(this);
        questionList = dbHelper.getAllCard();
        questionCountTotal = questionList.size();
        Collections.shuffle(questionList);

        showNextQuestion();

        button = findViewById(R.id.next_question);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });
    }

    private void showNextQuestion() {
        if (questionCounter < questionCountTotal){

            currentQuestion = questionList.get(questionCounter);
            textViewQuestion.setText(currentQuestion.getWord());
            questionCounter++;

        } else {
            finishQuiz();
        }
    }

    private void checkAnswer() {
        String ans = editText.getText().toString();
        String rightAns = currentQuestion.getDefinition();
        if (ans.equals(rightAns)){
            score++;
        } else {

            String q = currentQuestion.getWord();
            word.add(q);

            String def = currentQuestion.getDefinition();
            definition.add(def);
        }
        editText.setText(null);
        showNextQuestion();
    }

    private void finishQuiz() {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);

        View view = getLayoutInflater().inflate(R.layout.result_dialog, null);

        Button button = view.findViewById(R.id.show_result);

        builder.setCancelable(false)
                .setTitle("Kết quả")
                .setView(view)
                .setBackground(getResources().getDrawable(R.drawable.add_new_dialog, null))
                .setMessage("Bạn trả lời đúng " + score + " trên " + questionCountTotal + " câu")
                .setPositiveButton("OK", (dialog, which) -> finish())
                .show();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openResult();
            }
        });
    }

    private void openResult() {
        Intent intent = new Intent(FillQuiz.this, ChoiceResult.class);
        Bundle args = new Bundle();
        Bundle args1 = new Bundle();
        args.putSerializable("ARRAYLIST", (Serializable) word);
        args1.putSerializable("ARRAYLIST2", (Serializable) definition);
        intent.putExtra("BUNDLE", args);
        intent.putExtra("BUNDLE1", args1);
        startActivity(intent);
    }
}