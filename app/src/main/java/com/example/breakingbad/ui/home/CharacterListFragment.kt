package com.example.breakingbad.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.breakingbad.databinding.FragmentCharacterListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterListFragment: Fragment() {

    private val viewModel: CharactersViewModel by viewModels()

    private var binding: FragmentCharacterListBinding? = null

    private var adapter: CharacterAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharacterListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            viewModel.state.collect {
                when (it) {
                    is CharactersViewState.Success -> {
                        binding?.loading?.isVisible = false
                        it.data?.let { characters ->
                            adapter = CharacterAdapter(characters)

                            binding?.rvCharacters?.adapter = adapter

                        }


                    }

                    is CharactersViewState.Error -> {
                        binding?.loading?.isVisible = false
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }

                    is CharactersViewState.Loading -> {
                        binding?.loading?.isVisible = true
                    }
                }
            }
        }
    }

}