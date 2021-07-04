package com.irempakyurek.jewellux.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Persons(@SerializedName("deger")
                    @Expose
                    var value:Int,
                   @SerializedName("mail_adres")
                    @Expose
                    var mail_address:String,
                   @SerializedName("sifre")
                    @Expose
                    var password:String,
                   @SerializedName("ad_soyad")
                    @Expose
                    var name_surname:String,
                   @SerializedName("telefon")
                    @Expose
                    var phone:String): Serializable {
}