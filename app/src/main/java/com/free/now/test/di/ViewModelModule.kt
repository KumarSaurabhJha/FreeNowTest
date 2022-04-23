package com.free.now.test.di

import com.free.now.test.presentation.viewmodel.POIViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        POIViewModel(get())
    }
}