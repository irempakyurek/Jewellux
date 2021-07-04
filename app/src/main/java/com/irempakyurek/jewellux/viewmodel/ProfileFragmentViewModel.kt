package com.irempakyurek.jewellux.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.irempakyurek.jewellux.entity.Persons
import com.irempakyurek.jewellux.repository.ProductsDaoRepo

class ProfileFragmentViewModel:ViewModel() {
    private val prepo = ProductsDaoRepo()
    var personsList = MutableLiveData<List<Persons>>()

    init {
        personsList = prepo.getLoggedPersons()
    }
}