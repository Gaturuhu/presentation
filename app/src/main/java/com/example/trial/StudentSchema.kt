package com.example.trial

class StudentSchema {
    //blueprint
    //this is how data will be stored in the database....
    var user_mail : String = ""
    var admissionno : String = ""
    var first_name : String = ""
    var last_name : String = ""
//    var user_password : String = ""
    var is_admin:Boolean = false

    constructor(email:String, admno: String, firstname:String, lastname:String){

        this.user_mail = email
        this.admissionno = admno
        this.first_name = firstname
        this.last_name = lastname
//        this.user_password = password
        this.is_admin = false
    }

    constructor(){}

}
