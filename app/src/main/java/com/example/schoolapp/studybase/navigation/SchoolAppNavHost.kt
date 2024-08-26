package com.example.schoolapp.navigation

import androidx.compose.runtime.Composable

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.schoolapp.studybase.Activate.ActivateScreen
import com.example.schoolapp.authentication.b.NewScreen
import com.example.schoolapp.studybase.Activate.ActivatePassword
import com.example.schoolapp.studybase.Login.ForgetScreen
import com.example.schoolapp.studybase.Login.LoginScreen


sealed class Route(val name:String){
    data object ActivateScreen:Route(name = "activate_screen")
    data object NewScreen:Route(name = "display_screen")
    data object ForgetScreen :Route(name = "forget_screen")
    data object LoginScreen :Route(name = "login_screen")
    data object ActivatePassword :Route(name = "activate_password")
}

@Composable
fun SchoolAppNavHost(navController: NavHostController){

    // check if user is logged in or not by saving this data into a datastore and accessing it after app is reopened by the user
    // the usertype data in User class should listen to data in Role selection screens through which we can decide that which kind of user has logged in
    //  can we instantiate the user in authviewmodels of respective types
    // we can instantiate the User and  class only if either the auth(Signup or Login) is success
    //And when user logs out by pressing the log out button we have to de allocate the memory


    val navController = navController
    NavHost(
        navController = navController,
        startDestination = Route.ActivateScreen.name
    ){
        composable(route = Route.ActivateScreen.name) {
            ActivateScreen(navController)
        }
        composable(route = Route.NewScreen.name) {
            NewScreen(navController)

        }
        composable(route = "forget_screen") {
            ForgetScreen(navController)
        }
        composable(route = "login_screen") {
            LoginScreen(navController)
        }
        composable(route = "activate_password") {
            ActivatePassword(navController)
        }
    }
}

