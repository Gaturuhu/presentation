package com.example.trial

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_signup.*
import kotlinx.android.synthetic.main.activity_student_profile.*
import kotlinx.android.synthetic.main.activity_view.*

class StudentProfile : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_profile)

        val intent  = intent
        val email  = intent.getStringExtra("arafa")
        val admission  = intent.getStringExtra("namba")
        val firstname  = intent.getStringExtra("jina")
        val lastname  = intent.getStringExtra("jina2")


        student_email.setText(email)
        student_admission.setText(admission)
        student_Firstname.setText(firstname)
        student_lastname.setText(lastname)



    }
}