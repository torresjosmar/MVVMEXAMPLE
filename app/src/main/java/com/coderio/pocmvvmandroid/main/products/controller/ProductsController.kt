package com.coderio.pocmvvmandroid.main.products.controller

import com.coderio.pocmvvmandroid.main.products.repository.ProductsRepository
import javax.inject.Inject

class ProductsController@Inject constructor(
        private val productsRepository: ProductsRepository
) {
    fun getProducts(token: String) = productsRepository.getProducts(token)

    fun getProductDetail(token: String, productId: String) = productsRepository.getProductDetail(token, productId)
}