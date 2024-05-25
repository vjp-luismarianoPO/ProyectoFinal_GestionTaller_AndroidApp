package com.example.gestiontaller.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.gestiontaller.MainActivity
import com.example.gestiontaller.R
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        val email: EditText = findViewById(R.id.editTextEmail)
        val password: EditText = findViewById(R.id.editTextPassword)
        val loginButton: Button = findViewById(R.id.loginButton)
        //Get an instance of FireBaseAuth to handle authentication
        val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

        val emailTxt = email.text
        val passwordTxt = password.text

        //Add a listener for login button
        loginButton.setOnClickListener {
            //Check the email and password
            if (emailTxt.isNotEmpty() && passwordTxt.isNotEmpty()) {
                //Try to sign in with Firebase Authentication
                firebaseAuth.signInWithEmailAndPassword(
                    email.text.toString(),
                    password.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        //If is succesful, start MainActivity
                        val intent = Intent(applicationContext, MainActivity::class.java)
                        startActivity(intent)
                    } else
                        showToast("Error en el usuario o contraseña")
                }
            } else {
                showToast("Rellene usuario y contraseña")
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()

    }
}