package com.example.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.android.material.snackbar.Snackbar;

public class tao_moi_dialog extends AppCompatDialogFragment {

    private EditText editTextName;
    private EditText editTextDescription;

    LinearLayout layout;
    AlertDialog dialog;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.add_dialog, null);

        EditText name1 = view.findViewById(R.id.name1);
        EditText description1 = view.findViewById(R.id.description1);

        builder.setView(view);
        builder.setTitle("Tạo bộ thẻ mới");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Snackbar.make(button, "Thêm thành công", Snackbar.LENGTH_LONG).show();
                addCard(name1.getText().toString(), description1.getText().toString());
                dialog.dismiss();
            }

            private void addCard(String name1, String description1) {

                View view1 = getLayoutInflater().inflate(R.layout.card, null);

                TextView nameView = view1.findViewById(R.id.name1);
                TextView descriptionView = view1.findViewById(R.id.description1);
                Button add = view1.findViewById(R.id.add_card);
                Button practice = view1.findViewById(R.id.practice);

                nameView.setText(name1);
                descriptionView.setText(description1);

                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

                practice.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

                layout.addView(view1);

            }
        });

        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        editTextDescription = view.findViewById(R.id.description);
        editTextName = view.findViewById(R.id.name);

        return builder.create();

    }
}
