package dnomyar.rxgag.repository.realm.rx;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.exceptions.RealmException;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.subscriptions.Subscriptions;

/**
 * Created by Raymond on 2015-11-17.
 */


public abstract class OnSubscribeRealm<T> implements Observable.OnSubscribe<T> {
    private Context context;
    private RealmConfiguration mRealmConfiguration;

    public OnSubscribeRealm(Context context) {
        this.context = context;
        mRealmConfiguration = null;
    }

    public OnSubscribeRealm(Context context, RealmConfiguration realmConfiguration) {
        this.context = context;
        this.mRealmConfiguration = realmConfiguration;
    }

    @Override
    public void call(final Subscriber<? super T> subscriber) {
        final Realm realm = mRealmConfiguration != null ? Realm.getInstance(mRealmConfiguration) : Realm.getInstance(context);
        subscriber.add(Subscriptions.create(() -> {
            try {
                realm.close();
            } catch (RealmException ex) {
                subscriber.onError(ex);
            }
        }));

        T object;
        realm.beginTransaction();
        try {
            object = get(realm);
            realm.commitTransaction();
        } catch (RuntimeException e) {
            realm.cancelTransaction();
            subscriber.onError(new RealmException("Error during transaction.", e));
            return;
        } catch (Error e) {
            realm.cancelTransaction();
            subscriber.onError(e);
            return;
        }
        if (object != null) {
            subscriber.onNext(object);
        }
        subscriber.onCompleted();
    }

    public abstract T get(Realm realm);
}


