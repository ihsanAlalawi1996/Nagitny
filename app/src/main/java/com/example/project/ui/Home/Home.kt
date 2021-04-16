package com.example.project.ui.Home

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import com.example.project.R
import com.example.project.common.App
import com.example.project.ui.Splash.SplashScreen
import io.paperdb.Paper
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_home.profile_name
import kotlinx.android.synthetic.main.nav_header.*
import kotlinx.android.synthetic.main.profile.*


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

        val header: View = navigation_header_container.getHeaderView(0)
        var header_name = header.findViewById(R.id.header_name) as TextView

        var phone=Paper.book().read("mKey",0)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

       var name = viewModel.changeProfileName().toString()

        profile_name.text=name
        header_name.setText(name)


        add_item.setOnClickListener {

            viewModel.addItem(R.id.home_activity, AddItemFragemnt())
        }


        navigation_header_container.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.personal_information -> {
                    var counter=0
                    val dialog = Dialog(this)
                    dialog.setContentView(R.layout.profile)
                    dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
                    dialog.window!!.setGravity(Gravity.CENTER)

                    dialog.profile_name.text =
                        name

                    dialog.profile_phone_number.text =
                        phone.toString()
                    dialog.view_icon.setOnClickListener {
                        if (counter==0) {
                            dialog.profile_password.text = viewModel.getPassword(phone)
                            counter++
                        }
                        else{
                            dialog.profile_password.text = "***********"
                            counter--
                        }

                    }


                    dialog.show()

                }
                R.id.log_out -> logOut()

            }
            true
        }


    }
    private fun logOut() {
        Paper.book().destroy()
        var intent = Intent(App.instance, SplashScreen::class.java)
        startActivity(intent)
        finishAffinity()
    }

    companion object{
        lateinit var viewModel: HomeViewModel
        lateinit var instance: Home

    }
}