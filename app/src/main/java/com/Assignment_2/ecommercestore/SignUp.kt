package com.Assignment_2.ecommercestore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class SignUp : AppCompatActivity() {
    private lateinit var  auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        auth= FirebaseAuth.getInstance()
    }

    fun signup(view: View) {
        val editTextEmail : EditText = findViewById(R.id.editTextEmail)
        val editTextPassword : EditText = findViewById(R.id.editTextPassword)

        val email : String = editTextEmail.text.toString()
        val password : String = editTextPassword.text.toString()

        Log.d("DEBUG", email)
        Log.d("DEBUG", password)

        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener { task ->
            if(task.isSuccessful){
                val intent= Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }.addOnFailureListener { exception ->
            Toast.makeText(applicationContext,exception.localizedMessage,Toast.LENGTH_LONG).show()
        }

    }

    fun goToLogin(view: View) {
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
    }
}