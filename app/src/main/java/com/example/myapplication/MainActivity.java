package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, PopupMenu.OnMenuItemClickListener {

    private Toolbar mtoolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Button button;

    LinearLayout layout;
    AlertDialog dialog;
    AlertDialog dialog1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mtoolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setTitle("Memoka");

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        button = findViewById(R.id.button);
        layout = findViewById(R.id.container1);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                mtoolbar,
                R.string.openNavDrawer,
                R.string.closeNavDrawer
        );

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        buildDialog();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });
    }


    public void buildDialog() {

        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);
        View view = getLayoutInflater().inflate(R.layout.add_dialog, null);

        EditText name = view.findViewById(R.id.name);
        EditText description = view.findViewById(R.id.description);

        builder.setTitle("Tạo bộ thẻ mới");
        builder.setBackground(getResources().getDrawable(R.drawable.add_new_dialog, null));
        builder.setView(view);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(MainActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                addCard(name.getText().toString(), description.getText().toString());

                name.setText(null);
                description.setText(null);
            }
        });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        dialog = builder.create();

    }

    private void addCard(String name, String description) {
        View view = getLayoutInflater().inflate(R.layout.card, null);

        TextView name1 = view.findViewById(R.id.name1);
        TextView description1 = view.findViewById(R.id.description1);
        Button add = view.findViewById(R.id.add_card);
        Button practice = view.findViewById(R.id.practice);
        Button popup = findViewById(R.id.popup_menu);


        name1.setText(name);
        description1.setText(description);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openReview();
            }
        });

        openSelectionDialog();

        practice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.show();
            }
        });

        layout.addView(view);

    }

    public void openSelectionDialog() {

        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);
        View view = getLayoutInflater().inflate(R.layout.quiz_selection, null);

        Button button1 = view.findViewById(R.id.select_practice);
        Button button2 = view.findViewById(R.id.matching_practice);
        Button button3 = view.findViewById(R.id.check_practice);

        builder.setTitle("Vui lòng chọn chế độ thực hành");
        builder.setBackground(getResources().getDrawable(R.drawable.add_new_dialog, null));
        builder.setView(view);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFillQuiz();
            }
        });

        dialog1 = builder.create();

    }

    private void startFillQuiz() {
        Intent intent = new Intent(MainActivity.this, FillQuiz.class);
        startActivity(intent);
    }

    private void startQuiz() {
        Intent intent = new Intent(MainActivity.this, ChoiceQuizActivity.class);
        startActivity(intent);
    }

    public void openActivity2() {
        Intent intent = new  Intent(MainActivity.this, Activity2.class);
        startActivity(intent);
    }

    public void openReview() {
        Intent intent = new  Intent(MainActivity.this, ReviewCard.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void showPopUp (View v) {
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.popup_menu);
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                openActivity2();
                return true;
            case R.id.item3:
                layout.removeAllViews();
            default:
                return false;
        }
    }

}