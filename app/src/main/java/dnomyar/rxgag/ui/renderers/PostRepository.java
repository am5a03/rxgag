package dnomyar.rxgag.ui.renderers;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Raymond on 2015-11-19.
 */
public class PostRepository {
    public Observable<Post> getPosts() {
        return Observable.create(new Observable.OnSubscribe<Post>() {
            @Override
            public void call(Subscriber<? super Post> subscriber) {
                try {
                    // Emit items
                    // getAllPosts() From DB, network ...
                    List<Post> posts = getAllPosts();
                    for (Post p : posts) {
                        subscriber.onNext(p);
                    }
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    public List<Post> getAllPosts() {
        return null;
    };

    public static class Post {

    }
}
