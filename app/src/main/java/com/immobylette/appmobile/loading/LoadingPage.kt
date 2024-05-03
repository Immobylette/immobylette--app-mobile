package com.immobylette.appmobile.loading

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.immobylette.appmobile.ui.shared.component.GraphicFooter
import com.immobylette.appmobile.ui.shared.component.Logo

@Composable
fun LoadingPage(
    onNavigateToLoadingFinished: () -> Unit
){
    val loadingTime = 3

    LaunchedEffect(true) {
        kotlinx.coroutines.delay(loadingTime * 1000L)
        onNavigateToLoadingFinished()
    }

    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier.fillMaxSize().padding(top = 150.dp)
    ) {
        Logo(size = 350)
    }

    GraphicFooter()
}