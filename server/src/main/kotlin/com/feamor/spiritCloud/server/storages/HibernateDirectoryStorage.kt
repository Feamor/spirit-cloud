package com.feamor.spiritCloud.server.storages

import com.feamor.spiritCloud.server.kodein
import com.feamor.spiritCloud.server.misc.HibernateManager
import com.feamor.spiritCloud.server.storages.entities.SCDirectoryEntity
import org.kodein.di.instance

class HibernateDirectoryStorage : DirectoryStorage {
    private val hibernateManager by kodein.instance<HibernateManager>()

    override fun create(entity: SCDirectoryEntity): SCDirectoryEntity =
        hibernateManager.runWithTransaction {
            val id = save(entity) as Long
            entity.copy(id = id)
        }

    override fun getById(id: Long): SCDirectoryEntity? =
        hibernateManager.runWithTransaction {
            get(SCDirectoryEntity::class.java, id)
        }

    override fun getList(): List<SCDirectoryEntity> =
        hibernateManager.runWithTransaction {
            createQuery("from SCDirectoryEntity u order by u.createDate", SCDirectoryEntity::class.java)
                .resultList
        }

    override fun update(entity: SCDirectoryEntity): SCDirectoryEntity? =
        hibernateManager.runWithTransaction {
            update(entity)
            entity
        }

    override fun delete(entity: SCDirectoryEntity): SCDirectoryEntity? =
        hibernateManager.runWithTransaction {
            delete(entity)
            entity
        }
}