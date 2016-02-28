package dnomyar.rxgag.repository.realm;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import dnomyar.rxgag.models.db.realm.GagImage;
import dnomyar.rxgag.models.db.realm.GagItem;
import dnomyar.rxgag.models.db.realm.GagVote;
import dnomyar.rxgag.models.wrapper.ApiGag;
import dnomyar.rxgag.repository.GagRepositoryInterface;
import dnomyar.rxgag.repository.realm.rx.RealmObservable;
import rx.Observable;

/**
 * Created by Raymond on 2015-11-17.
 */
public class RealmGagRepository implements GagRepositoryInterface {

    private static final String TAG = "RealmGagRepository";

    private final Context mContext;

    public RealmGagRepository(Context context) {
        mContext = context;
    }

    @Override
    public Observable<List<ApiGag>> getGagList(String section, String page) {
        return RealmObservable.results(mContext, realm -> realm.where(GagItem.class)
                .equalTo("section", section)
                .findAll())
        .map(gagItems -> {
            ArrayList<ApiGag> apiGags = new ArrayList<ApiGag>();
            for (GagItem gagItem : gagItems) {
                ApiGag g = new ApiGag();
                g.id = gagItem.getId();
                g.caption = gagItem.getCaption();

                g.images = new ApiGag.Images();
                g.images.large = gagItem.getImages().getLarge();
                g.images.normal = gagItem.getImages().getNormal();
                g.images.small = gagItem.getImages().getSmall();

                g.votes = new ApiGag.Votes();
                g.votes.count = gagItem.getVotes().getCount();

                apiGags.add(g);
            }
            return apiGags;
        });
    }

    @Override
    public Observable<ApiGag> replaceGagItem(ApiGag apiGag, String section) {
        return RealmObservable.object(mContext, realm -> {
            GagItem item = new GagItem();
            item.setId(apiGag.id);
            item.setCaption(apiGag.caption);
            GagImage image = new GagImage();
            image.setLarge(apiGag.images.large);
            image.setNormal(apiGag.images.normal);
            image.setSmall(apiGag.images.small);

            GagVote vote = new GagVote();
            vote.setCount(apiGag.votes.count);

            item.setImages(image);
            item.setVotes(vote);
            Log.d(TAG, "replaceGagItem: " + item.getId());
            return realm.copyToRealmOrUpdate(item);
        }).map(gagItem -> null);
    }
}
