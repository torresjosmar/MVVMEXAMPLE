package com.coderio.pocmvvmandroid.mockdata

import com.coderio.pocmvvmandroid.main.products.model.GetProductsResponse
import com.coderio.pocmvvmandroid.main.products.model.ProductData
import com.coderio.pocmvvmandroid.main.products.model.ProductDetailResponse
import com.coderio.pocmvvmandroid.main.products.repository.ProductsService
import io.reactivex.Single

class FakeEmptyProductService : ProductsService {

    private val productData = emptyList<ProductData>()

    override fun getProducts(token: String) = Single.just(GetProductsResponse(productData))

    override fun getProductDetail(token: String, productId: String) = Single.just(
        ProductDetailResponse(0, "")
    )

}