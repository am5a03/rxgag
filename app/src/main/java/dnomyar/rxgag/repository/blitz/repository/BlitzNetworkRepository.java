package dnomyar.rxgag.repository.blitz.repository;

import dnomyar.rxgag.repository.blitz.BlitzItem;
import rx.Observable;

/**
 * Created by Raymond on 2016-02-28.
 */
abstract public class BlitzNetworkRepository<ApiModel, T extends BlitzItem> implements BlitzRepositoryInterface<T> {
    abstract public Observable<ApiModel> getNetworkObservable();
}
