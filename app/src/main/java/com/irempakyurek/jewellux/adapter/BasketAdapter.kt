package com.irempakyurek.jewellux.adapter

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.irempakyurek.jewellux.databinding.BasketDesignBinding
import com.irempakyurek.jewellux.databinding.PromotionProductDesignBinding
import com.irempakyurek.jewellux.entity.Products
import com.irempakyurek.jewellux.viewmodel.BasketFragmentViewModel
import com.irempakyurek.jewellux.viewmodel.MainPageFragmentViewModel
import com.squareup.picasso.Picasso
import java.math.RoundingMode
import java.text.DecimalFormat

class BasketAdapter(
    var mContext: Context,
    var productsList: List<Products>,
    var viewModel: BasketFragmentViewModel
)

    : RecyclerView.Adapter<BasketAdapter.BasketFragmentViewHolder>() {

    inner class BasketFragmentViewHolder(basketDesignBinding: BasketDesignBinding) :
        RecyclerView.ViewHolder(basketDesignBinding.root) {

        var basketDesignBinding: BasketDesignBinding

        init {
            this.basketDesignBinding = basketDesignBinding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketFragmentViewHolder {
        val layoutInflater = LayoutInflater.from(mContext)
        val design = BasketDesignBinding.inflate(layoutInflater, parent, false)
        return BasketFragmentViewHolder(design)
    }

    override fun onBindViewHolder(holder: BasketFragmentViewHolder, position: Int) {
        val product = productsList.get(position)

        val view = holder.basketDesignBinding

        view.productObject = product

        val url = product.urun_gorsel_url
        Picasso.get().load(url).into(view.imgProduct)

        view.cvRemoveProduct.setOnClickListener {
            viewModel.removeProductFromBasket(product.id, 0, mContext)
        }

        if(product.urun_indirimli_mi == 1){
            view.txtProductPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            view.txtDiscountedProductPrice.visibility = View.VISIBLE

            view.discountedPrice = viewModel.getDiscountedPrice(product.urun_fiyat)
        }

        view.newPrice = product.urun_fiyat
        var count = 1

        view.count = count.toString()

        view.cvIncrease.setOnClickListener {
            count++
            view.count = count.toString()
            view.newPrice = viewModel.newPrice(product.urun_fiyat, count, product.urun_indirimli_mi, true)
            view.discountedPrice = viewModel.getDiscountedPrice(view.newPrice.toString())
        }

        view.cvReduce.setOnClickListener {
            if (count != 1) {
                count--
                view.count = count.toString()
                view.newPrice = viewModel.newPrice(product.urun_fiyat, count, product.urun_indirimli_mi,false)
                view.discountedPrice = viewModel.getDiscountedPrice(view.newPrice.toString())
            } else {
                viewModel.removeProductFromBasket(product.id, 0,mContext)
            }
        }

        /*       view.cvProduct.setOnClickListener {
                   val transition = MainPageFragmentDirections.actionMainPageFragmentToProductDetailFragment(product)
                   Navigation.findNavController(it).navigate(transition)
               }*/
    }

    override fun getItemCount(): Int {
        return productsList.size
    }
}