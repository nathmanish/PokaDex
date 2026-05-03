package com.mn.features.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mn.features.data.database.daos.PokeDao
import com.mn.features.data.database.entities.PokeEntity

@Database(
    entities = [PokeEntity::class],
    version = 1
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun pokeDao(): PokeDao
}