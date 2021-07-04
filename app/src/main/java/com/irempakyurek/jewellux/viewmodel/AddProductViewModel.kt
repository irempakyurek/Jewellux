package com.irempakyurek.jewellux.viewmodel

import androidx.lifecycle.ViewModel
import com.irempakyurek.jewellux.repository.ProductsDaoRepo

class AddProductViewModel:ViewModel() {
    private val prepo = ProductsDaoRepo()

    fun addProduct(satici_adi:String, urun_adi:String, urun_fiyat:String, urun_aciklama:String, urun_gorsel_url:String){
        prepo.addProduct(satici_adi, urun_adi, urun_fiyat, urun_aciklama, urun_gorsel_url)
    }
}