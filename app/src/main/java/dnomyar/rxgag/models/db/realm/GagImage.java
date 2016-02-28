package dnomyar.rxgag.models.db.realm;

import io.realm.RealmObject;

/**
 * Created by Raymond on 2015-11-17.
 */
public class GagImage extends RealmObject {
    private String small, normal, large;

    public String getSmall() {
        return small;
    }

    public String getNormal() {
        return normal;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public void setNormal(String normal) {
        this.normal = normal;
    }

    public void setSmall(String small) {
        this.small = small;
    }
}
