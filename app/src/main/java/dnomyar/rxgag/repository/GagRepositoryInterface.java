package dnomyar.rxgag.repository;

import java.util.List;

import dnomyar.rxgag.models.wrapper.ApiGag;
import rx.Observable;

/**
 * Created by Raymond on 2015-11-17.
 */
public interface GagRepositoryInterface {
    Observable<List<ApiGag>> getGagList(String section, String page);
    Observable<ApiGag> replaceGagItem(ApiGag apiGag, String section);
}
