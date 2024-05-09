package com.immobylette.appmobile.loading

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val loadingRoute = "loading"

fun NavGraphBuilder.loadingNavigation(
    onNavigateToLoadingFinished: () -> Unit,
) {

    composable(loadingRoute) {
        LoadingPage (
            onNavigateToLoadingFinished = onNavigateToLoadingFinished
        )
    }
}

fun NavController.navigateToLoadingPage(){
    navigate(loadingRoute)
}
