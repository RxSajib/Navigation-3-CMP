package com.aliad.navigation3cmp.ui.screen.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun SignUpScreen(){
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(
                innerPadding
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Sign Up Screen"
            )

            Button(onClick = {}) {
                Text(
                    text = "Sign Up Button"
                )
            }
        }
    }
}