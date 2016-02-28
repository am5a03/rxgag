package dnomyar.rxgag.ui.renderers;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.Bind;
import butterknife.ButterKnife;
import dnomyar.rxgag.R;
import dnomyar.rxgag.models.wrapper.ApiGag;

/**
 * Created by Raymond on 2015-11-11.
 */
public class PostItemRenderer {


    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item, parent, false);
        return new PostItemViewHolder(v);
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, ApiGag apiGag) {
        PostItemViewHolder vh = (PostItemViewHolder) holder;
        vh.bindPost(apiGag);
    }

    static class PostItemViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.title) TextView mTitle;
        @Bind(R.id.points) TextView mPoints;
        @Bind(R.id.image) SimpleDraweeView mImage;

        public PostItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindPost(ApiGag apiGag) {
            mTitle.setText(apiGag.caption);
            mPoints.setText(String.format(itemView.getContext().getString(R.string.points), apiGag.votes.count));
            mImage.setImageURI(Uri.parse(apiGag.images.large));
        }
    }
}
