package com.pharos.photogallery.data.remote

import retrofit2.Retrofit
import javax.inject.Inject
import com.pharos.photogallery.domain.model.Photo
import com.pharos.photogallery.utils.ErrorUtils
import retrofit2.Response
import com.pharos.photogallery.utils.Result


class RemoteDataSource @Inject constructor(
    private val apiService: PhotoApi,
    private val retrofit: Retrofit
) {

    suspend fun photos(): Result<List<Photo>> {
        return getResponse(
            request = { apiService.getPhotos()},
            defaultErrorMessage = "Error!!"
        )
    }

    private suspend fun <T> getResponse(
        request: suspend () -> Response<T>,
        defaultErrorMessage: String
    ): Result<T> {
        return try {
            println("I'm working in thread ${Thread.currentThread().name}")
            val result = request.invoke()
            if (result.isSuccessful) {
                return Result.success(result.body())
            } else {
                val errorResponse = ErrorUtils.parseError(result, retrofit)
                Result.error(errorResponse?.detail ?: defaultErrorMessage, errorResponse)
            }
        } catch (e: Throwable) {
            Result.error("${e.message}", null)

        }
    }

}