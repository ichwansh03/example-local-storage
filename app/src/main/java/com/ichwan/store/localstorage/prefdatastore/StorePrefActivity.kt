package com.ichwan.store.localstorage.prefdatastore

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import com.ichwan.store.localstorage.databinding.ActivityStorePrefBinding
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class StorePrefActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStorePrefBinding
    private val Context.dataStore by preferencesDataStore("settings")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStorePrefBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            buttonStore.setOnClickListener {
                lifecycleScope.launch {
                    save(inputKey.text.toString(), inputValue.text.toString())
                }
            }

            buttonGet.setOnClickListener {
                lifecycleScope.launch {
                    val values = read(inputKeyForValue.text.toString())
                    txLatest.text = values ?: "No value found!"
                }
            }
        }
    }

    private suspend fun save(key: String, value: String){
        val dataStoreKey = stringPreferencesKey(key)
        dataStore.edit { settings ->
            settings[dataStoreKey] = value
        }
    }

    private suspend fun read(key: String): String? {
        val dataStoreKey = stringPreferencesKey(key)
        val preferences = dataStore.data.first()
        return preferences[dataStoreKey]
    }
}