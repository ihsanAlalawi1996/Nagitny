package com.example.project.ui.Home.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project.R
import com.example.project.common.App
import com.example.project.data.models.Transactions

class TransactionsAdapter(var arraylist: List<Transactions>?) :
    RecyclerView.Adapter<TransactionsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.transaction_design, parent, false)
        return ViewHolder(v)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var transactionModel = arraylist?.get(position)

        holder.transaction_amount.text = "${transactionModel?.amount} JD"
        holder.transaction_name.text = transactionModel?.item_name
        holder.transaction_date.text = transactionModel?.date


        if (transactionModel?.receiver_id == App.phone) {
            holder.transaction_sender_id.text = "(${transactionModel?.sender_id})"
            holder.transaction_sender_name.text = transactionModel?.sender_name
            holder.transaction_type.setImageResource(R.drawable.green)
            holder.from.text="from:"
        }
        else{
            holder.transaction_sender_id.text = "(${transactionModel?.receiver_id})"
            holder.transaction_sender_name.text = transactionModel?.receiver_name
            holder.transaction_type.setImageResource(R.drawable.red)
            holder.from.text="to:"
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var transaction_sender_id = itemView.findViewById(R.id.transaction_sender_id) as TextView
        var transaction_amount = itemView.findViewById(R.id.transaction_amount) as TextView
        var transaction_date = itemView.findViewById(R.id.transaction_date) as TextView
        var transaction_name = itemView.findViewById(R.id.transaction_name) as TextView
        var from = itemView.findViewById(R.id.from) as TextView
        var transaction_sender_name = itemView.findViewById(R.id.transaction_sender_name) as TextView
        var transaction_type = itemView.findViewById(R.id.transaction_type) as ImageView
    }


    override fun getItemCount(): Int {
        return arraylist!!.size
    }

}