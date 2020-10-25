package com.feamor.spiritCloud.models.rights

data class Right(
    val id: Long,
    val actor: RightActor,
    val obj: RightObject,
    val crud: Int
)