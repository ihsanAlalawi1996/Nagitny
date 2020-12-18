package com.example.project.ui.Splash

import `in`.aabhasjindal.otptextview.OTPListener
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.project.R
import com.example.project.common.App
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_phone_auth.*
import kotlinx.android.synthetic.main.fragment_phone_auth.view.*

class PhoneAuthFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        mView = inflater.inflate(R.layout.fragment_phone_auth, container, false)


        viewModel.openOTP(mView)

        mView.confirm_button.setOnClickListener {
            viewModel.checkOTP(mView, mView.otp_view.otp.toString())

        }
        return mView
    }

    companion object {
        lateinit var mView: View
    }
}