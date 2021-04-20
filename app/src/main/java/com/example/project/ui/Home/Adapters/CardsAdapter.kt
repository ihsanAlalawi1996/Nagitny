package com.example.project.ui.Home.Adapters

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.project.R
import com.example.project.common.App
import com.example.project.data.models.Cards
import com.example.project.ui.Home.Fragments.PaymentFragment
import com.example.project.ui.Home.Fragments.TransactionsFragment
import com.example.project.ui.Home.homeViewModel
import com.example.project.ui.Splash.RegisterFragment
import com.example.project.ui.Splash.viewModel
import kotlinx.android.synthetic.main.fragment_payment.view.*
import kotlinx.android.synthetic.main.fragment_register.view.*

class CardsAdapter(var arraylist: List<Cards>?) : RecyclerView.Adapter<CardsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.card_design, parent, false)
        return ViewHolder(v)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var cardModel = arraylist?.get(position)



        var mm=cardModel?.ex_date.toString().subSequence(0,2)
        var yy=cardModel?.ex_date.toString().subSequence(2,4)
        holder.name.text = cardModel?.card_holder
        holder.exDate.text = "EXP:$mm/$yy"
        holder.cardNumber.text = "${cardModel?.card_number?.subSequence(0,4)} ${cardModel?.card_number?.subSequence(4,8)} ${cardModel?.card_number?.subSequence(8,12)} ${cardModel?.card_number?.subSequence(12,16)}"


       holder.deleteCard.setOnClickListener {
            homeViewModel.deleteCard(cardModel?.card_number)                        // deleted card from recyclerview and the database in the home usaing viewmodel functions
        }
        holder.cardDesign.setOnClickListener {
            PaymentFragment.cardView.payment_card_number.text="${cardModel?.card_number?.subSequence(0,4)} ${cardModel?.card_number?.subSequence(4,8)} ${cardModel?.card_number?.subSequence(8,12)} ${cardModel?.card_number?.subSequence(12,16)}"

            PaymentFragment.cardView.payment_pass.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }

                override fun afterTextChanged(s: Editable?) {
                    if (s?.isNotEmpty()!!)
                    if (homeViewModel.checkCVV(s.toString(), cardModel!!.card_number)) {
                        PaymentFragment.cardView.payment_amount.visibility = View.VISIBLE
                        PaymentFragment.cardView.payment_button.visibility = View.VISIBLE
                    } else{
                        Toast.makeText(App.instance, "Wrong Cards CVV ", Toast.LENGTH_SHORT).show()
                        PaymentFragment.cardView.payment_amount.visibility = View.GONE
                        PaymentFragment.cardView.payment_button.visibility = View.GONE
                }}
            })

        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name = itemView.findViewById(R.id.credit_holder) as TextView
        var exDate = itemView.findViewById(R.id.credit_exp) as TextView
        var cardNumber = itemView.findViewById(R.id.credit_number) as TextView
        var deleteCard = itemView.findViewById(R.id.delete_card) as ImageView
        var cardDesign = itemView.findViewById(R.id.card_design) as ConstraintLayout

    }


    override fun getItemCount(): Int {
        return arraylist!!.size
    }

}