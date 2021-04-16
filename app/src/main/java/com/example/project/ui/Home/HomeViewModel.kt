package com.example.project.ui.Home

import android.content.Intent
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.startActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.example.data.bases.BaseViewModel
import com.example.my_first_task.data.models.Items
import com.example.project.common.App
import com.example.project.common.Log
import com.example.project.data.repositories.HomeRepository
import com.example.project.ui.Splash.SplashScreen
import com.google.android.material.snackbar.Snackbar
import io.paperdb.Paper
import kotlinx.android.synthetic.main.add_item_view.view.*


class HomeViewModel : BaseViewModel() {

    val repository = HomeRepository()
    val items = MutableLiveData<Items>()


    fun addItem(holderFragment:Int,desFragemnt: Fragment) {
        Home.instance.supportFragmentManager.beginTransaction().apply {
            replace(holderFragment,desFragemnt)
            addToBackStack(null)
            commit()
        }
    }
    fun postItem(name :String,price:Double,address:String,importance:Int){

        var newItem=Items(  0,Paper.book().read("mKey", 0),"none",name,price,address,importance,"unpaid",0.0)

        return repository.addItemtoDB(newItem)
    }
    fun changeProfileName(): String? {
        var phone =Paper.book().read("mKey",0)

        return repository.getProfileName(phone)

    }

    fun getPassword(phone: Int): String {
        return repository.getPassword(phone)
    }

    fun checkEmpty():Boolean {

        if(AddItemFragemnt.aView.item_name_field.text.isEmpty()||
            AddItemFragemnt.aView.item_price_field.text.isEmpty()||
            AddItemFragemnt.aView.item_address_field.text.isEmpty()){
            Snackbar.make(AddItemFragemnt.aView,"Please fill out all fields first",Snackbar.LENGTH_SHORT).show()

        return false}
        return true
    }



}




