package dnomyar.rxgag.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import dnomyar.rxgag.models.db.sqlite.DaoMaster;
import dnomyar.rxgag.models.db.sqlite.DaoSession;
import dnomyar.rxgag.repository.sqlite.SQLiteGagRepository;

/**
 * Created by Raymond on 2016-02-28.
 */
public class SQLiteDatabaseHelper {
    private SQLiteDatabase db;
    private DaoMaster daoMaster;
    private DaoSession daoSession;

    private SQLiteGagRepository mGagRepositoryInterface;

    private static final SQLiteDatabaseHelper sInstance = new SQLiteDatabaseHelper();

    public synchronized void init(Context context) {
        if (db == null) {
            DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "gag-db", null);
            db = helper.getWritableDatabase();
        }

        if (daoMaster == null) {
            daoMaster = new DaoMaster(db);
            daoSession = daoMaster.newSession();
        }

        mGagRepositoryInterface = new SQLiteGagRepository(daoSession);
    }

    public static SQLiteDatabaseHelper getInstance() {
        return sInstance;
    }

    public SQLiteGagRepository getGagRepository() {
        return mGagRepositoryInterface;
    }

    public SQLiteDatabase getDb() {
        return db;
    }
}
