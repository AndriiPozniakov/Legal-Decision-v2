package com.hazymoonstudio.legaldecision.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.hazymoonstudio.legaldecision.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_single_fragment.*

@AndroidEntryPoint
class SingleFragmentActivity : AppCompatActivity() {
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_fragment)

        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        navController = navHostFragment.navController

        bottomNavigationMenu.setupWithNavController(navController)

//        var fragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
//
//        if(fragment == null){
//            fragment = createFragment()
//            supportFragmentManager.beginTransaction()
//                .add(R.id.fragment_container, fragment)
//                .commit()
//        }
    }
}