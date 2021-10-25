package com.example.note_app_room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities=[Note::class],version = 1,exportSchema = false)
abstract class NoteDB: RoomDatabase() {

    companion object {
        @Volatile
        var instance:NoteDB?=null
        fun getInstance(cont: Context):NoteDB{
            return instance?:synchronized(this){
                instance?:buildDatabase(cont).also{instance=it}
            }
        }
        fun buildDatabase(cont: Context):NoteDB{
            return Room.databaseBuilder(cont,NoteDB::class.java,"Celeb").build()
        }
    }
    abstract fun NoteDao():NoteDao
}