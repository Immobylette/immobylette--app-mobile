package com.immobylette.appmobile.ui.shared.component

import android.annotation.SuppressLint
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.rememberAsyncImagePainter
import com.immobylette.appmobile.R
import com.immobylette.appmobile.data.dto.PhotoUrlDto
import com.immobylette.appmobile.data.enum.ElementState
import com.immobylette.appmobile.ui.shared.theme.Grey
import com.immobylette.appmobile.ui.shared.theme.GreyTransparent
import com.immobylette.appmobile.ui.shared.theme.ImmobyletteappmobileTheme
import com.immobylette.appmobile.ui.shared.theme.RedTransparent
import java.net.URL

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Element(
    photo: URL,
    name: String,
    description: String,
    state: ElementState,
    basePhotos: List<PhotoUrlDto>,
    nbBasePhotos: Int,
    previousPhotos: List<PhotoUrlDto>,
    nbPreviousPhotos: Int,
    checked: Boolean,
    error: Boolean,
    onClickNewState: () -> Unit,
    onClickSameState: () -> Unit,
    onClickPhoto: (PhotoUrlDto) -> Unit,
    onExpanded: () -> Unit,
    modifier: Modifier = Modifier
    ) {
    var expanded by remember { mutableStateOf(false) }

    val expandedHeight = if(previousPhotos.isNotEmpty()) 480.dp else 320.dp

    Scaffold(
        containerColor = Color.White,
        modifier = modifier
            .width(700.dp)
            .animateContentSize()
            .height(if (expanded) expandedHeight else 100.dp)
            .clickable {
                if (!error and !checked){
                    expanded = !expanded
                    if (expanded) onExpanded()
                }

            }
            .clip(RoundedCornerShape(10.dp)),
        bottomBar = {
            if (expanded){
                Row (modifier = Modifier.fillMaxWidth()){
                    Button(
                        text = stringResource(id = R.string.label_button_same_state),
                        onClick = {
                            expanded = false
                            onClickSameState()
                        },
                        modifier = Modifier.width(200.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Button(
                        text = stringResource(id = R.string.label_button_new_state),
                        onClick = onClickNewState,
                        isOnLeftSide = false,
                        modifier = Modifier.width(200.dp)
                    )
                }
            }
        }
    ) {
        if (error || checked) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(if (error) RedTransparent else GreyTransparent)
                    .zIndex(1f)
            )
        }
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = rememberAsyncImagePainter(photo.toString()),
                    contentScale = ContentScale.Crop,
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxHeight()
                        .aspectRatio(1f)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(5.dp)
                ) {
                    Row{
                        Row (
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Text(text = name, style = MaterialTheme.typography.headlineLarge)
                            Spacer(modifier = Modifier.width(10.dp))
                            Box (
                                modifier = Modifier
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(Grey)
                                    .padding(10.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = state.label, style = MaterialTheme.typography.labelSmall)
                            }
                        }
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = description,
                        style = MaterialTheme.typography.labelMedium
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Column(
                    modifier = Modifier
                        .width(200.dp)
                        .padding(10.dp),
                ) {
                    LabelNbPhotos(label = stringResource(id = R.string.label_base_photos).lowercase(), nbPhotos = nbBasePhotos)
                    Spacer(modifier = Modifier.weight(1f))
                    LabelNbPhotos(label = stringResource(id = R.string.label_previous_photos).lowercase(), nbPhotos = nbPreviousPhotos)
                }
            }

            Box(
                modifier = Modifier
                    .height(if (expanded) 480.dp else 0.dp)
            ) {
                Column(
                    modifier = Modifier.padding(10.dp)
                ) {
                    Spacer(modifier = Modifier.height(10.dp))
                    if (basePhotos.isNotEmpty()){
                        ElementPhotos(
                            title = stringResource(id = R.string.label_base_photos),
                            photos = basePhotos,
                            onClickPhoto = onClickPhoto
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                    if (previousPhotos.isNotEmpty()){
                        ElementPhotos(
                            title = stringResource(id = R.string.label_previous_photos),
                            photos = previousPhotos,
                            onClickPhoto = onClickPhoto
                        )
                    }
                }
            }
        }
    }

}

@Composable
fun ElementPhotos(
    title: String,
    photos: List<PhotoUrlDto>,
    onClickPhoto: (PhotoUrlDto) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(text = title)
        Spacer(modifier = Modifier.height(5.dp))
        LazyRow(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(photos) { photo ->
                Image(
                    painter = rememberAsyncImagePainter(photo.url.toString()),
                    contentScale = ContentScale.Crop,
                    contentDescription = photo.description ?: "",
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .height(100.dp)
                        .aspectRatio(1f)
                        .clickable(onClick = { onClickPhoto(photo) })
                )
                Spacer(modifier = Modifier.width(5.dp))
            }
        }
    }
}

@Composable
fun LabelNbPhotos(
    label: String,
    nbPhotos: Int,
){
    Row (
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Tip(
            text = nbPhotos.toString(),
            modifier = Modifier.size(20.dp),
            style = MaterialTheme.typography.labelSmall
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(text = label, style = MaterialTheme.typography.labelSmall)
    }
}

@Composable
@Preview(
    showBackground = true,
    showSystemUi = true,
)
fun ElementPreview() {
    ImmobyletteappmobileTheme {
        Element(
            photo = URL("http://placekitten.com/200/300"),
            name = "Nom de l'élément",
            description = "Description de l'élément",
            state = ElementState.NEW,
            basePhotos = listOf(
                PhotoUrlDto(
                    url = URL("http://placekitten.com/200/300"),
                    description = "cocuou"
                ),
                PhotoUrlDto(
                    url = URL("http://placekitten.com/200/300"),
                    description = "bonjour"
                )
            ),
            previousPhotos = listOf(
                PhotoUrlDto(
                    url = URL("http://placekitten.com/200/300"),
                    description = "cocuou"
                )
            ),
            checked = false,
            error = false,
            onClickNewState = {},
            onClickSameState = {},
            onClickPhoto = {},
            onExpanded = {},
            nbBasePhotos = 2,
            nbPreviousPhotos = 1
        )
    }
}