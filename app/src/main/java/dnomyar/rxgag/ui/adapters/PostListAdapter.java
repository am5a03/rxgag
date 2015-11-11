package dnomyar.rxgag.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;
import dnomyar.rxgag.models.api.Gag;
import dnomyar.rxgag.ui.renderers.LoadingIndicatorRenderer;
import dnomyar.rxgag.ui.renderers.PostItemRenderer;
import rx.Subscription;

/**
 * Created by Raymond on 2015-11-11.
 */
public class PostListAdapter extends RecyclerView.Adapter {

    List<Gag> mGagList;
    PostItemRenderer mPostItemRenderer;
    LoadingIndicatorRenderer mLoadingIndicatorRenderer;

    private static final int VIEW_TYPE_POST = 0;
    private static final int VIEW_TYPE_LOADING_INDICATOR = 1;

    public PostListAdapter (List<Gag> gagList, PostItemRenderer postItemRenderer) {
        mPostItemRenderer = postItemRenderer;
        mGagList = gagList;
        mLoadingIndicatorRenderer = new LoadingIndicatorRenderer();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_POST:
                return mPostItemRenderer.onCreateViewHolder(parent, viewType);
            case VIEW_TYPE_LOADING_INDICATOR:
                return mLoadingIndicatorRenderer.onCreateViewHolder(parent, viewType);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case VIEW_TYPE_POST:
                mPostItemRenderer.onBindViewHolder(holder, position,
                        mGagList.get(mapAdapterPositionToItemPosition(position)));
                return;
            case VIEW_TYPE_LOADING_INDICATOR:
                mLoadingIndicatorRenderer.onBindViewHolder(holder, position, null);
                return;
        }

    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return VIEW_TYPE_LOADING_INDICATOR;
        }
        return VIEW_TYPE_POST;
    }

    @Override
    public int getItemCount() {
        return mGagList.size() + 1;
    }

    protected int mapAdapterPositionToItemPosition(int position) {
        return position;
    }


}
