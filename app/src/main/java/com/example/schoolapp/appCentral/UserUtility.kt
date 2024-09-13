package com.example.schoolapp.appCentral

 class User(
    var email: String,
    var docId: String,
//    var name: String,
    var userType: UserType
 )








enum class UserType {

     TEACHER, SCHOOL_HEAD

}