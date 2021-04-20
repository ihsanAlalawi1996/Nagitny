package com.example.project.ui.Home.Fragments

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.project.R
import com.example.project.common.App
import com.example.project.common.Log
import com.example.project.data.models.Items
import com.example.project.ui.Home.Home
import com.example.project.ui.Home.homeViewModel
import com.google.android.material.snackbar.Snackbar
import io.paperdb.Paper
import kotlinx.android.synthetic.main.add_item_view.*
import kotlinx.android.synthetic.main.add_item_view.view.*
import kotlinx.android.synthetic.main.item_design.view.*

class AddItemFragemnt : Fragment() {

    companion object {
        lateinit var aView: View
    }

    fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        aView = inflater.inflate(R.layout.add_item_view, container, false)


        var itemModel = Paper.book().read("itemKey", Items(0, 0, "", "", 0.0, 0.0,"", 0, "", 0.0, ""))
        if (itemModel.couplesId != 0) {
            aView.item_name_field.setText(itemModel.name)
            aView.item_price_field.setText(itemModel.price.toString())
            aView.item_address_field.setText(itemModel.address)
            if (itemModel.importance == 1) {
                (aView.findViewById(R.id.switch1) as Switch).isChecked = true
            }
        }



        aView.post_button.setOnClickListener {

            var impo: Int
            if (aView.switch1.isChecked)
                impo = 1
            else
                impo = 0

            if (itemModel.couplesId == 0) {                                //checks if this is updating or new
                if (Home.viewModel.checkEmpty()) {
                    Home.viewModel.postItem(
                        aView.item_name_field.text.toString(),
                        aView.item_price_field.text.toString().toDouble(),
                        aView.item_address_field.text.toString(),
                        impo
                    )
                    activity?.onBackPressed()
                    Snackbar.make(aView, "Item Added Successfully", Snackbar.LENGTH_LONG).show()

                    Home.viewModel.setupAdapter(App.phone)
                }
            } else {
                if (Home.viewModel.checkEmpty()) {
                    itemModel.name = aView.item_name_field?.text.toString()
                    itemModel.price = aView.item_price_field?.text.toString().toDouble()
                    itemModel.address = aView.item_address_field?.text.toString()
                    itemModel.importance = impo
                    homeViewModel.updateItem(itemModel)
                    activity?.onBackPressed()                                                           //closes add item fragment
                    Snackbar.make(aView, "Item updated Successfully", Snackbar.LENGTH_LONG).show()
                    Paper.book().delete("itemKey")

                    Home.viewModel.setupAdapter(App.phone)
                }
            }


        }
        aView.post_close_btn.setOnClickListener {
            Paper.book().delete("itemKey")
            activity?.onBackPressed()
        }
        return aView
    }
}