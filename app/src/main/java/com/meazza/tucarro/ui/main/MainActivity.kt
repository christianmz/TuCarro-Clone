package com.meazza.tucarro.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.meazza.tucarro.R
import com.meazza.tucarro.databinding.ActivityMainBinding
import com.meazza.tucarro.ui.adverts.AdvertsFragment
import com.meazza.tucarro.ui.auth.login.LoginActivity
import com.meazza.tucarro.ui.sales.SalesFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import org.koin.android.ext.android.inject


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var tvName: TextView
    private lateinit var ivPhoto: ImageView
    private lateinit var buttonLogin: Button

    private val mainViewModel by inject<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DataBindingUtil
            .setContentView<ActivityMainBinding>(this, R.layout.activity_main)
            .apply {
                lifecycleOwner = this@MainActivity
                viewModel = mainViewModel
            }

        setSupportActionBar(tb_main)
        initialFragment()
        setNavDrawer()
        setNavHeaderViews()
        buttonAction()
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

    private fun initialFragment() {
        fragmentTransaction(AdvertsFragment())
        nav_view.menu.getItem(0).isChecked = true
    }

    private fun fragmentTransaction(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }

    private fun setNavHeaderViews() {
        tvName = nav_view.getHeaderView(0).findViewById(R.id.tv_user_name)
        ivPhoto = nav_view.getHeaderView(0).findViewById(R.id.iv_user_photo)
        buttonLogin = nav_view.getHeaderView(0).findViewById(R.id.btn_login_nav)
    }

    private fun changeVisibility(show: Boolean) {
        tvName.visibility = if (show) View.VISIBLE else View.GONE
        ivPhoto.visibility = if (show) View.VISIBLE else View.GONE
        buttonLogin.visibility = if (show) View.GONE else View.VISIBLE
    }

    private fun buttonAction() {
        buttonLogin.setOnClickListener {
            startActivity<LoginActivity>()
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        if (mainViewModel.user == null) {
            changeVisibility(false)
        } else {
            changeVisibility(true)
        }
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.mn_home -> fragmentTransaction(AdvertsFragment())
            R.id.mn_sales -> fragmentTransaction(SalesFragment())
            R.id.mn_sign_out -> {
                mainViewModel.signOut
                changeVisibility(false)
                invalidateOptionsMenu()
                initialFragment()
            }
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
