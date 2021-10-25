package com.example.note_app_room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="Note")
class Note(@PrimaryKey(autoGenerate = true)@ColumnInfo(name = "id") var id:Int,
           @ColumnInfo(name = "note")var note:String)