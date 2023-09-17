package com.ichwan.samples.roomdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ichwan.samples.roomdb.databinding.ActivityEditBinding
import com.ichwan.samples.roomdb.models.Note
import com.ichwan.samples.roomdb.models.NoteDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditBinding
    private lateinit var db: NoteDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NoteDB(this)

        binding.apply {
            buttonSave.setOnClickListener {
                CoroutineScope(Dispatchers.IO).launch {
                    db.noteDao().addNote(Note(0, editTitle.text.toString(), editNote.text.toString()))
                    finish()
                }
            }
        }
    }
}