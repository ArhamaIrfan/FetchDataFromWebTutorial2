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

class AjouterPlaqueUtilisateur : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ajouter)

        fetchData().start()
    }

    fun fetchData(): Thread {
        return Thread {
            val url = URL("https://89664529-4f7d-4aa4-bdd7-1a48c9bb8a88.mock.pstmn.io/utilisateurs")
            val connection = url.openConnection() as HttpsURLConnection
            connection.requestMethod = "POST"
            if (connection.responseCode == 200) {
                val inputSystem = connection.inputStream
                val inputStreamReader = InputStreamReader(inputSystem, "UTF-8")
                val resp =
                    Gson().fromJson(inputStreamReader, Request2::class.java)

                inputStreamReader.close()
                inputSystem.close()
                runOnUiThread {

                }
                val buttonAjouter = findViewById<Button>(R.id.ajouter)
                buttonAjouter.setOnClickListener {
                    val gson = Gson()
                    val respJson = gson.toJson(resp)

                    val intent = Intent(this, Reussie3::class.java)
                    intent.putExtra("reponse", respJson)
                    startActivity(intent)


                }
            }
            else {
                println("Connection failed")
            }
        }
    }


}
