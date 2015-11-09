package dnomyar.rxgag.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import dnomyar.rxgag.ui.fragments.PostListFragment;

/**
 * Created by Raymond on 2015-11-09.
 */
public class PostListPagerAdapter extends FragmentStatePagerAdapter {
    private static final int NUM_OF_PAGES = 3;

    public PostListPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return PostListFragment.newInstance();
            case 1:
                return PostListFragment.newInstance();
            case 2:
                return PostListFragment.newInstance();
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Hot";
            case 1:
                return "Trending";
            case 2:
                return "Fresh";
        }
        return null;
    }

    @Override
    public int getCount() {
        return NUM_OF_PAGES;
    }
}
