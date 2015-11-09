package dnomyar.rxgag.ui.activities;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dnomyar.rxgag.R;
import dnomyar.rxgag.ui.fragments.PostListFragment;
import dnomyar.rxgag.ui.fragments.PostListMainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, PostListMainFragment.newInstance());
        ft.commit();
    }
}
