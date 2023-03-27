package at.htl.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Artist() {
    @Id
    @GeneratedValue
    var id: Long? = null;
    lateinit var firstName: String;
    lateinit var lastName: String;
    lateinit var alias: String;
    lateinit var info: String;

    constructor(firstName: String, lastName: String, alias: String, info: String) : this() {
        this.firstName = firstName
        this.lastName = lastName
        this.alias = alias
        this.info = info
    }
}