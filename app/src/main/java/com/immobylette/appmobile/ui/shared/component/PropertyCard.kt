package com.example.testappkotlin.ui.shared.component

import android.media.Image
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.ShoppingCart
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
import androidx.compose.ui.composed
import androidx.compose.ui.draw.BlurredEdgeTreatment.Companion.Rectangle
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.rememberAsyncImagePainter
import com.immobylette.appmobile.R
import com.immobylette.appmobile.ui.shared.component.Title
import com.immobylette.appmobile.ui.shared.theme.Black
import com.immobylette.appmobile.ui.shared.theme.ImmobyletteappmobileTheme
import com.immobylette.appmobile.ui.shared.theme.Pink
import java.net.URL
import java.util.UUID

@Composable
fun PropertyCard(
    address: String,
    nb_rooms: Int,
    property_type: String,
    property_class: String,
    owner: String,
    photo_url: URL,
    distance: Float,
    in_progress: Boolean,
    modifier: Modifier
) {
    var tapped by remember { mutableStateOf(false) }

    Column {
        Surface(
            modifier = modifier
                .height(300.dp)
                .padding(start = 30.dp, end = 30.dp)
                .fillMaxWidth()
                .wrapContentSize()
                .shadow(if (tapped) 2.dp else 0.dp)
                .zIndex(2f)
                .clickable {
                    println(tapped)
                    tapped = !tapped
                },
        ) {
            Box(
                Modifier
                    .fillMaxWidth(0.65f)
                    .height(5.dp)
                    .clip(RectangleShape)
                    .background(Pink)
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 100.dp, end = 100.dp)
            ) {
                Column (
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(photo_url.toString()),
                        contentScale = ContentScale.Crop,
                        contentDescription = address,
                        modifier = Modifier
                            .width(172.dp)
                            .height(172.dp)
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
                        .fillMaxHeight()
                        .padding(top = 33.dp, bottom = 45.dp)
                ) {
                    if(in_progress) {
                        Text(text = "En cours",
                            style = MaterialTheme.typography.headlineSmall,
                            color = Pink,)
                    } else {
                        Spacer(modifier = Modifier
                            .width(1.dp)
                            .height(10.dp))
                    }
                    Text(text = "${distance.toInt()} m",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier
                            .fillMaxHeight()
                            .wrapContentHeight(align = Alignment.CenterVertically))
                }
            }
        }
        AnimatedVisibility(
            visible = tapped,
            modifier = Modifier.zIndex(1f)) {
            Surface(
                color = White,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 45.dp, end = 45.dp)
            ) {
                Column (
                    modifier = Modifier.padding(30.dp),
                    verticalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    Row (
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Rounded.LocationOn,
                            contentDescription = ""
                        )
                        Spacer(modifier = Modifier.height(1.dp).width(5.dp))
                        Text(
                            style = MaterialTheme.typography.bodyMedium,
                            text = address)
                    }
                    Row (
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Rounded.Info,
                            contentDescription = ""
                        )
                        Spacer(modifier = Modifier.height(1.dp).width(5.dp))
                        Text(
                            style = MaterialTheme.typography.bodyMedium,
                            text = property_type + ", " + property_class + ", " + nb_rooms.toString() + " chambre(s)")
                    }
                    Row (
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Rounded.Person,
                            contentDescription = ""
                        )
                        Spacer(modifier = Modifier.height(1.dp).width(5.dp))
                        Text(
                            style = MaterialTheme.typography.bodyMedium,
                            text = owner)
                    }
                }

            }
        }
    }


}

@Preview(showBackground=true, widthDp = 1200, heightDp = 1920)
@Composable
fun PropertyCardPreview(){
    ImmobyletteappmobileTheme(darkTheme = false, dynamicColor = false) {
        Surface(
            Modifier
                .fillMaxSize()
                .wrapContentSize()) {
            Column {
                PropertyCard(
                    address = "18 Rue Jean Souvraz",
                    nb_rooms = 3,
                    property_type = "Appartment",
                    property_class = "T1",
                    owner = "Pol Neutron",
                    photo_url = URL("https://cdn.shopify.com/s/files/1/0326/2057/6900/files/117_480x480.jpg?v=1689679260"),
                    distance = 500f,
                    in_progress = false,
                    modifier = Modifier
                        .height(350.dp)
                        .padding(16.dp)
                        .fillMaxWidth()
                        .wrapContentSize()
                )
                PropertyCard(
                    address = "18 Rue Jean Souvraz",
                    nb_rooms = 3,
                    property_type = "Appartment",
                    property_class = "T1",
                    owner = "Pol Neutron",
                    photo_url = URL("https://cdn.shopify.com/s/files/1/0326/2057/6900/files/117_480x480.jpg?v=1689679260"),
                    distance = 500f,
                    in_progress = true,
                    modifier = Modifier
                        .height(350.dp)
                        .padding(16.dp)
                        .fillMaxWidth()
                        .wrapContentSize()
                )
            }

        }
    }
}

@Composable
fun Rectangle() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(5.dp)
            .clip(RectangleShape)
            .background(Pink)
    )
}

