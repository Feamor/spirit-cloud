package com.feamor.spiritCloud.models

import com.feamor.spiritCloud.models.rights.RightActor

data class User(
    val id: Long?,
    val email: String,
    val password: String?,
    val name: String
) : RightActor