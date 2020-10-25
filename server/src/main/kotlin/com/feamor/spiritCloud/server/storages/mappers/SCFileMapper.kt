package com.feamor.spiritCloud.server.storages.mappers

import com.feamor.spiritCloud.models.files.SCFile
import com.feamor.spiritCloud.server.storages.entities.SCFileEntity

object SCFileMapper : EntityModelMapper<SCFileEntity?, SCFile?> {
    override fun entityToModel(entity: SCFileEntity?): SCFile? =
        entity?.let {
            SCFile(
                it.id,
                it.filename,
                SCDirectoryMapper.entityToModel(it.directory),
                it.mimeType,
                it.fileSize,
                it.chunkSize,
            )
        }

    override fun modelToEntity(model: SCFile?): SCFileEntity? =
        model?.let {
            SCFileEntity(
                it.id,
                it.filename,
                SCDirectoryMapper.modelToEntity(it.directory),
                it.mimeType,
                it.fileSize,
                it.chunkSize,
            )
        }
}