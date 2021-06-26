package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import java.util.Collections;
import java.util.List;

public class ReviewCard extends AppCompatActivity {

    private Toolbar reviewToolbar;
    private Button button;
    private int current;
    private CardInfo currentCard;
    private List<CardInfo>  cardList;
    private int cardCountTotal;
    private int cardCounter;

    TextView cardPosition;
    SliderAdapter adapter;
    ViewPager2 pager2;

    int list[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_card);

         reviewToolbar= findViewById(R.id.review_toolbar);
         setSupportActionBar(reviewToolbar);

         getSupportActionBar().setTitle("Ôn tập");
         getSupportActionBar().setDisplayHomeAsUpEnabled(true);
         getSupportActionBar().setDisplayShowHomeEnabled(true);

         CardDbHelper dbHelper = new CardDbHelper(this);
         cardList = dbHelper.getAllCard();
         cardCountTotal = cardList.size();
         Collections.shuffle(cardList);

         pager2 = findViewById(R.id.view_pager2);
         cardPosition = findViewById(R.id.card_position);

         list = new int[cardList.size()];

         adapter = new SliderAdapter(cardList);
         pager2.setAdapter(adapter);

         pager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
             @Override
             public void onPageSelected(int position) {
                 current = position + 1;
                 cardPosition.setText( current + "/" + list.length);
                 super.onPageSelected(position);
             }
         });

    }

}