package at.htl.entity

import javax.persistence.*

@Entity
class Song() {

    @Id
    @GeneratedValue
    var id: Long? = null;

    lateinit var title: String

    @ManyToOne
    lateinit var artist: Artist

    @ManyToMany
    var genres: List<Genre> = emptyList()

    constructor(title: String, artist: Artist, genres: List<Genre>) : this() {
        this.title = title
        this.artist = artist
        this.genres = genres
    }
}