package com.meazza.tucarro.ui.main

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.meazza.tucarro.R
import com.meazza.tucarro.databinding.ActivityMainBinding
import com.meazza.tucarro.ui.adverts.AdvertsFragment
import com.meazza.tucarro.ui.sales.SalesFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val mainViewModel by inject<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DataBindingUtil
            .setContentView<ActivityMainBinding>(this, R.layout.activity_main)
            .apply {
                lifecycleOwner = this@MainActivity
                viewModel = mainViewModel
            }

        setNavDrawer()
        fragmentTransaction(AdvertsFragment())
        nav_view.menu.getItem(0).isChecked = true
    }

    private fun setNavDrawer() {
        ActionBarDrawerToggle(
            this,
            drawer_layout,
            tb_main,
            R.string.open_drawer,
            R.string.close_drawer
        ).apply {
            isDrawerIndicatorEnabled = true
            drawer_layout.addDrawerListener(this)

            syncState()
        }

        nav_view.setNavigationItemSelectedListener(this)
    }

    private fun fragmentTransaction(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.mn_home -> fragmentTransaction(AdvertsFragment())
            R.id.mn_sales -> fragmentTransaction(SalesFragment())
            R.id.mn_sign_out -> toast("Sign Out")
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START))
            drawer_layout.closeDrawer(GravityCompat.START)
        else
            super.onBackPressed()
    }
}
