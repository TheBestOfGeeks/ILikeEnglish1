package com.sandro.ilikeenglish;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;



public class Database extends SQLiteAssetHelper {

    public static final String DATABASE_NAME = "data.db";
    public static final String DATABASE_TABLE = "Dictionary";
    private static final int DATABASE_VERSION = 1;
    public static final String KEY_ID = "_id";
    public static final String KEY_ENG_WORD = "EngWord";
    public static final String KEY_RUS_WORD = "RusWord";
    public static final String KEY_IS_LEARNED = "isLearned";


    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}


