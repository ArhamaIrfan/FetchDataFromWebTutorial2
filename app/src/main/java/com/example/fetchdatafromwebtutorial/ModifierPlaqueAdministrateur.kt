package com.example.fetchdatafromwebtutorial

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class ModifierPlaqueAdministrateur : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.modifier_administrateur)
        fetchData().start()
    }

    private fun fetchData(): Thread {
        return Thread {
            val url =
                URL("https://89664529-4f7d-4aa4-bdd7-1a48c9bb8a88.mock.pstmn.io/utilisateurs")
            val connection = url.openConnection() as HttpsURLConnection

            if (connection.responseCode == HttpsURLConnection.HTTP_OK) {
                val inputSystem = connection.inputStream
                val inputStreamReader = InputStreamReader(inputSystem, "UTF-8")
                val request =
                    Gson().fromJson(inputStreamReader, Array<Request>::class.java).toList()

                inputStreamReader.close()
                inputSystem.close()
                runOnUiThread {
                    updateForm(request)

                }
                println("Connection Success")
            } else {
                println("Connection Failed")
            }
        }
    }

    fun fetchData2(): Thread {
        return Thread {
            val url = URL("https://89664529-4f7d-4aa4-bdd7-1a48c9bb8a88.mock.pstmn.io/utilisateurs")
            val connection = url.openConnection() as HttpsURLConnection
            connection.requestMethod = "PUT"
            if (connection.responseCode == 200) {
                val inputSystem = connection.inputStream
                val inputStreamReader = InputStreamReader(inputSystem, "UTF-8")
                val resp =
                    Gson().fromJson(inputStreamReader, Request2::class.java)

                inputStreamReader.close()
                inputSystem.close()
                runOnUiThread {

                }
                val gson = Gson()
                val respJson = gson.toJson(resp)

                val intent = Intent(this, Reussie3::class.java)
                intent.putExtra("reponse", respJson)
                startActivity(intent)


            }
            else {
                println("Connection failed")
            }
        }
    }

    private fun updateForm(userList: List<Request>) {
        val recue = intent.getStringExtra("reponse")
        val recue2 = recue.toString()

        val dash = arrayListOf<String>()
        val spinner: Spinner = findViewById(R.id.spinner)
        val items = mutableListOf<String>()
        for (user in userList) {
            if (user.username.toString() == recue2) {

                items.add("ID: ${user.id}")
                items.add("Username: ${user.username}")
                items.add("Password: ${user.mdp}")
                items.add("Status: ${user.status}")
                items.add("Plaque 1: ${user.plaque1}")
                items.add("Plaque 2: ${user.plaque2}")
                items.add("Plaque 3: ${user.plaque3}")
                items.add("Plaque 4: ${user.plaque4}")
                items.add("Plaque 5: ${user.plaque5}")
                break
            }
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        val textView = findViewById<TextView>(R.id.myTextView)
        textView.text = dash.joinToString("\n")

        val button2 = findViewById<Button>(R.id.modifier)
        button2.setOnClickListener {
            startActivity(intent)
            fetchData2().start()
        }
    }
}
