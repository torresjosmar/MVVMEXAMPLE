package com.coderio.pocmvvmandroid.mockdata

import com.coderio.pocmvvmandroid.main.products.model.GetProductsResponse
import com.coderio.pocmvvmandroid.main.products.model.ProductDetailResponse
import com.coderio.pocmvvmandroid.main.products.repository.ProductsService
import io.reactivex.Single

class FakeErrorProductService : ProductsService {

    override fun getProducts(token: String): Single<GetProductsResponse> {
        TODO("Not yet implemented")
    }

    override fun getProductDetail(token: String, productId: String) =
        Single.just(ProductDetailResponse(404, "NOT FOUND"))
}