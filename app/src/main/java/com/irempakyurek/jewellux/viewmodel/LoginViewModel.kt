package com.irempakyurek.jewellux.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.irempakyurek.jewellux.entity.Persons
import com.irempakyurek.jewellux.repository.ProductsDaoRepo

class LoginViewModel: ViewModel() {
    private val prepo = ProductsDaoRepo()
    var personsList = MutableLiveData<List<Persons>>()

    init {
        personsList = prepo.getLoggedPersons()
    }

    fun signup(mail_adres:String, sifre:String, ad_soyad:String, telefon:String){
        prepo.personSignup(mail_adres, sifre, ad_soyad, telefon)
    }

    fun login(mail_adres:String, sifre:String){
        prepo.personLogin(mail_adres, sifre)
    }

    fun validate(){

    }
}