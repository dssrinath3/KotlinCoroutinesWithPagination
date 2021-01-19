package com.example.kotlincoroutinespagination.pagingdetails

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.kotlincoroutinespagination.pagingdetails.model.Data
import com.example.kotlincoroutinespagination.pagingdetails.model.Movie
import com.example.kotlincoroutinespagination.pagingdetails.network.APIService
import com.example.kotlincoroutinespagination.pagingdetails.repository.PaginationRepository
import com.example.kotlincoroutinespagination.pagingdetails.repository.PostDataSource
import kotlinx.coroutines.flow.Flow

class PagingViewModel @ViewModelInject constructor(
    private val paginationRepository: PaginationRepository) : ViewModel() {

    fun getPaginationList(): Flow<PagingData<Movie>>{
        return paginationRepository.getPaginationData().cachedIn(viewModelScope)
    }



}

