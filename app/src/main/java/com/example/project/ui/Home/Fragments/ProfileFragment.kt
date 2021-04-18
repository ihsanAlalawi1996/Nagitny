package com.example.project.ui.Home.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.project.R
import com.example.project.common.App
import com.example.project.ui.Home.Home
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.profile.*
import kotlinx.android.synthetic.main.profile.view.*

class ProfileFragment:Fragment(){
    companion object{
        lateinit var pView: View
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        pView= inflater.inflate(R.layout.profile, container, false)



        pView.profile_name.text = Home.viewModel.changeProfileName(App.phone).toString()

        pView.profile_phone_number.text = App.phone.toString()

        pView.view_icon.setOnClickListener {
            var counter = 0
            if (counter == 0) {
                pView.profile_password.text = Home.viewModel.getPassword(App.phone)
                counter++
            } else {
                pView.profile_password.text = "***********"
                counter--
            }
        }


        return pView
    }

}