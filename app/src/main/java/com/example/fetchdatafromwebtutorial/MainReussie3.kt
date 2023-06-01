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

class MainReussie3 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.reussie3)

        val button2 = findViewById<Button>(R.id.button2)
        button2.setOnClickListener {
            val intent = Intent(applicationContext, Ouvrir::class.java)
            startActivity(intent)
        }

        // Passage à la plaque gererplaque
        val button9 = findViewById<Button>(R.id.button9)
        button9.setOnClickListener {
            val intent = Intent(applicationContext, MainReussie2::class.java)
            startActivity(intent)
        }

        // Se déconnecter
        val button4 = findViewById<Button>(R.id.button4)
        button4.setOnClickListener {
            val intent = Intent(applicationContext, MainRater::class.java)
            startActivity(intent)
        }
    }
}
