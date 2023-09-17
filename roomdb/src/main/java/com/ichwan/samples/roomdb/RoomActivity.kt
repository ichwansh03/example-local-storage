package com.ichwan.samples.roomdb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.ichwan.samples.roomdb.adapter.NoteAdapter
import com.ichwan.samples.roomdb.databinding.ActivityRoomBinding
import com.ichwan.samples.roomdb.models.Note
import com.ichwan.samples.roomdb.models.NoteDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RoomActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRoomBinding
    private lateinit var adapter: NoteAdapter
    private var db = NoteDB(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            buttonCreate.setOnClickListener {
                startActivity(Intent(this@RoomActivity, EditActivity::class.java))
                intentEdit(0, Constant.TYPE_CREATE)
            }
        }

        setupList()
    }

    private fun setupList() {
        adapter = NoteAdapter(arrayListOf(), object : NoteAdapter.OnAdapterListener{
            override fun onClick(note: Note) {
                intentEdit(note.id,Constant.TYPE_READ)
            }

            override fun onUpdate(note: Note) {
                intentEdit(note.id,Constant.TYPE_UPDATE)
            }

            override fun onDelete(note: Note) {
                CoroutineScope(Dispatchers.IO).launch {
                    db.noteDao().deleteNote(note)
                    loadNote()
                }
            }

        })
        binding.listNote.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = adapter
        }
    }

    private fun intentEdit(noteId: Int, intentType: Int){
        startActivity(
            Intent(this@RoomActivity, EditActivity::class.java)
                .putExtra("ID", noteId)
                .putExtra("TYPE", intentType))
    }

    override fun onStart() {
        super.onStart()
        loadNote()
    }

    private fun loadNote(){
        CoroutineScope(Dispatchers.IO).launch {
            val notes = db.noteDao().getNotes()
            Log.d("MainActivity", "Response: $notes")
            withContext(Dispatchers.Main) {
                adapter.setData(notes)
            }
        }
    }
}