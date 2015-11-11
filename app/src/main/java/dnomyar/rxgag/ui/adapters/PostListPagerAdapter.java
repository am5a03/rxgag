package dnomyar.rxgag.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import dnomyar.rxgag.ui.fragments.PostListFragment;

/**
 * Created by Raymond on 2015-11-09.
 */
public class PostListPagerAdapter extends FragmentStatePagerAdapter {
    private static final String PAGE_HOT = "Hot";
    private static final String PAGE_TRENDING = "Trending";
    private static final String PAGE_FRESH = "Fresh";

    private static final int NUM_OF_PAGES = 3;

    public PostListPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return PostListFragment.newInstance(PAGE_HOT);
            case 1:
                return PostListFragment.newInstance(PAGE_TRENDING);
            case 2:
                return PostListFragment.newInstance(PAGE_FRESH);
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return PAGE_HOT;
            case 1:
                return PAGE_TRENDING;
            case 2:
                return PAGE_FRESH;
        }
        return null;
    }

    @Override
    public int getCount() {
        return NUM_OF_PAGES;
    }
}
