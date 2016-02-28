package dnomyar.rxgag.ui.views;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by Raymond on 2015-11-21.
 */
public class MyCustomView extends SimpleDraweeView {
    public MyCustomView(Context context, GenericDraweeHierarchy hierarchy) {
        super(context, hierarchy);
    }

    public MyCustomView(Context context) {
        super(context);
    }

    public MyCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyCustomView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
