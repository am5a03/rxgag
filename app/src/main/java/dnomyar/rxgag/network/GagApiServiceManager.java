package dnomyar.rxgag.network;

import com.squareup.okhttp.OkHttpClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dnomyar.rxgag.models.api.ApiGagResponse;
import dnomyar.rxgag.models.api.Gag;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Raymond on 2015-11-02.
 */
public class GagApiServiceManager {
    protected static final String HOST = "http://infinigag.eu01.aws.af.cm";

    private GagListService mGagListService;

    public GagApiServiceManager() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(new OkHttpClient())
                .baseUrl(HOST)
                .build();
        mGagListService = retrofit.create(GagListService.class);
    }

    public Observable<List<Gag>> getGagList(String section, String page) {
        return mGagListService
                .getGagListResponse(section, page)
                .map(new Func1<ApiGagResponse, List<Gag>>() {
                    @Override
                    public List<Gag> call(ApiGagResponse apiGagResponse) {
                        return Arrays.asList(apiGagResponse.data);
                    }
                });
    }
}
