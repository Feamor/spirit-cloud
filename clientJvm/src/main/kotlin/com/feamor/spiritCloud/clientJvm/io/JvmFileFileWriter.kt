package com.feamor.spiritCloud.clientJvm.io

import com.feamor.spiritCloud.exceptions.SCFileNotFoundException
import com.feamor.spiritCloud.exceptions.SCNotFileException
import com.feamor.spiritCloud.io.SCFileWriter
import com.feamor.spiritCloud.models.files.SCFile
import com.feamor.spiritCloud.models.files.SCFileChunk
import java.io.File
import java.io.RandomAccessFile

class JvmFileFileWriter(
    private val basePath: String
) : SCFileWriter {
    override suspend fun writeFile(file: SCFile): SCFile {
        val resPath = "$basePath${File.separator}${file.getFullPath()}"
        val jvmFile = File(resPath)
        jvmFile.parentFile.mkdirs()
        jvmFile.createNewFile()
        return file
    }

    override suspend fun writeChunk(chunk: SCFileChunk): SCFileChunk {
        val resPath = "$basePath${File.separator}${chunk.file.getFullPath()}"
        val jvmFile = File(resPath)
        if (!jvmFile.exists()) {
            throw SCFileNotFoundException("Not found", resPath)
        }
        if (!jvmFile.isFile) {
            throw SCNotFileException(resPath)
        }
        val raf = RandomAccessFile(jvmFile, "rw")

        val startByte = chunk.file.chunkSize * chunk.index
        raf.seek(startByte)
        raf.write(chunk.content)
        raf.close()
        return chunk
    }
}