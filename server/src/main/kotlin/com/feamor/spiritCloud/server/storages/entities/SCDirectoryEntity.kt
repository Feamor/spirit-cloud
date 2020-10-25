package com.feamor.spiritCloud.server.storages.entities

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.Instant
import javax.persistence.*

@Entity
@Table(name = "directories")
data class SCDirectoryEntity(
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    val id: Long,

    val name: String,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "directory_id")
    val directory: SCDirectoryEntity?,

    @OneToMany(mappedBy = "directory", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY)
    val files: List<SCFileEntity>
) {
    @field:CreationTimestamp
    @Column(name = "create_date", updatable = false)
    lateinit var createDate: Instant

    @field:UpdateTimestamp
    @Column(name = "modify_date")
    lateinit var updateDate: Instant
}