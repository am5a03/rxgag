package dnomyar.rxgag.ui.activities;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.facebook.common.util.UriUtil;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import dnomyar.rxgag.R;
import dnomyar.rxgag.ui.views.MyCustomView;

/**
 * Created by Raymond on 2015-11-21.
 */
public class MyDraweeActivity extends AppCompatActivity {
    private static final String TAG = "MyDraweeActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_drawee);

        MyCustomView v = (MyCustomView) findViewById(R.id.view);
//        ImageRequest r = ImageRequestBuilder
//                .newBuilderWithResourceId(R.drawable.map)
//                .build();
//
//        DraweeController c = Fresco.newDraweeControllerBuilder()
//                .setImageRequest(r)
//                .build();
//        v.setController(c);
        Uri u = new Uri.Builder()
                .scheme(UriUtil.LOCAL_RESOURCE_SCHEME)
                .path(String.valueOf(R.drawable.worldpage_gfx_world))
                .build();
        v.setImageURI(Uri.parse("res:///" + String.valueOf(R.drawable.worldpage_gfx_world)));
        Log.d(TAG, "onCreate: " + Uri.fromParts(UriUtil.LOCAL_RESOURCE_SCHEME, "map", ""));
    }
}
