package com.pharos.photogallery.presentation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.pharos.photogallery.databinding.ActivityMainBinding
import com.pharos.photogallery.presentation.adapter.GalleryAdapter
import com.pharos.photogallery.presentation.viewmodel.GalleryViewModel
import com.pharos.photogallery.utils.Result
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding
    private val viewModel: GalleryViewModel by viewModels()
    private lateinit var adapter: GalleryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.swipeRefreshLayout.setOnRefreshListener { viewModel.photos() }
        viewModel.photos()
        viewModel.photos.observe(this, Observer { result ->
            when (result.status) {
                Result.Status.SUCCESS -> {
                    adapter = GalleryAdapter(object : GalleryAdapter.PhotoClickListener {
                        override fun onPhotoClick(uri: String) {
                            val intent =
                                Intent(this@MainActivity, FullScreenPhotoActivity::class.java)
                            intent.putExtra("photoUri", uri)
                            startActivity(intent)
                        }
                    })
                    binding.rvPhotos.adapter = adapter
                    adapter.submitList(result.data)
                    binding.swipeRefreshLayout.isRefreshing = false
                }
                Result.Status.ERROR -> {
                    Toast.makeText(this, result.error?.detail, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}