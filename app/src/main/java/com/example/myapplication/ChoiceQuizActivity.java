package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class ChoiceQuizActivity extends AppCompatActivity {

    private static final long COUNTDOWN_IN_MILLIS = 15000;

    private TextView textViewQuestion;
    private TextView textViewQuestionCount;
    private TextView textViewCountdown;
    private RadioGroup rbGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private Button button;

    private ColorStateList textColorDefaultRb;
    private ColorStateList textColorDefaultCd;

    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;

    private int questionCounter;
    private int questionCountTotal;
    private Question currentQuestion;

    private int score;
    private boolean answered;

    private List<Question> questionList;

    private long backPressedTime;

    private Toolbar choiceToolbar;

    ArrayList<String> word = new ArrayList<String>();
    ArrayList<String> definition = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_quiz);

        choiceToolbar = findViewById(R.id.choice_quiz_toolbar);
        setSupportActionBar(choiceToolbar);
        getSupportActionBar().setTitle("Luyện tập");

        textViewQuestion = findViewById(R.id.text_view_question);
        textViewQuestionCount = findViewById(R.id.text_view_question_count);
        textViewCountdown = findViewById(R.id.text_view_countdown);
        rbGroup = findViewById(R.id.radio_group);
        rb1 = findViewById(R.id.radio_button1);
        rb2 = findViewById(R.id.radio_button2);
        rb3 = findViewById(R.id.radio_button3);

        textColorDefaultRb = rb1.getTextColors();
        textColorDefaultCd = textViewCountdown.getTextColors();

        QuizDbHelper dbHelper = new QuizDbHelper(this);
        questionList =dbHelper.getAllQuestions();
        questionCountTotal = questionList.size();
        Collections.shuffle(questionList);

        showNextQuestion();

        rb1.setOnClickListener(v -> {
            checkAnswer();
        });

        rb2.setOnClickListener(v -> {
            checkAnswer();
        });

        rb3.setOnClickListener(v -> {
            checkAnswer();
        });
    }

    private void showNextQuestion() {
        rb1.setTextColor(textColorDefaultRb);
        rb2.setTextColor(textColorDefaultRb);
        rb3.setTextColor(textColorDefaultRb);
        rbGroup.clearCheck();

        if(questionCounter < questionCountTotal) {
            currentQuestion = questionList.get(questionCounter);

            textViewQuestion.setText(currentQuestion.getQuestion());
            rb1.setText(currentQuestion.getOption1());
            rb2.setText(currentQuestion.getOption2());
            rb3.setText(currentQuestion.getOption3());

            questionCounter++;
            textViewQuestionCount.setText("Câu hỏi: " + questionCounter + "/" + questionCountTotal);
            answered = false;

            timeLeftInMillis = COUNTDOWN_IN_MILLIS;
            startCountDown();
        } else {
            finishQuiz();
        }
    }

    private void startCountDown() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timeLeftInMillis = 0;
                updateCountDownText();
                checkAnswer();
            }
        }.start();
    }

    private void updateCountDownText() {
        int minutes = (int) (timeLeftInMillis / 1000) /60;
        int seconds = (int) (timeLeftInMillis / 1000 ) %60;

        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        textViewCountdown.setText(timeFormatted);

        if (timeLeftInMillis < 6000) {
            textViewCountdown.setTextColor(Color.RED);
        } else {
            textViewCountdown.setTextColor(textColorDefaultCd);
        }
    }

    private void checkAnswer() {
        answered = true;

        countDownTimer.cancel();

        RadioButton rbSelected = findViewById(rbGroup.getCheckedRadioButtonId());
        int answerNr = rbGroup.indexOfChild(rbSelected) + 1;

        if(answerNr == currentQuestion.getAnswerNr()) {
            score++;
        } else{

            String q = currentQuestion.getQuestion();
            word.add(q);

            if (currentQuestion.getAnswerNr() == 1) {
                String ans = currentQuestion.getOption1();
                definition.add(ans);
            }
            if (currentQuestion.getAnswerNr() == 2) {
                String ans = currentQuestion.getOption2();
                definition.add(ans);
            }
            if (currentQuestion.getAnswerNr() == 3) {
                String ans = currentQuestion.getOption3();
                definition.add(ans);
            }
        }

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
        Intent intent = new Intent(ChoiceQuizActivity.this, ChoiceResult.class);
        Bundle args = new Bundle();
        Bundle args1 = new Bundle();
        args.putSerializable("ARRAYLIST", (Serializable) word);
        args1.putSerializable("ARRAYLIST2", (Serializable) definition);
        intent.putExtra("BUNDLE", args);
        intent.putExtra("BUNDLE1", args1);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            finishQuiz();
        } else {
            Toast.makeText(this, "Nhấn lại lần nữa để thoát", Toast.LENGTH_SHORT).show();
        }

        backPressedTime = System.currentTimeMillis();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}