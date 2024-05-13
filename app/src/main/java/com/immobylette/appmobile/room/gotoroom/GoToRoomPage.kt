package com.immobylette.appmobile.room.gotoroom

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import com.immobylette.appmobile.R
import com.immobylette.appmobile.ui.shared.component.Button
import com.immobylette.appmobile.ui.shared.component.GraphicFooter
import com.immobylette.appmobile.ui.shared.component.Tip
import com.immobylette.appmobile.ui.shared.component.Title
import com.immobylette.appmobile.ui.shared.theme.Blue
import java.util.UUID

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun GoToRoomPage(
    state: RoomState,
    fetchCurrentRoom: (id: UUID) -> Unit,
    setCurrentRoomNumber: (roomNumber: Int) -> Unit,
    onNavigateToRoomElements:() -> Unit
) {
    val activity = (LocalContext.current as? Activity)
    setCurrentRoomNumber(state.nbOrder)

    LaunchedEffect(Unit) {
        fetchCurrentRoom(UUID.fromString("ce5ea005-e965-4b1f-adaa-c5903c7ff975"))
    }

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Title(text = "${stringResource(id = R.string.label_go_to_room)}${state.nbOrder}")
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    )  {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(bottom = 150.dp)
                .fillMaxWidth()
                .height(650.dp),
        ) {
            Scaffold(
                bottomBar = {
                    Row(modifier = Modifier.fillMaxWidth()){
                        Button(
                            text = stringResource(id = R.string.label_button_quit),
                            onClick = { activity?.finish() },
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
                },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 50.dp)
                    .clip(RoundedCornerShape(30.dp))
            ){
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(30.dp))
                        .fillMaxSize()
                        .background(Color.White)
                ) {
                    Column(
                        modifier = Modifier.padding(top = 20.dp, start = 50.dp, end = 50.dp),
                    ) {
                        PropertyRow(propertyName = stringResource(id = R.string.label_room_name)) {
                            Text(text = state.name)
                        }
                        PropertyRow(propertyName = stringResource(id = R.string.label_description)) {
                            Text(text = state.description,fontWeight = FontWeight.Bold)
                        }
                        PropertyRow(propertyName = stringResource(id = R.string.label_type)) {
                            BlueRectangleWithText(text = state.roomType)
                        }
                        if(state.allocation != null) {
                            PropertyRow(propertyName = stringResource(id = R.string.label_allocation)) {
                                BlueRectangleWithText(text = state.allocation)
                            }
                        }
                        PropertyRow(propertyName = stringResource(id = R.string.label_nb_walls)) {
                            Tip(text = state.nbWalls.toString())
                        }
                        PropertyRow(propertyName = stringResource(id = R.string.label_nb_doors)) {
                            Tip(text = state.nbDoors.toString())
                        }
                        PropertyRow(propertyName = stringResource(id = R.string.label_nb_windows)) {
                            Tip(text = state.nbWindows.toString())
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
                        }
                    }
                }
            }
        }
    }
    GraphicFooter()
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