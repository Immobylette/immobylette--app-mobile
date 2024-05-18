package com.immobylette.appmobile.room.elements

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.immobylette.appmobile.R
import com.immobylette.appmobile.data.dto.PhotoUrlDto
import com.immobylette.appmobile.ui.shared.component.Button
import com.immobylette.appmobile.ui.shared.component.Chip
import com.immobylette.appmobile.ui.shared.component.ChipRoomInfo
import com.immobylette.appmobile.ui.shared.component.Element
import com.immobylette.appmobile.ui.shared.component.PhotoDetail
import com.immobylette.appmobile.ui.shared.component.Title
import com.immobylette.appmobile.ui.shared.component.TitleType
import com.immobylette.appmobile.ui.shared.theme.ImmobyletteappmobileTheme
import java.net.URL
import java.util.UUID
import com.immobylette.appmobile.data.enum.ElementState as ElementStateEnum

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ElementsPage(
    isWallsPage: Boolean = false,
    elements: ElementListState,
    getNbRooms: () -> Int,
    getRoomNumber: () -> Int,
    getNbWalls: () -> Int,
    getNbDoors: () -> Int,
    getNbWindows: () -> Int,
    fetchElements: () -> Unit,
    fetchElement: (UUID) -> Unit,
    setCurrentElement: (ElementState) -> Unit,
    onClickSameState: (ElementState) -> Unit,
    onClickOnNext: () -> Unit,
    onNavigateToTakePicture: () -> Unit
){
    val activity = (LocalContext.current as? Activity)
    var currentPhoto by remember { mutableStateOf(PhotoUrlDto()) }
    var displayModalCurrentPhoto by remember { mutableStateOf(false) }
    var displayModalConfirmCheckAll by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        if (elements.elements.isEmpty())
            fetchElements()
    }

    Surface(
        modifier = if (displayModalCurrentPhoto || displayModalConfirmCheckAll) Modifier.blur(radius = 10.dp) else Modifier
    ) {
        Scaffold(
            bottomBar = {
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        modifier = Modifier
                            .height(70.dp)
                            .width(170.dp),
                        text = stringResource(id = R.string.label_button_quit),
                        style = MaterialTheme.typography.titleMedium,
                    ){
                        activity?.finish()
                    }
                    ChipRoomInfo(
                        nbChecked = getRoomNumber(),
                        nbTotal = getNbRooms()
                    )
                    Button(
                        isOnLeftSide = false,
                        modifier = Modifier
                            .height(70.dp)
                            .width(170.dp),
                        text = stringResource(id = R.string.label_button_next),
                        style = MaterialTheme.typography.titleMedium,
                    ) {
                        displayModalConfirmCheckAll = true
                    }
                }
            }
        ) {
            Column (
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 50.dp),
                    horizontalAlignment = Alignment.CenterHorizontally

            ){
                Column{
                    Column{
                        Title(text = if (isWallsPage) stringResource(id = R.string.label_title_walls) else stringResource(id = R.string.label_title_elements))
                        Spacer(modifier = Modifier.height(20.dp))
                        Title(
                            modifier = Modifier.padding(start = 20.dp),
                            text = if (isWallsPage) stringResource(id = R.string.label_subtitle_walls) else stringResource(id = R.string.label_subtitle_elements),
                            type = TitleType.H2
                        )
                    }
                    Spacer(modifier = Modifier.height(50.dp))
                    Row {
                        Chip(number = elements.elements.size, label = stringResource(id = R.string.label_elements))
                        Spacer(modifier = Modifier.width(10.dp))
                        Chip(number = getNbWalls(), label = stringResource(id = R.string.label_walls))
                        Spacer(modifier = Modifier.width(10.dp))
                        Chip(number = getNbDoors(), label = stringResource(id = R.string.label_doors))
                        Spacer(modifier = Modifier.width(10.dp))
                        Chip(number = getNbWindows(), label = stringResource(id = R.string.label_windows))
                    }
                    Spacer(modifier = Modifier.height(30.dp))

                    LazyColumn(
                        modifier = Modifier.fillMaxHeight()
                    ) {
                        items(elements.elements) {element ->
                            Element(
                                photo = element.photo,
                                name = element.name,
                                description = element.description ?: "",
                                state = element.state,
                                basePhotos = element.basePhotos,
                                previousPhotos = element.previousPhotos,
                                checked = element.checked,
                                error = false,
                                onClickNewState = {
                                    setCurrentElement(element)
                                    onNavigateToTakePicture()
                                },
                                onClickSameState = { onClickSameState(element) },
                                onClickPhoto = { photo ->
                                    currentPhoto = photo
                                    displayModalCurrentPhoto = true
                                },
                                onExpanded = {
                                    // Fetch element if not already fetched
                                    if (element.nbBasePhotos == 0 && element.nbPreviousPhotos == 0)
                                        fetchElement(element.id)
                                }
                            )
                            Spacer(modifier = Modifier.height(15.dp))
                        }
                    }
                }
            }
        }

        if (displayModalCurrentPhoto) {
            AlertDialog(
                onDismissRequest = { displayModalCurrentPhoto = false }
            ) {
                PhotoDetail(
                    url = currentPhoto.url,
                    description = currentPhoto.description ?: "",
                ) {
                    displayModalCurrentPhoto = false
                }
            }
        }

        if (displayModalConfirmCheckAll) {
            AlertDialog(
                onDismissRequest = { displayModalConfirmCheckAll = false }
            ) {
                Confirm(
                    onCancelClicked = { displayModalConfirmCheckAll = false },
                    onConfirmClicked = {
                        displayModalConfirmCheckAll = false
                        onClickOnNext()
                    }
                )
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Confirm(
    onCancelClicked: () -> Unit,
    onConfirmClicked: () -> Unit,
    modifier: Modifier = Modifier
){
    Surface(
        shape = RoundedCornerShape(20.dp),
        modifier = modifier
            .width(500.dp)
            .height(210.dp)
            .padding(0.dp)
    ) {
        Scaffold(
            bottomBar = {
                Row (
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Button(
                        text = stringResource(id = R.string.label_button_cancel),
                        onClick = onCancelClicked,
                    )
                    Button(
                        isOnLeftSide = false,
                        text = stringResource(id = R.string.label_button_confirm),
                        onClick = onConfirmClicked,
                    )
                }
            }
        ) {
            Column(
                modifier = Modifier.padding(30.dp)
            ){
                Text(
                    text = stringResource(id = R.string.label_confirm_check_all),
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = stringResource(id = R.string.label_confirm_check_all_warning),
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        }
    }
}

@Composable
@Preview
fun ConfirmPreview(){
    ImmobyletteappmobileTheme {
        Confirm(
            onCancelClicked = {},
            onConfirmClicked = {}
        )
    }
}



@Composable
@Preview(showBackground = true)
fun ElementsPagePreview(){
    val images = listOf(
        PhotoUrlDto(
            url = URL("https://picsum.photos/200/300"),
            description = "Description 1"
        ),
        PhotoUrlDto(
            url = URL("https://picsum.photos/200/300"),
            description = "Description 2"
        ),
        PhotoUrlDto(
            url = URL("https://picsum.photos/200/300"),
            description = "Description 3"
        ),
        PhotoUrlDto(
            url = URL("https://picsum.photos/200/300"),
            description = "Description 4"
        ),
        PhotoUrlDto(
            url = URL("https://picsum.photos/200/300"),
            description = "Description 5"
        ),
    )

    ImmobyletteappmobileTheme {
        ElementsPage(
            elements = ElementListState(
                elements = listOf(
                    ElementState(
                        name = "Chaise 1",
                        description = "Chaise noire",
                        state = ElementStateEnum.NEW,
                        basePhotos = images,
                        previousPhotos = images
                    ),
                    ElementState(
                        name = "Canapé 1",
                        description = "Canapé 2 personnes en cuir",
                        state = ElementStateEnum.NEW,
                        basePhotos = images,
                        previousPhotos = images
                    ),
                    ElementState(
                        name = "Table 1",
                        description = "Table en bois",
                        state = ElementStateEnum.VERY_BAD,
                        basePhotos = images,
                        previousPhotos = images
                    ),
                    ElementState(
                        name = "Tv",
                        description = "Tv 4k 27\"",
                        state = ElementStateEnum.BAD,
                        basePhotos = images,
                        previousPhotos = images
                    ),
                    ElementState(
                        name = "Element 5",
                        description = "Description 5",
                        state = ElementStateEnum.GOOD,
                        basePhotos = images,
                        previousPhotos = images
                    ),
                    ElementState(
                        name = "Element 6",
                        description = "Description 6",
                        state = ElementStateEnum.NEW,
                        basePhotos = images,
                        previousPhotos = images
                    ),
                    ElementState(
                        name = "Element 7",
                        description = "Description 7",
                        state = ElementStateEnum.NEW,
                        basePhotos = images,
                        previousPhotos = images
                    ),
                    ElementState(
                        name = "Element 8",
                        description = "Description 8",
                        state = ElementStateEnum.NEW,
                        basePhotos = images,
                        previousPhotos = images
                    ),
                    ElementState(
                        name = "Element 9",
                        description = "Description 9",
                        state = ElementStateEnum.NEW,
                        basePhotos = images,
                        previousPhotos = images
                    ),
                    ElementState(
                        name = "Element 10",
                        description = "Description 10",
                        state = ElementStateEnum.NEW,
                        basePhotos = images,
                        previousPhotos = images
                    ),
                    ElementState(
                        name = "Element 11",
                        description = "Description 11",
                        state = ElementStateEnum.NEW,
                        basePhotos = images,
                        previousPhotos = images
                    ),
                    ElementState(
                        name = "Element 12",
                        description = "Description 12",
                        state = ElementStateEnum.NEW,
                        basePhotos = images,
                        previousPhotos = images
                    ),
                    ElementState(
                        name = "Element 13",
                        description = "Description 13",
                        state = ElementStateEnum.NEW,
                        basePhotos = images,
                        previousPhotos = images
                    ),
                ),
            ),
            getNbRooms = { 3 },
            getRoomNumber = { 1 },
            getNbWalls = { 2 },
            getNbDoors = { 1 },
            getNbWindows = { 3 },
            fetchElements = {},
            fetchElement = {},
            onNavigateToTakePicture = {},
            onClickSameState = {},
            onClickOnNext = {},
            setCurrentElement = {}
        )
    }
}