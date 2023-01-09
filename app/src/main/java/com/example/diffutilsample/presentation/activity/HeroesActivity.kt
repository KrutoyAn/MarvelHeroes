package com.example.diffutilsample.presentation.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diffutilsample.data.dto.GreatResult
import com.example.diffutilsample.databinding.ActivityHeroesBinding
import com.example.diffutilsample.presentation.adapter.HeroesAdapter
import com.example.diffutilsample.presentation.viewmodel.HeroesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HeroesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHeroesBinding
    private val viewModel by viewModels<HeroesViewModel>()
    private val adapter: HeroesAdapter = HeroesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHeroesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.heroesRecycler.adapter = adapter
        binding.heroesRecycler.layoutManager = GridLayoutManager(this,2)
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                when (val result = viewModel.fetchHeroes()) {
                    is GreatResult.Success -> {
                        adapter.setData(result.data.pagingInfo.results)
                        binding.redProgress.isGone = true
                    }
                    is GreatResult.Error -> {
                        binding.redProgress.isGone = true
                        Toast.makeText(
                            this@HeroesActivity,
                            "error",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    GreatResult.Progress -> binding.redProgress.isVisible = true
                }
            }
        }
    }


}