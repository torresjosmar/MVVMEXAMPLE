package com.coderio.pocmvvmandroid.main.products.model

import com.google.gson.annotations.SerializedName

data class ProductData(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("unitPrice") val unitPrice: Float,
    @SerializedName("quantity") val quantity: Int
)