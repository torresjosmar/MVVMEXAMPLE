package com.coderio.pocmvvmandroid.main.products.repository

import javax.inject.Inject

class ProductsRepository@Inject constructor(
    private val productsService: ProductsService
) {
    fun getProducts(token: String) = productsService.getProducts(token)

    fun getProductDetail(token: String, productId: String) = productsService.getProductDetail(token, productId)
}