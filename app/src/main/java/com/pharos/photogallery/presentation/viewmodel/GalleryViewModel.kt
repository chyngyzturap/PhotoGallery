package com.pharos.photogallery.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pharos.photogallery.data.repository.PhotoRepository
import com.pharos.photogallery.domain.model.Photo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.pharos.photogallery.utils.Result


@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val photoRepository: PhotoRepository
) : ViewModel() {

    private val _photos = MutableLiveData<Result<List<Photo>>>()
    val photos = _photos

    fun photos() {
        viewModelScope.launch {
            photoRepository.photos().collect {
                _photos.value = it
            }
        }
    }
}
