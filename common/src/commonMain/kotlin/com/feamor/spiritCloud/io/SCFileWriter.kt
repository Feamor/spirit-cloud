package com.feamor.spiritCloud.io

import com.feamor.spiritCloud.models.files.SCFile
import com.feamor.spiritCloud.models.files.SCFileChunk

interface SCFileWriter {
    suspend fun writeFile(file: SCFile) : SCFile
    suspend fun writeChunk(chunk: SCFileChunk) : SCFileChunk
}