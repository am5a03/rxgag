package dnomyar.rxgag.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import dnomyar.rxgag.R;
import dnomyar.rxgag.utils.PermissionManager;

/**
 * Created by Raymond on 2015-12-02.
 */
public class ActivityTwo extends AppCompatActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        mTextView = (TextView) findViewById(R.id.text);

        PermissionManager.setActivity(this);
//        PermissionManager.setActivity("onCreate", this, this);
    }

    @Override
    protected void onStart() {
        super.onStart();
//        PermissionManager.setActivity("onStart", this, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        PermissionManager.setActivity(this);
//        TimerTask tt = new TimerTask() {
//            @Override
//            public void run() {
//                mTextView.setText(PermissionManager.getActivity());
//            }
//        };
//        Timer t = new Timer();
//        t.schedule(tt, 5000);
    }

    @Override
    protected void onStop() {
        super.onStop();
        PermissionManager.setActivity("onStop", this, null);
    }
}
