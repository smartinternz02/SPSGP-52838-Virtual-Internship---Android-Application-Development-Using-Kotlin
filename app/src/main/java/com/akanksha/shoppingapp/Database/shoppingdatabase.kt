package com.akanksha.shoppingapp.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.akanksha.shoppingapp.Dao.shoppingdao
import com.akanksha.shoppingapp.Model.Shopping

@Database(entities =[Shopping::class] , version = 1 ,exportSchema = false)
 abstract class shoppingdatabase:RoomDatabase() {
    abstract fun getDao(): shoppingdao

    companion object {

        @Volatile
        var instance: shoppingdatabase? = null

       fun getInstance(context: Context):shoppingdatabase?{

           if(instance==null){
               synchronized(shoppingdatabase::class.java){
                   if(instance==null){
                       instance = Room.databaseBuilder(context , shoppingdatabase::class.java ,"db").build()
                   }
               }
           }
         return instance
       }




    }
}
