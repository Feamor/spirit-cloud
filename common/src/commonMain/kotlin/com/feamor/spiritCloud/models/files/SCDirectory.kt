package com.feamor.spiritCloud.models.files

import com.feamor.spiritCloud.models.rights.RightObject

data class SCDirectory(
    val id: Long,
    val name: String,
    val directory: SCDirectory?,
    val files: List<SCFile> = emptyList()
) : RightObject {
    fun getFullPath(): String {
        if (directory == null) return ""
        return buildString {
            var currentDirectory = directory
            do {
                append(currentDirectory!!.name)
                append("/")
                currentDirectory = currentDirectory.directory
            } while (currentDirectory != null)
        }
    }
}