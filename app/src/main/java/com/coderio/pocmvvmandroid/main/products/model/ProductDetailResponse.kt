package com.coderio.pocmvvmandroid.main.products.model

import com.google.gson.annotations.SerializedName

data class ProductDetailResponse(
    @SerializedName("errorCode") val errorCode: Int,
    @SerializedName("msj") val message: String,
)