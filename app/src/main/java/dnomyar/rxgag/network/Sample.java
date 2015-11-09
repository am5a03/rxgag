package dnomyar.rxgag.network;

import java.util.Arrays;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.internal.operators.OnSubscribeFromIterable;

/**
 * Created by Raymond on 2015-11-06.
 */
public class Sample {
    public void a() {
        Integer[] someNumbers = {1, 2, 3, 4, 5, 6};
        for (int i = 0; i < someNumbers.length; i++) {
            System.out.println(i);
        }

        Observable.create(new OnSubscribeFromIterable<>(Arrays.asList(someNumbers))).subscribe(new Action1<Object>() {
            @Override
            public void call(Object o) {

            }
        });

        Observable.just(someNumbers).subscribe(new Action1<Integer[]>() {
            @Override
            public void call(Integer[] integers) {

            }
        });
        Observable.from(someNumbers).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println(integer);
            }
        });

        Observable
                .from(someNumbers)
                .map((Integer integer) -> integer + 1)
                .subscribe(System.out::println);
    }
}
