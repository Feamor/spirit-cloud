package com.feamor.spiritCloud.server

import com.feamor.spiritCloud.server.misc.HibernateManager
import com.feamor.spiritCloud.server.misc.HibernateManagerImpl
import org.kodein.di.bind
import org.kodein.di.conf.ConfigurableDI
import org.kodein.di.singleton

val kodein = ConfigurableDI(mutable = true).also {
    it.addConfig {
        bind<HibernateManager>() with singleton { HibernateManagerImpl }
    }
}