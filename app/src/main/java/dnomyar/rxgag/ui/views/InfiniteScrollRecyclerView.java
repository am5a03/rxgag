package dnomyar.rxgag.ui.views;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;

import com.jakewharton.rxbinding.support.v7.widget.RecyclerViewScrollEvent;
import com.jakewharton.rxbinding.support.v7.widget.RecyclerViewScrollStateChangeEvent;
import com.jakewharton.rxbinding.support.v7.widget.RxRecyclerView;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Raymond on 2015-11-10.
 */
public class InfiniteScrollRecyclerView extends RecyclerView {
    private static final String TAG = "ISRecyclerView";
    private Subscription mScrollEventSubscriber;

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
        mScrollEventSubscriber = RxRecyclerView.scrollStateChangeEvents(this)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onScrollStateChanged);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mScrollEventSubscriber.unsubscribe();
    }

    private void onScrollStateChanged(RecyclerViewScrollStateChangeEvent ev) {
        RecyclerView v = ev.view();
        LayoutManager layoutManager = v.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            LinearLayoutManager ll = (LinearLayoutManager) layoutManager;
            int itemCount = v.getAdapter().getItemCount();
            int firstVisiblePosition = ll.findFirstVisibleItemPosition();
            int childCount = ll.getChildCount();

            if (firstVisiblePosition + childCount >= itemCount) {
                Log.d(TAG, "onScrollStateChanged: Load more");
            }
            Log.d(TAG, "onScrollStateChanged: " + firstVisiblePosition);
        }
    }
}
