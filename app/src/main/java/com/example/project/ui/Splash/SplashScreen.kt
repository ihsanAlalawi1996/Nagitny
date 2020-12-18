package com.example.project.ui.Splash

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.ViewModelProvider
import com.example.project.R
import com.example.project.ui.Home.Home
import io.paperdb.Paper
import kotlinx.android.synthetic.main.activity_main.*

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        viewModel= ViewModelProvider(this).get(SplashViewModel::class.java)
        instance=this

        var phone = (Paper.book().read("mKey", 0))
        if (phone!=null) {                                                     // checks if there was already a login  and logs in automatically
                var intent =Intent(this,Home::class.java)
                startActivity(intent)
            }


        viewModel.switchFragemnt(R.id.splash_activity,LogInFragment())
//        textView.visibility= View.GONE
//        progressBar.visibility= View.GONE
    }

    fun hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }


    companion object{
        lateinit var viewModel: SplashViewModel
        lateinit var instance:SplashScreen
    }
}


