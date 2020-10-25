package com.feamor.spiritCloud.io

import com.feamor.spiritCloud.models.files.SCFile
import com.feamor.spiritCloud.models.files.SCFileChunk

interface SCFileReader {
    suspend fun readFile(path: String): SCFile
    suspend fun readChunk(file: SCFile, index: Long): SCFileChunk
}