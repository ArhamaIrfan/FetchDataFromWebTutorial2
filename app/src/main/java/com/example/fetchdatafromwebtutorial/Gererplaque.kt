package com.example.fetchdatafromwebtutorial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.gson.Gson
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class Gererplaque : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.gerer_plaque)
        fetchData().start()
    }

    private fun fetchData(): Thread {
        return Thread {
            val url = URL("https://89664529-4f7d-4aa4-bdd7-1a48c9bb8a88.mock.pstmn.io/utilisateurs")
            val connection = url.openConnection() as HttpsURLConnection

            if (connection.responseCode == HttpsURLConnection.HTTP_OK) { // utiliser HttpsURLConnection.HTTP_OK pour 200
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

    private fun updateForm(userList: List<Request>) {
        // Utilisateur
        val recue = intent.getStringExtra("idtest")
        val recue2 = recue.toString()
        var message = ""
        var userFound =
            false // ajouter un indicateur pour savoir si l'utilisateur a été trouvé ou non

        for (user in userList) {
            if (user.id.toString() == recue2) {
                userFound = true // mettre l'indicateur à vrai
                val dash = Array(6) { arrayOfNulls<String>(2) }

                val plaque1 = user.plaque1
                val plaque2 = user.plaque2
                val plaque3 = user.plaque3
                val plaque4 = user.plaque4
                val plaque5 = user.plaque5

                dash[1][0] = "1"
                dash[2][0] = "2"
                dash[3][0] = "3"
                dash[4][0] = "4"
                dash[5][0] = "5"
                dash[1][1] = "$plaque1"
                dash[2][1] = "$plaque2"
                dash[3][1] = "$plaque3"
                dash[4][1] = "$plaque4"
                dash[5][1] = "$plaque5"


                for (ligne in dash.indices) {
                    for (valeur in dash[ligne].indices) {
                        if (dash[ligne][valeur] != null) // vérifier si la valeur n'est pas nulle avant de l'afficher
                            message += "${dash[ligne][valeur]} "
                        else {
                            message += "NULL    " // si la valeur est nulle, afficher "NULL"
                        }
                    }
                    message += "\n"
                }
                break
            }
        }

        if (userFound) { // afficher le message si l'utilisateur a été trouvé
            val dashTextView = findViewById<TextView>(R.id.gerer)
            dashTextView.text = message
            val buttonModifier = findViewById<Button>(R.id.modifier)
            val buttonAjouter = findViewById<Button>(R.id.ajouter)
            val buttonSupprimer = findViewById<Button>(R.id.supprimer)

            buttonModifier.setOnClickListener {

                val intent = Intent(this, ModifierPlaqueUtilisateur::class.java)
                startActivity(intent)
                return@setOnClickListener
            }
            buttonAjouter.setOnClickListener {

                val intent = Intent(this, AjouterPlaqueUtilisateur::class.java)
                startActivity(intent)
                return@setOnClickListener
            }
            buttonSupprimer.setOnClickListener {

                val intent = Intent(this, SupprimerPlaqueUtilisateur::class.java)
                startActivity(intent)
                return@setOnClickListener
            }
        }

        else {

        }
        println("Erreur")
    }
}
