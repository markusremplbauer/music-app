package at.htl.entity;

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Song extends PanacheEntity {

    public String title;

    @ManyToOne
    public Artist artist;

    @ManyToMany
    public List<Genre> genres;

    public Song() {
        this.genres = new ArrayList<>();
    }

    public Song(String title, Artist artist) {
        this.title = title;
        this.artist = artist;
    }

    public Song(String title, Artist artist, List<Genre> genres) {
        this.title = title;
        this.artist = artist;
        this.genres = genres;
    }
}
