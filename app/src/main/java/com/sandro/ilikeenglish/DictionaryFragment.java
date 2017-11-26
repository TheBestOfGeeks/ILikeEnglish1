package com.sandro.ilikeenglish;




import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;



public class DictionaryFragment extends Fragment  {


    public DictionaryFragment() {
        // Required empty public constructor
    }

    private static final String NEW_WORD = "newWord";
    private RecyclerView mDictionaryRecyclerView;
    private DictionaryAdapter mAdapter;
    Button addWordBtn;
    private List<Word> mWords;
    Database db;


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

                FragmentManager manager = getFragmentManager();
                AddWordFragment addWordFragment = new AddWordFragment();
                addWordFragment.show(manager, NEW_WORD);

            }
        });

        return view;

    }


    //доступ к базе - заполнение массива и передача его в лист - Запуск ресайклер вью
    private void updateUI() {
        db = new Database(getActivity());
        db.open(getActivity());

        mWords = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Word word = new Word();
            word.setRusWord(db.getMassive()[i][1]);
            word.setEngWord(db.getMassive()[i][2]);
            mWords.add(word);
        }

            mAdapter = new DictionaryAdapter(mWords);
            mDictionaryRecyclerView.setAdapter(mAdapter);

    }
    // ...........



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
