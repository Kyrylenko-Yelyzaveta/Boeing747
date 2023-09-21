package com.flyinthesky.boeing747.screens.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.flyinthesky.boeing747.R
import com.flyinthesky.boeing747.databinding.ActivityMainBinding
import com.flyinthesky.boeing747.dto.BoeingItem
import com.flyinthesky.boeing747.vm.SharedViewModel


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: SharedViewModel by viewModels()

    private val listener = object : DrawerAdapter.OnItemClickListener {
        override fun onItemClick(item: BoeingItem) {

            // Create a Bundle to hold the arguments
            val args = Bundle()
            args.putString("mainText", item.mainText) // Replace with your argument key and value
            args.putString("defaultText", item.defaultText)

            findNavController(R.id.container).navigate(
                R.id.aboutFragment, args
//                    MenuFragmentDirections.actionMenuFragmentToAboutFragment()
//                        .setArgDescription(item.defaultText)
//                        .setMainText((item.mainText)
//                        )
            )

            binding.drawer.closeDrawer(GravityCompat.END)

        }

    }

    private val adapter: DrawerAdapter by lazy {
        DrawerAdapter(listener = listener, itemList = mutableListOf())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initObservers()
        initListeners()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setupDrawerRecyclerView()

    }

    private fun initListeners() {
        binding.apply {
            imCloseVector.setOnClickListener() {
                drawer.closeDrawer(GravityCompat.END)
            }
        }
    }

    private fun initObservers() {
        viewModel.itemsList.observe(this, Observer {
            adapter.updateItems(it)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.action_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.idBase -> toggleDrawer()
            android.R.id.home -> {
                if (supportFragmentManager.backStackEntryCount > 0) {
                    supportFragmentManager.popBackStack()
                    return true // Navigation was successful
                }
                return false
            }

            else -> return super.onOptionsItemSelected(item)
        }


        return super.onOptionsItemSelected(item)
    }

    private fun toggleDrawer() {

        binding.apply {
            if (drawer.isDrawerOpen(GravityCompat.END)) {
                drawer.closeDrawer(GravityCompat.END)
            } else {
                drawer.openDrawer(GravityCompat.END)
            }
        }
    }

    private fun setupDrawerRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        binding.drawerRecyclerView.layoutManager = layoutManager
        binding.drawerRecyclerView.adapter = adapter

    }


}

