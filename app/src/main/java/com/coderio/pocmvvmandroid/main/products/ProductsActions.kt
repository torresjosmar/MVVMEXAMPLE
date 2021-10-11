package com.coderio.pocmvvmandroid.main.products

import com.coderio.pocmvvmandroid.main.products.model.ProductData
import com.coderio.pocmvvmandroid.main.products.model.ProductDetailResponse

sealed class ProductsActions {
    class OnProductsObtained(val productsList: List<ProductData?>): ProductsActions()
    class OnProductDetailFound(val detail: ProductDetailResponse): ProductsActions()
}