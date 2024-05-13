package com.immobylette.appmobile.ui.shared.component

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.immobylette.appmobile.R
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.immobylette.appmobile.ui.shared.theme.ImmobyletteappmobileTheme
import java.net.URL

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PhotoDetail(
    url: URL,
    modifier: Modifier = Modifier,
    photoDescription: String = "",
    description: String,
    onCancelClicked: () -> Unit
) {
    Box(
        modifier = modifier
            .width(400.dp)
            .height(600.dp)
            .clip(RoundedCornerShape(20.dp))
    ) {
        Scaffold(
            bottomBar = {
                Button(
                    text = stringResource(id = R.string.label_button_cancel),
                    onClick = onCancelClicked,
                    modifier = modifier.width(150.dp)
                )
            }
        ) {
            Column{
                Image(
                    painter = rememberAsyncImagePainter(url.toString()),
                    contentDescription = photoDescription,
                    modifier = modifier
                        .fillMaxWidth()
                        .height(400.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = modifier.height(10.dp))
                Text(
                    text = description,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    fontFamily = MaterialTheme.typography.headlineMedium.fontFamily,
                    fontSize = fontSize,
                )

            }
        }
    }
}


@Composable
@Preview
fun PhotoDetailPreview(){
    ImmobyletteappmobileTheme {
        PhotoDetail(
            URL("http://placekitten.com/200/300"),
            photoDescription = "Photo de chat",
            description = "Trou dans le mur qui fait que il y en a beaucoup",
            onCancelClicked = { println("Clic sur le bouton annuler") }
        )
    }
}