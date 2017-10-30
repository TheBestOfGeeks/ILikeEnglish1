package com.sandro.ilikeenglish;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.sandro.ilikeenglish.MainActivity.massiv;


public class DictionaryFragment extends Fragment {


    public DictionaryFragment() {
        // Required empty public constructor
    }

    private RecyclerView mDictionaryRecyclerView;
    MainActivity massive = new MainActivity();
    private DictionaryAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dictionary, container,
                false);
        mDictionaryRecyclerView = (RecyclerView) view.findViewById(R.id.dictionary_recycler_view);
        mDictionaryRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();

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