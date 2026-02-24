package com.aliad.navigation3cmp.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import com.aliad.navigation3cmp.ui.screen.auth.SignInScreen
import com.aliad.navigation3cmp.ui.screen.auth.SignUpScreen
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

@Composable
fun AuthNavigation(rootBackStack: NavBackStack<NavKey>) {

    val appConfig = SavedStateConfiguration {
        serializersModule = SerializersModule {
            polymorphic(NavKey::class){
                subclass(AppDestination.Auth.SignIn::class, AppDestination.Auth.SignIn.serializer())
                subclass(AppDestination.Auth.SignUp::class, AppDestination.Auth.SignUp.serializer())
            }
        }
    }

    val backStack = rememberNavBackStack(configuration = appConfig, AppDestination.Auth.SignIn)

    NavDisplay(
        backStack = backStack,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = entryProvider{
            entry<AppDestination.Auth.SignIn> {
                SignInScreen(backStack = backStack, rootBackStack = rootBackStack)
            }
            entry<AppDestination.Auth.SignUp> {
                SignUpScreen()
            }
        }
    )

}