package io.zensoft.auditing.envers.entity

import org.hibernate.envers.Audited
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Audited(withModifiedFlag = true)

@Entity
@Table(name = "persons")
class Person(

        @Column(name = "name")
        var name: String,
        @Column(name = "age")
        var age: Int?

): BaseEntity()