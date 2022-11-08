package com.example.stationareayouthhousing.model

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.stationareayouthhousing.model.dto.Plan

@Database(entities = [Plan::class], version = 1)
@TypeConverters(MyTypeConverter::class)
abstract class AppDataBase : RoomDatabase() {
    abstract fun localService(): LocalService
}