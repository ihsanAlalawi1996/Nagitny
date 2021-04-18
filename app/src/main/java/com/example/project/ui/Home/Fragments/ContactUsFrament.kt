package com.example.project.ui.Home.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.project.R


class ContactUsFrament : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var uView= inflater.inflate(R.layout.fragment_contact_us, container, false)




        return uView
    }

}