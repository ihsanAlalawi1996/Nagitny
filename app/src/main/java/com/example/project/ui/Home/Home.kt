package com.example.project.ui.Home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.lifecycle.ViewModelProvider
import com.example.project.R
import io.paperdb.Paper
import kotlinx.android.synthetic.main.activity_home.*

class Home : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        instance=this

        setSupportActionBar(toolbar)
        toggle =
            ActionBarDrawerToggle(this, home_activity, toolbar, R.string.string1, R.string.string2)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        getSupportActionBar()?.setDisplayShowTitleEnabled(false);
        getSupportActionBar()?.setHomeAsUpIndicator(R.drawable.menu_icon)



        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)


        add_item.setOnClickListener {

            viewModel.addItem(R.id.home_activity,AddItemFragemnt())
        }
    }


    companion object{
        lateinit var viewModel: HomeViewModel
        lateinit var instance: Home

    }
}