package com.meazza.tucarro.ui.adverts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.meazza.tucarro.R

class AdvertsFragment : Fragment() {

    companion object {
        fun newInstance() = AdvertsFragment()
    }

    private lateinit var viewModel: AdvertsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_adverts, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AdvertsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
