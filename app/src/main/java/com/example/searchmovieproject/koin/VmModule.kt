package com.example.searchmovieproject.koin

import com.example.searchmovieproject.features.details.DetailViewModel
import com.example.searchmovieproject.features.offline.OfflineViewModel
import com.example.searchmovieproject.features.search.SearchViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

   val vmModule = module {
         viewModel { SearchViewModel(get())}
         viewModel { DetailViewModel(get(),get())}
         viewModel { OfflineViewModel(get())}

   }