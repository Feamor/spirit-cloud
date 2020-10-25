package com.feamor.spiritCloud.server.storages

import com.feamor.spiritCloud.server.kodein
import com.feamor.spiritCloud.server.misc.HibernateManager
import com.feamor.spiritCloud.server.misc.HibernateManager.Companion.getOne
import com.feamor.spiritCloud.server.storages.entities.UserEntity
import org.kodein.di.instance

class HibernateUserStorage : UserStorage {
    private val hibernateManager by kodein.instance<HibernateManager>()

    override fun getByEmail(email: String): UserEntity? =
        hibernateManager.runWithTransaction {
            createQuery("select e from UserEntity e where e.email = :email", UserEntity::class.java)
                .setParameter("email", email)
                .getOne()
        }

    override fun create(entity: UserEntity): UserEntity =
        hibernateManager.runWithTransaction {
            val id = save(entity) as Long
            entity.copy(id = id)
        }

    override fun getById(id: Long): UserEntity? =
        hibernateManager.runWithTransaction {
            get(UserEntity::class.java, id)
        }

    override fun getList(): List<UserEntity> =
        hibernateManager.runWithTransaction {
            createQuery("from UserEntity u order by u.createDate", UserEntity::class.java)
                .resultList
        }

    override fun update(entity: UserEntity): UserEntity? =
        hibernateManager.runWithTransaction {
            update(entity)
            entity
        }

    override fun delete(entity: UserEntity): UserEntity? =
        hibernateManager.runWithTransaction {
            delete(entity)
            entity
        }
}