package com.coderio.pocmvvmandroid.mockdata

import com.coderio.pocmvvmandroid.main.products.model.GetProductsResponse
import com.coderio.pocmvvmandroid.main.products.model.ProductData
import com.coderio.pocmvvmandroid.main.products.model.ProductDetailResponse
import com.coderio.pocmvvmandroid.main.products.repository.ProductsService
import io.reactivex.Single

class FakeProductService : ProductsService {

    private lateinit var products: Single<GetProductsResponse>
    private lateinit var productsDetailResponse: Single<ProductDetailResponse>

    init {
        mockData()
    }

    private fun mockData() {
        val productsData = mutableListOf<ProductData>()

        productsData.add(ProductData("2", "mock", "mock", 12.5f, 12))
        productsData.add(ProductData("3", "mock3", "mock", 145.5f, 12))
        productsData.add(ProductData("4", "mock4", "mock", 16.5f, 12))

        productsDetailResponse = Single.just(ProductDetailResponse(404, "NOT FOUND"))

        products = Single.just(GetProductsResponse(productsData))
    }

    override fun getProducts(token: String) = products

    override fun getProductDetail(token: String, productId: String) = productsDetailResponse


}