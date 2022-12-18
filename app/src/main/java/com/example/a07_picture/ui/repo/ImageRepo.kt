package com.example.a07_picture.ui.repo

import androidx.annotation.WorkerThread
import com.example.a07_picture.data.dao.ImageDao
import com.example.a07_picture.data.entity.Image

class ImageRepo(private val imgDao: ImageDao) {
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun pathFile(id: Int): String{
        return imgDao.pathFile(id)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(image: Image){
        imgDao.insert(image)
    }
}