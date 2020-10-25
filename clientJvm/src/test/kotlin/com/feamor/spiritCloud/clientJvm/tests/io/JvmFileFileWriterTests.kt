package com.feamor.spiritCloud.clientJvm.tests.io

import com.feamor.spiritCloud.clientJvm.io.JvmFileFileWriter
import com.feamor.spiritCloud.clientJvm.io.JvmFileReader
import kotlinx.coroutines.runBlocking
import java.io.File
import kotlin.math.ceil
import kotlin.test.Test
import kotlin.test.assertTrue

class JvmFileFileWriterTests {
    @Test
    fun writeFileTest() {
        runBlocking {
            val reader = JvmFileReader("/home/feamor/Изображения")
            val file = reader.readFile("2020-07-03_17-15.png")
            val baseTestPath = "/home/feamor/Изображения/test"
            val writer = JvmFileFileWriter(baseTestPath)
            writer.writeFile(file)

            val jvmFile = File(baseTestPath, file.getFullPath())
            assertTrue(jvmFile.exists())
            assertTrue(jvmFile.isFile)
            assertTrue(jvmFile.length() == 0L)
            jvmFile.delete()
        }
    }

    @Test
    fun writeChunkTest() {
        runBlocking {
            val reader = JvmFileReader("/home/feamor/Изображения")
            val file = reader.readFile("2020-07-03_17-15.png")
            val chunksCount = ceil(file.fileSize.toDouble() / file.chunkSize.toDouble()).toLong()
            val chunks = (0 until chunksCount).map {
                reader.readChunk(file, it)
            }
            val baseTestPath = "/home/feamor/Изображения/test"
            val writer = JvmFileFileWriter(baseTestPath)
            writer.writeFile(file)

            for (chunk in chunks) {
                writer.writeChunk(chunk)
            }

            val jvmFile = File(baseTestPath, file.getFullPath())
            assertTrue(jvmFile.exists())
            assertTrue(jvmFile.isFile)
            jvmFile.delete()
        }
    }
}