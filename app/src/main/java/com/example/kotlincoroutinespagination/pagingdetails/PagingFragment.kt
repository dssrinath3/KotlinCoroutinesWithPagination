package com.example.kotlincoroutinespagination.pagingdetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlincoroutinespagination.R
import com.example.kotlincoroutinespagination.pagingdetails.adapter.FooterStateAdapter
import com.example.kotlincoroutinespagination.pagingdetails.adapter.MainListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_paging_list.*
import kotlinx.android.synthetic.main.layout_error.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class PagingFragment : Fragment(R.layout.fragment_paging_list) {
    lateinit var mainListAdapter: MainListAdapter
    private val viewModel: PagingViewModel by viewModels<PagingViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupList()
        setupView()
    }



    private fun setupView() {
        lifecycleScope.launch {
            viewModel.getPaginationList().collect{
                mainListAdapter.submitData(it)
            }
        }
    }

    private fun setupList() {
        mainListAdapter = MainListAdapter()
        rvPaging.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = mainListAdapter
        }
        rvPaging.adapter = mainListAdapter.withLoadStateFooter(
           FooterStateAdapter{
               mainListAdapter.retry()
            }
        )
        mainListAdapter.addLoadStateListener { loadStates ->
            llErrorContainer.isVisible = loadStates.source.refresh is LoadState.Error
            rvPaging.isVisible = !llErrorContainer.isVisible

            if (loadStates.source.refresh is LoadState.Error){
                btnRetry.setOnClickListener {
                    mainListAdapter.retry()
                }

                llErrorContainer.isVisible = loadStates.source.refresh is LoadState.Error
                val errorMessage = (loadStates.source.refresh as LoadState.Error).error.message
                tvErrorMesssage.text = errorMessage
            }
        }
    }

}



