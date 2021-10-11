package com.coderio.pocmvvmandroid.main.products.model

import com.google.gson.annotations.SerializedName

data class GetProductsResponse(
    @SerializedName("data") val productsData: List<ProductData?>
)