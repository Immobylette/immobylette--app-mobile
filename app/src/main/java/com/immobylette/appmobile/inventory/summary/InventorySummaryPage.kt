package com.immobylette.appmobile.inventory.summary

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.immobylette.appmobile.R
import com.immobylette.appmobile.ui.shared.component.Button
import com.immobylette.appmobile.ui.shared.component.GraphicFooter
import com.immobylette.appmobile.ui.shared.component.QuitAppPopup
import com.immobylette.appmobile.ui.shared.component.Title
import com.immobylette.appmobile.ui.shared.theme.ImmobyletteappmobileTheme
import java.text.SimpleDateFormat
import java.util.Date

@Composable
fun InventorySummaryPage(
    getInventorySummary: () -> Unit,
    getNbPhotos: () -> Int,
    getNbRooms: () -> Int,
    getDate: () -> Date,
    onNavigateToSignature: () -> Unit,
    onNavigateToPropertySelection: () -> Unit,
){
    var displayModalQuitApp by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        getInventorySummary()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .blur(if (displayModalQuitApp) 10.dp else 0.dp),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(bottom = 300.dp)
                .fillMaxSize()
        ) {
            Title(text = stringResource(id = R.string.label_inventory_summary_title))
            Spacer(modifier = Modifier.height(50.dp))
            Summary(
                nbPhotos = getNbPhotos(),
                nbRooms = getNbRooms(),
                date = getDate(),
                onClickConfirm = onNavigateToSignature,
                onClickQuit = { displayModalQuitApp = true }
            )
        }
        GraphicFooter()
        if (displayModalQuitApp) {
            QuitAppPopup(
                onDismissRequest = { displayModalQuitApp = false },
                onCancelClicked = { displayModalQuitApp = false },
                onQuitClicked = onNavigateToPropertySelection
            )
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "SimpleDateFormat")
@Composable
fun Summary(
    nbPhotos: Int,
    nbRooms: Int,
    date: Date,
    onClickConfirm: () -> Unit,
    onClickQuit: () -> Unit,
    modifier: Modifier = Modifier
){
    Scaffold(
        bottomBar = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ){
                Button(
                    text = stringResource(id = R.string.label_button_quit),
                    modifier = Modifier.width(250.dp),
                    onClick = onClickQuit
                )
                Button(
                    isOnLeftSide = false,
                    text = stringResource(id = R.string.label_button_confirm),
                    onClick = onClickConfirm,
                    modifier = Modifier.width(250.dp)
                )
            }
        },
        modifier = modifier
            .width(750.dp)
            .height(300.dp)
            .clip(RoundedCornerShape(16.dp)),
        containerColor = Color.White
    ){
        Column(
            modifier = Modifier.padding(30.dp)
        ) {
            Text(
                text = stringResource(id = R.string.label_summary_title),
            )
            Spacer(modifier = Modifier.size(30.dp))
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ){
                Column {
                    Information(
                        painter = painterResource(id = R.drawable.baseline_sensor_door_24),
                        label = stringResource(id = R.string.label_summary_nb_rooms) + " :",
                        text = "$nbRooms/$nbRooms"
                    )
                    Spacer(modifier = Modifier.size(16.dp))
                    Information(
                        painter = painterResource(id = R.drawable.baseline_image_24),
                        label = stringResource(id = R.string.label_summary_nb_photos) + " :",
                        text = nbPhotos.toString()
                    )
                    Spacer(modifier = Modifier.size(16.dp))
                    Information(
                        painter = painterResource(id = R.drawable.baseline_calendar_today_24),
                        label = stringResource(id = R.string.label_summary_date) + " :",
                        text = SimpleDateFormat("dd/MM/yyyy").format(date)
                    )
                }
            }
        }
    }
}

@Composable
fun Information(
    painter: Painter,
    label: String,
    text: String,
    modifier : Modifier = Modifier
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Icon(
            painter = painter,
            contentDescription = null,
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight(500)
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
@Preview
fun InformationPreview(){
    ImmobyletteappmobileTheme {
        Information(painterResource(id = R.drawable.baseline_image_24), label = "Test :", text = "4/4")
    }
}

@Composable
@Preview
fun SummaryPreview(){
    ImmobyletteappmobileTheme {
        Summary(
            nbPhotos = 4,
            nbRooms = 4,
            date = Date(),
            onClickConfirm = {},
            onClickQuit = {}
        )
    }
}

@Composable
@Preview(showBackground = true)
fun InventorySummaryPagePreview(){
    ImmobyletteappmobileTheme {
        InventorySummaryPage(
            getInventorySummary = {},
            getNbPhotos = { 4 },
            getNbRooms = { 4 },
            getDate = { Date() },
            onNavigateToSignature = {},
            onNavigateToPropertySelection = {}
        )
    }
}