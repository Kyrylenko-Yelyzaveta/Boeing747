package com.flyinthesky.boeing747.screens.menu

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.flyinthesky.boeing747.R
import com.flyinthesky.boeing747.databinding.FragmentMenuBinding
import com.flyinthesky.boeing747.dto.BoeingItem
import com.flyinthesky.boeing747.vm.SharedViewModel


class MenuFragment : Fragment() {
    private lateinit var binding: FragmentMenuBinding

    private val adapter: BoeingAdapter by lazy {
        BoeingAdapter(listener = listener)
    }

    private val listener = object : BoeingAdapter.OnItemClickListener {
        override fun onItemClick(item: BoeingItem) {

            val args = Bundle()
            args.putString("mainText", item.mainText) // Replace with your argument key and value
            args.putString("defaultText", item.defaultText)

            findNavController().navigate(
                R.id.aboutFragment, args
            )

//            findNavController().navigate(
//                MenuFragmentDirections.actionMenuFragmentToAboutFragment()
//                    .setArgDescription(item.defaultText)
//                    .setMainText((item.mainText)
//                    ))
        }

    }
    private val viewModel: SharedViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuBinding.inflate(inflater)
        fillAdapter()
        animateButton()


        initObservers()


        return binding.root
    }

    private fun initObservers() {
        viewModel.itemsList.observe(viewLifecycleOwner) {
            adapter.addBoeing(it)
        }
    }

    private fun fillAdapter() {
        binding.apply {
            rcView.layoutManager = GridLayoutManager(context, 2)
            rcView.adapter = adapter
        }
    }


    private fun animateButton() {
        val buttonAnimation = AnimationUtils.loadAnimation(context, R.anim.btn_anim)
        buttonAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {

                findNavController().navigate(R.id.action_menuFragment_to_descriptionFragment)
            }

            override fun onAnimationRepeat(animation: Animation?) {
            }

        })
        binding.btnAdd.setOnClickListener {
            binding.btnAdd.startAnimation(buttonAnimation)
        }
    }


}


