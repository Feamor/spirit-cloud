package com.feamor.spiritCloud.exceptions

class SCFileNotFoundException(
    override val message: String,
    val path: String
) : Throwable()