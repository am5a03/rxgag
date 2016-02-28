package dnomyar.rxgag.models.db.realm;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Raymond on 2016-02-24.
 */
public class GagListItem extends RealmObject {
    @PrimaryKey
    private String listKey;
    private RealmList<GagItem> gagItemList;

    public String getListKey() {
        return listKey;
    }

    public void setListKey(String listKey) {
        this.listKey = listKey;
    }

    public RealmList<GagItem> getGagItemList() {
        return gagItemList;
    }

    public void setGagItemList(RealmList<GagItem> gagItemList) {
        this.gagItemList = gagItemList;
    }
}
