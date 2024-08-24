package com.example.schoolapp.appCentral

class User(
    var docId: String,
    var name: String,
    var email: String,
    var userType: UserType
    // Preference Settings , Profile Picture and Themes may be
)

enum class UserType {
     TEACHER, SCHOOL_HEAD
}