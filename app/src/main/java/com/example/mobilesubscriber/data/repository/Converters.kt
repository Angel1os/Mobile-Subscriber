package com.example.mobilesubscriber.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import java.time.ZonedDateTime
import java.util.UUID

class Converters {

    @TypeConverter
    fun fromUUID(uuid: UUID?): String?{
        return uuid?.toString()
    }

    @TypeConverter
    fun toUUID(uuid: String?): UUID?{
        return uuid?.let { UUID.fromString(it) }
    }

    @TypeConverter
    fun fromZonedDateTime(zonedDateTime: ZonedDateTime?): String? {
        return zonedDateTime?.toString()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun toZonedDateTime(zonedDateTime: String?): ZonedDateTime? {
        return zonedDateTime?.let { ZonedDateTime.parse(it) }
    }
}