package com.akanksha.shoppingapp.Model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "shopping")
 data class Shopping (


    var name:String,

    var amount:Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
 }