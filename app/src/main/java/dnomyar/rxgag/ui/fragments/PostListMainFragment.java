package dnomyar.rxgag.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jakewharton.rxbinding.support.design.widget.RxTabLayout;
import com.jakewharton.rxbinding.support.design.widget.TabLayoutSelectionEvent;

import butterknife.Bind;
import butterknife.ButterKnife;
import dnomyar.rxgag.R;
import dnomyar.rxgag.ui.adapters.PostListPagerAdapter;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by Raymond on 2015-11-09.
 */
public class PostListMainFragment extends BaseFragment {
    private static final String TAG = "PostListMainFragment";
    public static PostListMainFragment newInstance() {

        Bundle args = new Bundle();

        PostListMainFragment fragment = new PostListMainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.view_pager)
    ViewPager mViewPager;

    @Bind(R.id.tab_layout)
    TabLayout mTabLayout;

    @Bind(R.id.fab)
    FloatingActionButton mFab;

    private Subscription mTabLayoutSubscription;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main_list, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        PagerAdapter adapter = new PostListPagerAdapter(getChildFragmentManager());
        mToolbar.setTitle("RxGag");
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.d(TAG, "onPageSelected: " + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mTabLayout.setTabsFromPagerAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mFab.setOnClickListener(v -> {
            FragmentStatePagerAdapter adapter1
                    = (FragmentStatePagerAdapter) mViewPager.getAdapter();
            PostListFragment f
                    = (PostListFragment) adapter1.instantiateItem(mViewPager, mViewPager.getCurrentItem());
            f.scrollToTopAndRefresh();
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        mTabLayoutSubscription = RxTabLayout.selectionEvents(mTabLayout)
                .subscribe(tabLayoutSelectionEvent -> {
                    if (tabLayoutSelectionEvent.kind() ==
                            TabLayoutSelectionEvent.Kind.RESELECTED) {
                        Log.d(TAG, "onStart: reselcted");
                        FragmentStatePagerAdapter adapter
                                = (FragmentStatePagerAdapter) mViewPager.getAdapter();
                        PostListFragment f
                                = (PostListFragment) adapter.instantiateItem(mViewPager, mViewPager.getCurrentItem());
                        f.scrollToTopAndRefresh();
                    } else if (tabLayoutSelectionEvent.kind() ==
                            TabLayoutSelectionEvent.Kind.SELECTED) {
                        mViewPager.setCurrentItem(mTabLayout.getSelectedTabPosition());
                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mTabLayoutSubscription.unsubscribe();
    }
}
