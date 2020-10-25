package com.feamor.spiritCloud.server.misc

import com.feamor.spiritCloud.models.User
import io.ktor.auth.*

data class UserPrincipal(
    val user: User
): Principal