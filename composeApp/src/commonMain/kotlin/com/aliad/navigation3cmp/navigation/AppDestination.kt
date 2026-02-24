package com.aliad.navigation3cmp.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed class AppDestination : NavKey {

    @Serializable
    data object Auth : AppDestination(){

        @Serializable
        data object SignIn : AppDestination()

        @Serializable
        data object SignUp : AppDestination()
    }

    @Serializable
    data object Note : AppDestination(){

        @Serializable
        data object NoteList : AppDestination()

        @Serializable
        data object Details : AppDestination()
    }
}