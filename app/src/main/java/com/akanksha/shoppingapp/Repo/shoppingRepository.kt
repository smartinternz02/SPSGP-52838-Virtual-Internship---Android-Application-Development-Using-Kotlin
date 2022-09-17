package com.akanksha.shoppingapp.Repo

import android.content.Context
import androidx.lifecycle.LiveData
import com.akanksha.shoppingapp.Database.shoppingdatabase
import com.akanksha.shoppingapp.Model.Shopping
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class shoppingRepository {
    companion object {
       private var instance:shoppingdatabase?=null

        fun initilioseDb(context: Context):shoppingdatabase?{
            return shoppingdatabase.getInstance(context)
        }

         fun insert(context: Context ,shopping: Shopping){
             instance= initilioseDb(context)
             CoroutineScope(IO).launch {
                 instance?.getDao()?.insert(shopping)
             }
         }

        fun delete(context: Context ,shopping: Shopping){
               instance= initilioseDb(context)
            CoroutineScope(IO).launch {
                instance?.getDao()?.delete(shopping)
            }
        }

        fun getalldata(context: Context): LiveData<List<Shopping>>? {
              instance= initilioseDb(context)
            return instance?.getDao()?.getAlldata()
        }

    }

}




