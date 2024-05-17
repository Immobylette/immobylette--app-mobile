package com.immobylette.appmobile.ui.shared.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.rememberAsyncImagePainter
import com.immobylette.appmobile.R
import com.immobylette.appmobile.ui.shared.component.Button
import com.immobylette.appmobile.ui.shared.theme.Grey
import com.immobylette.appmobile.ui.shared.theme.ImmobyletteappmobileTheme
import com.immobylette.appmobile.ui.shared.theme.Pink
import java.net.URL

@Composable
fun PropertyCard(
    address: String,
    nbRooms: Int,
    propertyType: String,
    propertyClass: String,
    owner: String,
    photoUrl: URL,
    distance: Float,
    inProgress: Boolean,
    modifier: Modifier = Modifier,
    onExpanded: () -> Unit = {},
    onClickButton: () -> Unit = {}
) {
    var tapped by remember { mutableStateOf(false) }
    val imageSize = 172.dp

    Column (
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .height(300.dp)
                .padding(start = 30.dp, end = 30.dp)
                .fillMaxWidth()
                .shadow(if (tapped) 2.dp else 0.dp)
                .zIndex(2f)
                .background(White)
                .clickable {
                    tapped = !tapped
                    if (tapped) onExpanded()
               },
        ) {
            Box(
                Modifier
                    .fillMaxWidth(0.65f)
                    .height(8.dp)
                    .clip(RectangleShape)
                    .zIndex(3f)
                    .background(Pink)
            )
            Column (
                modifier = Modifier
                    .padding(start = 100.dp)
                    .align(Alignment.CenterStart)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(photoUrl.toString()),
                    contentScale = ContentScale.Crop,
                    contentDescription = address,
                    modifier = Modifier
                        .size(imageSize)
                        .clip(RectangleShape)
                )
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = address,
                    style = MaterialTheme.typography.bodyMedium)
            }
            Column (
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .fillMaxHeight()
                    .padding(end = 60.dp, top = 33.dp, bottom = 45.dp)
            ) {
                if(inProgress) {
                    Text(
                        text = stringResource(id = R.string.label_in_progress),
                        style = MaterialTheme.typography.headlineSmall,
                        color = Pink,
                    )
                } else {
                    Spacer(modifier = Modifier
                        .width(1.dp)
                        .height(10.dp))
                }
                Text(text = convertDistanceToString(distance = distance),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .fillMaxHeight()
                        .wrapContentHeight(align = Alignment.CenterVertically))
            }
        }
        AnimatedVisibility(
            visible = tapped,
            modifier = Modifier.zIndex(1f)) {
            Surface(
                color = White,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 75.dp, end = 75.dp)
            ) {
                Column (
                    modifier = Modifier.padding(top = 30.dp, start = 30.dp),
                    verticalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    Row (
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        PropertyInfo(
                            icon = Icons.Rounded.LocationOn,
                            text = address)
                    }
                    Row (
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        PropertyInfo(
                            icon = Icons.Rounded.Info,
                            text = "$propertyType, $propertyClass, $nbRooms chambre(s)")
                    }
                    Row (
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        PropertyInfo(
                            icon = Icons.Rounded.Person,
                            text = owner)
                    }
                    Row (
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row {
                            Spacer(modifier = Modifier
                                .fillMaxWidth(0.56f)
                                .height(1.dp))
                            Button(
                                text = stringResource(R.string.label_button_inventory),
                                onClick = onClickButton,
                                isOnLeftSide = false,
                                modifier = Modifier.fillMaxWidth(),
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PropertyInfo(
    icon: ImageVector,
    text: String
) {
    val iconFontSize = 30.dp

    Icon(
        imageVector = icon,
        contentDescription = "",
        tint = Grey,
        modifier = Modifier.size(iconFontSize)
    )
    Spacer(modifier = Modifier
        .height(1.dp)
        .width(5.dp))
    Text(
        style = MaterialTheme.typography.bodyMedium,
        text = text)

}

fun convertDistanceToString(distance: Float): String {
    return if (distance < 1000) {
        "${distance.toInt()} m"
    } else {
        val distanceKm = distance / 1000
        "${"%.2f".format(distanceKm)} km"
    }
}

@Preview(showBackground=true, widthDp = 1200, heightDp = 1920)
@Composable
fun PropertyCardPreview(){
    ImmobyletteappmobileTheme(darkTheme = false, dynamicColor = false) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Surface(
                Modifier
                    .fillMaxSize()
                    .wrapContentSize()) {
                Column {
                    PropertyCard(
                        address = "25 Rue de l'Hôpital Militaire, 59800 Lille",
                        nbRooms = 3,
                        propertyType = "Appartment",
                        propertyClass = "T1",
                        owner = "Pol Neutron",
                        photoUrl = URL("https://fr.web.img4.acsta.net/r_1920_1080/medias/nmedia/18/74/23/35/20142770.jpg"),
                        distance = 542f,
                        inProgress = false,
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        onClickButton = { println("Clic bouton 1") }
                    )
                    PropertyCard(
                        address = "7 rue angela davis, Lens",
                        nbRooms = 3,
                        propertyType = "Appartment",
                        propertyClass = "T1",
                        owner = "Pol Neutron",
                        photoUrl = URL("https://www.shutterstock.com/image-photo/living-room-couches-chairs-coffee-600nw-2290619915.jpg"),
                        distance = 5524f,
                        inProgress = true,
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        onClickButton = { println("Clic bouton 2") }
                    )
                }
            }
        }
    }
}