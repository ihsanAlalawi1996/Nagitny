package com.example.project.ui.Home

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.lifecycle.ViewModelProvider
import com.example.project.R
import com.example.project.common.App
import com.example.project.ui.Home.Fragments.*
import com.example.project.ui.Splash.SplashScreen
import io.paperdb.Paper
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_home.profile_name
import kotlinx.android.synthetic.main.profile.*

class Home : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        instance=this
         App.phone=Paper.book().read("mKey",0)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        viewModel.setupAdapter(App.phone)

        setSupportActionBar(toolbar)
        toggle =
            ActionBarDrawerToggle(this, home_activity, toolbar, R.string.string1, R.string.string2)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        getSupportActionBar()?.setDisplayShowTitleEnabled(false);
        getSupportActionBar()?.setHomeAsUpIndicator(R.drawable.menu_icon)

        val header: View = navigation_header_container.getHeaderView(0)
        var header_name = header.findViewById(R.id.header_name) as TextView



       var name = viewModel.changeProfileName(App.phone).toString()

        profile_name.text=name
        header_name.setText(name)


        add_item.setOnClickListener {

            viewModel.addItem(R.id.home_activity, AddItemFragemnt())


        }


        navigation_header_container.setNavigationItemSelectedListener {

            when (it.itemId) {

                R.id.personal_information ->{
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.frame_layout, ProfileFragment())
                        addToBackStack(null)
                        commit()
                        closeDrawer()
                        frame_layout.visibility=View.VISIBLE
                    }

                }

                R.id.credit_cards -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.frame_layout, CardsFragment())
                        addToBackStack(null)  // enables the back click
                        commit()
                        closeDrawer()
                        frame_layout.visibility=View.VISIBLE
                    }
                }
                R.id.history ->{
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.frame_layout, HistoryFragment())
                        addToBackStack(null)
                        commit()
                        closeDrawer()
                        frame_layout.visibility=View.VISIBLE
                    }

                }
                R.id.contact_us ->{
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.frame_layout, ContactUsFrament())
                        addToBackStack(null)
                        commit()
                        closeDrawer()
                        frame_layout.visibility=View.VISIBLE
                    }

                }

                R.id.log_out -> logOut()

                }
                true
            }
        frame_layout.setOnClickListener {
            onBackPressed()
        }

    }
    private fun logOut() {
        Paper.book().destroy()
        var intent = Intent(App.instance, SplashScreen::class.java)
        startActivity(intent)
        finishAffinity()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item))
            return true
        return super.onOptionsItemSelected(item)
    }

    private fun closeDrawer() {
        home_activity.closeDrawer(Gravity.LEFT)
    }

    override fun onBackPressed() {
        frame_layout.visibility=View.GONE
        Home.instance.add_item.visibility=View.VISIBLE
        Home.instance.search_couples_button.visibility=View.VISIBLE
        if (home_activity.isDrawerOpen(GravityCompat.START))
            closeDrawer()
        else
            super.onBackPressed()
    }
    fun search(v:View){
        viewModel.setupAdapter(search_couples.text.toString().toInt())

    }
    fun home(v:View){
        viewModel.setupAdapter(App.phone)

    }

    companion object{
        lateinit var viewModel: HomeViewModel
        lateinit var instance: Home

    }

}