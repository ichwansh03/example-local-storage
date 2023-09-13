package com.ichwan.store.localstorage.preferences

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ichwan.store.localstorage.R
import com.ichwan.store.localstorage.databinding.ActivityPreferencesBinding

class PreferencesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPreferencesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreferencesBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}