package com.irempakyurek.jewellux.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.irempakyurek.jewellux.entity.Products
import com.irempakyurek.jewellux.repository.ProductsDaoRepo

class MainPageFragmentViewModel: ViewModel() {
    private val prepo = ProductsDaoRepo()
    var productsList = MutableLiveData<List<Products>>()

    init {
        productsList = prepo.bringProduct()
    }

    fun uploadProducts(satici_adi:String) {
        prepo.getAllProducts(satici_adi)
    }

    fun giveDiscount(id:Int, urun_indirimli_mi:Int){
        prepo.giveDiscount(id, urun_indirimli_mi)
    }

    fun addProductToBasket(id:Int, sepet_durum:Int, context:Context){
        prepo.changeProductBasketStatus(id, sepet_durum, context)
    }

    fun getDiscountedPrice(urun_fiyat:String):String{
        return prepo.getDiscountedPrice(urun_fiyat)
    }
}