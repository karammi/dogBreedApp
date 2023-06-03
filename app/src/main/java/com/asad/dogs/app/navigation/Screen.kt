package com.asad.dogs.app.navigation

sealed class Screen(val route: String) {

    open fun createRoute(vararg args: String): String {
        return route
    }

    object BreedListScreen : Screen(route = NavigationConstants.BreedListScreenName)

    object BreedPicturesScreen :
        Screen(route = "${NavigationConstants.BreedPictureScreenName}/{${NavigationConstants.BreedNameArg}}") {
        override fun createRoute(vararg args: String): String {
            return "${NavigationConstants.BreedPictureScreenName}/${args[0]}"
        }
    }

    object BreedFavoriteScreen : Screen(route = NavigationConstants.BreedFavoriteScreenName)
}
