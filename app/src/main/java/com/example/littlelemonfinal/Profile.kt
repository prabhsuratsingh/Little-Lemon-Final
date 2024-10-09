package com.example.littlelemonfinal

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.littlelemonfinal.ui.theme.AppColors

@Composable
fun ProfilePage(navController: NavController) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences(USER_PROFILE, Context.MODE_PRIVATE)
    val firstName = sharedPreferences.getString(FIRST_NAME, "N/A")
    val lastName = sharedPreferences.getString(LAST_NAME, "N/A")
    val email = sharedPreferences.getString(MAIL_ID, "N/A")


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Profile Logo",
            modifier = Modifier.padding(top = 16.dp)
        )
        Text(
            text = "PROFILE",
            style = typography.headlineMedium,
            modifier = Modifier.padding(top = 24.dp, bottom = 24.dp)
        )


        Text(
            "$firstName",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp, vertical = 8.dp)
                .border(
                    BorderStroke(2.dp, SolidColor(AppColors.charcoal)),
                    shape = RoundedCornerShape(5.dp)
                )
                .padding(5.dp),
            style = typography.bodyMedium,
            textAlign = TextAlign.Center
        )
        Text(
            "$lastName",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp, vertical = 8.dp)
                .border(
                    BorderStroke(2.dp, SolidColor(AppColors.charcoal)),
                    shape = RoundedCornerShape(5.dp)
                )
                .padding(5.dp),
            style = typography.bodyMedium,
            textAlign = TextAlign.Center
        )
        Text(
            "$email",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp, vertical = 8.dp)
                .border(
                    BorderStroke(2.dp, SolidColor(AppColors.charcoal)),
                    shape = RoundedCornerShape(5.dp)
                )
                .padding(5.dp),
            style = typography.bodyMedium,
            textAlign = TextAlign.Center
        )

        Button(
            onClick = {
                sharedPreferences.edit().clear().apply()

                navController.navigate(Onboarding.route) {
                    popUpTo(Onboarding.route) { inclusive = true }
                }
            },
            modifier = Modifier
                .padding(top = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = AppColors.yellow
            )
        ) {
            Text(
                "Log out",
                color = Color.Black
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    val navController = rememberNavController()
    ProfilePage(navController)
}