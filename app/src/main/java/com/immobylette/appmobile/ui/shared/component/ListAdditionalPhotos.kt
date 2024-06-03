package com.immobylette.appmobile.ui.shared.component

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.items
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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil.compose.rememberAsyncImagePainter
import com.immobylette.appmobile.data.model.Photo
import com.immobylette.appmobile.ui.shared.theme.BlueLight
import com.immobylette.appmobile.ui.shared.theme.ImmobyletteappmobileTheme
import java.io.File

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ListAdditionalPhotos(
    photos: List<Photo>,
    onDeletePhoto: (Int) -> Unit,
    onAddPhoto: () -> Unit,
    modifier: Modifier = Modifier
){
    Row (
        modifier = modifier
            .fillMaxWidth()
            .height(150.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        LazyRow (
            modifier = Modifier.fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            itemsIndexed(photos){ index, photo ->
                Scaffold (
                    topBar = {
                        if(photos.size > 1) {
                            Row (
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(5.dp),
                            ) {
                                ListAdditionalPhotosButton(
                                    icon = Icons.Rounded.Close,
                                    onClick = { onDeletePhoto(index) },
                                    modifier = Modifier.size(30.dp)
                                )
                            }
                        }
                    },
                    modifier = Modifier
                        .width(160.dp)
                        .fillMaxHeight()
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(photo!!.file!!.toUri()),
                        contentScale = ContentScale.Crop,
                        contentDescription = "",
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .fillMaxWidth()
                            .aspectRatio(1f)
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
            }
            item {
                ListAdditionalPhotosButton(
                    icon = Icons.Rounded.Add,
                    onClick = onAddPhoto,
                    modifier = Modifier
                        .size(50.dp)
                        .align(Alignment.CenterVertically)
                )
            }
        }
    }
}

@Composable
fun ListAdditionalPhotosButton(
    icon: ImageVector,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
){
    Card (
        shape = CircleShape,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary,
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
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
@Preview(showBackground = true)
fun ListAdditionalPhotosPreview(){
    ImmobyletteappmobileTheme {
        ListAdditionalPhotos(
            photos = mutableListOf(
                Photo(
                    description = "Test",
                    file = File("fregregr")
                ),
                Photo(
                    description = "Test",
                    file = File("fregregr")
                ),
                Photo(
                    description = "Test",
                    file = File("fregregr")
                ),
            ),
            onDeletePhoto = {},
            onAddPhoto = {}
        )
    }
}