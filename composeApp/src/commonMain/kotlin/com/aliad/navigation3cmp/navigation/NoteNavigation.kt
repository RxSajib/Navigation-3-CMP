package com.aliad.navigation3cmp.navigation

import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import com.aliad.navigation3cmp.App
import com.aliad.navigation3cmp.ui.screen.bottomSheet.NoteDetailsScreen
import com.aliad.navigation3cmp.ui.screen.bottomSheet.NoteSettingScreen
import com.aliad.navigation3cmp.ui.screen.bottomSheet.NotesScreen
import com.aliad.navigation3cmp.ui.screen.bottomSheet.SaveNoteScreen
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import navigation3cmp.composeapp.generated.resources.Res
import navigation3cmp.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteNavigation(rootBackStack: NavBackStack<NavKey>) {

    val appConfig = SavedStateConfiguration {
        serializersModule = SerializersModule {
            polymorphic(NavKey::class) {
                subclass(
                    AppDestination.Note.NoteList::class,
                    AppDestination.Note.NoteList.serializer()
                )
                subclass(
                    AppDestination.Note.SaveNote::class,
                    AppDestination.Note.SaveNote.serializer()
                )
                subclass(
                    AppDestination.Note.NoteSetting::class,
                    AppDestination.Note.NoteSetting.serializer()
                )

            }
        }
    }

    val noteListBackStack =
        rememberNavBackStack(configuration = appConfig, AppDestination.Note.NoteList)
    val saveNoteBackStack =
        rememberNavBackStack(configuration = appConfig, AppDestination.Note.SaveNote)
    val noteSettingBackStack =
        rememberNavBackStack(configuration = appConfig, AppDestination.Note.NoteSetting)


    val AppDestinationSaver: Saver<AppDestination, String> = Saver(
        save = { destination ->
            Json.encodeToString(AppDestination.serializer(), destination)
        },
        restore = { jsonString ->
            Json.decodeFromString(AppDestination.serializer(), jsonString)
        }
    )


    var currentTab by rememberSaveable(
        stateSaver = AppDestinationSaver
    ) {
        mutableStateOf<AppDestination>(AppDestination.Note.NoteList)
    }
    // Select the active back stack
    val activeBackStack: NavBackStack<NavKey> = when (currentTab) {
        is AppDestination.Note.NoteList -> noteListBackStack
        is AppDestination.Note.SaveNote -> saveNoteBackStack
        is AppDestination.Note.NoteSetting -> noteSettingBackStack
        else -> noteListBackStack
    }

    val topBarTitle = when (currentTab) {
        is AppDestination.Note.NoteList    -> "Notes"
        is AppDestination.Note.SaveNote    -> "New Note"
        is AppDestination.Note.NoteSetting -> "Settings"
        else                               -> "Notes"
    }


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = topBarTitle)
                }
            )
        },
        bottomBar = {

                NavigationBar {
                        NavigationBarItem(
                            selected = currentTab is AppDestination.Note.NoteList,
                            onClick = { currentTab = AppDestination.Note.NoteList },
                            icon = { Icon(painter = painterResource(Res.drawable.compose_multiplatform), "Notes", modifier = Modifier.size(24.dp)) },
                            label = { Text("List") }
                        )
                        NavigationBarItem(
                            selected = currentTab is AppDestination.Note.SaveNote,
                            onClick = { currentTab = AppDestination.Note.SaveNote },
                            icon = { Icon(painter = painterResource(Res.drawable.compose_multiplatform), "Notes", modifier = Modifier.size(24.dp)) },
                            label = { Text("Save Note") }
                        )
                        NavigationBarItem(
                            selected = currentTab is AppDestination.Note.NoteSetting,
                            onClick = { currentTab = AppDestination.Note.NoteSetting },
                            icon = { Icon(painter = painterResource(Res.drawable.compose_multiplatform), "Notes", modifier = Modifier.size(24.dp)) },
                            label = { Text("Settings") }
                        )

                }

        }
    ) {
        NavDisplay(
            backStack = activeBackStack,
            entryDecorators = listOf(
                rememberSaveableStateHolderNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator()
            ),
            entryProvider = entryProvider {
                entry<AppDestination.Note.NoteList> {
                    NotesScreen(rootBackStack = rootBackStack)
                }
                entry<AppDestination.Note.SaveNote> {
                    SaveNoteScreen()
                }
                entry<AppDestination.Note.NoteSetting> {
                    NoteSettingScreen(rootBackStack = rootBackStack)
                }
            }
        )
    }



}