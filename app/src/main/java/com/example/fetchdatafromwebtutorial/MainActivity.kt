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

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fetchData().start()
    }

     fun fetchData(): Thread {
        return Thread {
            val url = URL("https://89664529-4f7d-4aa4-bdd7-1a48c9bb8a88.mock.pstmn.io/utilisateurs")
            val connection = url.openConnection() as HttpsURLConnection

            if (connection.responseCode == 200) {
                val inputSystem = connection.inputStream
                val inputStreamReader = InputStreamReader(inputSystem, "UTF-8")
                val request = Gson().fromJson(inputStreamReader, Array<Request>::class.java).toList()

                inputStreamReader.close()
                inputSystem.close()
                runOnUiThread {
                    updateForm(request)
                }
                binding.connexion.text = "Connection Success"
            } else {
                binding.connexion.text = "Failed Connection"
            }
        }
    }

    private fun updateForm(userList: List<Request>) {
        val buttonLogin = findViewById<Button>(R.id.button)
        buttonLogin.setOnClickListener {

            for (user in userList) {
                //Utilisateur
                if ( (binding.identifiant.text.toString() == user.username) && (binding.mdp.text.toString() == user.mdp)  && (user.status == "utilisateur") ) {

                    val idTest=user.id.toString()

                    //Passage d'une variable vers MainReussie
                    val intent= Intent(this , MainReussie::class.java)
                    intent.putExtra("idtest",idTest)
                    println(idTest)




                    startActivity(intent)
                    return@setOnClickListener

                //Administrateur
                } else if (binding.identifiant.text.toString() == user.username && binding.mdp.text.toString() == user.mdp && user.status == "administrateur") {
                    val intent = Intent(applicationContext, MainReussie2::class.java)
                    startActivity(intent)

                    return@setOnClickListener

                //Erreur
                } else {
                    val intent = Intent(applicationContext, MainRater::class.java)
                    startActivity(intent)

                }
            }
        }
    }
}
