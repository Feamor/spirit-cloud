package com.feamor.spiritCloud.server.misc

import org.hibernate.SessionFactory
import org.hibernate.boot.MetadataSources
import org.hibernate.boot.registry.StandardServiceRegistryBuilder

object HibernateManagerImpl: HibernateManager() {
    private const val configureFileName = "hibernate.cfg.xml"
    private val registry = StandardServiceRegistryBuilder()
        .configure(configureFileName)
        .build()

    override val sessionFactory: SessionFactory = try {
        MetadataSources(registry).buildMetadata().buildSessionFactory()
    } catch (e: Throwable) {
        throw ExceptionInInitializerError(e)
    }
}