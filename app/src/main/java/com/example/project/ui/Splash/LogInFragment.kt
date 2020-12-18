package com.example.project.ui.Splash

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.project.R
import com.example.project.common.App
import com.example.project.ui.Home.Home
import io.paperdb.Paper
import kotlinx.android.synthetic.main.fragment_log_in.*
import kotlinx.android.synthetic.main.fragment_log_in.view.*

class LogInFragment : Fragment() {


    companion object {
        lateinit var lView: View
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var v = inflater.inflate(R.layout.fragment_log_in, container, false) // Inflate the layout for this fragment
        lView = v
        var intent = Intent(App.instance, Home::class.java)


        v.registerButt.setOnClickListener {                                            // transfares from log in fragment to register fragment via splash activity
            viewModel.switchFragemnt(R.id.splash_activity, RegisterFragment())
        }
        v.loginButt.setOnClickListener {


            if (!(v.loginuserField.text.isNotEmpty()
                        && v.loginPassField.text.toString().isNotEmpty())
            )    // checks if the one of the feilds in the login are empty
            {
                Toast.makeText(App.instance, "please fill out the feilds", Toast.LENGTH_SHORT)
                    .show()

            } else
                if (viewModel.checkInfoByVM(
                        loginuserField.text.toString().toInt(),
                        loginPassField.text.toString()
                    )
                ) {
                    Paper.book().write("mKey", loginuserField.text.toString().toInt())
                    startActivity(intent)
                }
        }
        return v
    }

}