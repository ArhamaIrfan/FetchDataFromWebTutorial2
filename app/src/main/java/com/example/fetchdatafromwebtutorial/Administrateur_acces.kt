package com.example.fetchdatafromwebtutorial

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import java.io.InputStreamReader
import java.net.URL
import java.sql.DriverManager.println
import javax.net.ssl.HttpsURLConnection

class Administrateur_acces : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.administrateur_acces)

        val recue = intent.getStringExtra("reponse")
        val recue2 = recue.toString()
        var message = ""

        val button2 = findViewById<Button>(R.id.button2)
        button2.setOnClickListener {
            val intent = Intent(this, ModifierPlaqueAdministrateur::class.java)
            intent.putExtra("reponse", recue)
            println(recue2)
            startActivity(intent)
        }

        val button6 = findViewById<Button>(R.id.button6)
        button6.setOnClickListener {
            val intent = Intent(this, SupprimerPlaqueAdministrateur::class.java)
            intent.putExtra("reponse", recue)
            println(recue)
            startActivity(intent)
        }
    }
}






