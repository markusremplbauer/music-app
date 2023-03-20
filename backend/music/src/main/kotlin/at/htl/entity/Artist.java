package at.htl.entity;

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity;

import javax.persistence.Entity;

@Entity
public class Artist extends PanacheEntity {
    public String firstName;

    public String lastName;

    public String alias;

    public String info;

    public Artist() {
    }

    public Artist(String firstName, String lastName, String alias, String info) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.alias = alias;
        this.info = info;
    }
}