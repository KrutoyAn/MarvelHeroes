package com.example.diffutilsample.presentation.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.diffutilsample.R

class FragmentHeroes : Fragment() {



    companion object {
        fun newInstance() = FragmentHeroes()
    }

    private lateinit var viewModel: FragmentHeroesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_hero, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FragmentHeroesViewModel::class.java)
        // TODO: Use the ViewModel
    }

}