package com.example.project.ui.Home.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.project.R
import com.example.project.common.App
import com.example.project.common.replaceNonstandardDigits
import com.example.project.data.models.Items
import com.example.project.data.models.Transactions
import com.example.project.ui.Home.Home
import com.example.project.ui.Home.homeViewModel
import io.paperdb.Paper
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.fragment_payment.view.*
import kotlinx.android.synthetic.main.item_design.*
import kotlinx.android.synthetic.main.item_design.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.round
import kotlin.math.roundToInt

class PaymentFragment : Fragment() {
    companion object {
        lateinit var cardView: View
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        cardView= inflater.inflate(R.layout.fragment_payment, container, false)
        Home.viewModel.setupPaymentCardsAdapter()
        Home.instance.add_item.visibility=View.GONE

        cardView.payment_button.setOnClickListener {
            var amount= cardView.payment_amount.text.toString().toDouble()
            var itemModel = Paper.book().read("itemKey", Items(0, 0, "", "", 0.0,0.0, "", 0, "", 0.0, ""))
            var senderName= homeViewModel.getProfileName(App.phone)
            var receiverName= homeViewModel.getProfileName(itemModel.couplesId)

            var transaction= Transactions(0,App.phone,senderName.toString(),receiverName!!,itemModel.couplesId,itemModel.name,amount,
                SimpleDateFormat("dd-MM-YYYY hh:mm").format(Date()).replaceNonstandardDigits())

            if (checkAmountLeft(itemModel.price,amount)) {
                makePaymentOnItem(amount, itemModel)
                homeViewModel.addTransaction(transaction)
                homeViewModel.setupAdapter(App.phone)

                activity?.onBackPressed()
                Paper.book().write("profile",0)
            }
        }


        return cardView
    }
    fun checkAmountLeft(price: Double, amount: Double):Boolean {
        if (price<amount) {
            Toast.makeText(App.instance, "Payment Amount Exceeds Item's Price", Toast.LENGTH_LONG)
                .show()
            return false
        }
        return true
    }

    fun makePaymentOnItem(amount: Double, itemModel: Items){
        itemModel.percentage+= ((amount / itemModel.original_price) * 100).roundToInt()
        itemModel.price-=amount
        if (itemModel.percentage==100.0)
            itemModel.status="paid"
        homeViewModel.makePaymentOnItem(itemModel)
    }
}