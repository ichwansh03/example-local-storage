package com.ichwan.store.localstorage.preferences

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ichwan.store.localstorage.R
import com.ichwan.store.localstorage.databinding.ActivityPreferencesBinding

class PreferencesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPreferencesBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreferencesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPref = getSharedPreferences("myPref", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        binding.apply {
            buttonSave.setOnClickListener {

                val name = etName.text.toString()
                val age = etAge.text.toString().toInt()
                val adult = cbAdult.isChecked

                editor.apply {
                    putString("name", name)
                    putInt("age", age)
                    putBoolean("isAdult", adult)
                    apply()
                }

                txLatest.text = "Latest Data: $name in $age years old, adult = $adult"
            }

            buttonLoad.setOnClickListener {
                val name = sharedPref.getString("name", null)
                val age = sharedPref.getInt("age", 0)
                val isAdult = sharedPref.getBoolean("isAdult", false)

                etName.setText(name)
                etAge.setText(age.toString())
                cbAdult.isChecked = isAdult
            }
        }
    }
}