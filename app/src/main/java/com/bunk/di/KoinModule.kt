package com.bunk.di

import com.bunk.data.BASE_URL
import com.bunk.data.GiphyApi
import com.bunk.data.GiphyDataSourceImpl
import com.bunk.domain.GetGifsUseCase
import com.bunk.domain.GiphyDataSource
import com.bunk.domain.util.ObserveOnScheduler
import com.bunk.domain.util.SubscribeOnScheduler
import com.bunk.domain.util.SubscribeOnSchedulerImpl
import com.bunk.util.ObserveOnSchedulerImpl
import com.bunk.view.list.GiphyListViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.ext.koin.viewModel
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val module = org.koin.dsl.module.module {

    single<OkHttpClient> {
        OkHttpClient.Builder()
//            .addInterceptor(OkReplayInterceptorSingleton)
            .build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .baseUrl(BASE_URL)
            .build()
    }

    single<GiphyApi> { get<Retrofit>().create(GiphyApi::class.java) }
    factory<GiphyDataSource> { GiphyDataSourceImpl(get(), get()) }
    factory<GetGifsUseCase> { GetGifsUseCase(get()) }


//    single<GitHubApi> { get<Retrofit>().create(GitHubApi::class.java) }
////    factory<GitHubDataSource> { GitHubDataSourceImpl(get()) }
//    factory<ObservableProvider> { ObservableProvider() }
//    factory<GitHubRepository> { GitHubRepositoryImpl(get()) }
//
    viewModel { GiphyListViewModel(get(), get()) }
////    viewModel { DetailsViewModel(get(), get(), get(), get()) }
//
    factory<ObserveOnScheduler> { ObserveOnSchedulerImpl() }
    factory<SubscribeOnScheduler> { SubscribeOnSchedulerImpl() }
}

