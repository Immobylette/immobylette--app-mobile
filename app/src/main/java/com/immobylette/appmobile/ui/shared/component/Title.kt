package com.immobylette.appmobile.ui.shared.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.immobylette.appmobile.R
import com.immobylette.appmobile.ui.shared.theme.ImmobyletteappmobileTheme
import com.immobylette.appmobile.ui.shared.theme.PinkLight

enum class TitleType {
    H1,
    H2,
}


@Composable
fun Title(text: String, modifier: Modifier = Modifier, type: TitleType = TitleType.H1) {
    val fontSize = if (type == TitleType.H1) 30.sp else 22.sp
    val color = if (type == TitleType.H1) MaterialTheme.colorScheme.primary else PinkLight

    Text(
        text = text,
        modifier = modifier,
        fontSize = fontSize,
        fontWeight = FontWeight.SemiBold,
        color = color,
        fontFamily = MaterialTheme.typography.headlineMedium.fontFamily
    )
}

@Preview(showBackground=true)
@Composable
fun TitlePreview(){
    ImmobyletteappmobileTheme(darkTheme = false, dynamicColor = false) {
        Surface(Modifier.fillMaxSize()) {
            Column{
                Title(stringResource(id = R.string.label_select_agent))
                Spacer(modifier = Modifier.height(5.dp))
                Title(text = "Bonjour le monde", type = TitleType.H2)
            }
        }
    }
}

