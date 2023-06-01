package com.example.fetchdatafromwebtutorial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.fetchdatafromwebtutorial.databinding.ActivityMainBinding
import com.google.gson.Gson
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.view.View
import android.widget.TextView

class Reussie3 : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reussie3)
        val recue = intent.getStringExtra("reponse")
        val recue2 = recue.toString()
        println(recue2)
        val textViewResult = findViewById<TextView>(R.id.reussie)
        textViewResult.setText(recue2)

    }


}
