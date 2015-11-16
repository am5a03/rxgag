package dnomyar.rxgag.repository;

import java.util.List;

import dnomyar.rxgag.models.db.GagItem;
import dnomyar.rxgag.models.wrapper.Gag;
import rx.Observable;

/**
 * Created by Raymond on 2015-11-17.
 */
public interface GagRepositoryInterface {
    Observable<List<GagItem>> getGagListItem(String section, String page);
    Observable<GagItem> replaceGagItem(Gag gag);
}
