package com.immobylette.appmobile.ui.shared.component

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.immobylette.appmobile.ui.shared.theme.ImmobyletteappmobileTheme

@Composable
fun AppButton(
    textButton: String = "Suivant",
    IsOnLeftSide: Boolean = true,
    modifier: Modifier = Modifier,
    onClickCb: () -> Unit
) {
    Button(
        onClick = {
            onClickCb()
        },
        shape =
            if(IsOnLeftSide){
                RoundedCornerShape(
                    topEnd = 20.dp
                )
            }
            else{
                RoundedCornerShape(
                    topStart = 20.dp
                )
            },
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF4081)),
        modifier =
            if(modifier == Modifier){
                Modifier
                    .padding(16.dp)
                    .width(400.dp)
                    .height(56.dp)
            }
            else{
                modifier
            },
    ) {
        Text(
            text = textButton
        )
    }
}

fun exempleCallback(){
    println("Suivant")
}

@Preview(showBackground=true)
@Composable
fun AppButtonPreview(){
    ImmobyletteappmobileTheme(darkTheme = false, dynamicColor = false) {
        Surface() {
            AppButton(onClickCb = { exempleCallback() }, IsOnLeftSide = false)
        }
    }
}