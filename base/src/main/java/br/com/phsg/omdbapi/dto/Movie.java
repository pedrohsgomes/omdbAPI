package br.com.phsg.omdbapi.dto;

import java.io.Serializable;
import java.util.List;

public class Movie implements Serializable {
    public String Title;
    public String Year;
    public String imdbID;
    public String Type;
    public String Poster;

    public List<Rating> Ratings;

    @Override
    public String toString() {
        return "\nMovie{" +
                "Title='" + Title + '\'' +
                ", Year='" + Year + '\'' +
                ", imdbID='" + imdbID + '\'' +
                ", Type='" + Type + '\'' +
                ", Poster='" + Poster + '\'' +
                '}';
    }

}
