package com.irempakyurek.jewellux.adapter

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.irempakyurek.jewellux.databinding.PromotionProductDesignBinding
import com.irempakyurek.jewellux.entity.Products
import com.irempakyurek.jewellux.fragment.PromotionsFragmentDirections
import com.irempakyurek.jewellux.viewmodel.PromotionsFragmentViewModel
import com.squareup.picasso.Picasso
import java.math.RoundingMode
import java.text.DecimalFormat

class PromotionProductsAdapter(var mContext: Context,
                               var productsList:List<Products>,
                               var viewModel: PromotionsFragmentViewModel
)

    : RecyclerView.Adapter<PromotionProductsAdapter.PromotionProductsAdapterViewHolder>() {

    inner class PromotionProductsAdapterViewHolder(promotionProductDesignBinding: PromotionProductDesignBinding): RecyclerView.ViewHolder(promotionProductDesignBinding.root){

        var promotionProductDesignBinding: PromotionProductDesignBinding
        init {
            this.promotionProductDesignBinding = promotionProductDesignBinding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PromotionProductsAdapterViewHolder {
        val layoutInflater = LayoutInflater.from(mContext)
        val design = PromotionProductDesignBinding.inflate(layoutInflater, parent,false)
        return PromotionProductsAdapterViewHolder(design)
    }

    override fun onBindViewHolder(holder: PromotionProductsAdapterViewHolder, position: Int) {
        val product = productsList.get(position)

        val view = holder.promotionProductDesignBinding

        view.productObject = product

        val url= product.urun_gorsel_url
        Picasso.get().load(url).into(view.imgProduct)

        view.txtProductPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        view.txtDiscountedProductPrice.visibility = View.VISIBLE

        view.discountedPrice = viewModel.getDiscountedPrice(product.urun_fiyat)

        view.cvProduct.setOnClickListener {
            val transition = PromotionsFragmentDirections.actionPromotionsFragmentToProductDetailFragment(product)
            Navigation.findNavController(it).navigate(transition)
        }
    }

    override fun getItemCount(): Int {
        return productsList.size
    }

}