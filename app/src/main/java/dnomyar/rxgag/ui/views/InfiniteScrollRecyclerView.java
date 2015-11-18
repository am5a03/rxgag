package dnomyar.rxgag.ui.views;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;

import com.jakewharton.rxbinding.support.v7.widget.RxRecyclerView;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by Raymond on 2015-11-10.
 */
public class InfiniteScrollRecyclerView extends RecyclerView {
    private static final String TAG = "ISRecyclerView";
    private Subscription mScrollEventSubscriber;
    private InfiniteScrollListener mInfiniteScrollListener;
    private boolean mIsLoading;

    public InfiniteScrollRecyclerView(Context context) {
        super(context);
    }

    public InfiniteScrollRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public InfiniteScrollRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mScrollEventSubscriber = RxRecyclerView.scrollStateChanges(this)
                .filter(isLoading -> ! isLoading()) // Filter all scrolling events when it's already loading
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onScrollStateChanges); // What to do with scrolling event?
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mScrollEventSubscriber.unsubscribe();
    }

    public void addInfiniteScrollListener(InfiniteScrollListener listener) {
        this.mInfiniteScrollListener = listener;
    }

    private void onScrollStateChanges(int state) {
        RecyclerView v = this;
        LayoutManager layoutManager = v.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            LinearLayoutManager ll = (LinearLayoutManager) layoutManager;
            int itemCount = v.getAdapter().getItemCount();
            int firstVisiblePosition = ll.findFirstVisibleItemPosition();
            int childCount = ll.getChildCount();

            if (firstVisiblePosition + childCount >= itemCount) {
                Log.d(TAG, "onScrollStateChanged: Load more");
                if (mInfiniteScrollListener != null) {
                    mInfiniteScrollListener.dispatchLoadMore();
                }
            }
            Log.d(TAG, "onScrollStateChanged: " + firstVisiblePosition);
        }
    }

    public void setIsLoading(boolean isLoading) {
        mIsLoading = isLoading;
    }

    public boolean isLoading() {
        return mIsLoading;
    }

    public interface InfiniteScrollListener {
        void dispatchLoadMore();
    }
}
