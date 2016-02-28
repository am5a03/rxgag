package dnomyar.rxgag.repository.blitz.repository;

import rx.Observable;

/**
 * Created by Raymond on 2016-02-28.
 */
public interface BlitzRepositoryInterface<S> {
    Observable<S> getObservable();
}
