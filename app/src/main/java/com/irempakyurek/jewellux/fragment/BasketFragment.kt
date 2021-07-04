package com.irempakyurek.jewellux.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.irempakyurek.jewellux.R
import com.irempakyurek.jewellux.adapter.BasketAdapter
import com.irempakyurek.jewellux.adapter.ProductsAdapter
import com.irempakyurek.jewellux.adapter.PromotionProductsAdapter
import com.irempakyurek.jewellux.databinding.FragmentBasketBinding
import com.irempakyurek.jewellux.databinding.FragmentPromotionsBinding
import com.irempakyurek.jewellux.entity.Products
import com.irempakyurek.jewellux.viewmodel.BasketFragmentViewModel
import com.irempakyurek.jewellux.viewmodel.MainPageFragmentViewModel
import com.irempakyurek.jewellux.viewmodel.PromotionsFragmentViewModel

class BasketFragment : Fragment() {

    private lateinit var adapter: BasketAdapter
    private lateinit var view: FragmentBasketBinding
    private lateinit var viewModel: BasketFragmentViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view = DataBindingUtil.inflate(inflater, R.layout.fragment_basket, container, false)

        viewModel.productsInBasket.observe(viewLifecycleOwner, { productsList ->
            adapter = BasketAdapter(requireContext(), productsList, viewModel)
            view.basketAdapter = adapter
        })

        viewModel.totalPrice.observe(viewLifecycleOwner,{ price ->
            view.totalPrice = price
        })

        return view.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: BasketFragmentViewModel by viewModels()
        viewModel = tempViewModel
        viewModel.getProductsInBasket("irempakyurek")
    }
}