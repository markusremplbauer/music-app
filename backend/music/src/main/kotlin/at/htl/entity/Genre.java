package at.htl.entity;

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity;

import javax.persistence.Entity;

@Entity
public class Genre extends PanacheEntity {
    public String name;
    public String description;

    public Genre() {
    }

    public Genre(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
