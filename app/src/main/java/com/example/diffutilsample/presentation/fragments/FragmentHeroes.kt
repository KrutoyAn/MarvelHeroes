package com.example.diffutilsample.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.diffutilsample.data.dto.GreatResult
import com.example.diffutilsample.data.dto.getImageUrl
import com.example.diffutilsample.databinding.FragmentHeroBinding
import com.example.diffutilsample.presentation.adapter.ComicsAdapter
import com.example.diffutilsample.presentation.adapter.HeroFragmentAdapter
import com.example.diffutilsample.presentation.model.HeroModel
import com.example.diffutilsample.presentation.model.ThumbNailModel
import dagger.hilt.android.AndroidEntryPoint
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import kotlinx.coroutines.launch

private const val HERO_ID = "HERO_ID"

@AndroidEntryPoint
class FragmentHeroes : Fragment() {

    companion object {
        fun newInstance(heroId: Long): FragmentHeroes {
            return FragmentHeroes().apply {
                val bundle = Bundle()
                bundle.putLong(HERO_ID, heroId)
                arguments = bundle
            }
        }
    }

    private lateinit var binding: FragmentHeroBinding
    private val viewModel: FragmentHeroesViewModel by viewModels()
    private val adapter: HeroFragmentAdapter = HeroFragmentAdapter()
    private val adapterComicsAdapter: ComicsAdapter = ComicsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHeroBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                when (val result =
                    viewModel.fetchHeroInfo(arguments?.getLong(HERO_ID) ?: error("Error"))) {

                    is GreatResult.Error -> {
                        binding.redProgressFragment.isGone = true
                        binding.textViewContent.text = "Нет соединения"
                        Toast.makeText(
                            context,
                            "error",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    is GreatResult.Success -> {
                        binding.redProgressFragment.isGone = true
                        Glide.with(binding.imageView.context)
                            .load(result.data.thumbnail.getImageUrl())
                            .apply(
                                RequestOptions.bitmapTransform(
                                    RoundedCornersTransformation(
                                        45,
                                        0
                                    )
                                )
                            )
                            .into(binding.imageView)
                        if (result.data.description.isEmpty()) {
                            binding.textViewName.text = result.data.name
                            binding.textViewContent.text = "Комикс находится на стадии редактирования"
                        } else {
                            binding.textViewName.text = result.data.name
                            binding.textViewContent.text = result.data.description
                        }

                        loadComicsDto(
                            result.data.comicsDto.collectionUri.toUri().path?.split (
                                    "/"
                                    )?.get(4).orEmpty()
                        )
                    }
                    GreatResult.Progress -> {
                        binding.redProgressFragment.isVisible = true
                    }
                }
            }
        }

        binding.fragmentRecycler.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.fragmentRecycler.adapter = adapterComicsAdapter
    }

    private fun loadComicsDto(comicsId: String) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                when (val result =
                    viewModel.fetchComicsInfoById(comicsId)) {
                    is GreatResult.Success -> {
                        //adapter.setData(result.data.results)
                        binding.fragmentRecycler.adapter = adapter
//                        {
//                            HeroModel(
//                                id = it.id.toLong(),
//                                name = it.description.orEmpty(),
//                             thumbnail = ThumbNailModel(
//                                    it.thumbnail.extension,
//                                    it.thumbnail.path
//                                )
//                            )
//                        })
                    }
                    is GreatResult.Progress -> {
                        binding.redProgressFragment.isGone = true
                    }
                    is GreatResult.Error -> {
                        binding.redProgressFragment.isGone = true

                    }
                    GreatResult.Progress -> binding.redProgressFragment.isVisible = true
                }
            }
        }
    }
}