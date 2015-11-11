package dnomyar.rxgag;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.squareup.okhttp.OkHttpClient;

/**
 * Created by Raymond on 2015-10-19.
 */
public class RxGagApplication extends Application {
    private static final OkHttpClient sClient = new OkHttpClient();

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }

    public static OkHttpClient getClient() {
        return sClient;
    }
}
