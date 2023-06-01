package com.example.fetchdatafromwebtutorial

import android.os.AsyncTask
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket

class ServerConnection : AsyncTask<Void, Void, String>() {
    private val host = "192.168.0.1" // Remplacez par l'adresse IP du serveur
    private val port = 8000 // Remplacez par le port du serveur

    override fun doInBackground(vararg params: Void?): String? {
        try {
            val socket = Socket(host, port)

            val inputReader = BufferedReader(InputStreamReader(socket.getInputStream()))
            val outputWriter = PrintWriter(socket.getOutputStream(), true)

            // Envoyer des données au serveur
            val message = "Hello Server"
            outputWriter.println(message)

            // Lire la réponse du serveur
            val response = inputReader.readLine()

            // Fermer les ressources
            outputWriter.close()
            inputReader.close()
            socket.close()

            return response
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }

    override fun onPostExecute(result: String?) {
        // Gérer la réponse du serveur ici
        if (result != null) {
            // Faites quelque chose avec la réponse
        }
    }
}