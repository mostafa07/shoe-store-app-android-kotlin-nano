package com.udacity.shoestore.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val navigationController: NavController by lazy {
        (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
    }
    private val appBarConfiguration: AppBarConfiguration by lazy {
        AppBarConfiguration(setOf(R.id.loginFragment, R.id.shoeListFragment))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setupActionBarWithNavController(navigationController, appBarConfiguration)

        setContentView(binding.root)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navigationController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
