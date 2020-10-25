package com.feamor.spiritCloud.clientJvm.io

import com.feamor.spiritCloud.DEFAULT_CHUNK_SIZE
import com.feamor.spiritCloud.exceptions.SCFileNotFoundException
import com.feamor.spiritCloud.exceptions.SCNotFileException
import com.feamor.spiritCloud.io.SCFileReader
import com.feamor.spiritCloud.models.files.SCFile
import com.feamor.spiritCloud.models.files.SCFileChunk
import java.io.File
import java.io.RandomAccessFile
import java.nio.file.Files

@Suppress("BlockingMethodInNonBlockingContext")
class JvmFileReader(
    private val basePath: String
) : SCFileReader {
    override suspend fun readFile(path: String): SCFile {
        val jvmFile = openJvmFile(path)
        val jvmPath = jvmFile.toPath()
        val mimeType = Files.probeContentType(jvmPath)
        return SCFile(
            null,
            jvmFile.name,
            null, // TODO: Create directories
            mimeType,
            jvmFile.length(),
            DEFAULT_CHUNK_SIZE
        )
    }

    override suspend fun readChunk(file: SCFile, index: Long): SCFileChunk {
        val path = file.getFullPath()
        val jvmFile = openJvmFile(path)
        val fullSize = jvmFile.length()
        val startByte = file.chunkSize * index
        if (startByte >= fullSize) {
            throw IndexOutOfBoundsException("Wrong chunk index")
        }
        val len = file.chunkSize
        val factChunkSize = if (startByte + len > fullSize) {
            fullSize - startByte
        } else {
            len
        }
        val raf = RandomAccessFile(jvmFile, "r")
        val byteArray = ByteArray(factChunkSize.toInt())
        raf.seek(startByte)
        raf.read(byteArray, 0, factChunkSize.toInt())
        raf.close()
        return SCFileChunk(index, factChunkSize, byteArray, file)
    }

    private fun openJvmFile(path: String): File {
        val resPath = "$basePath${File.separator}$path"
        val jvmFile = File(resPath)
        if (!jvmFile.exists()) {
            throw SCFileNotFoundException("Not found", resPath)
        }
        if (!jvmFile.isFile) {
            throw SCNotFileException(resPath)
        }
        return jvmFile
    }
}