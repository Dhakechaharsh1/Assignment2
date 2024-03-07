package com.Assignment_2.ecommercestore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.tasks.OnCompleteListener
import com.Assignment_2.ecommercestore.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider

private val Any.signInAccount: GoogleSignInAccount?
    get() {
        TODO("Not yet implemented")
    }
private val GoogleSignInResult?.isSuccessful: Boolean
    get() {
        TODO("Not yet implemented")
    }

class Login : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        // Configure Google Sign-In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)
    }

    fun login(view : View) {
        val editTextEmail : EditText = findViewById(R.id.editTextEmail)
        val editTextPassword : EditText = findViewById(R.id.editTextPassword)

        val email = editTextEmail.text.toString()
        val password = editTextPassword.text.toString()

        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener { task ->
            if(task.isSuccessful){
                val intent= Intent(this,ProductActivity::class.java)
                startActivity(intent)
                finish()
            }
        }.addOnFailureListener { exception ->
            Toast.makeText(applicationContext,exception.localizedMessage, Toast.LENGTH_LONG).show()
        }
    }

    fun goToSignup(view : View) {
        val i = Intent(this,SignUp::class.java)
        startActivity(i)
    }

    fun signInWithGoogle(view: View) {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = Auth.GoogleSignInApi.getSignInResultFromIntent(data!!)
            if (task.isSuccessful) {
                val result = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(result.signInAccount)
            } else {
                Toast.makeText(applicationContext, "Google sign in failed", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount?) {
        val credential = GoogleAuthProvider.getCredential(acct?.idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this, ProductActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(applicationContext, "Authentication failed.", Toast.LENGTH_LONG).show()
                }
            }
    }

    companion object {
        private const val RC_SIGN_IN = 9001
    }
}

private fun GoogleSignInResult?.getResult(java: Class<ApiException>): Any {
    TODO("Not yet implemented")
}
