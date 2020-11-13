package ru.study.hello.model

import javax.persistence.*

@Entity
data class Role (
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "ROLE_ID")
        var id: Int = 0,
        @Column(name = "ROLE")
        var role: String = ""
)