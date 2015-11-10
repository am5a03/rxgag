package dnomyar.rxgag.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
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
        mTabLayout.setTabsFromPagerAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);

    }

    @Override
    public void onStart() {
        super.onStart();
        mTabLayoutSubscription = RxTabLayout.selectionEvents(mTabLayout)
                .subscribe(tabLayoutSelectionEvent -> {
                    if (tabLayoutSelectionEvent.kind() ==
                            TabLayoutSelectionEvent.Kind.RESELECTED) {

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
