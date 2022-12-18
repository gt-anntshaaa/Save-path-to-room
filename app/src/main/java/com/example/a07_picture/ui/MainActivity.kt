package com.example.a07_picture.ui

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.a07_picture.data.entity.Image
import com.example.a07_picture.databinding.ActivityMainBinding
import com.example.a07_picture.ui.viewmodel.ImgViewModel
import com.example.a07_picture.ui.viewmodel.ImgViewModelFactory
import java.io.File
import java.io.FileInputStream
import java.net.URLEncoder

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val imgViewModel: ImgViewModel by viewModels {
        ImgViewModelFactory((application as ImgApplication).repository)
    }


    private val requestPermissionHandler = registerForActivityResult(ActivityResultContracts.RequestPermission()){isGranted ->
        if (isGranted){
            pickImage.launch("image/*")
        }else{
            Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
        }
    }

    private val pickImage = registerForActivityResult(ActivityResultContracts.GetContent()){result ->
        // Gambar berhasil dipilih, proses URI dan simpan data path ke dalam database di sini
        val path = getRealPathFromURI(result).toString()
        val image = Image(path)
        imgViewModel.insert(image)

        // Gambar ditampilkan ke ImageView dengan menggunakan path yang telah disimpan ke room db
        try{
            val inputStream = FileInputStream(image.path)
            val bitmap = BitmapFactory.decodeStream(inputStream)
            binding.imagePreview.setImageBitmap(bitmap)

            inputStream.close()
        }catch (e: Exception){
            Toast.makeText(this, "${e.stackTrace}", Toast.LENGTH_SHORT).show()
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupSelectImgBtn()

    }

    private fun setupSelectImgBtn(){
        binding.selectImageButton.setOnClickListener {
            requestPermissionHandler.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
    }

    private fun getRealPathFromURI(uri: Uri?): String? {
        if (uri == null) return null

        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = contentResolver.query(uri, projection, null, null, null)

        if (cursor != null) {
            val columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            cursor.moveToFirst()
            val filePath = cursor.getString(columnIndex)
            cursor.close()
            return filePath
        }

        return uri.path
    }
}