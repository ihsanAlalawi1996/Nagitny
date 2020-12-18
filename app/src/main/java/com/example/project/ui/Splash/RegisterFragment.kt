package com.example.project.ui.Splash

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.project.R
import com.example.project.common.App
import kotlinx.android.synthetic.main.fragment_register.view.*

class RegisterFragment :  Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        rView= inflater.inflate(R.layout.fragment_register, container, false)
        var strength=0

        rView.passFeild.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                strength=viewModel.evaluatePassword(s.toString())
                rView.passFeild.setTextColor(getResources().getColor(viewModel.setColor(strength)))

            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
        rView.finish_register.setOnClickListener {
            if (strength<3)
                Toast.makeText(App.instance, "password level must be at least green", Toast.LENGTH_SHORT).show()
            else
                    viewModel.createNewUserbyVM()
        }

        return rView

    }
    companion object{
        lateinit var rView: View

    }


}