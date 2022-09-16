package com.bsk.indianbite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {
    lateinit var  mNavController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHost = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        mNavController = navHost.navController

     //    setupActionBarWithNavController(mNavController)

    }

  /*  override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp()
        return mNavController.navigateUp() || super.onSupportNavigateUp()
    }*/
}