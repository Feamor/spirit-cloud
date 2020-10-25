package com.feamor.spiritCloud.server.misc

import org.hibernate.Session
import org.hibernate.SessionFactory
import org.hibernate.query.Query

abstract class HibernateManager {
    abstract val sessionFactory: SessionFactory

    fun <T>runWithTransaction(func: Session.() -> T): T {
        val session = this.sessionFactory.openSession()
        session.beginTransaction()
        val value = func.invoke(session)
        session.transaction.commit()
        session.close()

        return value
    }

    companion object {
        fun <T> Query<T>.getOne(): T? =
            this.resultList.firstOrNull()
    }
}