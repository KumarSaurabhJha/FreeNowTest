package com.free.now.test.di

import com.free.now.test.data.repository.PoiRepository
import org.koin.dsl.module

val restRepositoryModule = module {
    single {
        PoiRepository(get())
    }
}