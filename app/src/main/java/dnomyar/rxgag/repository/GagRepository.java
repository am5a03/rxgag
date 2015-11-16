package dnomyar.rxgag.repository;

import android.content.Context;

import java.util.List;

import dnomyar.rxgag.models.db.GagImage;
import dnomyar.rxgag.models.db.GagItem;
import dnomyar.rxgag.models.db.GagVote;
import dnomyar.rxgag.models.wrapper.Gag;
import io.realm.Realm;
import io.realm.RealmObject;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Raymond on 2015-11-17.
 */
public class GagRepository implements GagRepositoryInterface {

    private final Context mContext;

    public GagRepository(Context context) {
        mContext = context;
    }

    @Override
    public Observable<List<GagItem>> getGagListItem(String section, String page) {
        return null;
    }

    @Override
    public Observable<GagItem> replaceGagItem(Gag gag) {
        RealmObservable.object(mContext, realm -> {
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
            return realm.copyToRealmOrUpdate(item);
        });
        return null;
    }
}
