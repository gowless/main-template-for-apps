package com.maindevpattern.adapters.uitabbed;



import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;


import com.maindevpattern.activities.SplashActivity;
import com.maindevpattern.fragments.FragmentAllList;
import com.maindevpattern.fragments.FragmentWithBadList;
import com.maindevpattern.fragments.FragmentZeroList;


/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

    private static final String[] TAB_TITLES = new String[]{SplashActivity.first, SplashActivity.second, SplashActivity.third};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return super.getItemPosition(object);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new FragmentAllList();
                break;
            case 1:
                fragment = new FragmentZeroList();
                break;
            case 2:
                fragment = new FragmentWithBadList();
                break;
        }
        assert fragment != null;
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return TAB_TITLES[position];
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return SplashActivity.numberOfTabs;
    }
}