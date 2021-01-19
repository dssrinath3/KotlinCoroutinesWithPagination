package com.example.kotlincoroutinespagination.pagingdetails.repository

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.kotlincoroutinespagination.pagingdetails.model.Data
import com.example.kotlincoroutinespagination.pagingdetails.model.Movie
import com.example.kotlincoroutinespagination.pagingdetails.network.APIService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PaginationRepository @Inject constructor(private val apiService: APIService) {
    fun getPaginationData(): Flow<PagingData<Movie>> {
        return Pager(PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
                prefetchDistance = 1),
            pagingSourceFactory = {
                PostDataSource(apiService)
            }
        ).flow;

    }
}