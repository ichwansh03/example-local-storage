package com.ichwan.store.localstorage.realm

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.ichwan.store.localstorage.databinding.ActivityMainBinding
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val config = RealmConfiguration.Builder(
            schema = setOf(Item::class),
        ).build()

        Log.d("test", "created Realm...")

        realm = Realm.open(config)

        binding.apply {
            buttonSave.setOnClickListener {
                lifecycleScope.launch {
                    saveData()
                }
            }

            buttonUpdate.setOnClickListener {
                lifecycleScope.launch {
                    updateData()
                }
            }

            buttonDelete.setOnClickListener {
                lifecycleScope.launch {
                    deteleData()
                }
            }
        }

        realm.close()

    }

    private suspend fun deteleData() {
        realm.write {
            val item: Item = this.query<Item>("_id == $0", Item().id).find().first()

            delete(item)
        }
    }

    private suspend fun updateData() {
        realm.write {
            val item: Item? = this.query<Item>("_id == $0", Item().id).first().find()

            item?.name = binding.nameInput.text.toString()
            item?.email = binding.emailInput.text.toString()
            item?.address = binding.addressInput.text.toString()
        }
    }

    @SuppressLint("SetTextI18n")
    private suspend fun saveData() {
        realm.write {
            this.copyToRealm(Item().apply {
                name = binding.nameInput.text.toString()
                email = binding.emailInput.text.toString()
                address = binding.addressInput.text.toString()

            })
        }
    }
}