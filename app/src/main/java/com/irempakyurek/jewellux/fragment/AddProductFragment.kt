package com.irempakyurek.jewellux.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.irempakyurek.jewellux.R
import com.irempakyurek.jewellux.databinding.FragmentAddProductBinding
import com.irempakyurek.jewellux.databinding.FragmentMainPageBinding
import com.irempakyurek.jewellux.entity.CRUDAnswer
import com.irempakyurek.jewellux.retrofit.ApiUtils
import com.irempakyurek.jewellux.retrofit.ProductsDaoInterface
import com.irempakyurek.jewellux.viewmodel.AddProductViewModel
import com.irempakyurek.jewellux.viewmodel.MainPageFragmentViewModel

class AddProductFragment : Fragment() {

    private lateinit var view : FragmentAddProductBinding
    private lateinit var viewModel: AddProductViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view = DataBindingUtil.inflate(inflater,R.layout.fragment_add_product, container, false)

        view.addProductFragment = this

        return view.root
    }

    fun btnAddProductClick(satici_adi:String, urun_adi:String, urun_fiyat:String, urun_aciklama:String, urun_gorsel_url:String){
        viewModel.addProduct(satici_adi, urun_adi, urun_fiyat, urun_aciklama, urun_gorsel_url)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: AddProductViewModel by viewModels()
        viewModel = tempViewModel
    }
}