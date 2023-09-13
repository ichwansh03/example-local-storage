package com.ichwan.store.localstorage.prefdatastore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ichwan.store.localstorage.R
import com.ichwan.store.localstorage.databinding.ActivityStorePrefBinding

class StorePrefActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStorePrefBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStorePrefBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}