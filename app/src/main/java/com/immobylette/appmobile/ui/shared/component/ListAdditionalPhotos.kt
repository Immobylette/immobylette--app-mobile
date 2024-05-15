package com.immobylette.appmobile.ui.shared.component

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.immobylette.appmobile.ui.shared.theme.BlueLight
import com.immobylette.appmobile.ui.shared.theme.ImmobyletteappmobileTheme
import java.net.URL

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ListAdditionalPhotos(
    photos: List<URL>,
    onDeletePhoto: (URL) -> Unit,
    onAddPhoto: () -> Unit,
    modifier: Modifier = Modifier
){
    Row (
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        LazyRow (
            modifier = modifier
                .fillMaxHeight()
                .weight(0.8f)
                .background(Color.Green)
        ) {
            items(photos){ photo ->
                Scaffold (
                    topBar = {
                        Row (
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp),
                        ) {
                            ListAdditionalPhotosButton(
                                icon = Icons.Rounded.Close,
                                onClick = { onDeletePhoto(photo) },
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    },
                    modifier = Modifier
                        .width(100.dp)
                        .fillMaxHeight()
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(photo.toString()),
                        contentScale = ContentScale.Crop,
                        contentDescription = "",
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .fillMaxWidth()
                            .aspectRatio(1f)
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
        Spacer(modifier = Modifier.width(10.dp))
        ListAdditionalPhotosButton(
            icon = Icons.Rounded.Add,
            onClick = onAddPhoto,
            modifier = Modifier
                .size(50.dp)
                .align(Alignment.CenterVertically)
        )
    }
}

@Composable
fun ListAdditionalPhotosButton(
    icon: ImageVector,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
){
    Box (
        modifier = modifier
            .clip(shape = CircleShape)
            .background(MaterialTheme.colorScheme.primary)
            .shadow(
                elevation = 15.dp,
                spotColor = Color.Gray,
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "",
            tint = BlueLight,
            modifier = modifier
                .clickable { onClick() }
                .padding(4.dp)
        )
    }
}

@Composable
@Preview
fun ListAdditionalPhotosPreview(){
    ImmobyletteappmobileTheme {
        ListAdditionalPhotos(
            photos = listOf(URL("https://picsum.photos/200/300")),
            onDeletePhoto = {},
            onAddPhoto = {}
        )
    }
}