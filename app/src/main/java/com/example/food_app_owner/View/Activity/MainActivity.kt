package com.example.food_app_owner.View.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.food_app_owner.R
import com.example.food_app_owner.View.Fragment.MenuFragment
import com.example.food_app_owner.View.Fragment.PesananFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentMenu = MenuFragment()
        val fragmentPesanan = PesananFragment()

        bottomNav = findViewById(R.id.bottomNavigationView)

        bottomNav.selectedItemId = R.id.pesanan

        bottomNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.pesanan -> pindahFragment(fragmentPesanan)
                R.id.menu -> pindahFragment(fragmentMenu)
                else -> {
                    val gagal= Intent(this, MainActivity::class.java)
                    startActivity(gagal)
                }
            }
            true
        }


    }

    fun pindahFragment(fragment: Fragment){
        val fragmentManager =supportFragmentManager
        val transaksi  = fragmentManager.beginTransaction()
        transaksi.replace(R.id.fragmentContainerView,fragment)
        transaksi.commit()
    }
}