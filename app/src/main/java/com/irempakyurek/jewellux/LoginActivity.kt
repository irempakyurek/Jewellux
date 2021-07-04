package com.irempakyurek.jewellux

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import com.irempakyurek.jewellux.databinding.ActivityLoginBinding
import com.irempakyurek.jewellux.entity.Persons
import com.irempakyurek.jewellux.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel
    val PREFS_FILENAME = "com.irempakyurek.jewellux"
    val KEY_NAME_SURNAME = "NAME_SURNAME"
    val KEY_EMAIL = "EMAIL"
    val KEY_PHONE = "PHONE"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val tempViewModel: LoginViewModel by viewModels()
        viewModel = tempViewModel

        binding.loginActivity = this

        var value: Int

        val prefences = getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)
        val editor = prefences.edit()

        viewModel.personsList.observe(this, { persons ->
            val person = persons
            if (person != null) {
                for (i in 0 until person.size) {

                    value = person.get(i).value

                    if (value == 1) {
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        editor.putString(KEY_NAME_SURNAME, person.get(i).name_surname)
                        editor.putString(KEY_EMAIL, person.get(i).mail_address)
                        editor.putString(KEY_PHONE, person.get(i).phone)
                        editor.apply()
                        Log.e("aaa", "başarılı")
                    } else {
                        Toast.makeText(applicationContext, "Mail adresiniz veya şifreniz hatalı!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

        binding.txtSignup.setOnClickListener {
            binding.loginLayout.visibility = View.GONE
            binding.signupLayout.visibility = View.VISIBLE
        }

        binding.txtLogin.setOnClickListener {
            binding.signupLayout.visibility = View.GONE
            binding.loginLayout.visibility = View.VISIBLE
        }
    }

    fun btnSignupClick(mail_adres:String, sifre:String, ad_soyad:String, telefon:String) {
        viewModel.signup(mail_adres, sifre, ad_soyad, telefon)
    }

    fun btnLoginClick(mail_adres:String, sifre:String) {
        viewModel.login(mail_adres, sifre)
    }
}
