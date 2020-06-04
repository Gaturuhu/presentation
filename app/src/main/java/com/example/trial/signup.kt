package com.example.trial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_signup.*

class signup : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        auth = FirebaseAuth.getInstance()

        btn_sign_up.setOnClickListener {
            signUpUser()
        }
        btn_log_in.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
        }

    }

         private fun signUpUser() {

            val username =tv_username.text.toString()
            val admission =tv_admission.text.toString()
            val pass =tv_password.text.toString()
             val id = System.currentTimeMillis()

            if (username.isEmpty()) {
                tv_username.error = "Please enter email"
                tv_username.requestFocus()
                return
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(tv_username.text.toString()).matches()) {
                tv_username.error = "Please enter valid email"
                tv_username.requestFocus()
                return
            }
            if (admission.isEmpty()) {
                tv_admission.error = "Please enter student admission number"
                tv_username.requestFocus()
                return
            }

            if (pass.isEmpty()) {
                tv_password.error = "Please enter password"
                tv_password.requestFocus()
                return
            }

             val myref = FirebaseDatabase.getInstance().reference.child("Parents/$id")
             val parent_object = UserSchema(username,admission)

             myref.setValue(parent_object).addOnCompleteListener {task ->
                 if(task.isSuccessful){
                     auth.createUserWithEmailAndPassword(username, pass).addOnCompleteListener(this) { task ->
                         if (task.isSuccessful) {
                             val user = auth.currentUser
                             user?.sendEmailVerification()
                                 ?.addOnCompleteListener { task ->
                                     if (task.isSuccessful) {
                                         startActivity(Intent(this, LoginActivity::class.java))
                                         finish()
                                     }
                                 }
                         } else {
                             Toast.makeText(
                                 baseContext, "Sign Up failed. Try again after some time.",
                                 Toast.LENGTH_SHORT
                             ).show()
                         }
                     }

                 }
             }



        }
    }