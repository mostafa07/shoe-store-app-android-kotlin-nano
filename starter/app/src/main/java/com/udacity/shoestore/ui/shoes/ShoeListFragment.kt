package com.udacity.shoestore.ui.shoes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.LinearLayout.LayoutParams.MATCH_PARENT
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.udacity.shoestore.data.model.Shoe
import com.udacity.shoestore.databinding.FragmentShoeListBinding

class ShoeListFragment : Fragment() {

    private lateinit var _binding: FragmentShoeListBinding
    private val binding
        get() = _binding

    private val shoesViewModel: ShoesViewModel by lazy {
        ViewModelProvider(requireActivity()).get(ShoesViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShoeListBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.shoesViewModel = shoesViewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()
        setupFab()
    }


    private fun setupViewModel() {
        shoesViewModel.shoeList.observe(requireActivity()) { shoes ->
            shoes?.let {
                binding.linearLayoutContainer.removeAllViews()

                shoes.forEach { shoe ->
                    val textView = TextView(requireContext()).apply {
                        text = shoe.name
                        layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
                    }
                    binding.linearLayoutContainer.addView(textView)
                }
            }
        }
    }

    private fun setupFab() {
        binding.addFab.setOnClickListener {
            shoesViewModel.addShoe(
                Shoe(
                    name = "Shoe 1",
                    size = 42.0,
                    company = "adidas",
                    description = "bla bla bla"
                )
            )
        }
    }
}