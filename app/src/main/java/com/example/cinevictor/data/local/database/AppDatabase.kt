package com.example.cinevictor.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cinevictor.data.local.dao.MovieDao
import com.example.cinevictor.data.local.dao.ReviewDao

@Database(entities = [MovieEntity::class, ReviewEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun reviewDao(): ReviewDao

}
