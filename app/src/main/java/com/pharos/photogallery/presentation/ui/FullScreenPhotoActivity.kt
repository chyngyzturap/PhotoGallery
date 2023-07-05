package com.pharos.photogallery.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.pharos.photogallery.databinding.ActivityFullScreenPhotoBinding

class FullScreenPhotoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFullScreenPhotoBinding
    private var photoUri: String? = null

    companion object {
        private const val PHOTO_URI_KEY = "photoUri"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFullScreenPhotoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState != null) {
            photoUri = savedInstanceState.getString(PHOTO_URI_KEY)
        } else {
            photoUri = intent.getStringExtra("photoUri")
        }

        photoUri?.let { uri ->
            binding.ivFullPhoto.load(uri)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(PHOTO_URI_KEY, photoUri)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        photoUri = savedInstanceState.getString(PHOTO_URI_KEY)
    }
}
