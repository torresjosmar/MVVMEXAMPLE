package com.coderio.pocmvvmandroid.mainapplication.di.module

import android.util.JsonReader
import androidx.lifecycle.ViewModelProvider
import com.coderio.pocmvvmandroid.mainapplication.di.factory.ViewModelFactory
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [])
abstract class MainModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Module
    companion object {
        @Provides
        @Singleton
        @JvmStatic
        fun providesOkHttp(): OkHttpClient {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(logging)
            return httpClient.build()
        }

        @Provides
        @Singleton
        @JvmStatic
        fun retrofit(client: OkHttpClient): Retrofit {
            return Retrofit.Builder()
                .client(client)
                .baseUrl("https://a4aa071c-7938-42a8-994f-a47f0eac32bd.mock.pstmn.io/")
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }
    }
}