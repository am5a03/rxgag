package dnomyar.rxgag.models.api;

import dnomyar.rxgag.models.wrapper.ApiGag;

/**
 * Created by Raymond on 2015-11-01.
 */
public class ApiGagResponse {
    public ApiGag[] data;
    public Paging paging;
    public static class Paging {
        public String next;
    }
}
