package dnomyar.rxgag.models.db.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Raymond on 2016-02-24.
 */
public class GagList extends RealmObject {
    @PrimaryKey
    private String listKey;
    private String nextOffset;
    private GagListItem gagListItem;

    public String getListKey() {
        return listKey;
    }

    public void setListKey(String listKey) {
        this.listKey = listKey;
    }

    public String getNextOffset() {
        return nextOffset;
    }

    public void setNextOffset(String nextOffset) {
        this.nextOffset = nextOffset;
    }

    public GagListItem getGagListItem() {
        return gagListItem;
    }

    public void setGagListItem(GagListItem gagListItem) {
        this.gagListItem = gagListItem;
    }
}
