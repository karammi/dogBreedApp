package com.asad.dogs.app

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.asad.dogs.app.navigation.NavigationConstants
import com.asad.dogs.app.navigation.Screen
import com.asad.dogs.breedFavorites.presentation.screen.BreedFavoritesScreen
import com.asad.dogs.breedList.presentation.screen.BreedListScreen
import com.asad.dogs.breedPictures.presentation.screen.BreedPictureScreen

@Composable
fun DogBreedsApp() {
    val navController = rememberNavController()

    val onNavigationToScreen: (String) -> Unit = { breedName ->
        navController.navigate(Screen.BreedPicturesScreen.createRoute(breedName))
    }

    val navigateToFavoriteScreen: () -> Unit = {
        navController.navigate(Screen.BreedFavoriteScreen.route)
    }

    NavHost(navController = navController, startDestination = Screen.BreedListScreen.route) {
        composable(Screen.BreedListScreen.route) {
            BreedListScreen(
                onNavigate = onNavigationToScreen,
                onNavigateToFavorite = navigateToFavoriteScreen,
            )
        }

        composable(
            route = Screen.BreedPicturesScreen.route,
            arguments = listOf(
                navArgument(name = NavigationConstants.BreedNameArg) {
                    nullable = false
                    type = NavType.StringType
                },
            ),
        ) { navBackStackEntry ->

            val breedName = navBackStackEntry.arguments?.getString(NavigationConstants.BreedNameArg)

            BreedPictureScreen(breed = breedName ?: "")
        }

        composable(route = Screen.BreedFavoriteScreen.route) {
            BreedFavoritesScreen()
        }
    }
}
