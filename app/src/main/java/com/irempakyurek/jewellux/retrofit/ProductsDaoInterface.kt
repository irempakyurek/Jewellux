package com.irempakyurek.jewellux.retrofit

import com.irempakyurek.jewellux.entity.CRUDAnswer
import com.irempakyurek.jewellux.entity.PersonsAnswer
import com.irempakyurek.jewellux.entity.ProductsAnswer
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ProductsDaoInterface {
    @POST("urunler.php")
    @FormUrlEncoded
    fun allProducts(@Field("satici_adi") satici_adi: String) : Call<ProductsAnswer>

    @POST("uye_ol.php")
    @FormUrlEncoded
    fun signUp(
        @Field("mail_adres") mail_adres: String,
        @Field("sifre") sifre: String,
        @Field("ad_soyad") ad_soyad: String,
        @Field("telefon") telefon: String
    ): Call<CRUDAnswer>

    @POST("giris_yap.php")
    @FormUrlEncoded
    fun login(
        @Field("mail_adres") mail_adres: String, @Field("sifre") sifre: String
    ): Call<PersonsAnswer>

    @POST("urun_ekle.php")
    @FormUrlEncoded
    fun addProduct(
        @Field("satici_adi") satici_adi: String,
        @Field("urun_adi") urun_adi: String,
        @Field("urun_fiyat") urun_fiyat: String,
        @Field("urun_aciklama") urun_aciklama: String,
        @Field("urun_gorsel_url") urun_gorsel_url: String
    ): Call<CRUDAnswer>

    @POST("sepet_durum_degistir.php")
    @FormUrlEncoded
    fun basketStatus(
        @Field("id") id:Int, @Field("sepet_durum") sepet_durum: Int
    ): Call<CRUDAnswer>


    @POST("indirimli_urun_durum_degistir.php")
    @FormUrlEncoded
    fun product_discount(
        @Field("id") id:Int, @Field("urun_indirimli_mi") urun_indirimli_mi: Int
    ): Call<CRUDAnswer>

}