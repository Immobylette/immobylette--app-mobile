package com.immobylette.appmobile.confirmation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import com.immobylette.appmobile.ui.shared.component.GraphicFooter
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.immobylette.appmobile.R
import com.immobylette.appmobile.ui.shared.component.Button
import com.immobylette.appmobile.ui.shared.component.QuitAppPopup
import com.immobylette.appmobile.ui.shared.component.Title
import com.immobylette.appmobile.ui.shared.theme.ImmobyletteappmobileTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ConfirmationPage(
    onNavigateToConfirmed: () -> Unit,
    getTenant: () -> String?
) {
    val currentTenant = getTenant()
    val buttonWidth = 200.dp
    var displayModalQuitApp by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize().blur(if (displayModalQuitApp) 10.dp else 0.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(bottom = 300.dp)
                .fillMaxWidth()
                .height(400.dp),
        ) {
            Title(text = stringResource(id = R.string.label_confirmation_of_attendance))
            Spacer(modifier = Modifier.height(50.dp))
            Scaffold(
                bottomBar = {
                    Row(modifier = Modifier.fillMaxWidth()){
                        Button(
                            text = stringResource(id = R.string.label_button_quit),
                            onClick = { displayModalQuitApp = true },
                            hasTwoRoundedCorners = true,
                            modifier = Modifier.width(buttonWidth),
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Button(
                            text = stringResource(id = R.string.label_button_confirm),
                            onClick = onNavigateToConfirmed,
                            isOnLeftSide = false,
                            modifier = Modifier.width(buttonWidth),
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
                        modifier = Modifier.padding(50.dp),
                    ) {
                        Row {
                            Text(text = stringResource(id = R.string.label_denomination))
                            Spacer(modifier = Modifier.width(20.dp))
                            Text(text = currentTenant ?: "")
                        }
                        Spacer(modifier = Modifier.height(50.dp))
                        Text(text = stringResource(id = R.string.label_confirmation_message))
                    }
                }

            }
        }
        GraphicFooter()
        if (displayModalQuitApp) {
            QuitAppPopup(
                onDismissRequest = { displayModalQuitApp = false },
                onCancelClicked = { displayModalQuitApp = false },
            )
        }
    }
}

@Preview(showBackground=true, widthDp = 1200, heightDp = 1920)
@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun ConfirmationPagePreview(){
    val currentTenant = "John Doe"

    ImmobyletteappmobileTheme {

      ConfirmationPage(
          onNavigateToConfirmed = {},
          getTenant = { currentTenant }
      )
    }

}