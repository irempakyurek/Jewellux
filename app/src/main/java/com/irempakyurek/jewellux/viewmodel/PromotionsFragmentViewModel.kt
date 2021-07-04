package com.irempakyurek.jewellux.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.irempakyurek.jewellux.entity.Products
import com.irempakyurek.jewellux.repository.ProductsDaoRepo

class PromotionsFragmentViewModel: ViewModel() {
    private val prepo = ProductsDaoRepo()
    var promotionProductList = MutableLiveData<List<Products>>()

    init {
        promotionProductList = prepo.bringPromotionProduct()
    }

    fun getDiscountedProducts(satici_adi: String) {
        prepo.getPromotionProducts(satici_adi)
    }

    fun getDiscountedPrice(urun_fiyat:String):String{
        return prepo.getDiscountedPrice(urun_fiyat)
    }
}