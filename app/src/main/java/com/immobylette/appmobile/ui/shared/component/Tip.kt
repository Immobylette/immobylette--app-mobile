package com.immobylette.appmobile.ui.shared.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import com.immobylette.appmobile.ui.shared.theme.ImmobyletteappmobileTheme

@Composable
fun Tip(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.headlineLarge,
) {
    val padding: Dp
    val width: Dp

    if (style != MaterialTheme.typography.labelSmall){
        padding = 10.dp
        width = (text.length * 15.dp) + 10.dp
    }else {
        padding = 5.dp
        width = (text.length * 10.dp) + 5.dp
    }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(30.dp))
            .background(MaterialTheme.colorScheme.primary)
            .padding(padding)
            .width(width),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = style,
            color = Color.White
        )
    }
}


@Composable
@Preview(showBackground=true)
fun TipPreview(){
    ImmobyletteappmobileTheme {
        Row {
            Tip("1/4")
            Tip("1", style = MaterialTheme.typography.headlineSmall)
            Tip("20/20")
            Tip("1", style = MaterialTheme.typography.labelSmall)
            Tip("10", style = MaterialTheme.typography.labelSmall)
        }
    }
}