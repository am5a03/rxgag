package dnomyar.rxgag.repository;

import dnomyar.rxgag.models.api.ApiGagResponse;
import dnomyar.rxgag.models.wrapper.GagWrapper;
import dnomyar.rxgag.network.GagApiServiceManager;
import dnomyar.rxgag.repository.blitz.repository.BlitzNetworkRepository;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Raymond on 2016-02-28.
 */
public class NetworkGagRepository extends BlitzNetworkRepository<ApiGagResponse, GagWrapper> {

    private GagApiServiceManager mGagApiServiceManager;

    private NetworkGagRepository(GagApiServiceManager apiServiceManager) {
        mGagApiServiceManager = apiServiceManager;
    }

    @Override
    public Observable<ApiGagResponse> getNetworkObservable() {
        return mGagApiServiceManager.getGagList("", "");
    }

    @Override
    public Observable<GagWrapper> getObservable() {
        return mGagApiServiceManager.getGagList("", "")

                .map(new Func1<ApiGagResponse, GagWrapper>() {
            @Override
            public GagWrapper call(ApiGagResponse apiGagResponse) {
                return null;
            }
        });
    }
}
