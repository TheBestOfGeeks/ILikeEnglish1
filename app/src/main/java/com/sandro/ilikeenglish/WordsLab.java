package com.sandro.ilikeenglish;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Sandro on 01.09.2017.
 */

public class WordsLab {

    private List<Word> mWords;
    private static WordsLab sWordsLab;

    public static WordsLab get(Context context) {
        if (sWordsLab == null) {
            sWordsLab = new WordsLab(context);
        }
        return sWordsLab;
    }
    private WordsLab(Context context) {
        mWords = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Word word = new Word();
            word.setRusWord(mass.massiv[i][1]);
            word.setEngWord(mass.massiv[i][2]);
            mWords.add(word);
        }
    }

    public  List<Word> getWords(){
        return mWords;
    }
}
