package dnomyar.rxgag.models.api;

/**
 * Created by Raymond on 2015-11-01.
 */
public class ApiGagResponse {
    public Gag[] data;
    public Paging paging;
    public static class Paging {
        public String next;
    }
}
