package com.example.note_app_room

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RVAdapter(private val rv: ArrayList<Note>, val cont: Context): RecyclerView.Adapter<RVAdapter.ItemViewHolder>()  {
    val db= NoteDB.getInstance(cont).NoteDao()
    class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.rvlist,parent,false )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val rvv = rv[position].note
        holder.itemView.apply {
            var rvlisting= findViewById<CardView>(R.id.rvlisting)
            var ct= findViewById<TextView>(R.id.cardtitle)
            var ed=findViewById<ImageButton>(R.id.edbut)
            var del=findViewById<ImageButton>(R.id.delbut)
            ct.text = rvv.toString()
            ed.setOnClickListener{
                alert(rv[position])
            }
            del.setOnClickListener{
                CoroutineScope(Dispatchers.IO).launch {
                    db.deleteNote(rv[position])
                }
                if(cont is MainActivity)
                    cont.setuprvdata()
            }


        }
    }
    override fun getItemCount() = rv.size

    fun alert(note: Note) {
        var n=note
        var d= AlertDialog.Builder(cont)
        d.setTitle("Edit note")
        d.setCancelable(false)
        var Ed= EditText(cont)
        d.setPositiveButton("Change") { _, _ ->
            n.note = Ed.text.toString();CoroutineScope(Dispatchers.IO).launch {db.addeditNote(n)};if(cont is MainActivity)cont.setuprvdata()
        }
            .setNegativeButton("Cancel") { d, _ -> d.cancel() }
        d.setView(Ed)
        d.show()
    }
}