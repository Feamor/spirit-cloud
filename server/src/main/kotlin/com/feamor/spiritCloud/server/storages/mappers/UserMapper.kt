package com.feamor.spiritCloud.server.storages.mappers

import com.feamor.spiritCloud.models.User
import com.feamor.spiritCloud.server.storages.entities.UserEntity

object UserMapper : EntityModelMapper<UserEntity?, User?> {
    override fun entityToModel(entity: UserEntity?): User? =
        entity?.let {
            User(
                it.id,
                it.email,
                null,
                it.name
            )
        }

    override fun modelToEntity(model: User?): UserEntity? =
        model?.let {
            UserEntity(
                it.id,
                it.email,
                it.password,
                it.name
            )
        }
}