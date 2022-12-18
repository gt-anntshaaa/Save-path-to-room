package com.example.a07_picture.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a07_picture.data.entity.Image
import com.example.a07_picture.ui.repo.ImageRepo
import kotlinx.coroutines.launch

class ImgViewModel(private val imgRepo: ImageRepo) : ViewModel() {
    fun pathFile(id: Int): String {
        val job = viewModelScope.launch { imgRepo.pathFile(id) }
        return job.toString()
    }

    fun insert(image: Image) = viewModelScope.launch { imgRepo.insert(image) }
}