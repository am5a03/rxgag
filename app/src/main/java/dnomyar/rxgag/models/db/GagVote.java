package dnomyar.rxgag.models.db;

import io.realm.RealmObject;

/**
 * Created by Raymond on 2015-11-17.
 */
public class GagVote extends RealmObject {
    private int count;

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }
}
