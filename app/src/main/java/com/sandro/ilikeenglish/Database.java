package com.sandro.ilikeenglish;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;



public class Database extends SQLiteAssetHelper {

    public static final String DATABASE_NAME = "data.db";
    public static final String DATABASE_TABLE = "Dictionary";
    private static final int DATABASE_VERSION = 1;
    public static final String KEY_ID = "_id";
    public static final String KEY_ENG_WORD = "EngWord";
    public static final String KEY_RUS_WORD = "RusWord";
    public static final String KEY_IS_LEARNED = "isLearned";

    static String[][] massiv = new String[100][4];
    SQLiteDatabase database;


    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    // Открыть подключение к БД
    public void open(Context context) {
        Database mMyDatabase = new Database(context);
        database = mMyDatabase.getWritableDatabase();
    }
// Закрыть подключение к базе
    public void close() {
        database.close();
    }
    public int countOfQestions(){
        Cursor cursor = database.query(Database.DATABASE_TABLE,
                null, null, null, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        return count;

    }


    public void getAllData (){
        Cursor cursor = database.query(Database.DATABASE_TABLE,
                null, null, null, null, null, null);

    }


    public void deleteWord (){
        database.delete(Database.DATABASE_TABLE, Database.KEY_ID + "=" + "1", null);
    }


    public String[][] getMassive () {

        Cursor cursor = database.query(Database.DATABASE_TABLE,
                null, null, null, null, null, null);
        if (cursor.moveToFirst()) {

            int throughMassiv = 0;

            int idIndex = cursor.getColumnIndex(Database.KEY_ID);
            int engIndex = cursor.getColumnIndex(KEY_ENG_WORD);
            int rusIndex = cursor.getColumnIndex(Database.KEY_RUS_WORD);
            int isLearned = cursor.getColumnIndex(Database.KEY_IS_LEARNED);


            do {
                massiv[throughMassiv][0] = String.valueOf(cursor.getInt(idIndex));
                massiv[throughMassiv][1] = cursor.getString(engIndex);
                massiv[throughMassiv][2] = cursor.getString(rusIndex);


                throughMassiv++;

            } while (cursor.moveToNext());

        }
        cursor.close();
        return massiv;

    }
// добавить слово в базу
    public void addNewWord (String rusWord, String engWord, String isLearned){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Database.KEY_RUS_WORD, rusWord);
        contentValues.put(Database.KEY_ENG_WORD, engWord);
        contentValues.put(Database.KEY_IS_LEARNED, isLearned);
        database.insert(Database.DATABASE_TABLE, null, contentValues);
    }

}


