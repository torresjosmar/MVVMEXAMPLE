package com.coderio.pocmvvmandroid.main.products.repository

import com.coderio.pocmvvmandroid.main.products.model.GetProductsResponse
import com.coderio.pocmvvmandroid.main.products.model.ProductDetailResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ProductsService {
    @GET("apimock/dashboard")
    fun getProducts(
        @Header("Authorization") token: String
    ): Single<GetProductsResponse>

    @GET("apimock/productDetail&")
    fun getProductDetail(
        @Header("Authorization") token: String,
        @Query("id") productId: String
    ): Single<ProductDetailResponse>
}