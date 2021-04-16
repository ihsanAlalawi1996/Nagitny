package com.example.project.ui.Home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.project.R
import com.example.project.common.App
import com.example.project.common.Log
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.add_item_view.view.*

class AddItemFragemnt : Fragment() {

    companion object {
        lateinit var aView: View
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        aView = inflater.inflate(R.layout.add_item_view, container, false)

        aView.post_button.setOnClickListener {
            var impo: Int
            if (aView.switch1.isChecked)
                impo = 1
            else
                impo = 0
            Log("addItemFragemnt", "$impo")
            if (Home.viewModel.checkEmpty()) {
                Home.viewModel.postItem(
                    aView.item_name_field.text.toString(),
                    aView.item_price_field.text.toString().toDouble(),
                    aView.item_address_field.text.toString(),
                    impo
                )
                activity?.onBackPressed()
                Snackbar.make(aView,"Item Added ", Snackbar.LENGTH_SHORT).show()

            }


        }
        aView.post_close_btn.setOnClickListener { activity?.onBackPressed() }
        return aView
    }
}