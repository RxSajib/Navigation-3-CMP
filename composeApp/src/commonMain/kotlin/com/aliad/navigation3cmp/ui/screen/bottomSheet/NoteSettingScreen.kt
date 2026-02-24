package com.aliad.navigation3cmp.ui.screen.bottomSheet

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
fun NoteSettingScreen(rootBackStack: NavBackStack<NavKey>) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Button(onClick = {
                rootBackStack.add(AppDestination.Dest(AppDestination.Dest.Profile::class.simpleName?: ""))
            }) {
                Text("Profile")
            }

            Spacer(modifier = Modifier.height(10.dp))

            Button(onClick = {
                rootBackStack.clear()
                rootBackStack.add(AppDestination.Auth)
            }) {
                Text("Logout")
            }
        }
    }
}