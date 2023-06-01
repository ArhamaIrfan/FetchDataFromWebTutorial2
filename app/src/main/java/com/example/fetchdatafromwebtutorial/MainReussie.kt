package com.example.fetchdatafromwebtutorial



import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.sql.DriverManager.println


class MainReussie : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reussie)
        val recue=intent.getStringExtra("idtest")

        //Recuperation de la valeur de MainActivity




        //Passage a la pahe ouvrir
        val button2 = findViewById<Button>(R.id.button2)
        button2.setOnClickListener {
            val intent = Intent(applicationContext, Ouvrir::class.java)
            startActivity(intent)
        }
        //passage a la plaque gererplaque
        val button9 = findViewById<Button>(R.id.button9)
        button9.setOnClickListener {

            if (recue != null) {
                println("idrecue:$recue")
            } else {
                println("L'extra 'idTest' n'est pas présent dans l'intent.")
            }
            val intent= Intent(this , Gererplaque::class.java)
            intent.putExtra("idtest",recue)
            startActivity(intent)


        }
        //Se deconnecter
        val button4 = findViewById<Button>(R.id.button4)
        button4.setOnClickListener {

        }

    }
}
