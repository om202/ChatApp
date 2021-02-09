package com.example.chat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private  lateinit var mAuth: FirebaseAuth
    private lateinit var refUser: DatabaseReference
    private var firebaseUserId: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide() // hide top bar

        mAuth = FirebaseAuth.getInstance()
        login_btn.setOnClickListener{
            loginUser()
        }
    }

    private fun loginUser() {
        val email: String = email_login.text.toString()
        val password: String = password_login.text.toString()
        when {
            email == "" -> {
                val toast = Toast.makeText(this@LoginActivity, "Please enter your email!", Toast.LENGTH_SHORT)
                toast.show()
            }
            password == "" -> {
                val toast = Toast.makeText(this@LoginActivity, "Please enter your password!", Toast.LENGTH_SHORT)
                toast.show()
            }
            else -> {
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener {
                            task ->
                            if(task.isSuccessful)
                            {
                                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                                startActivity(intent)
                                finish()
                            }else {
                                val toast = Toast.makeText(this@LoginActivity, "Error"+task.exception!!.message.toString(), Toast.LENGTH_LONG)
                                toast.show()
                            }
                        }
            }
        }
    }
}