package com.flyinthesky.boeing747.screens.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.flyinthesky.boeing747.R
import com.flyinthesky.boeing747.databinding.FragmentAboutBinding


class AboutFragment : Fragment() {
    //    private val args: AboutFragmentArgs by navArgs()
    private lateinit var binding: FragmentAboutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAboutBinding.inflate(inflater)

        fillScreen()
        initListeners()

        return binding.root
    }

    private fun initListeners() {
        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_descriptionFragment_to_menuFragment)
        }
    }

    private fun fillScreen() {
        if (arguments?.getInt("defaultText") != 0) {
            binding.txtHeader.text =
                "This is about ${arguments?.getString("mainText")} ${arguments?.getInt("defaultText")}"
        } else {
            binding.txtHeader.text =
                "This is about ${arguments?.getString("mainText")} 1970"
        }
    }
}

