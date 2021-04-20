package com.example.project.ui.Splash

import `in`.aabhasjindal.otptextview.OTPListener
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.example.data.bases.BaseViewModel
import com.example.project.R
import com.example.project.common.App
import com.example.project.data.models.Users
import com.example.project.data.repositories.SplashRepository
import kotlinx.android.synthetic.main.fragment_phone_auth.view.*
import kotlinx.android.synthetic.main.fragment_register.view.*

val viewModel: SplashViewModel by lazy {
    ViewModelProvider(SplashScreen.instance).get(
        SplashViewModel::class.java
    )
}     // lazy only initializes the variable when its used
lateinit var rView: View

class SplashViewModel : BaseViewModel() {

    val repository = SplashRepository()
    val users = MutableLiveData<Users>()

    fun createNewUserbyVM() {
        rView = RegisterFragment.rView
        var name = rView.nameField.text.toString()
        var pass = rView.passFeild.text.toString()
        var phone = rView.phoneField.text.toString()


        if (!(name.length > 0 &&                           //checks if all fields are entered
                    pass.length > 0 &&
                    phone.length > 0)
        )
            Toast.makeText(App.instance, "please fill out the fields first", Toast.LENGTH_SHORT)
                .show()
        else {
            if (!(rView.checkBox.isChecked))                                          //checks the check box
                Toast.makeText(
                    App.instance,
                    "please accept the agreement first",
                    Toast.LENGTH_SHORT
                ).show()
            else {
                if (rView.confirm_field.text.toString() != pass) {   // checks if 2 passwords match each other
                    Toast.makeText(
                        App.instance,
                        "passwords do not match",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    if (pass.length < 6)                         // checks if pass length is greater than 5
                        Toast.makeText(
                            App.instance,
                            "password must have at least 6 Characters ",
                            Toast.LENGTH_SHORT
                        ).show()
                    else
                        if (phone.length < 10 || phone[0] != '0' || phone[1] != '7')                         // checks if pass length is greater than 5
                            Toast.makeText(
                                App.instance,
                                "phone number does not match the format ",
                                Toast.LENGTH_SHORT
                            ).show()
                        else {
                            var userinfo =
                                Users(                                        // puts the info entered inside the data model
                                    name,
                                    pass,
                                    phone.toInt(), ""
                                )
                            repository.registerNewUser(userinfo)
                            Toast.makeText(App.instance, "account created\nauthenticate to login", Toast.LENGTH_LONG)
                                .show()
                            switchFragemnt(R.id.splash_activity, PhoneAuthFragment())
                        }
                }
            }


        }
    }

    fun checkInfoByVM(phone: Int, pass: String): Boolean {
        if (repository.checkInfo(phone, pass)) {
            return true
        } else {
            Toast.makeText(App.instance, "user or password does not match", Toast.LENGTH_SHORT).show()
            return false
        }
    }

    fun switchFragemnt(holderFragment: Int, desFragment: Fragment) {
        SplashScreen.instance.supportFragmentManager.beginTransaction().apply {
            replace(holderFragment, desFragment)
            addToBackStack(null)  // enables the back click
            commit()

        }

    }

    fun openOTP(v: View) {
        v.otp_view.requestFocusOTP()
        v.otp_view.otpListener = object : OTPListener {
            override fun onInteractionListener() {
            }

            override fun onOTPComplete(otp: String) {
                checkOTP(v, otp)
            }
        }
    }

    fun checkOTP(v: View, otp: String) {
        if (otp == "1111") {
            viewModel.switchFragemnt(R.id.phone_auth_fragment, LogInFragment())
            v.otp_view.showSuccess()
        } else {
            Toast.makeText(App.instance, "wrong  code", Toast.LENGTH_SHORT).show()
            v.otp_view.showError()
        }

    }

    fun hideKeyboard(v: View) {
        v.let { SplashScreen.instance.hideKeyboard(it) }
    }

    fun evaluatePassword(passwordToTest: String): Int {

        var factor = 0

        var hasLetters = passwordToTest.matches(Regex(".*[" + letters + "].*"))
        var hasUpper = passwordToTest.matches(Regex(".*[" + uppercaseLetters + "].*"))
        var hasNumbers = passwordToTest.matches(Regex(".*[" + numbers + "].*"))
        var hasSpecial = passwordToTest.matches(Regex(".*[" + special + "].*"))

        if (hasLetters)
            factor++
        if (hasNumbers)
            factor++
        if (hasSpecial)
            factor++
        if (hasUpper)
            factor++
        return factor
    }

    fun setColor(strength: Int): Int {

        when (strength) {
            1 -> return R.color.red
            2 -> return R.color.orange
            3 -> return R.color.green
            4 -> return R.color.blue
            else -> {
                return R.color.black
            }
        }

    }

    companion object {
        val letters: String = "abcdefghijklmnopqrstuvwxyz"
        val uppercaseLetters: String = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        val numbers: String = "0123456789"
        val special: String = "@#=+!£$%&?"
        val maxPasswordLength: Float = 20F
        val maxPasswordFactor: Float = 10F
    }

}



