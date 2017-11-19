package com.sandro.ilikeenglish;


import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class DictionaryFragment extends Fragment  {


    public DictionaryFragment() {
        // Required empty public constructor
    }

    private RecyclerView mDictionaryRecyclerView;
    private DictionaryAdapter mAdapter;
    Button addWordBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       final View view = inflater.inflate(R.layout.fragment_dictionary, container,
                false);
        mDictionaryRecyclerView = (RecyclerView) view.findViewById(R.id.dictionary_recycler_view);
        mDictionaryRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();



        addWordBtn = (Button) view.findViewById(R.id.addWord);
        addWordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Подключение базы
                Database mMyDatabase = new Database(getActivity());
                final SQLiteDatabase database = mMyDatabase.getWritableDatabase();
                //*****
                ContentValues contentValues = new ContentValues();
                contentValues.put(Database.KEY_RUS_WORD, "олололо");
                contentValues.put(Database.KEY_ENG_WORD, "ляляля");
                contentValues.put(Database.KEY_IS_LEARNED, "ррррррр");
                database.insert(Database.DATABASE_TABLE, null, contentValues);
            }
        });

        return view;

    }
    private void updateUI() {
        WordsLab wordsLab = WordsLab.get(getActivity());
        List<Word> words = wordsLab.getWords();
        mAdapter = new DictionaryAdapter(words);
        mDictionaryRecyclerView.setAdapter(mAdapter);
    }



    // Холдер - удерживает объект в списке.
    private class CrimeHolder extends RecyclerView.ViewHolder {
        public TextView mRusTextView;
        public TextView mEngTextView;

        public CrimeHolder(View itemView) {
            super(itemView);
            mRusTextView = (TextView) itemView.findViewById(R.id.rus_word);
            mEngTextView = (TextView) itemView.findViewById(R.id.eng_word);
        }

        public void bindWord(Word word) {
            mRusTextView.setText(word.getRusWord());
            mEngTextView.setText(word.getEngWord());
        }
    }

    //************************
    // Адаптер - для построения списка (которому передается объект класса Холдер)
    private class DictionaryAdapter extends RecyclerView.Adapter<CrimeHolder> {

        private List<Word> mWords;

        public DictionaryAdapter(List<Word> words) {
            mWords = words;
        }

        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_dictionary, parent, false);
            return new CrimeHolder(view);
        }

        @Override
        public void onBindViewHolder(CrimeHolder holder, int position) {
            Word word = mWords.get(position);
            holder.bindWord(word);
        }

        @Override
        public int getItemCount() {
            int count = 100;
            return count;
        }
    }
    //****************************


}
