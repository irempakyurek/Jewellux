package com.irempakyurek.jewellux.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.irempakyurek.jewellux.entity.Products
import com.irempakyurek.jewellux.repository.ProductsDaoRepo

class BasketFragmentViewModel:ViewModel() {
    private val prepo = ProductsDaoRepo()
    var productsInBasket = MutableLiveData<List<Products>>()
    var totalPrice = MutableLiveData<String>()

    init {
        productsInBasket = prepo.bringProductInBasket()
        totalPrice = prepo.bringTotalPrice()
    }

    fun getProductsInBasket(satici_adi: String) {
        prepo.getProductsInBasket(satici_adi)
    }

    fun removeProductFromBasket(id:Int, sepet_durum:Int, context:Context){
        prepo.changeProductBasketStatus(id, sepet_durum, context)
    }

    fun getDiscountedPrice(urun_fiyat:String):String{
        return prepo.getDiscountedPrice(urun_fiyat)
    }

    fun increasePrice(urun_fiyat:String, count:Int, indirimli_mi:Int, increase:Boolean):String{
        return prepo.getNewPrice(urun_fiyat, count, indirimli_mi, increase)
    }
}