package dnomyar.rxgag.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jakewharton.rxbinding.widget.RxAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import dnomyar.rxgag.R;
import dnomyar.rxgag.models.api.Gag;
import dnomyar.rxgag.ui.adapters.PostListAdapter;
import dnomyar.rxgag.ui.renderers.PostItemRenderer;

/**
 * Created by Raymond on 2015-10-19.
 */
public class PostListFragment extends BaseFragment {

    private static final String TAG = "PostListFragment";

    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    public static PostListFragment newInstance() {

        Bundle args = new Bundle();

        PostListFragment fragment = new PostListFragment();
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
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        PostItemRenderer postItemRenderer = new PostItemRenderer();
        mRecyclerView.setAdapter(new PostListAdapter(getDummyGag(), postItemRenderer));
        mSwipeRefreshLayout.setColorSchemeColors(R.color.colorPrimary, R.color.colorPrimaryDark, R.color.colorAccent);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
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
    }

    private List<Gag> getDummyGag() {
        ArrayList<Gag> gagList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Gag g = new Gag();
            g.images = new Gag.Images();
            g.votes = new Gag.Votes();
            g.caption = "Title" + i;
            g.images.large = "https://upload.wikimedia.org/wikipedia/en/7/7d/Bliss.png";
            g.votes.count = 2501;
            gagList.add(g);
        }
        return gagList;
    };
}
