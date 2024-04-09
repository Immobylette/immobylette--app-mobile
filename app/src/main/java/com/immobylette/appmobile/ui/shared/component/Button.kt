package com.immobylette.appmobile.ui.shared.component

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.immobylette.appmobile.ui.shared.theme.ImmobyletteappmobileTheme
import com.immobylette.appmobile.ui.shared.theme.LocalBorderRadius
import com.immobylette.appmobile.ui.shared.theme.LocalFontSize
import com.immobylette.appmobile.ui.shared.theme.Pink


@Composable
fun AppButton(
    modifier: Modifier = Modifier,
    text: String = "Suivant",
    isOnLeftSide: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        shape =
            RoundedCornerShape(
                topEnd = if (isOnLeftSide) LocalBorderRadius.current else 0.dp,
                topStart = if (!isOnLeftSide) LocalBorderRadius.current else 0.dp
            ),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
        modifier = modifier,
    ) {
        Text(
            text = text,
            fontFamily = MaterialTheme.typography.headlineMedium.fontFamily,
            fontSize = LocalFontSize.current,
        )
    }
}

fun ExempleCallback(){
    println("Clic bouton Suivant")
}

@Preview(showBackground=true)
@Composable
fun AppButtonPreview(){
    ImmobyletteappmobileTheme(
        darkTheme = false,
        dynamicColor = false,
    ) {
        Surface() {
            AppButton(onClick = { ExempleCallback() }, isOnLeftSide = false)
        }
    }
}