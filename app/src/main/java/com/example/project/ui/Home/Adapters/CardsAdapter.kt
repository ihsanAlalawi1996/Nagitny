package com.example.project.ui.Home.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project.R
import com.example.project.data.models.Cards
import com.example.project.ui.Home.homeViewModel

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

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name = itemView.findViewById(R.id.credit_holder) as TextView
        var exDate = itemView.findViewById(R.id.credit_exp) as TextView
        var cardNumber = itemView.findViewById(R.id.credit_number) as TextView
        var deleteCard = itemView.findViewById(R.id.delete_card) as ImageView

    }


    override fun getItemCount(): Int {
        return arraylist!!.size
    }

}