package com.aliad.navigation3cmp.ui.screen.bottomSheet

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.aliad.navigation3cmp.navigation.AppDestination

@Composable
fun NoteDetailsScreen(details: AppDestination.Dest.NoteDetails) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            Text(
                text = "This is note details ${details.destId}"
            )
        }

}