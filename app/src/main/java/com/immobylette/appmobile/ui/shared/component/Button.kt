package com.immobylette.appmobile.ui.shared.component

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.immobylette.appmobile.ui.shared.theme.ImmobyletteappmobileTheme

val borderRadius = 20.dp
val appButtonWidth = 200.dp
val fontSize = 20.sp

@Composable
fun Button(
    modifier: Modifier = Modifier,
    text: String = "Suivant",
    isOnLeftSide: Boolean = true,
    hasTwoRoundedCorners: Boolean = false,
    style: TextStyle = MaterialTheme.typography.headlineLarge,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        shape =
            RoundedCornerShape(
                topEnd = if (isOnLeftSide) borderRadius else 0.dp,
                bottomStart = if (isOnLeftSide && hasTwoRoundedCorners) borderRadius else 0.dp,
                topStart = if (!isOnLeftSide) borderRadius else 0.dp,
                bottomEnd = if (!isOnLeftSide && hasTwoRoundedCorners) borderRadius else 0.dp
            ),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
        modifier = modifier
            .height(IntrinsicSize.Min),
    ) {
        Text(
            text = text,
            style = style,
            color = Color.White
        )
    }
}

@Preview(showBackground=true)
@Composable
fun AppButtonPreview(){
    ImmobyletteappmobileTheme(
        darkTheme = false,
        dynamicColor = false,
    ) {
        Surface() {
            Button(onClick = { println("Clic bouton Suivant") }, isOnLeftSide = false)
        }
    }
}