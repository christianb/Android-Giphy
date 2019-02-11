package com.bunk.di

import com.bunk.common.ObserveOnScheduler
import com.bunk.common.SubscribeOnScheduler
import com.bunk.data.GiphyDataSourceImpl
import com.bunk.data.api.GiphyApiFactory
import com.bunk.domain.GetGifsUseCase
import com.bunk.domain.GiphyDataSource
import com.bunk.domain.util.SubscribeOnSchedulerImpl
import com.bunk.util.ObserveOnSchedulerImpl
import com.bunk.view.list.GiphyListViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel

val module = org.koin.dsl.module.module {

    factory<GiphyDataSource> { GiphyDataSourceImpl(GiphyApiFactory.getGiphyApi(), get()) }
    factory<GetGifsUseCase> { GetGifsUseCase(get()) }

    viewModel { GiphyListViewModel(get(), get()) }

    factory<ObserveOnScheduler> { ObserveOnSchedulerImpl() }
    factory<SubscribeOnScheduler> { SubscribeOnSchedulerImpl() }
}