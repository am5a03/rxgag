package dnomyar.rxgag.repository;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Raymond on 2015-11-17.
 */


public final class RealmObservable {
    private RealmObservable() {
    }

    public static <T extends RealmObject> Observable<T> object(Context context, final Func1<Realm, T> function) {
        return Observable.create(new OnSubscribeRealm<T>(context) {
            @Override
            public T get(Realm realm) {
                return function.call(realm);
            }
        });
    }

    public static <T extends RealmObject> Observable<T> object(Context context, RealmConfiguration realmConfiguration, final Func1<Realm, T> function) {
        return Observable.create(new OnSubscribeRealm<T>(context, realmConfiguration) {
            @Override
            public T get(Realm realm) {
                return function.call(realm);
            }
        });
    }
}


