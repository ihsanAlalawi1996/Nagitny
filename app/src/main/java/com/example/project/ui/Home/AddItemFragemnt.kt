package com.example.project.ui.Home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.project.R
import kotlinx.android.synthetic.main.add_item_view.view.*

class AddItemFragemnt :Fragment() {

    companion object{
        lateinit var aView: View
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v = inflater.inflate(R.layout.add_item_view, container, false)
        aView = v
        v.post_button.setOnClickListener {
            Home.viewModel.postItem()
        }

        return v
    }
}