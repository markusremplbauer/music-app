package at.htl.entity;

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Song extends PanacheEntity {

    public String title;

    @OneToMany
    public List<Genre> genres;

    @ManyToOne
    public Artist artist;
}
