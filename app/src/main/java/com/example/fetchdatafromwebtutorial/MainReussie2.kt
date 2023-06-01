package com.example.fetchdatafromwebtutorial

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainReussie2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reussie2)

        val button2 = findViewById<Button>(R.id.button2)
        button2.setOnClickListener {
            val intent = Intent(applicationContext, Ouvrir::class.java)
            startActivity(intent)
        }
        val button4 = findViewById<Button>(R.id.button4)
        button4.setOnClickListener {
            val intent = Intent(applicationContext, GererAcces::class.java)
            startActivity(intent)
        }
        val button6 = findViewById<Button>(R.id.button6)
        button6.setOnClickListener {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
