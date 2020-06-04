package com.example.trial

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //add data into the database
        btnaddstudent.setOnClickListener {
            adduser()
        }

    }
    fun adduser(){
        val email = studentemail.text.trim().toString()
        val adm = studentadmno.text.trim().toString()
        val firstname = studentFname.text.trim().toString()
        val lastname = studentLname.text.trim().toString()
//        val password = studentPword.text.trim().toString()
        val id = System.currentTimeMillis()
        //initialize show progress bar
        val appProgress = showProgress()

        //validate inputs
        if(email.isEmpty()||adm.isEmpty()||firstname.isEmpty()||lastname.isEmpty()){
            showMessage("Empty Fields","Please fill in all fields")
        }else{
            //firebase intergration
            //save data into the database
            val my_ref = FirebaseDatabase.getInstance().reference.child("Students/$id")

            val user_object = StudentSchema(email,adm,firstname,lastname)

            //show progress as data is being added
            appProgress.show()
            my_ref.setValue(user_object).addOnCompleteListener{task->
                appProgress.dismiss()
                if(task.isSuccessful){
                    showMessage("Saved","Data has been added Succesfully")
                    clearEdittext()

                }else{
                    showMessage("Not saved!","Data has not been Added")
                }
            }
        }
    }









    //show dialog to the user
    fun showMessage(title:String, message: String){
        val dialogBox = AlertDialog.Builder(this)
        dialogBox.setTitle(title)
        dialogBox.setMessage(message)

        dialogBox.setPositiveButton("OK",{dialog,which->dialog.dismiss() })
        dialogBox.create().show()
    }
    //progress bar
    fun showProgress(): ProgressDialog {
        val progress = ProgressDialog(this)
        progress.setTitle("Saving Data...")
        progress.setMessage("Please wait")
        return progress

    }

    fun clearEdittext(){
        studentemail.setText(null)
        studentadmno.setText(null)
        studentFname.setText(null)
        studentLname.setText(null)
//        studentPword.setText(null)
    }
    fun showPassword(){
        //create show password function
    }

}