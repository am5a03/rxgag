package dnomyar.rxgag.models.wrapper;

import java.util.List;

/**
 * Created by Raymond on 2015-11-11.
 */
public class GagListWrapper {
    List<ApiGag> mApiGagList;

    public GagListWrapper(List<ApiGag> apiGagList) {
        mApiGagList = mApiGagList;
    }

    public int size() {
        return mApiGagList.size();
    }
}
