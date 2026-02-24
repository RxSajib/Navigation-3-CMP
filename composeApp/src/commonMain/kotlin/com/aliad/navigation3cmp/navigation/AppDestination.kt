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

        @Serializable
        data object NoteSetting : AppDestination()

        @Serializable
        data object SaveNote : AppDestination()
    }


    @Serializable
    data class Dest(val noteDetails: String) : AppDestination(){

        @Serializable
        data class NoteDetails(val destId : Int) : AppDestination()

        @Serializable
        data object Profile : AppDestination()
    }

}