package com.irempakyurek.jewellux.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.irempakyurek.jewellux.entity.Products

data class ProductsAnswer(
    @SerializedName("urunler")
    @Expose
    var products: List<Products>,
    @SerializedName("success")
    @Expose
    var success: Int
) {
}