package com.immobylette.appmobile.ending

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.immobylette.appmobile.R
import com.immobylette.appmobile.ui.shared.component.GraphicFooter
import com.immobylette.appmobile.ui.shared.component.Logo
import com.immobylette.appmobile.ui.shared.theme.ImmobyletteappmobileTheme

@Composable
fun EndingPage(
    onNavigateToWaitingFinished: () -> Unit
){
    val waitingTime = 5

    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(waitingTime * 1000L)
        onNavigateToWaitingFinished()
    }
    Box(
        modifier = Modifier.padding(100.dp),
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(color = Color.White)
            ,
        ){
            Text(
                modifier = Modifier,
                text = stringResource(id = R.string.label_inventory_ending),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Medium
            )
        }
        Box(
           contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Logo(size = 350)
        }
    }
    GraphicFooter()
}

@Composable
@Preview(showBackground=true, showSystemUi = true)
fun EndingPagePreview(){
    ImmobyletteappmobileTheme {
        EndingPage(
            onNavigateToWaitingFinished = {}
        )
    }
}