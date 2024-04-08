package com.immobylette.appmobile.ui.shared.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.immobylette.appmobile.ui.shared.theme.ImmobyletteappmobileTheme

@Composable
fun Title(text: String) {
    Text(
        text,
        fontSize = 20.sp,
        fontWeight =
        FontWeight.SemiBold,
        color =
        MaterialTheme.colorScheme.primary
    )
}

@Preview(showBackground=true)
@Composable
fun TitlePreview(){
    ImmobyletteappmobileTheme {
        Surface(Modifier.fillMaxSize()) {
            Title("Sélectionner un agent")
        }

    }
}

