package com.irempakyurek.jewellux.fragment

import android.graphics.Paint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.irempakyurek.jewellux.R
import com.irempakyurek.jewellux.utils.Utils
import com.irempakyurek.jewellux.databinding.FragmentProductDetailBinding
import com.irempakyurek.jewellux.databinding.PromotionProductDesignBinding
import com.irempakyurek.jewellux.entity.Products
import com.irempakyurek.jewellux.viewmodel.AddProductViewModel
import com.irempakyurek.jewellux.viewmodel.MainPageFragmentViewModel
import java.math.RoundingMode
import java.text.DecimalFormat

class ProductDetailFragment : Fragment() {

    private lateinit var view: FragmentProductDetailBinding
    private lateinit var viewModel: MainPageFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view = DataBindingUtil.inflate(inflater, R.layout.fragment_product_detail, container, false)

        val bundle: ProductDetailFragmentArgs by navArgs()
        val product = bundle.productObject

        view.product = product

        if (product.urun_indirimli_mi == 1) {
            discountedPrice(view, product)
        }

        Utils.loadImage(view.imgProduct, product.urun_gorsel_url)

        view.btnAddToBasket.setOnClickListener {
            viewModel.addProductToBasket(product.id, 1, requireContext())
        }

        return view.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: MainPageFragmentViewModel by viewModels()
        viewModel = tempViewModel
    }

    private fun discountedPrice(view: FragmentProductDetailBinding, products: Products) {
        view.txtProductPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        view.txtDiscountedProductPrice.visibility = View.VISIBLE

        val count: Double
        val formattedCount: String

        val doublePrice = products.urun_fiyat.toDouble()
        count = doublePrice.div(1.5)

        val df = DecimalFormat("##.##")
        df.roundingMode = RoundingMode.CEILING
        formattedCount = df.format(count)
        view.discountedPrice = formattedCount
    }
}