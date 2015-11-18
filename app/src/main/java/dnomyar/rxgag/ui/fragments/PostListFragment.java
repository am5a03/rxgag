package dnomyar.rxgag.ui.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import dnomyar.rxgag.R;
import dnomyar.rxgag.RxGagApplication;
import dnomyar.rxgag.models.api.ApiGagResponse;
import dnomyar.rxgag.models.wrapper.Gag;
import dnomyar.rxgag.network.GagApiServiceManager;
import dnomyar.rxgag.ui.adapters.PostListAdapter;
import dnomyar.rxgag.ui.renderers.PostItemRenderer;
import dnomyar.rxgag.ui.views.InfiniteScrollRecyclerView;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Raymond on 2015-10-19.
 */
public class PostListFragment extends BaseFragment implements InfiniteScrollRecyclerView.InfiniteScrollListener {

    private static final String TAG = "PostListFragment";

    @Bind(R.id.recycler_view)
    InfiniteScrollRecyclerView mInfiniteScrollView;

    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private GagApiServiceManager mGagApiServiceManager;
    private List<Gag> mGagList;
    private String mPageOffset = "0";
    private String mSection;

    Subscription mInit;
    Subscription mLoadMore;

    public static PostListFragment newInstance(String section) {

        Bundle args = new Bundle();

        PostListFragment fragment = new PostListFragment();
        args.putString("section", section);
        fragment.setArguments(args);
        return fragment;
    }
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.post_list, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mInfiniteScrollView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mInfiniteScrollView.addInfiniteScrollListener(this);
        mInfiniteScrollView.setItemAnimator(new DefaultItemAnimator());
        PostItemRenderer postItemRenderer = new PostItemRenderer();
        mInfiniteScrollView.setAdapter(new PostListAdapter(mGagList, postItemRenderer));
        mSwipeRefreshLayout.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE);
        mSwipeRefreshLayout.setOnRefreshListener(this::scrollToTopAndRefresh);
        if (savedInstanceState == null) {
            mInit = getGagSubscription();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGagApiServiceManager = new GagApiServiceManager(RxGagApplication.getClient());
        mGagList = new ArrayList<>();
        mSection = getArguments().getString("section");
        mSection = mSection.toLowerCase();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: " + getUserVisibleHint());
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        if (mInit != null) {
            mInit.unsubscribe();
        }

        if (mLoadMore != null) {
            mLoadMore.unsubscribe();
        }

        super.onDestroy();
    }

    @Override
    public void dispatchLoadMore() {
        mLoadMore = getGagSubscription();

    }

    public void scrollToTopAndRefresh() {
        mPageOffset = "0";
        mInfiniteScrollView.scrollToPosition(0);
        mGagList.clear();
        mInfiniteScrollView.getAdapter().notifyDataSetChanged();
        mInit = getGagSubscription();
    }

    // FIXME: 2015-11-18 Better to create an Observable rather than Subscription
    private Subscription getGagSubscription() {

        return mGagApiServiceManager.getGagList(mSection, mPageOffset)
                // get the paging offset
                .doOnNext(apiGagResponse -> {
                    mPageOffset = apiGagResponse.paging.next;
                    mInfiniteScrollView.setIsLoading(true);
                })
                .doOnCompleted(() -> {
                    mInfiniteScrollView.setIsLoading(false);
                    Log.d(TAG, "getGagSubscription: api completed page=" + mPageOffset);
                })
                .onErrorReturn(throwable -> {
                    throwable.printStackTrace();
                    mInfiniteScrollView.setIsLoading(false);
                    mSwipeRefreshLayout.setRefreshing(false);
                    return null; // Return null for any errors
                })
                // Skip all null result
                .filter(apiGagResponse -> apiGagResponse != null)
                // We map the response to a list
                .map(apiGagResponse -> Arrays.asList(apiGagResponse.data))
                // We convert the ArrayList to a single Object
                .flatMap(gags -> Observable.from(gags))
                // We observe on main thread
                .observeOn(AndroidSchedulers.mainThread())
                // The sequence should be generated from background thread
                .subscribeOn(Schedulers.io())
                        // onNext()
                .subscribe(
                        gag -> {
                            mGagList.add(gag);
                            mInfiniteScrollView.getAdapter().notifyItemInserted(mGagList.size());
                        },
                        // onError()
                        throwable -> {
                            throwable.printStackTrace();
                            // Subscribe to Error events on UI thread
                            Toast.makeText(getActivity(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                            mSwipeRefreshLayout.setRefreshing(false);
                        },
                        // onCompleted
                        () -> {
                            // The whole sequence is completed
                            Toast.makeText(getActivity(), mPageOffset, Toast.LENGTH_SHORT).show();
                            mSwipeRefreshLayout.setRefreshing(false);
                            Log.d(TAG, "getGagSubscription: completed");
                });
    }
}
