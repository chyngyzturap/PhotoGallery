package com.pharos.photogallery.data.repository

import com.pharos.photogallery.data.remote.RemoteDataSource
import com.pharos.photogallery.domain.model.Photo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import com.pharos.photogallery.utils.Result
import kotlinx.coroutines.flow.flowOn

class PhotoRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
    ) {
    suspend fun photos(): Flow<Result<List<Photo>>> {
        return flow {
            emit(Result.loading())
            val result = remoteDataSource.photos()
            emit(result)
        }.flowOn(Dispatchers.IO)
    }
}
