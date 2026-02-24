package com.aliad.navigation3cmp.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import com.aliad.navigation3cmp.ui.screen.bottomSheet.NoteDetailsScreen
import com.aliad.navigation3cmp.ui.screen.dest.profile.ProfileScreen
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlin.collections.listOf

@Composable
fun DestNavigation(firstDest: AppDestination.Dest) {

    val appConfig = SavedStateConfiguration {
        serializersModule = SerializersModule {
            polymorphic(NavKey::class) {
                subclass(AppDestination.Dest.NoteDetails::class, AppDestination.Dest.NoteDetails.serializer())
                subclass(AppDestination.Dest.Profile::class, AppDestination.Dest.Profile.serializer())
            }
        }
    }

    val firstDest = when{
        firstDest.firstElement == "note details" -> AppDestination.Dest.NoteDetails(destId = 0)
        firstDest.firstElement == "profile screen" -> AppDestination.Dest.Profile
        else -> throw Exception("Invalid destination")
    }

    val backStack = rememberNavBackStack(configuration = appConfig, firstDest)

    NavDisplay(
        backStack = backStack,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = entryProvider {
            entry<AppDestination.Dest.NoteDetails> {details ->
                NoteDetailsScreen(details)
            }
            entry<AppDestination.Dest.Profile> {
                ProfileScreen()
            }
        }
    )
}