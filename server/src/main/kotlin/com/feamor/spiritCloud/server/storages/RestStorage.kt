package com.feamor.spiritCloud.server.storages

import com.feamor.spiritCloud.server.misc.HibernateManager

interface RestStorage<E> {
    fun create(entity: E): E
    fun getById(id: Long): E?
    fun getList(): List<E>
    fun update(entity: E): E?
    fun delete(entity: E): E?
}