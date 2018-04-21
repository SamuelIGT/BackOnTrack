package br.ufc.samuel.backontrack.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import br.ufc.samuel.backontrack.R;
import br.ufc.samuel.backontrack.fragments.LevelsFragment;
import br.ufc.samuel.backontrack.fragments.PlaceholderFragment;

/**
 * Created by samue on 10/01/2018.
 */

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private Context context;

    public SectionsPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        switch (position){
            case 0:
                return LevelsFragment.newInstance(position + 1);
        }
        return PlaceholderFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 1;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return context.getResources().getString(R.string.Exercise_TabLabel);
//            case 1:
//                return context.getResources().getString(R.string.Calendar_TabLabel);
        }
        return null;
    }
}


