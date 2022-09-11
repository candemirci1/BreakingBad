package com.example.breakingbad.ui.quote

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.breakingbad.databinding.FragmentQuoteBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class QuoteFragment: Fragment() {

    private val viewModel: QuoteListViewModel by viewModels()

    private var binding: FragmentQuoteBinding? = null

    private var adapter: QuoteAdapter? = null

    private val args: QuoteFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQuoteBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getQuotes(args.name)
        lifecycleScope.launch {
            viewModel.state.collect {
                when (it) {
                    is QuotesViewState.Success -> {
                        binding?.loading?.isVisible = false
                        it.data?.let { quotes ->
                            if (quotes.isNotEmpty()) {
                                adapter = QuoteAdapter(quotes)
                                binding?.rvQuote?.adapter = adapter
                            } else {
                                Toast.makeText(requireContext(),"No Quote Found!!!",Toast.LENGTH_SHORT)
                                    .show()
                                findNavController().popBackStack()
                            }

                        }
                    }

                    is QuotesViewState.Error -> {
                        binding?.loading?.isVisible = false
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                        findNavController().popBackStack()
                    }

                    is QuotesViewState.Loading -> {
                        binding?.loading?.isVisible = true
                    }
                }
            }
        }
    }

}