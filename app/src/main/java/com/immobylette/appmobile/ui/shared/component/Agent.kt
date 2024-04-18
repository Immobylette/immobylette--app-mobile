package com.immobylette.appmobile.ui.shared.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.immobylette.appmobile.ui.shared.theme.ImmobyletteappmobileTheme
import java.net.URL

@Composable
fun Agent(
    url: URL,
    name: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
){
    Column(
        modifier = modifier
            .height(140.dp)
            .width(100.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(url.toString()),
            contentScale = ContentScale.Crop,
            contentDescription = name,
            modifier = modifier
                .clip(CircleShape)
                .fillMaxWidth()
                .aspectRatio(1f)
                .clickable(onClick = onClick)
        )
        Spacer(modifier.height(8.dp))
        Text(
            text = name,
            modifier = modifier.fillMaxSize(),
            textAlign = TextAlign.Center,
            fontFamily = MaterialTheme.typography.displayMedium.fontFamily,
            fontWeight = FontWeight(1),
        )

    }
}

@Preview(showBackground = true)
@Composable
fun AgentPreview(){
    ImmobyletteappmobileTheme {
        Agent(
            URL("https://static.cotemaison.fr/medias_10824/w_2048,h_1146,c_crop,x_0,y_184/w_640,h_360,c_fill,g_north/v1456392403/10-conseils-pour-rendre-votre-chien-heureux_5542245.jpg"),
            "toutou"
        )
    }
}