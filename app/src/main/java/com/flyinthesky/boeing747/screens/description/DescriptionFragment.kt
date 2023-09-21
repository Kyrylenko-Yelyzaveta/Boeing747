package com.flyinthesky.boeing747.screens.description

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.flyinthesky.boeing747.R
import com.flyinthesky.boeing747.databinding.FragmentDescriptionBinding
import com.flyinthesky.boeing747.dto.BoeingItem
import com.flyinthesky.boeing747.vm.SharedViewModel

class DescriptionFragment : Fragment() {
    lateinit var binding: FragmentDescriptionBinding
    private val viewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDescriptionBinding.inflate(layoutInflater)
        initButtons()
        return binding.root
    }

    private fun initButtons() = with(binding) {

        bDone.setOnClickListener {
            if (edTitle.text.isNotEmpty() && edDesc.text.isNotEmpty()) {
                val boeing = BoeingItem(
                    R.drawable.boeing_747,
                    edTitle.text.toString(),
                    edDesc.text.toString()
                )
                viewModel.addItem(boeing)
                findNavController().navigate(DescriptionFragmentDirections.actionDescriptionFragment2ToMenuFragment())
            } else
                Toast.makeText(context, "Fill both of fields", Toast.LENGTH_LONG).show()


        }
    }


}