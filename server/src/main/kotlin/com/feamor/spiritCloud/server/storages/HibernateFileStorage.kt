package com.feamor.spiritCloud.server.storages

import com.feamor.spiritCloud.server.kodein
import com.feamor.spiritCloud.server.misc.HibernateManager
import com.feamor.spiritCloud.server.storages.entities.SCFileEntity
import org.kodein.di.instance

class HibernateFileStorage : FileStorage {
    private val hibernateManager by kodein.instance<HibernateManager>()

    override fun create(entity: SCFileEntity): SCFileEntity =
        hibernateManager.runWithTransaction {
            val id = save(entity) as Long
            entity.copy(id = id)
        }

    override fun getById(id: Long): SCFileEntity? =
        hibernateManager.runWithTransaction {
            get(SCFileEntity::class.java, id)
        }

    override fun getList(): List<SCFileEntity> =
        hibernateManager.runWithTransaction {
            createQuery("from SCFileEntity u order by u.createDate", SCFileEntity::class.java)
                .resultList
        }

    override fun update(entity: SCFileEntity): SCFileEntity?  =
        hibernateManager.runWithTransaction {
            update(entity)
            entity
        }

    override fun delete(entity: SCFileEntity): SCFileEntity? =
        hibernateManager.runWithTransaction {
            delete(entity)
            entity
        }
}