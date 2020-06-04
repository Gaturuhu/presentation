package com.example.trial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_students.*

class Students : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_students)



        adduser.setOnClickListener{
            //this one goes to add student page
            startActivity(Intent(this,MainActivity::class.java))
        }
        btnviewusers.setOnClickListener{
            //this one retrieves data of all students details
            startActivity(Intent(this,View::class.java))
        }
//


    }
}
