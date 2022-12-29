package com.kyg.stationareayouthhousing.model

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.also {
            it.execSQL("ALTER TABLE Plan DROP COLUMN expectedMoveInDate")
            it.execSQL("ALTER TABLE Plan DROP CONSTRAINT serialNumber PRIMARY")
            it.execSQL("ALTER TABLE Plan ADD CONSTRAINT address PRIMARY")
        }
    }
}