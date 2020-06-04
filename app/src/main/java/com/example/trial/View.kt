package com.example.trial

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_view.*

class View : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)

        //pull data from data base
        getstudents()



    }
    fun getstudents() {
        val userarray: ArrayList<StudentSchema> = ArrayList()
        val myAdapter = CustomAdapter(this, userarray)

        val progress = showProgress()

        //fetch data from db
        progress.show()
        val fbdb_ref = FirebaseDatabase.getInstance().reference.child("Students")

        fbdb_ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                //clear previously added data in the user array
                userarray.clear()
                for (snap in p0.children) {
                    val student = snap.getValue(StudentSchema::class.java)
                    userarray.add(student!!)

                }
                myAdapter.notifyDataSetChanged()
                progress.dismiss()
            }

            override fun onCancelled(p0: DatabaseError) {
                progress.dismiss()
                showMessage("Database Locked","Please Wait or contact Developer")
            }
        })

        user_list.adapter = myAdapter
    }

    fun showMessage(title:String,Message: String) {
        val dialogBox = AlertDialog.Builder(this)
        dialogBox.setTitle(title)
        dialogBox.setMessage(Message)
        dialogBox.setPositiveButton("OK",{dialog, which -> dialog.dismiss()  })
        dialogBox.create().show()
    }
    //progress bar
    fun showProgress(): ProgressDialog {
        val progress = ProgressDialog(this)
        progress.setTitle("Geting Data...")
        progress.setMessage("Please wait")
        return progress
    }
}

