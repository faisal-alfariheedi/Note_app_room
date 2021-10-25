package com.example.note_app_room

import androidx.room.*


@Dao
interface NoteDao {
    @Query("SELECT * from Note")
    fun getall():List<Note>

    @Insert(onConflict= OnConflictStrategy.REPLACE)
    fun addeditNote(note:Note)

    @Delete
    fun deleteNote(note:Note)
}
