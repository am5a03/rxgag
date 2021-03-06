package dnomyar.rxgag.repository;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import dnomyar.rxgag.models.db.GagImage;
import dnomyar.rxgag.models.db.GagItem;
import dnomyar.rxgag.models.db.GagVote;
import dnomyar.rxgag.models.wrapper.Gag;
import dnomyar.rxgag.repository.realm.rx.RealmObservable;
import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Raymond on 2015-11-17.
 */
public class GagRepository implements GagRepositoryInterface {

    private static final String TAG = "GagRepository";

    private final Context mContext;

    public GagRepository(Context context) {
        mContext = context;
    }

    @Override
    public Observable<List<Gag>> getGagList(String section, String page) {
        return RealmObservable.results(mContext, realm -> realm.where(GagItem.class)
                .equalTo("section", section)
                .findAll())
        .map(gagItems -> {
            ArrayList<Gag> gags = new ArrayList<Gag>();
            for (GagItem gagItem : gagItems) {
                Gag g = new Gag();
                g.id = gagItem.getId();
                g.caption = gagItem.getCaption();

                g.images = new Gag.Images();
                g.images.large = gagItem.getImages().getLarge();
                g.images.normal = gagItem.getImages().getNormal();
                g.images.small = gagItem.getImages().getSmall();

                g.votes = new Gag.Votes();
                g.votes.count = gagItem.getVotes().getCount();

                gags.add(g);
            }
            return gags;
        });
    }

    @Override
    public Observable<Gag> replaceGagItem(Gag gag, String section) {
        return RealmObservable.object(mContext, realm -> {
            GagItem item = new GagItem();
            item.setId(gag.id);
            item.setCaption(gag.caption);
            GagImage image = new GagImage();
            image.setLarge(gag.images.large);
            image.setNormal(gag.images.normal);
            image.setSmall(gag.images.small);

            GagVote vote = new GagVote();
            vote.setCount(gag.votes.count);

            item.setImages(image);
            item.setVotes(vote);
            Log.d(TAG, "replaceGagItem: " + item.getId());
            return realm.copyToRealmOrUpdate(item);
        }).map(gagItem -> null);
    }
}
