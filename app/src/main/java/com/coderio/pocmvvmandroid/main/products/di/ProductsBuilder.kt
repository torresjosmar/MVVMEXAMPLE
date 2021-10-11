package com.coderio.pocmvvmandroid.main.products.di

import com.coderio.pocmvvmandroid.main.products.ProductsFragment
import com.coderio.pocmvvmandroid.main.products.repository.ProductsService
import com.coderio.pocmvvmandroid.mainapplication.di.scopes.FragmentScope
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import retrofit2.Retrofit

@Module
abstract class ProductsBuilder {

    @FragmentScope
    @ContributesAndroidInjector(modules = [ProductsModule::class])
    abstract fun bindProductsFragment(): ProductsFragment

    @Module
    companion object {
        @Provides
        fun provideProductsService(retrofit: Retrofit): ProductsService {
            return retrofit.create(ProductsService::class.java)
        }
    }
}