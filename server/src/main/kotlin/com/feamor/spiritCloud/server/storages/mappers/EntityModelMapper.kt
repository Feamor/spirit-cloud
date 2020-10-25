package com.feamor.spiritCloud.server.storages.mappers

interface EntityModelMapper<E, M> {
    fun entityToModel(entity: E): M
    fun modelToEntity(model: M): E
}