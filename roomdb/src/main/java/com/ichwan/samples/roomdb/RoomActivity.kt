package com.ichwan.samples.roomdb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.ichwan.samples.roomdb.databinding.ActivityRoomBinding
import com.ichwan.samples.roomdb.models.Note
import com.ichwan.samples.roomdb.models.NoteDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RoomActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRoomBinding
    private var db = NoteDB(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            buttonCreate.setOnClickListener {
                startActivity(Intent(this@RoomActivity, EditActivity::class.java))
            }
        }
    }

    override fun onStart() {
        super.onStart()
        CoroutineScope(Dispatchers.IO).launch {
            val notes = db.noteDao().getNotes()
            Log.d("MainActivity", "Response: $notes")
        }
    }
}