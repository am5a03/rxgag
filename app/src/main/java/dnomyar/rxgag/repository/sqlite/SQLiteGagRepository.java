package dnomyar.rxgag.repository.sqlite;

import dnomyar.rxgag.models.db.sqlite.DaoSession;
import dnomyar.rxgag.models.db.sqlite.GagDao;
import dnomyar.rxgag.models.db.sqlite.GagListDao;
import dnomyar.rxgag.models.db.sqlite.GagListItemDao;

/**
 * Created by Raymond on 2016-02-28.
 */
public class SQLiteGagRepository {

    private GagDao mGagDao;
    private GagListDao mGagListDao;
    private GagListItemDao mGagListItemDao;

    public SQLiteGagRepository(DaoSession daoSession) {
        mGagDao = daoSession.getGagDao();
        mGagListDao = daoSession.getGagListDao();
        mGagListItemDao = daoSession.getGagListItemDao();
    }
}
