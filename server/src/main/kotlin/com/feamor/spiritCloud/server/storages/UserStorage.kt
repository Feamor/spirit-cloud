package com.feamor.spiritCloud.server.storages

import com.feamor.spiritCloud.server.storages.entities.UserEntity

interface UserStorage : RestStorage<UserEntity> {
    fun getByEmail(email: String): UserEntity?
}