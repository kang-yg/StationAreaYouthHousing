package com.kyg.stationareayouthhousing.model

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 * Plan(DTO) - Modify table column
 */
val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        with(database) {
            execSQL("CREATE TABLE `Plan_backup` (year TEXT NOT NULL, complexName TEXT NOT NULL, address TEXT NOT NULL, station TEXT NOT NULL, supply TEXT NOT NULL, publicDueDate TEXT NOT NULL, privateDueDate TEXT NOT NULL, PRIMARY KEY (address))")
            execSQL("INSERT INTO `Plan_backup` (year, address, station, supply, publicDueDate, privateDueDate) SELECT year, address, station, supply, publicDueDate, privateDueDate FROM `Plan`")
            execSQL("DROP TABLE `Plan`")
            execSQL("ALTER TABLE `Plan_backup` RENAME TO `Plan`")
        }
    }
}

/**
 * Plan(DTO) - Add 'complexName' column default value
 */
val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        with(database) {
            execSQL("CREATE TABLE `Plan_backup` (year TEXT NOT NULL, complexName TEXT NOT NULL DEFAULT '', address TEXT NOT NULL, station TEXT NOT NULL, supply TEXT NOT NULL, publicDueDate TEXT NOT NULL, privateDueDate TEXT NOT NULL, PRIMARY KEY (address))")
            execSQL("INSERT INTO `Plan_backup` (year, complexName, address, station, supply, publicDueDate, privateDueDate) SELECT year, complexName, address, station, supply, publicDueDate, privateDueDate FROM `Plan`")
            execSQL("DROP TABLE `Plan`")
            execSQL("ALTER TABLE `Plan_backup` RENAME TO `Plan`")
        }
    }
}