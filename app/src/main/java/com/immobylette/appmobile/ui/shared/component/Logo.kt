package com.immobylette.appmobile.ui.shared.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.immobylette.appmobile.R

@Composable
fun Logo(
    size: Int = 400,
    corner: Int = 60
) {
    Image(
        painter = painterResource(id = R.drawable.logo_immobylette),
        contentDescription = "Logo Immobylette",
        modifier = Modifier
            .padding(40.dp)
            .clip(RoundedCornerShape(corner.dp))
            .background(Color.Red)
            .width(size.dp)
            .height(size.dp)
    )
}

@Preview
@Composable
fun PreviewLogo() {
    Logo()
}