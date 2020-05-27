package com.example.a00_practica_quiz.Activity

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import com.example.a00_practica_quiz.AbstractClass.BaseActivity
import com.example.a00_practica_quiz.Models.DataHolder
import com.example.a00_practica_quiz.R
import io.paperdb.Paper
import kotlinx.android.synthetic.main.activity_main.*

class StartActivity : BaseActivity() {

    lateinit var sharedPref: SharedPreferences




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Saved in the memory
        sharedPref = getSharedPreferences(DataHolder.DBName, Context.MODE_PRIVATE)


        Log.w("miapp","nombre $sharedPref")
        Log.w("miapp","nombre ${DataHolder.user}")

        isLogged()

        button_start.setOnClickListener {
            var name = isNameAndLengthValid(text_input_name.text.toString())

            if (name){
                DataHolder.username = text_input_name.text.toString()
                storeMemory()
                gotoActivity(HomeQuestionsActivity())
            }
        }

    }

    // Check If the condictions are good to go to the next MainActivity
    fun isNameAndLengthValid(name: String): (Boolean) {
        var isValidName = !name.isNullOrBlank()
        var isValidLengthName = false

        var text_length = name.length

        if (text_length <= 10) {
            isValidLengthName = true
        }

        Log.w("miapp","$isValidName and $isValidLengthName")

        when (isValidName && isValidLengthName) {
            false-> text_input_name.error = getString(R.string.error_Name)
            true -> textInputLayout.error = null
        }

        return (isValidName && isValidLengthName)
    }

    fun storeMemory(){
        val edit = sharedPref.edit()
        edit.putString(DataHolder.user, text_input_name.text.toString())
        edit.apply()
    }

    fun isLogged(){
        var username: String? = sharedPref.getString(DataHolder.user, null)

        Log.w("miapp","nombre $username")

        if(username != null){
            text_input_name.setText(username)
        }
    }

}
