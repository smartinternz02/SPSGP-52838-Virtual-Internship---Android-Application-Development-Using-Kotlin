package com.akanksha.shoppingapp.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.akanksha.shoppingapp.Model.Shopping
import com.akanksha.shoppingapp.Repo.shoppingRepository

class shoppingviewmodel:ViewModel() {

       fun insert(context: Context , shopping: Shopping){
           shoppingRepository.insert(context ,shopping)
       }
    fun delete(context: Context , shopping: Shopping){
        shoppingRepository.delete(context  ,shopping)
    }
    fun getalldata(context: Context):LiveData<List<Shopping>>?{
        return shoppingRepository.getalldata(context)
    }
    }
