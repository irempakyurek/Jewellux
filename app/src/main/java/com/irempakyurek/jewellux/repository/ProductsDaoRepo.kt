package com.irempakyurek.jewellux.repository

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.irempakyurek.jewellux.entity.*
import com.irempakyurek.jewellux.retrofit.ApiUtils
import com.irempakyurek.jewellux.retrofit.ProductsDaoInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.math.RoundingMode
import java.text.DecimalFormat

class ProductsDaoRepo {
    private val personsList: MutableLiveData<List<Persons>>
    private val productsList: MutableLiveData<List<Products>>
    private val productsInBasket: MutableLiveData<List<Products>>
    private val promotionProducts: MutableLiveData<List<Products>>
    private var basketTotalPrice: MutableLiveData<String>
    private var basketTotalPriceDouble: Double
    private val pdaoi: ProductsDaoInterface


    init {
        personsList = MutableLiveData()
        productsList = MutableLiveData()
        productsInBasket = MutableLiveData()
        promotionProducts = MutableLiveData()
        basketTotalPrice = MutableLiveData<String>("0")
        basketTotalPriceDouble = 0.0
        pdaoi = ApiUtils.getProductsDaoInterface()
    }

    fun getLoggedPersons(): MutableLiveData<List<Persons>> {
        return personsList
    }

    fun bringProduct(): MutableLiveData<List<Products>> {
        return productsList
    }

    fun bringPromotionProduct(): MutableLiveData<List<Products>> {
        return promotionProducts
    }

    fun bringProductInBasket(): MutableLiveData<List<Products>> {
        return productsInBasket
    }

    fun bringTotalPrice(): MutableLiveData<String> {
        return basketTotalPrice
    }

    fun getAllProducts(satici_adi: String) {
        pdaoi.allProducts(satici_adi).enqueue(object : Callback<ProductsAnswer> {
            override fun onResponse(
                call: Call<ProductsAnswer>?,
                response: Response<ProductsAnswer>
            ) {
                val liste: List<Products> = response.body().products
                productsList.value = liste
            }

            override fun onFailure(call: Call<ProductsAnswer>?, t: Throwable?) {}
        })
    }

    fun getPromotionProducts(satici_adi: String) {
        pdaoi.allProducts(satici_adi).enqueue(object : Callback<ProductsAnswer> {
            override fun onResponse(
                call: Call<ProductsAnswer>?,
                response: Response<ProductsAnswer>
            ) {
                val liste: List<Products> = response.body().products
                val promotionProductsList = ArrayList<Products>()
                for (i in 0 until liste.size) {
                    if (liste.get(i).urun_indirimli_mi == 1) {
                        promotionProductsList.add(liste.get(i))
                    }
                }
                promotionProducts.value = promotionProductsList
            }

            override fun onFailure(call: Call<ProductsAnswer>?, t: Throwable?) {}
        })
    }

    fun getProductsInBasket(satici_adi: String) {
        pdaoi.allProducts(satici_adi).enqueue(object : Callback<ProductsAnswer> {
            override fun onResponse(
                call: Call<ProductsAnswer>?,
                response: Response<ProductsAnswer>
            ) {
                val liste: List<Products> = response.body().products
                val productsInBasketList = ArrayList<Products>()
                var count = 0.0
                for (i in 0 until liste.size) {
                    if (liste.get(i).sepet_durum == 1) {
                        productsInBasketList.add(liste.get(i))
                        val doublePrice = liste.get(i).urun_fiyat.toDouble()

                        count += if (liste.get(i).urun_indirimli_mi == 1) doublePrice.minus(
                            doublePrice.div(4)
                        ) else doublePrice
                    }
                }
                productsInBasket.value = productsInBasketList

                val df = DecimalFormat("##.##")
                df.roundingMode = RoundingMode.CEILING
                basketTotalPrice.value = df.format(count)
                basketTotalPriceDouble = count
            }

            override fun onFailure(call: Call<ProductsAnswer>?, t: Throwable?) {}
        })
    }

    fun getDiscountedPrice(urun_fiyat: String): String {
        val doublePrice = urun_fiyat.toDouble()
        val discount = doublePrice.div(4)
        val newPrice = doublePrice.minus(discount)
        val df = DecimalFormat("##.##")
        df.roundingMode = RoundingMode.CEILING
        return df.format(newPrice)
    }

    fun getNewPrice(price: String, count: Int, indirimli_mi: Int, increase: Boolean): String {
        val doublePrice = price.toDouble()
        val newPrice = doublePrice.times(count)
        val discountedPrice = doublePrice.minus(doublePrice.div(4))
        val df = DecimalFormat("##.##")
        df.roundingMode = RoundingMode.CEILING
        val discountedNewPrice = newPrice.minus(newPrice.div(4))
        if (indirimli_mi == 1) {
            if (increase) {
                basketTotalPriceDouble = basketTotalPriceDouble.minus(discountedPrice.times(count - 1)) + discountedNewPrice
            } else {
                basketTotalPriceDouble = basketTotalPriceDouble.minus(discountedPrice.times(count + 1)) + discountedNewPrice
            }


        } else {
            if (increase) {
                basketTotalPriceDouble = basketTotalPriceDouble.minus(doublePrice.times(count - 1)) + newPrice
            } else {
                basketTotalPriceDouble = basketTotalPriceDouble.minus(doublePrice.times(count + 1)) + newPrice
            }
        }
        basketTotalPrice.value = df.format(basketTotalPriceDouble)
        return df.format(newPrice)
    }

    fun personSignup(mail_adres: String, sifre: String, ad_soyad: String, telefon: String) {
        pdaoi.signUp(mail_adres, sifre, ad_soyad, telefon)
            .enqueue(object : Callback<CRUDAnswer> {
                override fun onResponse(call: Call<CRUDAnswer>?, response: Response<CRUDAnswer>?) {
                    Log.e("aa", "kayıt başarılı")
                }

                override fun onFailure(call: Call<CRUDAnswer>?, t: Throwable?) {}
            })
    }

    fun personLogin(mail_adres: String, sifre: String) {
        pdaoi.login(mail_adres, sifre)
            .enqueue(object : Callback<PersonsAnswer> {
                override fun onResponse(
                    call: Call<PersonsAnswer>?,
                    response: Response<PersonsAnswer>
                ) {
                    val list: List<Persons> = response.body().persons
                    personsList.value = list
                }

                override fun onFailure(call: Call<PersonsAnswer>?, t: Throwable?) {}
            })
    }

    fun addProduct(
        satici_adi: String,
        urun_adi: String,
        urun_fiyat: String,
        urun_aciklama: String,
        urun_gorsel_url: String
    ) {
        pdaoi.addProduct(satici_adi, urun_adi, urun_fiyat, urun_aciklama, urun_gorsel_url)
            .enqueue(object : Callback<CRUDAnswer> {
                override fun onResponse(call: Call<CRUDAnswer>?, response: Response<CRUDAnswer>?) {
                    Log.e("aaa", "başarılı")
                }

                override fun onFailure(call: Call<CRUDAnswer>?, t: Throwable?) {
                    Log.e("aaa", "başarısız")
                }

            })
    }

    fun giveDiscount(id: Int, urun_indirimli_mi: Int) {
        pdaoi.product_discount(id, urun_indirimli_mi)
            .enqueue(object : Callback<CRUDAnswer> {
                override fun onResponse(call: Call<CRUDAnswer>?, response: Response<CRUDAnswer>?) {
                    Log.e("aaa", "başarılı")
                }

                override fun onFailure(call: Call<CRUDAnswer>?, t: Throwable?) {
                    Log.e("aaa", "başarısız")
                }

            })
    }

    fun changeProductBasketStatus(id: Int, sepet_durum: Int, context: Context) {
        pdaoi.basketStatus(id, sepet_durum)
            .enqueue(object : Callback<CRUDAnswer> {
                override fun onResponse(call: Call<CRUDAnswer>?, response: Response<CRUDAnswer>?) {
                    if (sepet_durum == 1) {
                        //when the product added to basket
                        Toast.makeText(context, "Ürün sepete eklendi.", Toast.LENGTH_SHORT).show()
                    } else if (sepet_durum == 0) {
                        //when the product removed from basket
                        getProductsInBasket("irempakyurek")
                    }
                    Log.e("aaa", "başarılı")
                }

                override fun onFailure(call: Call<CRUDAnswer>?, t: Throwable?) {
                    Log.e("aaa", "başarısız")
                }

            })
    }

    /*fun removeProductFromBasket(id: Int, sepet_durum: Int) {
        pdaoi.basketStatus(id, sepet_durum)
            .enqueue(object : Callback<CRUDAnswer> {
                override fun onResponse(call: Call<CRUDAnswer>?, response: Response<CRUDAnswer>?) {
                    getProductsInBasket("irempakyurek")
                    Log.e("aaa", "başarılı")
                }

                override fun onFailure(call: Call<CRUDAnswer>?, t: Throwable?) {
                    Log.e("aaa", "başarısız")
                }

            })
    }*/

}