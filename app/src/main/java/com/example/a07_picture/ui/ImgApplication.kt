package com.example.a07_picture.ui

import android.app.Application
import com.example.a07_picture.database.ImageDb
import com.example.a07_picture.ui.repo.ImageRepo

class ImgApplication : Application() {
    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { ImageDb.getDatabase(this) }
    val repository by lazy { ImageRepo(database.imageDao()) }
}