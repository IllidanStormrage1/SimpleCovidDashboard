package ru.zkv.covid19app.di.module

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.zkv.covid19app.BuildConfig
import ru.zkv.covid19app.data.CovidAPI
import javax.inject.Singleton

@Module
object NetworkModule {

    @Provides
    @JvmStatic
    @Singleton
    fun provideHttpClient(): OkHttpClient = with(OkHttpClient.Builder()) {
        build()
    }

    @Provides
    @JvmStatic
    @Singleton
    fun provideRetrofit(httpClient: OkHttpClient): Retrofit = with(Retrofit.Builder()) {
        addConverterFactory(GsonConverterFactory.create())
        client(httpClient)
        baseUrl(BuildConfig.API_BASE_URL)
        build()
    }

    @Provides
    @JvmStatic
    fun provideApi(retrofit: Retrofit): CovidAPI = retrofit.create(CovidAPI::class.java)
}