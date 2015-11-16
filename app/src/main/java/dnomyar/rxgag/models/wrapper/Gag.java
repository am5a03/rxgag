package dnomyar.rxgag.models.wrapper;

/**
 * Created by Raymond on 2015-11-01.
 */
public class Gag {
    public String id;
    public String caption;
    public String link;
    public Images images;
    public Votes votes;

    public static class Images {
        public String small, normal, large;
    }

    public static class Votes {
        public int count;
    }
}
