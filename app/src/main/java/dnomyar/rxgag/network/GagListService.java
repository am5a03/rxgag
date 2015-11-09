package dnomyar.rxgag.network;

import java.util.List;

import dnomyar.rxgag.models.api.ApiGagResponse;
import dnomyar.rxgag.models.api.Gag;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by Raymond on 2015-11-02.
 */
public interface GagListService {
    @GET("/{section}/{page}")
    Observable<ApiGagResponse> getGagListResponse(@Path("section") String section, @Path("page") String page);
}
