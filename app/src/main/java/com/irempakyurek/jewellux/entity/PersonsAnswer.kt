package com.irempakyurek.jewellux.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PersonsAnswer(
    @SerializedName("kullanicilar")
    @Expose
    var persons: List<Persons>
) {
}