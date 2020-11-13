package ru.study.hello.model

import javax.persistence.Entity
import javax.persistence.*

@Entity
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "USER_ID")
        var id: Int = 0,
        @Column(name = "EMAIL")
        var email : String = "",
        @Column(name = "PASSWORD")
        var password : String = "",
        @Column(name = "NAME")
        var name : String = "",
        @Column(name = "LAST_NAME")
        var lastName : String = "",
        @Column(name = "ACTIVE")
        var active : Int = 0,
        @ManyToMany(cascade = [CascadeType.ALL])
        @JoinTable(name = "USER_ROLE", joinColumns = [JoinColumn(name = "USER_ID")], inverseJoinColumns = [JoinColumn(name = "ROLE_ID")])
        var roles : Set<Role> = emptySet()
)