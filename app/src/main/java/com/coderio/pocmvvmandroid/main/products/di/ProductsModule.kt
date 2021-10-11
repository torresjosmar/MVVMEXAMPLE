package com.coderio.pocmvvmandroid.main.products.di

import androidx.lifecycle.ViewModel
import com.coderio.pocmvvmandroid.common.TOKEN
import com.coderio.pocmvvmandroid.main.products.ProductsFragment
import com.coderio.pocmvvmandroid.main.products.controller.ProductsController
import com.coderio.pocmvvmandroid.main.products.viewmodel.ProductsViewModel
import com.coderio.pocmvvmandroid.mainapplication.di.scopes.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class ProductsModule {

    @Provides
    @Token
    fun getToken(productsFragment: ProductsFragment): String {
        return productsFragment.arguments?.getString(TOKEN) ?: ""
    }

    @Provides
    @IntoMap
    @ViewModelKey(ProductsViewModel::class)
    fun bindProductsViewModel(
        @Token token: String,
        productsController: ProductsController
    ): ViewModel {
        return ProductsViewModel(
            token,
            productsController
        )
    }
}