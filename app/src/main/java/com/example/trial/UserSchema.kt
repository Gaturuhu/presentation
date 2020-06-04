package com.example.trial

import android.widget.EditText

class UserSchema {
    var user_mail : String = ""
    var admissionno : String = ""
    var is_admin:Boolean = false

    constructor(email:String, admno: String){

        this.user_mail = email
        this.admissionno = admno
        this.is_admin = false
    }

    constructor(){}

}

