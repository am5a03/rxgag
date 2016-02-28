package dnomyar.rxgag.ui.renderers;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import dnomyar.rxgag.R;
import dnomyar.rxgag.models.wrapper.ApiGag;

/**
 * Created by Raymond on 2015-11-12.
 */
public class LoadingIndicatorRenderer {
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout v = new LinearLayout(parent.getContext());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        v.setLayoutParams(lp);
        ProgressBar pb = new ProgressBar(parent.getContext());
        pb.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        pb.setId(R.id.progress_bar);
        v.addView(pb);
        return new ProgressBarViewHolder(v);
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, ApiGag apiGag) {

    }

    static class ProgressBarViewHolder extends RecyclerView.ViewHolder {
        ProgressBar mProgressBar;
        public ProgressBarViewHolder(View itemView) {
            super(itemView);
            mProgressBar = (ProgressBar) itemView.findViewById(R.id.progress_bar);
        }
    }
}
