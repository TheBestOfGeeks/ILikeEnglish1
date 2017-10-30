package com.sandro.ilikeenglish;




import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {

    private void fragmentTransaction (Fragment fragment){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_repeat:
                    RepeatFragment repeatFragment = new RepeatFragment();
                    fragmentTransaction(repeatFragment);
                    return true;
                case R.id.navigation_learn:
                    LearnFragment learnFragment = new LearnFragment();
                    fragmentTransaction(learnFragment);
                    return true;
                case R.id.navigation_dictionary:
                    DictionaryFragment dictionaryFragment = new DictionaryFragment();
                    fragmentTransaction(dictionaryFragment);
                    return true;
            }
            return false;
        }

    };


    static String[][] massiv = new String[100][4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Подключение базы
        Database mMyDatabase = new Database(this);
        final SQLiteDatabase database = mMyDatabase.getWritableDatabase();
        //*****
        Cursor cursor = database.query(Database.DATABASE_TABLE, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {

            int throughMassiv = 0;

            int idIndex = cursor.getColumnIndex(Database.KEY_ID);
            int engIndex = cursor.getColumnIndex(Database.KEY_ENG_WORD);
            int rusIndex = cursor.getColumnIndex(Database.KEY_RUS_WORD);
            int trueIndex = cursor.getColumnIndex(Database.KEY_TRUE_ID);


            do {
                massiv[throughMassiv][0] = String.valueOf(cursor.getInt(idIndex));
                massiv[throughMassiv][1] = cursor.getString(engIndex);
                massiv[throughMassiv][2] = cursor.getString(rusIndex);
                massiv[throughMassiv][3] = String.valueOf(cursor.getInt(trueIndex));

                throughMassiv++;

            } while (cursor.moveToNext());

        }
        cursor.close();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
