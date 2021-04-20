package com.example.project.ui.Home.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.project.R
import com.example.project.data.models.Cards
import com.example.project.ui.Home.Home
import com.google.android.material.snackbar.Snackbar
import io.paperdb.Paper
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.fragment_cards.view.*


class CardsFragment : Fragment() {



    companion object {
        lateinit var cView: View
        lateinit var instance : CardsFragment
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        cView = inflater.inflate(R.layout.fragment_cards, container, false)
        instance =this

        Home.viewModel.setupCardsAdapter()

        cView.create_new_card.setOnClickListener {
            cView.card_form.visibility = View.VISIBLE
            cView.cards_recycler_view.visibility = View.GONE
        }
        cView.add_card_button.setOnClickListener {
            var ex_date=(cView.end_month.text.toString()+ cView.end_year.text.toString()).toInt()
            var card=Cards(
                cView.credit_card_number.text.toString()
                , cView.holder_first_name.text.toString()
                ,Paper.book().read("mKey",0)
                ,ex_date
                , cView.card_code.text.toString().toInt())
            Home.viewModel.addCard(card)
            Snackbar.make(cView,"Card Added Successfully", Snackbar.LENGTH_LONG).show()
            Home.instance.add_item.visibility=View.VISIBLE
            Home.instance.search_couples_button.visibility=View.VISIBLE
            activity?.onBackPressed()
        }



        return cView
    }

}