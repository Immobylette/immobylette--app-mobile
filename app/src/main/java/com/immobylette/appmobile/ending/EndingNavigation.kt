package com.immobylette.appmobile.ending

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val endingRoute = "ending"

fun NavGraphBuilder.endingNavigation(
    onNavigateToWaitingFinished: () -> Unit
) {

    composable(endingRoute) {
        EndingPage(onNavigateToWaitingFinished = onNavigateToWaitingFinished)
    }
}

fun NavController.navigateToEndingPage(){
    navigate(endingRoute)
}