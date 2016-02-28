package dnomyar.rxgag.repository.blitz.repository;

import android.database.sqlite.SQLiteOpenHelper;

import dnomyar.rxgag.repository.blitz.BlitzInfo;
import dnomyar.rxgag.repository.blitz.BlitzItem;

/**
 * Created by Raymond on 2016-02-26.
 */
public abstract class BlitzDiskRepository<DbModel, T extends BlitzItem> implements BlitzRepositoryInterface<T> {

    private BlitzInfo blitzInfo;

    public BlitzDiskRepository() {

    }

    abstract public void replaceItem(DbModel item);
    abstract public void appendList(DbModel item);
    abstract String getNextOffset();
}
