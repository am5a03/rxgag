package dnomyar.rxgag.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import dnomyar.rxgag.R;
import dnomyar.rxgag.utils.PermissionManager;

/**
 * Created by Raymond on 2015-12-02.
 */
public class ActivityOne extends AppCompatActivity {

    Button mButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
        mButton = (Button) findViewById(R.id.btn);
        mButton.setOnClickListener(v -> {
            Intent i = new Intent(this, ActivityTwo.class);
            startActivity(i);
        });

//        PermissionManager.setActivity("onCreate:", this, this);
        PermissionManager.setActivity(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
//        PermissionManager.setActivity("onStart:", this, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        PermissionManager.setActivity(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
//        PermissionManager.setActivity("onStop:", this, null);
    }
}
