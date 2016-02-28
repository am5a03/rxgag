package dnomyar.rxgag.repository.sqlite;

import java.util.List;

import dnomyar.rxgag.models.db.sqlite.DaoSession;
import dnomyar.rxgag.models.db.sqlite.GagDao;
import dnomyar.rxgag.models.db.sqlite.GagListDao;
import dnomyar.rxgag.models.db.sqlite.GagListItemDao;
import dnomyar.rxgag.models.wrapper.Gag;
import dnomyar.rxgag.repository.GagRepositoryInterface;
import rx.Observable;

/**
 * Created by Raymond on 2016-02-28.
 */
public class SQLiteGagRepository implements GagRepositoryInterface {

    private GagDao mGagDao;
    private GagListDao mGagListDao;
    private GagListItemDao mGagListItemDao;

    public SQLiteGagRepository(DaoSession daoSession) {
        mGagDao = daoSession.getGagDao();
        mGagListDao = daoSession.getGagListDao();
        mGagListItemDao = daoSession.getGagListItemDao();
    }


    @Override
    public Observable<List<Gag>> getGagList(String section, String page) {
        return null;
    }

    @Override
    public Observable<Gag> replaceGagItem(Gag gag, String section) {
        return null;
    }
}
