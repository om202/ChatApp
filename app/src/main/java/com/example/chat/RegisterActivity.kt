package com.example.chat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.toolbar_main_register
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register.*
import java.util.*
import kotlin.collections.HashMap

class RegisterActivity : AppCompatActivity() {

    private  lateinit var mAuth : FirebaseAuth
    private lateinit var refuser: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        supportActionBar?.hide() // hide top bar

        val toolbar = toolbar_main_register
        toolbar.setNavigationOnClickListener{
            val intent = Intent(this@RegisterActivity, Welcome::class.java)
            startActivity(intent)
            finish()
        }

     mAuth = FirebaseAuth.getInstance()
        register_btn.setOnClickListener{
            registerUser()
        }
    }

    private fun registerUser() {
        val name: String = name_register.text.toString()
        val email: String = email_register.text.toString()
        val password: String = password_login.text.toString()

        when {
            name == "" -> {
                val toast = Toast.makeText(this@RegisterActivity, "Please enter your name!", Toast.LENGTH_SHORT)
                toast.show()
            }
            email == "" -> {
                val toast = Toast.makeText(this@RegisterActivity, "Please enter your email!", Toast.LENGTH_SHORT)
                toast.show()
            }
            password == "" -> {
                val toast = Toast.makeText(this@RegisterActivity, "Please enter your password!", Toast.LENGTH_SHORT)
                toast.show()
            }
            else -> {
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{ task ->
                    if (task.isSuccessful){
                        val firebaseUserID : String = mAuth.currentUser!!.uid
                        refuser = FirebaseDatabase.getInstance().reference.child("Users").child(firebaseUserID)
                        val userHashMap = HashMap<String, Any>()
                        userHashMap["uid"] = firebaseUserID
                        userHashMap["name"] = name
                        userHashMap["profile"] = "https://firebasestorage.googleapis.com/v0/b/chat-1b9d0.appspot.com/o/profile.png?alt=media&token=3c634630-7755-4432-890b-c9a22c3e8b69"
                        userHashMap["status"] = "offline"
                        userHashMap["search"] = name.toLowerCase(Locale.getDefault())
                        userHashMap["link1"] = "www.google.com"
                        userHashMap["link2"] = "www.linkedin.com"
                        refuser.updateChildren(userHashMap).addOnCompleteListener{ task2 ->
                            if(task2.isSuccessful){
                                val intent = Intent(this@RegisterActivity, MainActivity::class.java)
                                //to make user not go back to register activity
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                                startActivity(intent)
                                finish()
                            }
                        }
                    } else {
                        Toast.makeText(this@RegisterActivity, "Error:"+task.exception!!.message.toString(), Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}