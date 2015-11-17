package dnomyar.rxgag.repository;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.RealmResults;
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

    public static <T extends RealmObject> Observable<RealmList<T>> list(Context context, final Func1<Realm, RealmList<T>> function) {
        return Observable.create(new OnSubscribeRealm<RealmList<T>>(context) {
            @Override
            public RealmList<T> get(Realm realm) {
                return function.call(realm);
            }
        });
    }

    public static <T extends RealmObject> Observable<RealmList<T>> list(Context context, RealmConfiguration realmConfiguration, final Func1<Realm, RealmList<T>> function) {
        return Observable.create(new OnSubscribeRealm<RealmList<T>>(context, realmConfiguration) {
            @Override
            public RealmList<T> get(Realm realm) {
                return function.call(realm);
            }
        });
    }

    public static <T extends RealmObject> Observable<RealmResults<T>> results(Context context, final Func1<Realm, RealmResults<T>> function) {
        return Observable.create(new OnSubscribeRealm<RealmResults<T>>(context) {
            @Override
            public RealmResults<T> get(Realm realm) {
                return function.call(realm);
            }
        });
    }

    public static <T extends RealmObject> Observable<RealmResults<T>> results(Context context, RealmConfiguration realmConfiguration, final Func1<Realm, RealmResults<T>> function) {
        return Observable.create(new OnSubscribeRealm<RealmResults<T>>(context, realmConfiguration) {
            @Override
            public RealmResults<T> get(Realm realm) {
                return function.call(realm);
            }
        });
    }
}


