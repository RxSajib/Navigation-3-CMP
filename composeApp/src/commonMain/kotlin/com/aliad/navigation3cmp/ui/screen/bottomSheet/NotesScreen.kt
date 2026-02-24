package com.aliad.navigation3cmp.ui.screen.bottomSheet

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.aliad.navigation3cmp.navigation.AppDestination

@Composable
fun NotesScreen(rootBackStack: NavBackStack<NavKey>) {
    Scaffold { innerPadding ->
        Box(modifier = Modifier.fillMaxSize().padding(innerPadding), contentAlignment = Alignment.Center){
            Button(onClick = {
                rootBackStack.add(AppDestination.Dest("note details"))
            }){
                Text(
                    text = "Goto Note Details"
                )
            }
        }
    }
}