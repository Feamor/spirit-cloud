package com.feamor.spiritCloud.server.storages.entities

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.Instant
import javax.persistence.*

@Entity
@Table(name = "users")
data class UserEntity(
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    val id: Long?,

    @Column(nullable = false)
    val email: String,

    @Column(nullable = false)
    val password: String?,

    @Column(nullable = false)
    val name: String
) {
    @field:CreationTimestamp
    @Column(name = "create_date", updatable = false)
    lateinit var createDate: Instant

    @field:UpdateTimestamp
    @Column(name = "modify_date")
    lateinit var updateDate: Instant
}