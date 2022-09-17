package com.akanksha.shoppingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import com.akanksha.shoppingapp.R
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.akanksha.shoppingapp.Adaptor.shoppingAdaptor
import com.akanksha.shoppingapp.Model.Shopping
import com.akanksha.shoppingapp.viewmodel.shoppingviewmodel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: shoppingviewmodel
    private lateinit var builder: AlertDialog.Builder
    private lateinit var dialog: AlertDialog
    private lateinit var name: EditText
    private lateinit var amount: EditText
    private lateinit var textadd: TextView
    private lateinit var textcancel: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var shoppingAdaptor: shoppingAdaptor


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        shoppingAdaptor= shoppingAdaptor(this , shoppingviewmodel() ,ArrayList<Shopping>())



        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = shoppingAdaptor
        }


       viewModel=ViewModelProvider(this).get(shoppingviewmodel::class.java)
        viewModel.getalldata(applicationContext)?.observe(this , Observer {
            shoppingAdaptor.setData(it as ArrayList<Shopping>)
        })

        fabButton.setOnClickListener{
            openDialog()
        }
    }

    private fun openDialog() {
        builder=AlertDialog.Builder(this)
        var itemView:View =LayoutInflater.from(applicationContext).inflate(R.layout.dialog , null)
        dialog=builder.create()
        dialog.setView(itemView)
             name =itemView.findViewById(R.id.etname)
              amount=itemView.findViewById(R.id.etamount)
             textadd=itemView.findViewById(R.id.txtAdd)
             textcancel=itemView.findViewById(R.id.txtCancel)

           textadd.setOnClickListener {
               saveData()
           }
        textcancel.setOnClickListener {
            deleteData()
        }
           dialog.show()
    }

    private fun deleteData() {
        dialog.dismiss()
    }

    private fun saveData() {
        var getName=name.text.toString()
        var getAmount = amount.text.toString()
         if (!TextUtils.isEmpty(getName)&&!TextUtils.isEmpty(getAmount)){
             viewModel.insert(this , Shopping(getName ,Integer.parseInt(getAmount)))
            dialog.dismiss()
        }
        else{
            Toast.makeText(this , "Please fill your data" ,Toast.LENGTH_SHORT).show()
        }
    }
}