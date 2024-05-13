package com.immobylette.appmobile.ui.shared.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.immobylette.appmobile.ui.shared.theme.Black
import com.immobylette.appmobile.ui.shared.theme.ImmobyletteappmobileTheme

@Composable
fun Chip(
    number: Int,
    label: String
) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(Black)
            .padding(horizontal = 30.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Tip(text = number.toString())
        Spacer(modifier = Modifier.width(15.dp))
        Text(
            text = label,
            color = Color.White,
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )

    }
}

@Preview
@Composable
fun ChipPreview() {
    ImmobyletteappmobileTheme() {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Chip(
                number= 11,
                label = "Elements"
            )
            Spacer(modifier = Modifier.width(15.dp))
            Chip(
                number= 4,
                label = "Murs"
            )
            Spacer(modifier = Modifier.width(15.dp))
            Chip(
                number= 3,
                label = "Portes"
            )

        }
    }
}