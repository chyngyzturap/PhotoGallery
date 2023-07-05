package com.pharos.photogallery.data.remote

import com.pharos.photogallery.domain.model.Photo
import com.pharos.photogallery.utils.PhotoSortOrder
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoApi {
    @GET("photos/?client_id=SGwx8zGZpQ1GWh9s807nPn-7AHkV8B8LO8Wvewe_uO0&per_page=30")
    suspend fun getPhotos(
        @Query("order_by") order_by: String = PhotoSortOrder.getRandom().name
    ): Response<List<Photo>>
}
