package com.example.a07_picture.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.a07_picture.ui.repo.ImageRepo

class ImgViewModelFactory(private val imgRepo: ImageRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ImgViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ImgViewModel(imgRepo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}