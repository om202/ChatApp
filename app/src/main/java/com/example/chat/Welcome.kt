package com.example.chat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.internal.InternalTokenProvider
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_welcome.*

class Welcome : AppCompatActivity() {
    var fireBaseUser: FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        supportActionBar?.hide() // hide top bar
        welcome_register_btn.setOnClickListener{
            val intent = Intent(this@Welcome, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }

        welcome_login_btn.setOnClickListener{
            val intent = Intent(this@Welcome, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        fireBaseUser = FirebaseAuth.getInstance().currentUser

        if(fireBaseUser !=null)
        {
            val intent = Intent(this@Welcome, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}