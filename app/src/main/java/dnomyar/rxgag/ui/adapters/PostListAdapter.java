package dnomyar.rxgag.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.ButterKnife;
import dnomyar.rxgag.models.api.Gag;
import dnomyar.rxgag.models.api.GagList;
import dnomyar.rxgag.ui.renderers.PostItemRenderer;

/**
 * Created by Raymond on 2015-11-11.
 */
public class PostListAdapter extends RecyclerView.Adapter {

    List<Gag> mGagList;
    PostItemRenderer mPostItemRenderer;

    public PostListAdapter (List<Gag> gagList, PostItemRenderer postItemRenderer) {
        mPostItemRenderer = postItemRenderer;
        mGagList = gagList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return mPostItemRenderer.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        mPostItemRenderer.onBindViewHolder(holder, position, mGagList.get(mapAdapterPositionToItemPosition(position)));
    }

    @Override
    public int getItemCount() {
        return mGagList.size();
    }

    protected int mapAdapterPositionToItemPosition(int position) {
        return position;
    }


}
