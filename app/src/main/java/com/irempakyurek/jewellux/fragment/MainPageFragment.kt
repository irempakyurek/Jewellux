package com.irempakyurek.jewellux.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.irempakyurek.jewellux.R
import com.irempakyurek.jewellux.adapter.ProductsAdapter
import com.irempakyurek.jewellux.databinding.FragmentMainPageBinding
import com.irempakyurek.jewellux.viewmodel.MainPageFragmentViewModel

class MainPageFragment : Fragment() {

    private lateinit var adapter: ProductsAdapter
    private lateinit var view: FragmentMainPageBinding
    private lateinit var viewModel: MainPageFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view = DataBindingUtil.inflate(inflater, R.layout.fragment_main_page, container, false)

        view.rv.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

/*        //ürün ekleme sayfasına gitmek için
        view.btnAddProduct.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_mainPageFragment_to_addProductFragment)
        }*/

        viewModel.productsList.observe(viewLifecycleOwner, { productsList ->
            adapter = ProductsAdapter(requireContext(), productsList, viewModel)
            view.productsAdapter = adapter
        })

        return view.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: MainPageFragmentViewModel by viewModels()
        viewModel = tempViewModel
        viewModel.uploadProducts("irempakyurek")
    }
}