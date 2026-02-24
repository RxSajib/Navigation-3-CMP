package com.aliad.navigation3cmp.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import com.aliad.navigation3cmp.ui.screen.note.NoteDetailsScreen
import com.aliad.navigation3cmp.ui.screen.note.NotesScreen
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

@Composable
fun NoteNavigation(){

    val appConfig = SavedStateConfiguration {
        serializersModule = SerializersModule {
            polymorphic(NavKey::class){
                subclass(AppDestination.Note.NoteList::class, AppDestination.Note.NoteList.serializer())
                subclass(AppDestination.Note.Details::class, AppDestination.Note.Details.serializer())
            }
        }
    }

    val appBackStack = rememberNavBackStack(configuration = appConfig, AppDestination.Note.NoteList)

    NavDisplay(
        backStack = appBackStack,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = entryProvider{
            entry<AppDestination.Note.NoteList> {
                NotesScreen(appBackStack = appBackStack)
            }
            entry<AppDestination.Note.Details> {
                NoteDetailsScreen()
            }
        }
    )

}