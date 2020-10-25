package com.feamor.spiritCloud.server.storages.mappers

import com.feamor.spiritCloud.models.files.SCDirectory
import com.feamor.spiritCloud.server.storages.entities.SCDirectoryEntity

object SCDirectoryMapper : EntityModelMapper<SCDirectoryEntity?, SCDirectory?> {
    override fun entityToModel(entity: SCDirectoryEntity?): SCDirectory? =
        entity?.let {
            SCDirectory(
                it.id,
                it.name,
                entityToModel(it.directory),
                it.files.mapNotNull(SCFileMapper::entityToModel)
            )
        }

    override fun modelToEntity(model: SCDirectory?): SCDirectoryEntity? =
        model?.let {
            SCDirectoryEntity(
                it.id,
                it.name,
                modelToEntity(it.directory),
                it.files.mapNotNull(SCFileMapper::modelToEntity)
            )
        }
}