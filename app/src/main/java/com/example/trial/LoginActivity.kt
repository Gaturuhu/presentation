package com.example.trial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_signup.*
import kotlin.math.sign

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()


        btn_login.setOnClickListener {
            doLogin()
        }
        btn_sign.setOnClickListener{
            startActivity(Intent(this,signup::class.java))
        }
    }
    private fun doLogin(){

        if (tv_loginusername.text.toString().isEmpty()) {
            tv_loginusername.error = "Please enter email"
            tv_loginusername.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(tv_loginusername.text.toString()).matches()) {
            tv_loginusername.error = "Please enter valid email"
            tv_loginusername.requestFocus()
            return
        }
        if (tv_loginpassword.text.toString().isEmpty()) {
            tv_loginpassword.error = "Please enter password"
            tv_loginpassword.requestFocus()
            return
        }

        auth.signInWithEmailAndPassword(tv_loginusername.text.toString(), tv_loginpassword.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(baseContext, "Login  failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)
                    // ...
                }

                // ...
            }
    }
    public override fun onStart(){
        super.onStart()
        val currentUser:FirebaseUser? = auth.currentUser
        updateUI(currentUser)
    }
    private fun updateUI(currentUser: FirebaseUser?) {


        if (currentUser != null) {
            if(currentUser.isEmailVerified) {
                if (currentUser.email=="monarchschoolapp@gmail.com"){
                    startActivity(Intent(this,Admin::class.java))
                    finish()
                }else {
                    val fbdb_ref = FirebaseDatabase.getInstance().reference.child("Students")

                    fbdb_ref.addValueEventListener(object : ValueEventListener {
                        override fun onDataChange(p0: DataSnapshot) {
                            //clear previously added data in the user array

                            for (snap in p0.children) {
                                val student = snap.getValue(StudentSchema::class.java)
                                if (student != null) {
                                    if (currentUser.email == student.user_mail) {
                                        val student_email = student.user_mail
                                        val firstname = student.first_name
                                        val lastname = student.last_name
                                        val admission = student.admissionno

                                        val intent =
                                            Intent(applicationContext, StudentProfile::class.java)
                                        intent.putExtra("arafa", student_email)
                                        intent.putExtra("namba", admission)
                                        intent.putExtra("jina", firstname)
                                        intent.putExtra("jina2", lastname)

                                        startActivity(intent)
                                        finish()

                                    }

                                }


                            }

                        }

                        override fun onCancelled(p0: DatabaseError) {
                            Toast.makeText(
                                applicationContext,
                                "Database Locked, Please Wait or contact Developer",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    })
                }

            }else{
                Toast.makeText(
                    baseContext, "Please verify your email address.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else {
            Toast.makeText(
                baseContext, "Login failed.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }


}