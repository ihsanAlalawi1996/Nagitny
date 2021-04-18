package com.example.project.ui.Home

import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.data.bases.BaseViewModel
import com.example.project.R
import com.example.project.common.App
import com.example.project.common.replaceNonstandardDigits
import com.example.project.data.models.Cards
import com.example.project.data.models.DataBase
import com.example.project.data.models.History
import com.example.project.data.models.Items
import com.example.project.data.repositories.HomeRepository
import com.example.project.ui.Home.Adapters.CardsAdapter
import com.example.project.ui.Home.Adapters.HistoryAdapter
import com.example.project.ui.Home.Adapters.ItemsAdapter
import com.example.project.ui.Home.Fragments.AddItemFragemnt
import com.example.project.ui.Home.Fragments.CardsFragment
import com.example.project.ui.Home.Fragments.HistoryFragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.add_item_view.view.*
import kotlinx.android.synthetic.main.fragment_cards.view.*
import kotlinx.android.synthetic.main.fragment_history.view.*
import java.text.SimpleDateFormat
import java.util.*


val homeViewModel: HomeViewModel by lazy {
    ViewModelProvider(Home.instance).get(
        HomeViewModel::class.java
    )
}

class HomeViewModel : BaseViewModel() {

    val repository = HomeRepository()
    val items = MutableLiveData<Items>()


    fun addItem(holderFragment: Int, desFragemnt: Fragment) {
        Home.instance.supportFragmentManager.beginTransaction().apply {
            replace(holderFragment, desFragemnt)
            addToBackStack(null)
            commit()
        }
    }

    fun postItem(name: String, price: Double, address: String, importance: Int) {
        var newItem = Items(
            0,
            App.phone,
            "none",
            name,
            price,
            address,
            importance,
            "unpaid",
            0.0,
            SimpleDateFormat("dd-MM-YYYY hh:mm").format(Date()).replaceNonstandardDigits()
        )
        setupAdapter(App.phone)

        return repository.addItemtoDB(newItem)
    }

    fun changeProfileName(phone: Int): String? {

        return repository.getProfileName(phone)

    }

    fun getPassword(phone: Int): String {
        return repository.getPassword(phone)
    }

    fun checkEmpty(): Boolean {

        if (AddItemFragemnt.aView.item_name_field.text.isEmpty() ||
            AddItemFragemnt.aView.item_price_field.text.isEmpty() ||
            AddItemFragemnt.aView.item_address_field.text.isEmpty()
        ) {
            Snackbar.make(
                AddItemFragemnt.aView,
                "Please fill out all fields first",
                Snackbar.LENGTH_SHORT
            ).show()

            return false
        }
        return true
    }

    fun addCard(card: Cards) {
        repository.addCard(card)
        setupCardsAdapter()
    }

    fun addTransaction(history: History) {
        repository.addTransaction(history)
        setupCardsAdapter()
    }

    fun getAllItems(phone: Int): List<Items>? {
        return repository.getAllItems(phone)
    }

    fun getAllCards(phone: Int): List<Cards>? {
        return repository.getAllCards(phone)
    }

    fun getAllTransactions(phone: Int): List<History> {
        return repository.getAllTransactions(phone)

    }

    fun setupAdapter(phone: Int) {
        var model = getAllItems(phone)

        Home.instance.recycler_view.layoutManager =
            LinearLayoutManager(App.instance, RecyclerView.VERTICAL, false)
        var costumeAdopter = ItemsAdapter(model)
        Home.instance.recycler_view.adapter = costumeAdopter
        Home.instance.profile_name.text = changeProfileName(phone)
        if (phone == 1111)
            Home.instance.circleImageView.setImageDrawable(
                ContextCompat.getDrawable(
                    App.instance,
                    R.drawable.ihsan
                )
            )
        else
            Home.instance.circleImageView.setImageDrawable(
                ContextCompat.getDrawable(
                    App.instance,
                    R.drawable.ramiz
                )
            )
    }

    fun setupCardsAdapter() {
        var model = getAllCards(App.phone)

        CardsFragment.cView.cards_recycler_view.layoutManager =
            LinearLayoutManager(App.instance, RecyclerView.HORIZONTAL, false)
        var costumeAdopter = CardsAdapter(model)
        CardsFragment.cView.cards_recycler_view.adapter = costumeAdopter

    }

    fun setupCardsTransactionsHistory() {
        var model = getAllTransactions(App.phone)

        HistoryFragment.hView.history_recycler_view.layoutManager =
            LinearLayoutManager(App.instance, RecyclerView.VERTICAL, false)
        var costumeAdopter = HistoryAdapter(model)
        HistoryFragment.hView.history_recycler_view.adapter = costumeAdopter

    }
    fun deleteCard(cardNumber: String?) {
        repository.deleteCard(cardNumber)
        setupCardsAdapter()
    }

    fun deleteItem(itemId: Int) {
        repository.deleteItem(itemId)
        setupAdapter(App.phone)
    }

}



