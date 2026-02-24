package com.aliad.navigation3cmp.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlin.collections.listOf

@Composable
fun RootNavigation() {

    val appConfig = SavedStateConfiguration {
        serializersModule = SerializersModule {
            polymorphic(NavKey::class) {
                subclass(AppDestination.Auth::class, AppDestination.Auth.serializer())
                subclass(AppDestination.Note::class, AppDestination.Note.serializer())
            }
        }
    }

    val navBackStack = rememberNavBackStack(configuration = appConfig, AppDestination.Auth)

    NavDisplay(
        backStack = navBackStack,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = entryProvider {
            entry<AppDestination.Auth> {
                AuthNavigation(rootBackStack = navBackStack)
            }
            entry<AppDestination.Note> {
                NoteNavigation()
            }
        }
    )

}