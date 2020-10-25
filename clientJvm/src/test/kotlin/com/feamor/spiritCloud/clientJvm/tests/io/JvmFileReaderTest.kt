package com.feamor.spiritCloud.clientJvm.tests.io

import com.feamor.spiritCloud.clientJvm.io.JvmFileReader
import kotlinx.coroutines.runBlocking
import java.lang.IndexOutOfBoundsException
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class JvmFileReaderTest {
    @Test
    fun readFileTest() {
        runBlocking {
            val reader = JvmFileReader("/home/feamor/Изображения")
            val file = reader.readFile("2020-07-03_17-15.png")
            assertEquals("2020-07-03_17-15.png", file.filename)
            assertEquals("image/png", file.mimeType)
        }
    }

    @Test
    fun readChunkTest() {
        runBlocking {
            val reader = JvmFileReader("/home/feamor/Изображения")
            val file = reader.readFile("2020-07-03_17-15.png")
            val chunk = reader.readChunk(file, 0)
            assertEquals(0, chunk.index)
            assertEquals(65536, chunk.size)
            val lastChunk = reader.readChunk(file, 3)
            assertEquals(3, lastChunk.index)
            assertEquals(17836, lastChunk.size)
            val exception = assertFailsWith<IndexOutOfBoundsException> {
                reader.readChunk(file, 4)
            }
            assertEquals("Wrong chunk index", exception.message)
        }
    }
}