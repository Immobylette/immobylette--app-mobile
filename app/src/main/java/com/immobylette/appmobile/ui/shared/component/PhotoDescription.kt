package com.immobylette.appmobile.ui.shared.component

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.immobylette.appmobile.R
import com.immobylette.appmobile.ui.shared.theme.ImmobyletteappmobileTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PhotoDescription(
    uri: Uri,
    modifier: Modifier = Modifier,
    onNextClick: (String) -> Unit
) {
    var photoDescription by remember { mutableStateOf("") }

    Box(
        modifier = modifier
            .width(500.dp)
            .height(720.dp)
            .clip(RoundedCornerShape(20.dp))
    ) {
        Scaffold (
            bottomBar = {
                Row (
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    Button(
                        text = stringResource(id = R.string.label_button_next),
                        onClick = { onNextClick(photoDescription) },
                        isOnLeftSide = false,
                        modifier = Modifier.width(200.dp)
                    )
                }
            }
        ) {
            Column {
                Image(
                    painter = rememberAsyncImagePainter(uri),
                    contentDescription = stringResource(id = R.string.label_captured_photo),
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(20.dp))
                )
                Spacer(modifier = modifier.height(10.dp))
                Column (
                    modifier = Modifier.padding(20.dp)
                ){
                    Text(
                        text = stringResource(id = R.string.label_photo_description),
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    TextField(
                        value = photoDescription,
                        onValueChange = { newValue ->
                            photoDescription = newValue
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White),
                        placeholder = {
                          Text(
                              text = stringResource(id = R.string.label),
                            style = MaterialTheme.typography.headlineMedium
                          )
                        },
                        textStyle = MaterialTheme.typography.headlineMedium,
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = stringResource(id = R.string.label_clear),
                                modifier = Modifier.clickable {
                                    photoDescription = ""
                                }
                            )
                        },
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                        )
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun PhotoDescriptionPreview(){
    ImmobyletteappmobileTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = androidx.compose.ui.Alignment.Center
        ) {
            PhotoDescription(
                uri = Uri.parse("https://fr.web.img4.acsta.net/r_1920_1080/medias/nmedia/18/74/23/35/20142770.jpg"),
                onNextClick = {}
            )
        }
    }
}
