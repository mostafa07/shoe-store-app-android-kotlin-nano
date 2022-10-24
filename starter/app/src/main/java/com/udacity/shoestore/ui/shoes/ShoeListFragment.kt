package com.udacity.shoestore.ui.shoes

import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.LinearLayout.LayoutParams.MATCH_PARENT
import android.widget.LinearLayout.LayoutParams.WRAP_CONTENT
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI.onNavDestinationSelected
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding

class ShoeListFragment : Fragment() {

    private lateinit var binding: FragmentShoeListBinding
    private val shoesViewModel: ShoesViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShoeListBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.shoesViewModel = shoesViewModel

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()
        setupFab()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_shoe_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return onNavDestinationSelected(item, findNavController()) || super.onOptionsItemSelected(
            item
        )
    }


    private fun setupViewModel() {
        shoesViewModel.shoeList.observe(requireActivity()) { shoes ->
            shoes?.let {
                binding.linearLayoutContainer.removeAllViews()

                shoes.forEach { shoe ->
                    val textView = TextView(requireContext()).apply {
                        text = shoe.name
                        layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
                    }
                    binding.linearLayoutContainer.addView(textView)
                }
            }
        }
    }

    private fun setupFab() {
        binding.addFab.setOnClickListener {
            val action = ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment()
            findNavController().navigate(action)
        }
    }
}