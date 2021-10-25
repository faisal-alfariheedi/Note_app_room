package com.example.note_app_room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var edn: EditText
    lateinit var add: Button
    lateinit var rv: RecyclerView
    lateinit var db:NoteDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        add.setOnClickListener{
            if(edn.text.isNotEmpty()){
                CoroutineScope(Dispatchers.IO).launch {
                    var st = db.addeditNote(Note(0, edn.text.toString()))
                }
                setuprvdata()
                Toast.makeText(this,"note saved",Toast.LENGTH_SHORT).show()
                edn.text.clear()
            }
        }
    }
    fun init(){
        edn=findViewById(R.id.ednote)
        add=findViewById(R.id.add)
        rv=findViewById(R.id.rvm)
        db=NoteDB.getInstance(this).NoteDao()
        setuprvdata()

    }

    fun setuprvdata() {
        CoroutineScope(Dispatchers.IO).launch {
            var list = db.getall()
            runOnUiThread {
                rv.adapter = RVAdapter(list as ArrayList<Note>, this@MainActivity)
                rv.layoutManager = LinearLayoutManager(this@MainActivity)
            }
        }
    }


}