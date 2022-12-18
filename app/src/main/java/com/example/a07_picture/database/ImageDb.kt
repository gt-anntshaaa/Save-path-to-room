package com.example.a07_picture.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.a07_picture.data.dao.ImageDao
import com.example.a07_picture.data.entity.Image

@Database(entities = [Image::class], version = 1, exportSchema = false)
abstract class ImageDb : RoomDatabase() {
    abstract fun imageDao(): ImageDao

    companion object{
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: ImageDb? = null

        fun getDatabase(context: Context): ImageDb {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ImageDb::class.java,
                    "image_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}