package com.example.diffutilsample.presentation.activity

import android.os.Bundle
import android.os.Process
import android.view.View
import android.widget.ProgressBar
import android.widget.SearchView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.isTraceInProgress
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import androidx.paging.map
import androidx.recyclerview.widget.GridLayoutManager
import com.example.diffutilsample.R
import com.example.diffutilsample.data.dto.heroinfo.mapToEntity
import com.example.diffutilsample.data.dto.heroinfo.mapToModel
import com.example.diffutilsample.databinding.ActivityHeroesBinding
import com.example.diffutilsample.presentation.adapter.ComicsAdapter
import com.example.diffutilsample.presentation.adapter.HeroesAdapter
import com.example.diffutilsample.presentation.adapter.LoadMoreAdapter
import com.example.diffutilsample.presentation.fragments.FragmentHeroes
import com.example.diffutilsample.presentation.model.HeroModel
import com.example.diffutilsample.presentation.viewmodel.HeroesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.*

@AndroidEntryPoint
class HeroesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHeroesBinding
    private val viewModel by viewModels<HeroesViewModel>()
    private val adapter: HeroesAdapter = HeroesAdapter()
    private val searchList: MutableList<HeroModel> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHeroesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.heroesRecycler.adapter = adapter

        adapter.mListener = {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_hero, FragmentHeroes.newInstance(it))
                .addToBackStack("BackStep")
                .setReorderingAllowed(true)
                .commit()
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchList.clear()
                val searchText = newText!!.toLowerCase(Locale.getDefault())
                if (searchText.isNotEmpty()) {
                    viewModel.heroesList.forEach {
                        if (it.name.lowercase(Locale.getDefault()).contains(searchText)) {
                            searchList.add(it)
                        }
                    }
                } else {
                    binding.heroesRecycler.smoothScrollToPosition(0)
                }
                return false
            }
        })

        binding.heroesRecycler.layoutManager = GridLayoutManager(this, 2)
        lifecycleScope.launch {

            repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.heroes.collectLatest { pagingData ->

                    adapter.submitData(pagingData.map { it.mapToEntity().mapToModel() })

                }

                /* when (val result = viewModel.fetchHeroes()) {
                     is GreatResult.Success -> {
                         viewModel.heroesList = result.data.toMutableList()
                         adapter.setData(result.data)
                         binding.redProgress.isGone = true
                     }
                     is GreatResult.Progress -> {

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
                 }*/
            }
        }

        lifecycleScope.launchWhenCreated {
            adapter.loadStateFlow.collect {
                val state = it.refresh
                binding.redProgress.isVisible = state is LoadState.Loading
            }
        }
        binding.heroesRecycler.adapter = adapter.withLoadStateFooter(
            LoadMoreAdapter{
                adapter.retry()
            }
        )
    }
}


