package com.sandro.ilikeenglish;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AddWordFragment extends DialogFragment {

    Database db;
    Button add;
    EditText forRusWord, forEngWord, forIsLearned;

    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.new_word, null);

        forRusWord = (EditText) v.findViewById(R.id.rus_word);
        forEngWord = (EditText) v.findViewById(R.id.eng_word);
        forIsLearned = (EditText) v.findViewById(R.id.is_learned);
        add = (Button) v.findViewById(R.id.button);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = new Database(getActivity());
                db.open(getActivity());
                db.addNewWord(forRusWord.getText().toString(), forEngWord.getText().toString(),forIsLearned.getText().toString());

            }
        });

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.add_word_title)
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                .create();
    }


}
