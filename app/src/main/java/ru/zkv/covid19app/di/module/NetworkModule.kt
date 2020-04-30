package ru.zkv.covid19app.di.module

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.zkv.covid19app.data.CovidAPi
import javax.inject.Singleton

@Module
object NetworkModule {

    @Provides
    @JvmStatic
    fun provideRetrofit(): Retrofit = with(Retrofit.Builder()) {
        addConverterFactory(GsonConverterFactory.create())
        baseUrl(ru.zkv.covid19app.BuildConfig.API_BASE_URL)
        build()
    }

    @Provides
    @JvmStatic
    fun provideApi(retrofit: Retrofit): CovidAPi = retrofit.create(CovidAPi::class.java)
}