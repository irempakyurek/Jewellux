package com.irempakyurek.jewellux.adapter

import android.content.Context
import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.irempakyurek.jewellux.databinding.ProductDesignBinding
import com.irempakyurek.jewellux.entity.Products
import com.irempakyurek.jewellux.fragment.MainPageFragmentDirections
import com.irempakyurek.jewellux.viewmodel.MainPageFragmentViewModel
import com.squareup.picasso.Picasso
import java.math.RoundingMode
import java.text.DecimalFormat

class ProductsAdapter(
    var mContext: Context,
    var productsList: List<Products>,
    var viewModel: MainPageFragmentViewModel
)

    : RecyclerView.Adapter<ProductsAdapter.ProductsAdapterViewHolder>() {

    inner class ProductsAdapterViewHolder(productDesignBinding: ProductDesignBinding) :
        RecyclerView.ViewHolder(productDesignBinding.root) {

        var productDesignBinding: ProductDesignBinding

        init {
            this.productDesignBinding = productDesignBinding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsAdapterViewHolder {
        val layoutInflater = LayoutInflater.from(mContext)
        val design = ProductDesignBinding.inflate(layoutInflater, parent, false)
        return ProductsAdapterViewHolder(design)
    }

    override fun onBindViewHolder(holder: ProductsAdapterViewHolder, position: Int) {
        val product = productsList.get(position)

        val view = holder.productDesignBinding

        view.productObject = product

        val url = product.urun_gorsel_url
        Picasso.get().load(url).into(view.imgProduct)

        view.cvProduct.setOnClickListener {
            val transition = MainPageFragmentDirections.actionMainPageFragmentToProductDetailFragment(product)
            Navigation.findNavController(it).navigate(transition)
        }

        view.imgDetail.setOnClickListener {
            val transition = MainPageFragmentDirections.actionMainPageFragmentToProductDetailFragment(product)
            Navigation.findNavController(it).navigate(transition)
        }

        view.btnAddToBasket.setOnClickListener {
            viewModel.addProductToBasket(product.id, 1, mContext)
        }

        if (product.urun_indirimli_mi == 1) {
            view.txtProductPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            view.txtDiscountedProductPrice.visibility = View.VISIBLE

            view.discountedPrice = viewModel.getDiscountedPrice(product.urun_fiyat)
        }
/*
        //ürünü indirimli yapmak için
        view.btnAddToBasket.setOnClickListener {
            viewModel.giveDiscount(product.id, 1)
        }*/

    }

    override fun getItemCount(): Int {
        return productsList.size
    }

}