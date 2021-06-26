package com.example.myapplication;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class Activity2 extends AppCompatActivity {

    private Toolbar addtoolbar;
    private Button button;

    LinearLayout layout;
    AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        layout = findViewById(R.id.container2);
        button = findViewById(R.id.add_button);
        addtoolbar = findViewById(R.id.add_toolbar);
        setSupportActionBar(addtoolbar);

        getSupportActionBar().setTitle("Chỉnh sửa");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        openDialog();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

    }

    private void openDialog() {

        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);
        View view = getLayoutInflater().inflate(R.layout.input_dialog, null);

        EditText input1 = view.findViewById(R.id.input1);
        EditText input2 = view.findViewById(R.id.input2);

        builder.setTitle("Tạo thẻ mới");
        builder.setBackground(getResources().getDrawable(R.drawable.add_new_dialog, null));
        builder.setView(view);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(Activity2.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                addNewCard(input1.getText().toString(), input2.getText().toString());

                input1.setText(null);
                input2.setText(null);
            }
        });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        dialog = builder.create();
    }

    private void addNewCard(String input1, String input2) {

        View view = getLayoutInflater().inflate(R.layout.card_view, null);

        TextView word = view.findViewById(R.id.word);
        TextView definition = view.findViewById(R.id.definition);
        Button delete = view.findViewById(R.id.delete_card);

        word.setText(input1);
        definition.setText(input2);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.removeView(view);
            }
        });

        layout.addView(view);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.add_menu, menu);
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }


}