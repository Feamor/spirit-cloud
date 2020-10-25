package com.feamor.spiritCloud.models

import com.feamor.spiritCloud.models.rights.RightActor

data class Group(
    val id: Long,
    val users: List<User> = emptyList()
) : RightActor