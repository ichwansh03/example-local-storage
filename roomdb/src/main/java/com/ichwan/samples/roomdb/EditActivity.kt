package com.ichwan.samples.roomdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ichwan.samples.roomdb.databinding.ActivityEditBinding
import com.ichwan.samples.roomdb.models.Note
import com.ichwan.samples.roomdb.models.NoteDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditBinding
    private lateinit var db: NoteDB

    private var noteId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NoteDB(this)

        setupView()

        binding.apply {
            buttonSave.setOnClickListener {
                CoroutineScope(Dispatchers.IO).launch {
                    db.noteDao().addNote(Note(0, editTitle.text.toString(), editNote.text.toString()))
                    finish()
                }
            }

            buttonUpdate.setOnClickListener {
                CoroutineScope(Dispatchers.IO).launch {
                    db.noteDao().updateNote(Note(noteId, editTitle.text.toString(), editNote.text.toString()))
                    finish()
                }
            }

        }
    }

    private fun setupView(){
        val type = intent.getIntExtra("TYPE",0)
        when (type) {
            Constant.TYPE_CREATE -> {
                binding.buttonSave.visibility = View.GONE
            }
            Constant.TYPE_READ -> {
                binding.buttonSave.visibility = View.GONE
                binding.buttonUpdate.visibility = View.GONE
                getNote()
            }
            Constant.TYPE_UPDATE -> {
                binding.buttonSave.visibility = View.GONE
                getNote()
            }
        }
    }

    private fun getNote(){
        noteId = intent.getIntExtra("ID",0)

        CoroutineScope(Dispatchers.IO).launch {
            val notes = db.noteDao().getNoteId(noteId)[0]
            binding.editTitle.setText(notes.title)
            binding.editNote.setText(notes.note)
        }
    }
}