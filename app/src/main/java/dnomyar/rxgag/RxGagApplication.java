package dnomyar.rxgag;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.squareup.okhttp.OkHttpClient;

import dnomyar.rxgag.repository.SQLiteDatabaseHelper;

/**
 * Created by Raymond on 2015-10-19.
 */
public class RxGagApplication extends Application {
    private static final OkHttpClient sClient = new OkHttpClient();

    private static final SQLiteDatabaseHelper sDB = new SQLiteDatabaseHelper();

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        sDB.init(this);
    }

    public static OkHttpClient getClient() {
        return sClient;
    }

    public static SQLiteDatabaseHelper getDB() {
        return sDB;
    }
}
