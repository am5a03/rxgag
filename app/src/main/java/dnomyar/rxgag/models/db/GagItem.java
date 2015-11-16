package dnomyar.rxgag.models.db;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Raymond on 2015-11-17.
 */
public class GagItem extends RealmObject {
    @PrimaryKey
    private String id;
    private String caption;
    private String link;
    private GagImage images;
    private GagVote votes;

    public void setId(String id) {
        this.id = id;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public void setImages(GagImage images) {
        this.images = images;
    }

    public void setVotes(GagVote votes) {
        this.votes = votes;
    }

    public String getId() {
        return id;
    }

    public String getCaption() {
        return caption;
    }

    public GagImage getImages() {
        return images;
    }

    public GagVote getVotes() {
        return votes;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
