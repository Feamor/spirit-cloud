package com.feamor.spiritCloud.models.files

import com.feamor.spiritCloud.models.rights.RightObject

data class SCFile(
    val id: Long?,
    val filename: String,
    val directory: SCDirectory?,
    val mimeType: String,
    val fileSize: Long,
    val chunkSize: Long,
): RightObject {
    fun getFullPath(): String {
        val directoryPath = directory?.getFullPath() ?: ""
        return "$directoryPath/$filename"
    }
}