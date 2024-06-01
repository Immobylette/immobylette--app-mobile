package com.immobylette.appmobile.ui.shared.component

import android.annotation.SuppressLint
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.immobylette.appmobile.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.draw.clip
import com.immobylette.appmobile.ui.shared.theme.ImmobyletteappmobileTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuitAppPopup(
    onDismissRequest: () -> Unit,
    onCancelClicked: () -> Unit,
    onQuitClicked: () -> Unit,
    modifier : Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
    ) {
        Scaffold(
            modifier = modifier
                .height(190.dp)
                .width(600.dp)
                .clip(RoundedCornerShape(20.dp)),
            bottomBar = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Button(
                        text = stringResource(id = R.string.label_button_cancel),
                        onClick = onCancelClicked,
                        modifier = Modifier.width(150.dp)
                    )
                    Button(
                        isOnLeftSide = false,
                        text = stringResource(id = R.string.label_button_confirm),
                        modifier = Modifier.width(150.dp)
                    ) {
                        onQuitClicked()
                    }
                }
            }
        ){
            Column(
                modifier = Modifier.padding(30.dp)
            ){
                    Text(
                        text = stringResource(id = R.string.label_popup_quit_app),
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = stringResource(id = R.string.label_description_popup_quit_app),
                        style = MaterialTheme.typography.labelMedium
                    )

            }
        }
    }
}


@Composable
@Preview(showBackground = true, showSystemUi = true)
fun QuitAppPopupPreview(){
    ImmobyletteappmobileTheme {
        QuitAppPopup(
            onDismissRequest = {},
            onCancelClicked = {},
            onQuitClicked = {}
        )
    }
}