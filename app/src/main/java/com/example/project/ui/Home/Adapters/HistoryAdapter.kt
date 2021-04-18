package com.example.project.ui.Home.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project.data.models.History
import com.example.project.R

class HistoryAdapter(var arraylist: List<History>?) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.item_design, parent, false)
        return ViewHolder(v)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var transactionModel = arraylist?.get(position)




        holder.transaction_sender_id.text = "(${transactionModel?.couplesId.toString()})"
        holder.transaction_amount.text = "${transactionModel?.amount} JD"
        holder.transaction_date.text = transactionModel?.date
        holder.transaction_name.text = transactionModel?.item_name
        holder.transaction_sender_name.text = transactionModel?.sender_name
//        if (transactionModel?.type=="incomes")
//            holder.status.visibility=View.VISIBLE



    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var transaction_sender_id = itemView.findViewById(R.id.transaction_sender_id) as TextView
        var transaction_amount = itemView.findViewById(R.id.transaction_amount) as TextView
        var transaction_date = itemView.findViewById(R.id.transaction_date) as TextView
        var transaction_name = itemView.findViewById(R.id.transaction_name) as TextView
        var transaction_sender_name = itemView.findViewById(R.id.transaction_sender_name) as TextView
        var transaction_type = itemView.findViewById(R.id.transaction_type) as ImageView
    }


    override fun getItemCount(): Int {
        return arraylist!!.size
    }

}