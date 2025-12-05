package com.udemy.openapidemo.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.annotations.Type
import org.hibernate.type.SqlTypes
import java.util.UUID


@Entity
@Table(name = "post")
class PostEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "UUID")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "id", nullable = false)
    val id: UUID? = null
) {

    @Column(name = "title", nullable = false)
    var title: String? = null

    @Column(name = "description", nullable = false)
    var description: String? = null

    @Column(name = "authorName", nullable = false)
    var authorName: String? = null
}