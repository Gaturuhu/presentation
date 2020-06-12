package com.example.trial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_admin.*

class Admin : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)

        auth = FirebaseAuth.getInstance()

        btnstudent.setOnClickListener {
            //this one goes to students page
            startActivity(Intent(this,Students::class.java))
        }
        btnlogout.setOnClickListener{
            logout()
            auth.addAuthStateListener {
                if(auth.currentUser==null){
                    this.finish()
                }
            }
        }
//        btnperformance.setOnClickListener {
//            //this one goes to performance page
//           //startActivity(Intent(this,Mainperformance::class.java))
//        }
//        btnfee.setOnClickListener {
//            //this one goes to fee page
//           // startActivity(Intent(this,Mainfee::class.java))
//        }
    }
    fun logout(){
        auth.signOut()

    }
}
