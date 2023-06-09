package com.asad.dogs.app

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.asad.dogs.app.navigation.NavigationConstants
import com.asad.dogs.app.navigation.Screen
import com.asad.dogs.breedList.domain.model.BreedModel
import com.asad.dogs.breedList.presentation.screen.BreedListScreen
import com.asad.dogs.breedPictures.presentation.screen.BreedPictureScreen
import com.asad.dogs.favoritePictures.presentation.screen.FavoritePictureScreen

@Composable
fun DogBreedsApp() {
    val navController = rememberNavController()

    /**
     * This callback must be passed to [BreedListScreen] so that
     * it would be able to navigate to the breed picture screen
     * */
    val onNavigationToScreen: (BreedModel) -> Unit = { breed ->
        navController.navigate(Screen.BreedPicturesScreen.createRoute(breed.title))
    }

    val navigateToFavoriteScreen: () -> Unit = {
        navController.navigate(Screen.BreedFavoriteScreen.route)
    }

    /**
     * This is callback must be passed to the screens (excluding [NavHost]'s startDestination)
     * so that they can navigate back to the previous screens
     * */
    val onNavigationBack: () -> Unit = {
        navController.navigateUp()
    }

    NavHost(navController = navController, startDestination = Screen.BreedListScreen.route) {
        composable(Screen.BreedListScreen.route) {
            BreedListScreen(
                onBreedItemClicked = onNavigationToScreen,
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

            val breedName =
                navBackStackEntry.arguments?.getString(NavigationConstants.BreedNameArg) ?: ""

            BreedPictureScreen(breed = breedName, onNavigationUp = onNavigationBack)
        }

        composable(route = Screen.BreedFavoriteScreen.route) {
            FavoritePictureScreen(onNavigationUp = onNavigationBack)
        }
    }
}
