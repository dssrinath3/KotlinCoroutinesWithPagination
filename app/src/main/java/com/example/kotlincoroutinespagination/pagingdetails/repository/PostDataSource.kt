package com.example.kotlincoroutinespagination.pagingdetails.repository

import androidx.paging.PagingSource
import com.example.kotlincoroutinespagination.pagingdetails.model.Movie
import com.example.kotlincoroutinespagination.pagingdetails.network.APIService



class PostDataSource(private val apiService: APIService) :PagingSource<Int, Movie>(){
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        try {
            val currentLoadingPageKey = params.key ?: 1
            val response = apiService.getListData(currentLoadingPageKey)
            val responseData = mutableListOf<Movie>()
            val data = response.results ?: emptyList()
            responseData.addAll(data)
            val prevKey = if (currentLoadingPageKey == 1) null else currentLoadingPageKey - 1

            return LoadResult.Page(
                data = responseData,
                prevKey = prevKey,
                nextKey = currentLoadingPageKey.plus(1)
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}