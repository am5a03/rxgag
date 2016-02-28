package dnomyar.rxgag.models.wrapper;

import java.util.List;

/**
 * Created by Raymond on 2015-11-11.
 */
public class GagListWrapper {
    List<Gag> mGagList;

    public GagListWrapper(List<Gag> gagList) {
        mGagList = mGagList;
    }

    public int size() {
        return mGagList.size();
    }
}
