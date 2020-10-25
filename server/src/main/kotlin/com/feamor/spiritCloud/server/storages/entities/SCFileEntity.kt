package com.feamor.spiritCloud.server.storages.entities

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.Instant
import javax.persistence.*

@Entity
@Table(name = "files")
data class SCFileEntity(
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    val id: Long?,

    val filename: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "directory_id")
    val directory: SCDirectoryEntity?,

    @Column(name = "mime_type")
    val mimeType: String,

    @Column(name = "file_size")
    val fileSize: Long,

    @Column(name = "chunk_size")
    val chunkSize: Long,
) {
    @field:CreationTimestamp
    @Column(name = "create_date", updatable = false)
    lateinit var createDate: Instant

    @field:UpdateTimestamp
    @Column(name = "modify_date")
    lateinit var updateDate: Instant
}