package com.example.project.ui.Home.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.project.R
import com.example.project.ui.Home.homeViewModel

class HistoryFragment : Fragment() {

    companion object {
        lateinit var hView: View
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         hView = inflater.inflate(R.layout.fragment_history, container, false)

        homeViewModel.setupCardsTransactionsHistory()
        return hView
    }

}