package com.irempakyurek.jewellux.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.irempakyurek.jewellux.R
import com.irempakyurek.jewellux.databinding.FragmentProfileBinding
import com.irempakyurek.jewellux.viewmodel.AddProductViewModel
import com.irempakyurek.jewellux.viewmodel.LoginViewModel
import com.irempakyurek.jewellux.viewmodel.ProfileFragmentViewModel

class ProfileFragment : Fragment() {

    private lateinit var viewModel: ProfileFragmentViewModel
    private lateinit var view: FragmentProfileBinding
    val PREFS_FILENAME = "com.irempakyurek.jewellux"
    val KEY_NAME_SURNAME = "NAME_SURNAME"
    val KEY_EMAIL = "EMAIL"
    val KEY_PHONE = "PHONE"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)

        val prefences = requireContext().getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)

        view.personNameSurname = prefences.getString(KEY_NAME_SURNAME, "Ad Soyad")
        view.personEmail = prefences.getString(KEY_EMAIL, "EMAIL")
        view.personPhone = prefences.getString(KEY_PHONE, "Phone")

        return view.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: ProfileFragmentViewModel by viewModels()
        viewModel = tempViewModel
    }
}