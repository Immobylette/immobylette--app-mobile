package com.immobylette.appmobile.room.gotoroom

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import com.immobylette.appmobile.R
import com.immobylette.appmobile.ui.shared.component.Button
import com.immobylette.appmobile.ui.shared.component.GraphicFooter
import com.immobylette.appmobile.ui.shared.component.QuitAppPopup
import com.immobylette.appmobile.ui.shared.component.Tip
import com.immobylette.appmobile.ui.shared.component.Title
import com.immobylette.appmobile.ui.shared.theme.Blue
import java.util.UUID

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun GoToRoomPage(
    state: RoomState,
    fetchCurrentRoom: (id: UUID, errorCallback: () -> Unit) -> Unit,
    setCurrentRoom: (room: RoomState) -> Unit,
    getCurrentInventory: () -> UUID,
    onNavigateToRoomElements:() -> Unit,
    onNavigateToPropertySelection: () -> Unit,
    onNavigateToInventorySummary: () -> Unit
) {
    var displayModalQuitApp by remember { mutableStateOf(false) }
    setCurrentRoom(state)

    LaunchedEffect(Unit) {
        fetchCurrentRoom(getCurrentInventory(), onNavigateToInventorySummary)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .blur(if (displayModalQuitApp) 10.dp else 0.dp),
        verticalArrangement = Arrangement.Center
    ){
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Title(text = "${stringResource(id = R.string.label_go_to_room)}${state.nbOrder}")
        }
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(horizontal = 100.dp)
                .clip(RoundedCornerShape(30.dp))
                .background(Color.White)
        ){
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(top = 20.dp, start = 50.dp, end = 50.dp),
                ) {
                    PropertyRow(propertyName = stringResource(id = R.string.label_room_name)) {
                        Text(text = state.name)
                    }
                    PropertyRow(propertyName = stringResource(id = R.string.label_description)) {
                        Text(text = state.description)
                    }
                    if(state.roomType != null) {
                        PropertyRow(propertyName = stringResource(id = R.string.label_type)) {
                            BlueRectangleWithText(text = state.roomType)
                        }
                    }
                    if(state.allocation != null) {
                        PropertyRow(propertyName = stringResource(id = R.string.label_allocation)) {
                            BlueRectangleWithText(text = state.allocation)
                        }
                    }
                    PropertyRow(propertyName = stringResource(id = R.string.label_nb_walls)) {
                        Tip(
                            text = state.nbWalls.toString(),
                            style = MaterialTheme.typography.labelMedium,
                            modifier = Modifier.size(30.dp),
                        )
                    }
                    PropertyRow(propertyName = stringResource(id = R.string.label_nb_doors)) {
                        Tip(
                            text = state.nbDoors.toString(),
                            style = MaterialTheme.typography.labelMedium,
                            modifier = Modifier.size(30.dp),
                        )
                    }
                    PropertyRow(propertyName = stringResource(id = R.string.label_nb_windows)) {
                        Tip(
                            text = state.nbWindows.toString(),
                            style = MaterialTheme.typography.labelMedium,
                            modifier = Modifier.size(30.dp),
                        )
                    }
                    if(state.reference != null) {
                        val spanStyles = listOf(
                            AnnotatedString.Range(
                                SpanStyle(fontWeight = FontWeight.Bold),
                                start = 0,
                                end = "${stringResource(id = R.string.label_reference)} : ".length
                            )
                        )

                        Row(
                            modifier = Modifier.padding(5.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = AnnotatedString(text = "${stringResource(id = R.string.label_reference)} : ${state.reference}", spanStyles = spanStyles))
                        }
                        Spacer(modifier = Modifier.size(20.dp))
                    }
                }
            }
            Row(modifier = Modifier.fillMaxWidth()){
                Button(
                    text = stringResource(id = R.string.label_button_quit),
                    onClick = { displayModalQuitApp = true },
                    hasTwoRoundedCorners = true,
                    modifier = Modifier.width(200.dp),
                )
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    text = stringResource(id = R.string.label_button_is_in_room),
                    onClick = onNavigateToRoomElements,
                    isOnLeftSide = false,
                    modifier = Modifier.width(280.dp),
                )
            }
        }
    }
    GraphicFooter(modifier = Modifier.blur(if (displayModalQuitApp) 10.dp else 0.dp))
    if (displayModalQuitApp){
        QuitAppPopup(
            onDismissRequest = { displayModalQuitApp = false },
            onCancelClicked = { displayModalQuitApp = false },
            onQuitClicked = onNavigateToPropertySelection
        )
    }
}


@Composable
fun PropertyRow(
    propertyName: String,
    content: @Composable RowScope.() -> Unit
) {
    Row(
        modifier = Modifier.padding(5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "$propertyName : ",fontWeight = FontWeight.Bold)
        content()
    }
}

@Composable
fun BlueRectangleWithText(
    text: String
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(Blue)
            .padding(10.dp)
            .width((text.length * 15.dp) + 20.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = Color.White
        )
    }
}
