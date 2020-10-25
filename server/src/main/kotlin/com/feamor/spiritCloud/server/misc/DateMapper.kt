package com.feamor.spiritCloud.server.misc

import java.text.ParseException
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException


object DateMapper {
    private val zoneUTC = ZoneOffset.ofTotalSeconds(0)

    fun instantToIsoString(instant: Instant?): String? {
        if (instant == null) {
            return null
        }
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssX")
        return instant.atZone(zoneUTC).format(formatter)
    }

    fun isoStringToInstant(str: String?): Instant? {
        if (str == null) {
            return null
        }

        return try {
            val fullFormatter = DateTimeFormatter.ofPattern("y-M-d'T'H:m:sX")
            ZonedDateTime.parse(str, fullFormatter).toInstant()
        } catch (e: DateTimeParseException) {
            try {
                val shortFormatter = DateTimeFormatter.ofPattern("y-M-d'T'H:m:s")
                LocalDateTime.parse(str, shortFormatter).toInstant(zoneUTC)
            } catch (e: ParseException) {
                null
            }
        }
    }
}