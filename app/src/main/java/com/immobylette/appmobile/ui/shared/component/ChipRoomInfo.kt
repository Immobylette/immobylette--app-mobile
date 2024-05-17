package com.immobylette.appmobile.ui.shared.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import com.immobylette.appmobile.R
import com.immobylette.appmobile.ui.shared.theme.Blue
import com.immobylette.appmobile.ui.shared.theme.ImmobyletteappmobileTheme
import com.immobylette.appmobile.ui.shared.theme.fredokaFontFamily

@Composable
fun ChipRoomInfo(
    nbChecked: Int,
    nbTotal: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(
                topEnd = 30.dp,
                topStart = 30.dp,
            ))
            .background(Blue)
            .padding(horizontal = 10.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.label_verified_rooms),
            fontFamily = fredokaFontFamily,
            color = Color.White
        )
        Spacer(modifier = Modifier.width(5.dp))
        Tip(
            text = "${nbChecked}/${nbTotal}",
            modifier = Modifier.width(("${nbChecked}/${nbTotal}".length * 15.dp) + 20.dp),
            fontFamily = fredokaFontFamily
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ChipRoomInfoPreview() {
    ImmobyletteappmobileTheme() {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            ChipRoomInfo(
                nbChecked = 1,
                nbTotal = 5
            )

        }
    }
}