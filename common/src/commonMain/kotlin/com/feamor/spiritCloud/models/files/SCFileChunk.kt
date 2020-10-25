package com.feamor.spiritCloud.models.files

data class SCFileChunk(
    val index: Long,
    val size: Long,
    val content: ByteArray,
    val file: SCFile,
)