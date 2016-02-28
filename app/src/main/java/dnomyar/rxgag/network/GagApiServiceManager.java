package dnomyar.rxgag.network;

import com.squareup.okhttp.OkHttpClient;

import dnomyar.rxgag.models.api.ApiGagResponse;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Observable;

/**
 * Created by Raymond on 2015-11-02.
 */
public class GagApiServiceManager {
    protected static final String HOST = "http://infinigag.k3min.eu";

    private GagListService mGagListService;


    public GagApiServiceManager(OkHttpClient client) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .baseUrl(HOST)
                .build();
        mGagListService = retrofit.create(GagListService.class);
    }

    public Observable<ApiGagResponse> getGagList(String section, String page) {
        return mGagListService
                .getGagListResponse(section, page);
    }
}
