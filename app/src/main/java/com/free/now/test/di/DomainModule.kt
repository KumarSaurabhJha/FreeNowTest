package com.free.now.test.di

import com.free.now.test.domain.usecase.GetPoiListUseCase
import org.koin.dsl.module

val domainModule = module {
    single {
        GetPoiListUseCase(get())
    }
}