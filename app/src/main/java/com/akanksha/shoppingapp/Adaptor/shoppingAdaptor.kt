package com.akanksha.shoppingapp.Adaptor

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.akanksha.shoppingapp.Model.Shopping
import com.akanksha.shoppingapp.R
import com.akanksha.shoppingapp.viewmodel.shoppingviewmodel

class shoppingAdaptor(
    var context: Context,
     private var shoppingModel: shoppingviewmodel,
      var itemList: ArrayList<Shopping>
):
    RecyclerView.Adapter<shoppingAdaptor.shoppingHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): shoppingHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.each_row , parent , false)
        return shoppingHolder(view)
    }

    override fun getItemCount(): Int {
      return itemList.size
    }

    override fun onBindViewHolder(holder: shoppingHolder, position: Int) {
        val text=itemList[position]
        holder.name.text=text.name
        holder.amount.text= text.amount.toString()

        holder.imgdelete.setOnClickListener {
            shoppingModel.delete(context , text)

        }

        holder.imgadd.setOnClickListener {
            text.amount++
            shoppingModel.insert(context , text)
        }
              holder.imgminus.setOnClickListener {
                  if (text.amount>0) {
                      text.amount--
                      shoppingModel.insert(context ,text)
                  }
              }


    }
    fun setData(itemList: ArrayList<Shopping>){
        this.itemList=itemList
        notifyDataSetChanged()

    }


     class shoppingHolder(view: View) : RecyclerView.ViewHolder(view){
         val name:TextView=view.findViewById(R.id.txtname)
         val amount:TextView= view.findViewById(R.id.txtamount)
         val imgdelete:ImageView=view.findViewById(R.id.imgdelete)
         val imgadd:ImageView=view.findViewById(R.id.imgadd)
         val imgminus:ImageView=view.findViewById(R.id.imgminus)
     }




}