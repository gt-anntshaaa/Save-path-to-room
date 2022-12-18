package com.example.a07_picture.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "image_table")
data class Image(
    @ColumnInfo(name = "path") val path: String
){
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}
