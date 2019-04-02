package com.android.notenavigation.db

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.databinding.BindingAdapter
import android.widget.TextView

@Entity
data class Note1(
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    var title: String?
){
}