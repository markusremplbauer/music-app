package at.htl.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Genre() {

    @Id
    @GeneratedValue
    var id: Long? = null;
    lateinit var name: String
    lateinit var description: String

    constructor(name: String, description: String) : this() {
        this.name = name
        this.description = description
    }
}