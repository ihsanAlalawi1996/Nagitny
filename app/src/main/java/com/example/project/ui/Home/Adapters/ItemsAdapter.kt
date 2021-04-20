package com.example.project.ui.Home.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.project.data.models.Items
import com.example.project.R
import com.example.project.common.App
import com.example.project.ui.Home.Fragments.AddItemFragemnt
import com.example.project.ui.Home.Fragments.PaymentFragment
import com.example.project.ui.Home.Home
import com.example.project.ui.Home.homeViewModel
import io.paperdb.Paper
import kotlinx.android.synthetic.main.item_design.*
import org.w3c.dom.Text

class ItemsAdapter(var arraylist: List<Items>?) : RecyclerView.Adapter<ItemsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.item_design, parent, false)
        return ViewHolder(v)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var postModel = arraylist?.get(position)


        holder.name.text = postModel?.name
        holder.price.text = postModel?.price.toString()
        holder.date.text = postModel?.date
        holder.description.text = postModel?.address
        holder.percentage.text = postModel?.percentage.toString()
        holder.progressBar.progress = postModel?.percentage!!.toInt()

        if(Paper.book().read("profile",0)==0){
            holder.edit_post.visibility=View.VISIBLE
            holder.deleteItem.visibility=View.VISIBLE
            holder.pay_button.visibility=View.GONE
        }
        else{
            holder.edit_post.visibility=View.GONE
            holder.deleteItem.visibility=View.GONE
            holder.pay_button.visibility=View.VISIBLE
        }

        if (postModel.status == "paid")
            holder.status.visibility = View.VISIBLE

        holder.deleteItem.setOnClickListener {
            homeViewModel.deleteItem(postModel?.itemId)                        // deleted card from recyclerview and the database in the home using viewmodel functions
        }
        holder.edit_post.setOnClickListener {
            if (postModel.percentage == 0.0) {
                homeViewModel.addItem(
                    R.id.home_activity,
                    AddItemFragemnt()
                )       // edits card from recyclerview and the database in the home using viewmodel functions
                Paper.book().write("itemKey", postModel)
            } else
                Toast.makeText(App.instance, "You Cant Edit This Item", Toast.LENGTH_SHORT).show()
        }
        holder.pay_button.setOnClickListener {
            Paper.book().write("itemKey", postModel)
            Home.viewModel.openPaymentWindow(R.id.home_activity, PaymentFragment())

        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name = itemView.findViewById(R.id.item_name) as TextView
        var date = itemView.findViewById(R.id.item_date) as TextView
        var description = itemView.findViewById(R.id.item_description) as TextView
        var image = itemView.findViewById(R.id.item_image) as ImageView
        var price = itemView.findViewById(R.id.item_price) as TextView
        var progressBar = itemView.findViewById(R.id.progressBar) as ProgressBar
        var percentage = itemView.findViewById(R.id.percentage) as TextView
        var status = itemView.findViewById(R.id.item_status) as ImageView
        var deleteItem = itemView.findViewById(R.id.delete_post) as ImageView
        var edit_post = itemView.findViewById(R.id.edit_post) as ImageView
        var pay_button = itemView.findViewById(R.id.pay_button) as TextView

    }

    override fun getItemCount(): Int {
        return arraylist!!.size
    }

}