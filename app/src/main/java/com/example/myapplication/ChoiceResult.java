package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class ChoiceResult extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerAdapter adapter;

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_result);

        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        Bundle args1 = intent.getBundleExtra("BUNDLE1");
        ArrayList<String> word = (ArrayList<String>) args.getSerializable("ARRAYLIST");
        ArrayList<String> definition = (ArrayList<String>) args1.getSerializable("ARRAYLIST2");

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerAdapter(this, word,definition);
        recyclerView.setAdapter(adapter);

        button = findViewById(R.id.end_result_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}