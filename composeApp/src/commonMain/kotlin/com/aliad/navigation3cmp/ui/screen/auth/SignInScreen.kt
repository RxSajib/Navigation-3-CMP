package com.aliad.navigation3cmp.ui.screen.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.aliad.navigation3cmp.navigation.AppDestination

@Composable
fun SignInScreen(backStack: NavBackStack<NavKey>, rootBackStack: NavBackStack<NavKey>) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(
                innerPadding
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Sign In Screen"
            )

            Button(onClick = {
                backStack.add(AppDestination.Auth.SignUp)
            }) {
                Text(
                    text = "Sign Up Button"
                )
            }

            Spacer(modifier = Modifier.height(40.dp))
            Button(onClick = {
                rootBackStack.clear()
                rootBackStack.add(AppDestination.Note)
            }){
                Text(
                    text = "Goto Note"
                )
            }
        }
    }
}