package dnomyar.rxgag.repository.blitz;

import java.util.concurrent.CopyOnWriteArrayList;

import dnomyar.rxgag.repository.blitz.repository.BlitzDiskRepository;
import dnomyar.rxgag.repository.blitz.repository.BlitzNetworkRepository;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.subjects.PublishSubject;

/**
 * Created by Raymond on 2016-02-26.
 */
abstract public class RxBlitz<T extends BlitzItem> extends CopyOnWriteArrayList<T> {
    private BlitzInfo blitzInfo;

    private final PublishSubject<Integer> changes = PublishSubject.create();

    public RxBlitz() {

    }

    @Override
    public synchronized boolean add(T t) {
        return super.add(t);
    }

    @Override
    public synchronized boolean remove(Object o) {
        return super.remove(o);
    }

    @Override
    public synchronized T remove(int index) {
        return super.remove(index);
    }

    public Observable<T> getListObservable() {
        return null;
    }
}
