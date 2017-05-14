package id.sch.smktelkom_mlg.privateassignment.xirpl332.moviegeek.model;

import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by Shelin on 14/05/2017.
 */

public class Favorites extends SugarRecord implements Serializable {
    public String title;
    public String overview;
    public int color;

    public Favorites() {

    }

    public Favorites(String title, String overview, int color) {
        this.title = title;
        this.overview = overview;
        this.color = color;
    }
}
