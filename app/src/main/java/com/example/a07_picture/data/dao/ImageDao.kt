package com.example.a07_picture.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.a07_picture.data.entity.Image

@Dao
interface ImageDao {
    @Query("SELECT path FROM image_table WHERE id = :id")
    suspend fun pathFile(id: Int): String

    @Insert
    suspend fun insert(image: Image)
}