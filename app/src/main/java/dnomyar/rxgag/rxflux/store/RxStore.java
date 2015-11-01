package dnomyar.rxgag.rxflux.store;

import dnomyar.rxgag.rxflux.dispatcher.Dispatcher;
import dnomyar.rxgag.rxflux.dispatcher.RxActionDispatch;

/**
 * Created by marcel on 10/09/15.
 */
public abstract class RxStore implements RxActionDispatch {

    private final Dispatcher dispatcher;

    public RxStore(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public void register() {
        dispatcher.registerRxAction(this);
    }

    protected void postChange(RxStoreChange change) {
        dispatcher.postRxStoreChange(change);
    }
}
