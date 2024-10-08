package com.example.littlelemonfinal

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavComposable(navController: NavHostController, database: LittleLemonDatabase) {
    val user = doesUserExist()

    NavHost(
        navController = navController,
        startDestination = if(user) {
            Onboarding.route
        } else {
            Home.route
        }
    ) {
        composable(Onboarding.route) {
            //OnboardingPage(navController = navController)
        }

        composable(Home.route) {
            //HomePage(navController = navController)
        }

        composable(Profile.route) {
            //ProfilePage(navController = navController)
        }
    }
}

@Composable
fun doesUserExist() : Boolean {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences(USER_PROFILE, Context.MODE_PRIVATE)
    val mailId = sharedPreferences.getString(MAIL_ID, "") ?: ""
    return mailId.isNotBlank()
}