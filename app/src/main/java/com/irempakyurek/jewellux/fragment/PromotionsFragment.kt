package com.irempakyurek.jewellux.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.irempakyurek.jewellux.R
import com.irempakyurek.jewellux.adapter.ProductsAdapter
import com.irempakyurek.jewellux.adapter.PromotionProductsAdapter
import com.irempakyurek.jewellux.databinding.FragmentMainPageBinding
import com.irempakyurek.jewellux.databinding.FragmentPromotionsBinding
import com.irempakyurek.jewellux.entity.Products
import com.irempakyurek.jewellux.viewmodel.MainPageFragmentViewModel
import com.irempakyurek.jewellux.viewmodel.PromotionsFragmentViewModel

class PromotionsFragment : Fragment() {

    private lateinit var adapter: PromotionProductsAdapter
    private lateinit var view: FragmentPromotionsBinding
    private lateinit var viewModel: PromotionsFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view = DataBindingUtil.inflate(inflater, R.layout.fragment_promotions, container, false)

        viewModel.getDiscountedProducts("irempakyurek")

        viewModel.promotionProductList.observe(viewLifecycleOwner, { promotionProductsList ->
            adapter = PromotionProductsAdapter(requireContext(), promotionProductsList, viewModel)
            view.viewPagerPromotions.adapter = adapter

            val indicator = view.indicator
            indicator.setViewPager(view.viewPagerPromotions)
        })

        return view.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: PromotionsFragmentViewModel by viewModels()
        viewModel = tempViewModel
    }
}