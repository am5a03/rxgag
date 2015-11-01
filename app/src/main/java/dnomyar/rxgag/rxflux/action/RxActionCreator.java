package dnomyar.rxgag.rxflux.action;

import android.support.annotation.NonNull;

import dnomyar.rxgag.rxflux.dispatcher.Dispatcher;
import dnomyar.rxgag.rxflux.util.SubscriptionManager;

import rx.Subscription;

/**
 * Created by marcel on 10/09/15.
 */
public abstract class RxActionCreator {

    private final Dispatcher dispatcher;
    private final SubscriptionManager manager;

    public RxActionCreator(Dispatcher dispatcher, SubscriptionManager manager) {
        this.dispatcher = dispatcher;
        this.manager = manager;
    }

    public void addRxAction(RxAction rxAction, Subscription subscription) {
        manager.add(rxAction, subscription);
    }

    public boolean hasRxAction(RxAction rxAction) {
        return manager.contains(rxAction);
    }

    public void removeRxAction(RxAction rxAction) {
        manager.remove(rxAction);
    }

    public RxAction newRxAction(@NonNull String actionId, @NonNull Object... data) {
        if (actionId.isEmpty()) {
            throw new IllegalArgumentException("Type must not be empty");
        }

        if (data.length % 2 != 0) {
            throw new IllegalArgumentException("Data must be a valid list of key,value pairs");
        }

        RxAction.Builder actionBuilder = RxAction.type(actionId);
        int i = 0;
        while (i < data.length) {
            String key = (String) data[i++];
            Object value = data[i++];
            actionBuilder.bundle(key, value);
        }
        return actionBuilder.build();
    }

    public void postRxAction(@NonNull RxAction action) {
        dispatcher.postRxAction(action);
    }

    public void postError(RxAction action, Throwable throwable) {
        dispatcher.postRxAction(RxError.newRxError(action, throwable));
    }

}
