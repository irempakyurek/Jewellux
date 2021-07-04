package com.irempakyurek.jewellux

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.irempakyurek.jewellux.databinding.ActivityLoginBinding
import com.irempakyurek.jewellux.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.toolbarTitle.text = getString(R.string.app_name)

        val navHost =supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        NavigationUI.setupWithNavController(bottomNav,navHost.navController)

        binding.imgBasket.setOnClickListener {
            navHost.navController.navigate(R.id.basketFragment)
        }

        navHost.navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when(destination.id){
                R.id.productDetailFragment -> hideBottomNav()
                else -> showBottomNav()
            }
        }

    }

    fun showBottomNav() {
        bottomNav.visibility = View.VISIBLE

    }

    fun hideBottomNav() {
        bottomNav.visibility = View.GONE

    }
}