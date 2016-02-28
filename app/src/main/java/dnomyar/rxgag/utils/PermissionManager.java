package dnomyar.rxgag.utils;

import android.app.Activity;
import android.util.Log;

/**
 * Created by Raymond on 2015-12-02.
 */
public class PermissionManager {
    private static final String TAG = "PermissionManager";
    private static PermissionManager sInstance;
    private Activity mActivity;
    private static PermissionManager getInstance() {
        if (sInstance == null) {
            sInstance = new PermissionManager();
//            Log.d(TAG, "getInstance: init" + sInstance);
        } else {
//            Log.d(TAG, "getInstance: " + sInstance);
        }
        return sInstance;
    }

    public static void setActivity(String lifeCycle, Activity prev, Activity newAct) {
        Log.d(TAG, "lifeCycle=" + lifeCycle + " instance=" + getInstance().mActivity + " prev=" + prev + " curr="+ newAct);
        if (prev == getInstance().mActivity) {
            Log.d(TAG, "eq:lifeCycle=" + lifeCycle + " instance=" + getInstance().mActivity + " prev=" + prev + " curr="+ newAct);
            if (newAct == null) {
                getInstance().mActivity = null;
                Log.d(TAG, "null:lifeCycle=" + lifeCycle + " instance=" + getInstance().mActivity + " prev=" + prev + " curr="+ newAct);
            }
        } else {
            getInstance().mActivity = newAct;
        }
    }

    public static void setActivity(Activity activity) {
        getInstance().mActivity = activity;
    }

    public static String getActivity() {
        return getInstance().mActivity.getClass().getSimpleName();
    }
}
