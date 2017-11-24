package com.sandro.ilikeenglish;








import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;




public class MainActivity extends AppCompatActivity /*implements LoaderManager.LoaderCallbacks<Cursor>*/ {

    // Метод для создания транцакций фрагментов
    private void fragmentTransaction (Fragment fragment){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }
    //.........


    // Создание меню с переключением между фрагментрами
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
    //.........

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

/*    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
       return new MyCursorLoader(this, mMyDatabase);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    static class MyCursorLoader extends CursorLoader {
        // Подключение базы
        Database mMyDatabase = new Database(getContext());
        final SQLiteDatabase database = mMyDatabase.getWritableDatabase();
        /*//*****

        public MyCursorLoader(Context context, Database db) {
            super(context);
            db = mMyDatabase;
        }

  @Override
  public Cursor loadInBackground() {
      Cursor cursor = database.query(Database.DATABASE_TABLE, null, null, null, null, null, null);
      return cursor;


    }
    }*/


}
