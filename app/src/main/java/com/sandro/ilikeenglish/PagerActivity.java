package com.sandro.ilikeenglish;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.UUID;

/**
 * PagerActivity класс для слайда фрагментов налево и направо
 */

public class PagerActivity extends FragmentActivity {

    private ViewPager mViewPager;
    private int count = 3;


    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, PagerActivity.class);
        return intent;
    }

    private void fragmentTransaction (Fragment fragment){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);

        mViewPager = (ViewPager) findViewById(R.id.activity_pager);
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                RepeatFragment repeatFragment = new RepeatFragment();
                fragmentTransaction(repeatFragment);

return repeatFragment;


            }
            @Override
            public int getCount() {
                return count ;
            }
        });
    }
}
